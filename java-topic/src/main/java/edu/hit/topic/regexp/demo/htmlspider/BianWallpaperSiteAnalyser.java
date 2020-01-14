package edu.hit.topic.regexp.demo.htmlspider;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.ivy.util.common.FileUtil;
import org.ivy.util.common.SystemUtil;
import org.ivy.xutil.http.ApacheHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p> description: BianWallpaperSiteAnalyser
 * <br>---------------------------------------------------------
 * <br> site: http://pic.netbian.com/
 * <br> auto download picture form this site
 * <br>
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author ivybest (ivybestdev@163.com)
 * @version 1.0
 * @date 2020/1/11 18:42
 */
@Slf4j
public class BianWallpaperSiteAnalyser {
    private static Logger bizLog = LoggerFactory.getLogger("spider.bian");

    private static final String URL_BASE = "http://pic.netbian.com";

    private String url;

    private String content;
    /**
     * result store dictionary
     */
    private String dest;

    private BianWallpaperSiteAnalyser() {
    }

    private BianWallpaperSiteAnalyser(String url) {
        this.content = new String(FileUtil.read(url));
    }

    /**
     * get BianWallpaperSiteAnalyser instance
     *
     * @return BianWallpaperSiteAnalyser
     */
    public static BianWallpaperSiteAnalyser getInstance() {
        return SingletonInsHolder.HOLDER.instance;
    }

    public static void main(String[] args) throws IOException {
//        new BianWallpaperSiteAnalyser("D:/ImiaoDev/Test/QCon_Shanghai_2016_2.txt").launch();

        String classpath = SystemUtil.getClasspath();

        String page = "index.html";
        String topicUrl = URL_BASE + "/4kzongjiao/" + page;

        // step-1 get all topic
        // step-2 get max page No of every topic
        // step-3 get all page url of every topic
        // setp-4 get all download url of every topic

        BianWallpaperSiteAnalyser.getInstance()
                .build("classpath/bian/", topicUrl)
                .launch();


    }

    public BianWallpaperSiteAnalyser build(String dest, String... url) {
        for (String e : url) {
            this.initialize(e, dest);
        }
        return this;
    }


    private BianWallpaperSiteAnalyser initialize(String url, String dest) {
        this.url = url;
        this.dest = dest;
        return this;
    }

    private String doGet(String url) throws IOException {
        HttpGet get = new HttpGet(url);
        HttpResponse response = ApacheHttpClient.getInstance().doGet(get);
        String content = EntityUtils.toString(response.getEntity(), Charset.forName("GBK"));
        for (Header header : response.getAllHeaders()) {
            log.debug("{header: {}, value: {}}", header.getName(), header.getValue());
        }
        int statusCode = response.getStatusLine().getStatusCode();
        log.debug("{statusCode: {}", statusCode);
//        log.debug("{content: \n{}}", content);
        return content;
    }

    private BianWallpaperSiteAnalyser launch() throws IOException {
        String content = this.doGet(url);
        // ---step-1: get all topic
        List<String> stepOneResult =  step_1_getTopic(content);
        stepOneResult.stream().forEach(bizLog::debug);

        // ---step-2 get all download url
        List<String> stepTwoResult;
        for (String e : stepOneResult) {
            stepTwoResult = step_2_getAllTopicPages(e);
//            break;
        }

        return this;
    }

    public List<String> step_1_getTopic(String data) throws IOException {
        List<String> result = new ArrayList<>();


        // ----step-1: 获取 <div> block ,
        String regex = "<div class=\"classify clearfix\">(.|[ \f\r\t\n])*?</div>";
        List<String> items = this.regExp(data, regex);
        if (items == null || items.size() == 0) {
            throw new IOException("====> step-1: regex-[" + regex + "] not matched");
        }
        String content = items.get(0);
        log.debug("{\nblock: {}\n}", content);

        // ----step-2: 解析 <div> 获取所有 topic 便签
//        regex = "href=\"/([(0-9)|(a-z)|(A-Z)]+)/\" title=\"[(\\S)&&[^\"]]+\"";
        regex = "href=\"/([(0-9)|(a-z)|(A-Z)]+)/\"";
        items = this.regExp(content, regex);
        if (items == null || items.size() == 0) {
            throw new IOException("====> step-1: regex-[" + regex + "] not matched");
        }
        for (String item : items) {
//            log.debug("{title: {}}", item.split("\"")[1]);
            result.add(item.split("\"")[1]);
        }

        // ----step-3: 解析 <div> 获取所有 topic alt（别名）
        regex = "title=\"[(\\S)&&[^\"]]+\"";
        items = this.regExp(content, regex);
        if (items == null || items.size() == 0) {
            throw new IOException("====> step-1: regex-[" + regex + "] not matched");
        }

        for (int i = 0, size = items.size(); i < size; i++) {
//            log.debug("{alt: {}}", items.get(i).split("\"")[1]);
            result.set(i, result.get(i) + "_" + items.get(i).split("\"")[1]);
        }

        // ----step-4: 获取每个主题的最大页码
        String page = "index.html";
        String topicUrl;
        for (int i = 0, size = result.size(); i < size; i++) {
            topicUrl = URL_BASE + result.get(i).split("_")[0] + page;
            log.debug("{url: {}}", topicUrl);
            content = this.doGet(topicUrl);
            regex = "<div class=\"page\">(.|[ \f\r\t\n])*?</div>";
            items = this.regExp(content, regex);
            if (items == null || items.size() == 0) {
                throw new IOException("====> step-1: regex-[" + regex + "] not matched");
            }
            content = items.get(0);
            log.debug("{block: {}}", content);
            Integer[] pages = this.regExptoIntArr(content, "\\d+");
            Arrays.sort(pages);
            result.set(i, result.get(i) + "_" + pages[pages.length - 1]);
        }

        return result;

//        for (String item : items) {
//            String[] result = this.process(item);
//            for (int i = 0; i < result.length; i++) {
//                log.debug(result[i]);
//            }
//            log.debug("------");
//        }
    }

    private List<String> step_2_getAllTopicPages (String topic) {
        String[] items = topic.split("_");
        String topicName = items[0];
        String topicAlt = items[1];
        int topicMaxPage = Integer.parseInt(items[2]);
        bizLog.debug("{topic: {}, title: {}}", topicName, topicAlt);

        List<String> result = new ArrayList<>();
        String urlBase = URL_BASE + topicName ;
        String urlTopic;
        for (int i = 1; i <= topicMaxPage; i++) {
            if (1 == i) {
                urlTopic = urlBase + "index.html";
            }
            urlTopic = urlBase + "index_" + i + ".html";
//            bizLog.debug("{url: {}}", urlTopic);
            result.add(urlTopic);
        }
        return result;
    }

    private List<String> step_3_getAllDownloadUrl(String topic, List<String> url) throws IOException{
        List<String> result = new ArrayList<>();

        String content;
        for(String e : url) {
            content = this.doGet(e);
        }


        return result;
    }




    private String[] process(String original) {
        String[] result = new String[3];
        String regex_title = "<a\\s{1}.*\\s{1}class=\"link\">(.|[ \\s])*?</a>";
        String regex_url = "<a\\s{1}.*\\s{1}target=\"_blank\">";
        String regex_author = "<span class=\"date\">.*?</span>";

        result[0] = this.regExp(original, regex_title).get(0).replaceAll("<[\\s|\\S]*?>", "");
        result[1] = "http://ppt.geekbang.org" + this.regExp(original, regex_url).get(0).replaceAll("(<a href=\")|(\" target=\"_blank\">)", "");
        result[2] = this.regExp(original, regex_author).get(0).replaceAll("<[\\s|\\S]*?>", "").replace(" &nbsp;", "_");
        return result;
    }

    public List<String> regExp(String data, String regex) {
        List<String> result = new ArrayList<String>();

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(data);
        while (m.find()) {
            result.add(m.group());
        }

        return result;
    }

    public Integer [] regExptoIntArr(String data, String regex) {
        List<Integer> result = new ArrayList<>();

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(data);
        while (m.find()) {
            result.add(Integer.parseInt(m.group()));
        }
        Integer[] arr = new Integer[result.size()];
        result.toArray(arr);
        return arr;
    }


    /**
     * singleton instance holder
     */
    private enum SingletonInsHolder {
        /**
         * sing
         */
        HOLDER;

        private BianWallpaperSiteAnalyser instance;

        SingletonInsHolder() {
            this.instance = new BianWallpaperSiteAnalyser();
        }

    }


}
