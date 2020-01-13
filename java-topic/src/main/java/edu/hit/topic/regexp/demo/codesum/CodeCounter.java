package edu.hit.topic.regexp.demo.codesum;

import org.ivy.util.common.FileUtil;

import java.io.File;
import java.util.List;


/**
 * <p>CodeCounter</p>
 * <p>Description:</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2014年12月22日 下午3:52:14
 */
public class CodeCounter {
    private String fileUrl;
    private List<String> types;

    public CodeCounter() {
        super();
    }

    public CodeCounter(String fileUrl, String type) {
        this();
    }

    public void initialize() {

    }

    public Object launch() {
        return this.counter();
    }

    /**
     * 计算代码行数
     *
     * @return
     * @author ivybest ivybestdev@163.com
     * @date 2014年12月22日 下午3:58:45
     * @version 1.0
     */
    private CodeSum counter() {
        CodeSum sc = new CodeSum();
        File[] files = FileUtil.getFileList(fileUrl);
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isFile()) {

            } else {
            }
        }


        //TODO
        return sc;
    }

    private File[] getFiles(File file) {
        return FileUtil.getFileList(file);
    }

    /**
     * @param fileUrl the fileUrl to set
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * @param types the types to set
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

    public static void main(String[] args) {
        CodeSum cs = new CodeSum();
        cs.setCodeCount("110");
        cs.setCommentsCount("20");
        System.out.println(cs.toString());
    }

}
