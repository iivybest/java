/**
 * @Package edu.hit.guide.java.adv.thread.concurrent.collection
 * @author ivybest ivybestdev@163.com
 * @date 2016年3月29日-上午9:16:48
 */
package edu.hit.adv.thread.concurrent.collection;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>SynchronizedMapTest</p>
 *
 *
 * @author ivybest ivybestdev@163.com
 * @date 2016年3月29日-上午9:16:48
 * @version 1.0
 *
 */
public class SynchronizedMapTest {
    public void launch() {
        Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());
        Map<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        Map<String, String> skipListMap = new ConcurrentSkipListMap<>();
        Collection<String> c = new CopyOnWriteArrayList<>();
    }
}
