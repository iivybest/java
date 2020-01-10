/**
 * @Filename RoncooFileHandler
 * @author Ivybest
 * @version V1.0
 * @date 2018年4月20日 上午10:48:02
 * @Company IB.
 * @Copyright Copyright(C) 2010-
 * All rights Reserved, Designed By Ivybest
 * <p>
 * Modification History:
 * Date				Author		Version		Discription
 * --------------------------------------------------------
 * 2018年4月20日	Ivybest			1.0			new create
 */
package hit.tool;

import org.ivy.util.common.FileUtil;

import java.io.File;

/**
 * @Classname RoncooFileHandler
 * @author Ivybest imiaodev@163.com
 * @date 2018年4月20日 上午10:48:02
 * @Version 1.0
 * ------------------------------------------
 *  TODO(这里用一句话描述这个类的作用)
 */
public class RoncooFileHandler {

    private String path;

    public RoncooFileHandler initialize() {
        this.path = "D:/BaiduNetdiskDownload/亿级流量电商详情页系统的大型高并发与高可用缓存架构实战-中华石杉/资料";
        return this;
    }

    public void processCatalog(File file) throws Exception {
        File[] catalogs = FileUtil.getDirFileList(file);
        if (catalogs.length > 0) {
            for (File c : catalogs) {
                if (c.getName().endsWith("资料")) {
                    File[] subFiles = FileUtil.getFileList(c);
                    for (File sf : subFiles) FileUtil.copy(sf, file.getAbsolutePath());
                    FileUtil.delete(c);
                }
            }
        }

        File[] files = FileUtil.getNonDirFileList(file);
        String dirname = FileUtil.getUnixStyleFilePath(file);
        String name = file.getName();
        String type = null;
        for (File f : files) {
            type = FileUtil.getFileType(f);
            if (type.equals("ppt") || type.equals("txt") || type.equals("pptx") || type.equals("png")) {
                System.out.println(dirname + name + "." + type);
                f.renameTo(new File(dirname + name + "." + type));
            }
        }

        File[] sfs = FileUtil.getFileList(file);
        if (sfs == null || sfs.length == 0) FileUtil.delete(file);

    }


    public RoncooFileHandler launch() throws Exception {
        File[] catalogs = FileUtil.getDirFileList(this.path);

        for (File file : catalogs) {
            System.out.println(file.getName());
            this.processCatalog(file);
        }
        return this;
    }

    public static void main(String[] args) throws Exception {
        new RoncooFileHandler().initialize().launch();
    }
}
