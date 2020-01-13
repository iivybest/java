package edu.hit.topic.regexp.demo.htmlspider;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.ivy.util.common.FileUtil;
import org.ivy.xutil.http.ApacheHttpClient;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
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

    private String url;

    private String content;
    /**
     * result store dictionary
     */
    private String dest;

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

    public BianWallpaperSiteAnalyser build(String dest, String ... url) {
        for (String e : url) {
            this.initialize(e, dest);
        }
        return this;
    }


    private BianWallpaperSiteAnalyser initialize(String url, String dest) {
        this.url = url;
        this.dest = dest;
        try {
            this.content = this.doGet(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        log.debug("{content: \n{}}", content);
        return content;
    }





    public static void main(String[] args) {
//        new BianWallpaperSiteAnalyser("D:/ImiaoDev/Test/QCon_Shanghai_2016_2.txt").launch();
        log.debug("============");
        BianWallpaperSiteAnalyser.getInstance().build("F:/Downloads/", "http://pic.netbian.com/4kmeinv/index.html");


    }

    public void build() {
        String regex_item = "<div class=\"download\">(.|[ \f\r\t\n])*?</div>";
        List<String> items = this.regExp(this.content, regex_item);

        for (String item : items) {
            String[] result = this.process(item);
            for (int i = 0; i < result.length; i++) {
                log.debug(result[i]);
            }
            log.debug("------");
        }
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

    public List<String> regExp(String original, String regex) {
        List<String> result = new ArrayList<String>();

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(original);
        while (m.find()) {
            result.add(m.group());
        }

        return result;
    }


}
