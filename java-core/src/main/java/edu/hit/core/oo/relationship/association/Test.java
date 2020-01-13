package edu.hit.core.oo.relationship.association;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Test</p>
 * <p>Description : </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2015年7月27日 - 下午3:45:26
 */
public class Test {

    public static void main(String[] args) {
        CatalogueEntry screw = new CatalogueEntry("screw", 28834, 0.02);
        Part screw1 = new Part(screw);
        screw1.cost();

        List<String> list = new ArrayList<String>();
        list.add("yyyy");
//		Map<String, List<String>> map = new HashMap<String, List<String>>();
        Map<String, List<String>> map = newHashMap();
        map.put("1", list);
        System.out.println(map.get("1"));
    }


    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<K, V>();
    }
}
