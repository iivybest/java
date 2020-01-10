package edu.hit.adv.classloader;


/**
 * ClassLoaderView
 * 类加载器初窥
 *
 * @author Ares miao.xl@live.cn
 * @version V1.0
 * @date 2017年3月28日 上午11:22:11
 */
public class ClassLoaderView {

    public static void main(String[] args) {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        System.out.println(loader);
        while (null != loader.getParent()) {
            loader = loader.getParent();
            System.out.println(loader);
        }
    }

}
