package edu.hit.topic.regexp.demo.htmlspider;

import org.ivy.util.common.FileUtil;
import org.ivy.xutil.http.HttpClient;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 微软壁纸下载
 *
 * @author ivybest ivybestdev@163.com
 */
public class MSWallpaperDownloadHandler {
    // 原始页面内容
    private String content;
    // 下载目录
    private String dest;
    // 下载日志
    private File downloadLog;
    // 已下载列表
    private List<String> downloadedList;

    /**
     * constructor
     *
     * @param urlpath
     * @param dest
     */
    public MSWallpaperDownloadHandler(String urlpath, String dest) {
        super();
        this.initialize(urlpath, dest);
    }

    /**
     * 初始化操作
     *
     * @param urlpath
     * @param dest
     */
    public void initialize(String urlpath, String dest) {
        // initialize args
        this.content = new String(FileUtil.read(urlpath));
        this.dest = dest;
        this.downloadLog = new File(this.dest + File.separator + "download.txt");
        if (!this.downloadLog.exists()) {
            FileUtil.createNewFile(downloadLog);
        }

        // initialize
        this.downloadedList = new ArrayList<String>();
        String downlistfilecon = new String(FileUtil.read(this.downloadLog));
        String[] items = downlistfilecon.trim().split("\n");
        for (String item : items) {
            this.downloadedList.add(item);
        }
    }


    public static void main(String[] args) {
        String url = "F:/MyFiles/Audio/Storytelling/明朝那些事儿/明朝那些事儿11（朱常洛篇）";
        File[] files = FileUtil.getAllNonDirFileList(url);
        StringBuilder sb = new StringBuilder();
        for (File file : files) {
            sb.append(file.getName()).append("\n");
        }
        String st = null;
        if (sb.length() > 0) {
            st = sb.substring(0, sb.length() - 1);
        }
        System.out.println(st);
        FileUtil.writer(url + "/down.txt", "\n" + st, true);
        FileUtil.writer(url + "/down.txt", "\n" + "conima", true);
        FileUtil.writer(url + "/down.txt", "\n" + "conima", true);


//		new MSWallpaperDownloadHandler("E:/wp.xml", "F:/test").launch();
    }


    public void launch() {
        // 匹配td标签
        String regex_item = "<td.*?>(.*?)</td>";
        List<String> items = this.regExp(this.content, regex_item);

//		for(String item : items) System.out.println(item);
        for (String item : items) {
            if (this.downloadedList == null || !this.downloadedList.contains(item)) {
                this.download(this.dest, this.process(item));
            }
        }

    }

    private String[] process(String original) {
        String[] result = new String[2];

        String regex_title = "<p><b>(.*?)</b></p>";
        String regex_url = "<p><a(.*?)>(.*?)</a></p>";

        List<String> titles = this.regExp(original, regex_title);
        List<String> urls = this.regExp(original, regex_url);

        result[0] = titles.size() > 0 ? titles.get(0).replaceAll("<[\\s|\\S]*?>", "") : "";
        result[1] = urls.size() > 0 ? this.getHref(urls.get(0)).get(0).replace("https", "http") : "";

        return result;
    }

    public List<String> regExp(String original, String regex) {
        List<String> result = new ArrayList<String>();

        Matcher m = Pattern.compile(regex).matcher(original);

        while (m.find()) {
            result.add(m.group());
        }

        return result;
    }


    public List<String> getHref(String original) {
        String regex_href_tag = "<a(.*?)>";
        List<String> tags = this.regExp(original, regex_href_tag);
        for (int i = 0; i < tags.size(); i++) {
            String tag = tags.get(i);
            String url = tag.substring(tag.indexOf('"') + 1, tag.lastIndexOf('"'));
            tags.set(i, url);
        }
        return tags;
    }


    public void download(String location, String[] args) {
        if (args == null || args[1].length() <= 0) {
            return;
        }

        FileUtil.checkDir(location);
        File file = new File(location + "/" + args[0] + ".jpg");
        if (!file.exists()) {
            FileUtil.write(file, HttpClient.service(args[1], "GET", null));
        }
        // 将下载的文件记录到日志中
        FileUtil.writer(this.downloadLog, "\n" + file.getName());
        System.out.println("----> " + file.getName() + " download successed...");
    }

}








