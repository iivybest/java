package edu.hit.core.collections.map;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: FuzzyLookupInMap
 * @author: ivybest imiaodev@163.com
 * @date: 2019年7月23日 上午9:24:29
 * : TODO(用一句话描述该文件做什么)
 */

public class FuzzyLookupInMap {
    String[][] origin;

    Map<String, String> map;

    @Before
    public void setUp() {
        this.origin = new String[][]{
                {"abc.news.sport.football", "abc.football"},
                {"abc.news.sport.swimming ", "abc.swimming"},
                {"abc.news.sport.diving", "abc.diving"},
                {"abc.news.entertainment.dance", "abc.dance"},
                {"abc.news.entertainment.sing", "abc.sing"},
                {"abc.news.entertainment.model", "abc.model"},
                {"wiz.news.sport.football", "wiz.football"},
                {"wiz.news.sport.swimming ", "wiz.swimming"},
                {"wiz.news.sport.diving", "wiz.diving"},
                {"wiz.news.entertainment.dance", "wiz.dance"},
                {"wiz.news.entertainment.sing", "wiz.sing"},
                {"wiz.news.entertainment.model", "wiz.model"}
        };
        this.map = new HashMap<String, String>();
        for (String[] e : origin) this.map.put(e[0], e[1]);
    }

    @Test
    public void test_01() {
        String key = "wiz.news.entertainment.model";
        this.getvalue(key);
        key = "abc.#";
        this.getvalue(key);
        key = "#";
        this.getvalue(key);
        key = ".#.";
        this.getvalue(key);
        key = "abc.#.football";
        this.getvalue(key);
        key = "abc.#.football.";
        this.getvalue(key);
        key = "#.sport.#";
        this.getvalue(key);
        key = "#.news.#.football";
        this.getvalue(key);
        key = "#.football";
        this.getvalue(key);

    }


    @Test
    public void test_02() {
        String key = "wiz.news.entertainment.sing";
        this.printLookup(key);
        key = "abc.#";
        this.printLookup(key);
        key = "#";
        this.printLookup(key);
        key = ".#.";
        this.printLookup(key);
        key = "abc.#.football";
        this.printLookup(key);
        key = "abc.#.football.";
        this.printLookup(key);
        key = "#.sport.#";
        this.printLookup(key);
        key = "#.news.#.football";
        this.printLookup(key);
        key = "#.football";
        this.printLookup(key);

    }


    private void getvalue(String key) {
        key = this.formatKey(key);
        System.out.println("---------------------------------" + key);
        if (!key.contains("#")) {
            System.out.println("---->精确匹配");
            System.out.println("====>" + key + ": " + this.map.get(key) + "\n");
            return;
        }

        Set<String> keys = this.map.keySet();
        String[] subkeys = key.split("#");
        int len = subkeys.length;
        int first = 0;
        int end = len - 1;

        if (len == 0) {
            for (String e : keys) System.out.println("====>" + e + ": " + this.map.get(e));
            return;
        }


        for (String e : keys) {
            String subkey;
            boolean valid = true;
            for (int i = 0; i < len; i++) {
                if (!valid) break;
                subkey = subkeys[i];
                if (i == first) {
                    // 开头匹配
                    valid = "".equals(subkey) || e.startsWith(subkey);
                } else if (i == end) {
                    // 结尾匹配
                    if (subkey.endsWith(".")) valid = e.contains(subkey);
                    else valid = e.endsWith(subkey);
                } else {
                    // 中间匹配
                    valid = e.contains(subkey);
                }
            }
            if (valid) System.out.println("====>" + e + ": " + this.map.get(e));
        }


//		for (String e : subkeys) System.out.println("====>" + e);
        System.out.println();
    }

    private String formatKey(String key) {
        if (key.startsWith(".")) key = key.substring(1);
        if (key.endsWith(".")) key = key.substring(0, key.length() - 1);
        return key;
    }

    private void printLookup(String key) {
        System.out.println("---------------------------------" + key);
        Map<String, String> map = this.lookup(key);
        for (Map.Entry<String, String> e : map.entrySet())
            System.out.println("---->" + e.getKey() + ": " + e.getValue());
        System.out.println();
    }


    private Map<String, String> lookup(String key) {
        // ----非空校验
        if (null == key || key.trim().length() == 0) return null;
        // ----key格式化
        key = this.formatKey(key);
        // ----符合要求配置
        Map<String, String> matchedItems = new HashMap<>();

        // case-1--精确匹配-------------------------
        if (!key.contains("#")) {
            if (map.containsKey(key)) matchedItems.put(key, this.map.get(key));
            return matchedItems;
        }

        // case-2--全量匹配-------------------------
        if ("#".equals(key)) {
            matchedItems.putAll(this.map);
            return matchedItems;
        }

        // case-3--条件匹配-------------------------
        Set<String> keys = this.map.keySet();
        String[] subkeys = key.split("#");
        int len = subkeys.length;
        int first = 0;
        int end = len - 1;

        for (String e : keys) {
            boolean valid = true;
            String subkey;
            for (int i = 0; i < len; i++) {
                if (!valid) break;
                subkey = subkeys[i];
                if (i == first) {
                    // 开头匹配
                    valid = "".equals(subkey) || e.startsWith(subkey);
                } else if (i == end) {
                    // 结尾匹配
                    if (subkey.endsWith(".")) valid = e.contains(subkey);
                    else valid = e.endsWith(subkey);
                } else {
                    // 中间匹配
                    valid = e.contains(subkey);
                }
            }
            if (valid) matchedItems.put(e, this.map.get(e));
        }
        return matchedItems;
    }

}

















