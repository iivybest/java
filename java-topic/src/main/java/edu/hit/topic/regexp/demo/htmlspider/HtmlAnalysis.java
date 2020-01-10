package edu.hit.topic.regexp.demo.htmlspider;

import org.ivy.util.common.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HtmlAnalysis {

    private String content;

    public HtmlAnalysis(String urlpath) {
        this.content = new String(FileUtil.read(urlpath));
    }

    public static void main(String[] args) {
        new HtmlAnalysis("D:/ImiaoDev/Test/QCon_Shanghai_2016_2.txt").launch();
    }


    public void launch() {
        String regex_item = "<div class=\"download\">(.|[ \f\r\t\n])*?</div>";
        List<String> items = this.regExp(this.content, regex_item);

        for (String item : items) {
            String[] result = this.process(item);
            for (int i = 0; i < result.length; i++) System.out.println(result[i]);
            System.out.println("------");
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
        while (m.find()) result.add(m.group());

        return result;
    }


}
