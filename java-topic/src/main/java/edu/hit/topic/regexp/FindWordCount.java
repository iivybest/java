/**
 * Filename 	FindWordCount
 * TODO
 *
 * @author ivybest
 * @version V1.0
 * CreateDate 	2017年8月9日 上午9:50:52
 * Company 		IB.
 * Copyright 	Copyright(C) 2010-
 * All rights Reserved, Designed By ivybest
 * <p>
 * Modification History:
 * Date			Author		Version		Discription
 * --------------------------------------------------------
 * 2017年8月9日		ivybest		1.0			new create
 */
package edu.hit.topic.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname FindWordCount
 *  TODO(这里用一句话描述这个类的作用)
 * @author
 * Createdate    2017年8月9日 上午9:50:52
 */
public class FindWordCount {
    /**
     *  		main
     *  查找以以y,z结尾的单词个数，不区分大小写。
     * @param        args
     * @return void
     */
    public static void main(String[] args) {
        String origin = "yellow tevez zero bily ASHILY";
        String regexp = "([a-z]+)[y-z]\\b";

        Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(origin);

        while (matcher.find()) System.out.println(matcher.group());
    }
}





