package edu.hit.adv.nio.file;

import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.nio.file.StandardWatchEventKinds.*;

public class MonitorDir {

    Map<WatchKey, Path> keys = new ConcurrentHashMap<WatchKey, Path>();

    private static WatchService watcher = null;

    static {
        try {
            watcher = FileSystems.getDefault().newWatchService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void register(Path dir) throws IOException { // IOException ,InterruptedException{
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

        Path existing = keys.get(key);
        if (existing == null) {
            System.out.format("register: %s \n", dir);
        } else if (!dir.equals(existing)) {
            System.out.format("update register: %s -> %s \n", existing, dir);
        }

        keys.put(key, dir);
    }

    @SuppressWarnings("unchecked")
    private static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>) event;
    }

    public void monitor(Path dir) throws IOException, InterruptedException {
        register(dir);

        WatchKey key = null;
        for (; ; ) {
            // 等待监视事件发生
            key = watcher.take();

//			System.out.println("====> " + key);
            Path path = keys.get(key);

            if (path == null) return;

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                // 事件可能lost or discarded
                if (kind == OVERFLOW) {
                    System.out.println("====> " + kind);
                    continue;
                }

                // 目录监视事件的上下文是文件名
                WatchEvent<Path> evt = cast(event);
                Path name = evt.context();
                Path child = path.resolve(name);
                System.out.format(new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())
                                + "-%s-%s-%s\n",
                        evt.kind().name(),
                        evt.count(),
                        child);
            }

            // 重置 key
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);
                if (keys.isEmpty()) {
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        MonitorDir monitor = new MonitorDir();

//		Path dir = Paths.get("F:/download/4k/");
//		Path dir = Paths.get("E:/Ivybest/test/cache/");
        Path dir = Paths.get("D:/ImiaoDev/MyFiles/temp/");
        try {
            monitor.monitor(dir);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
