package edu.hit.topic.regexp.demo.codesum;

/**
 * <p>CodeSum</p>
 * <p>Description:</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2014年12月22日 下午4:00:55
 */
public class CodeSum {
    private String fileCount;
    private String codeCount;
    private String commentsCount;


    /**
     * @return the fileCount
     */
    public String getFileCount() {
        return fileCount;
    }

    /**
     * @param fileCount the fileCount to set
     */
    public void setFileCount(String fileCount) {
        this.fileCount = fileCount;
    }

    /**
     * @return the codeCount
     */
    public String getCodeCount() {
        return codeCount;
    }

    /**
     * @param codeCount the codeCount to set
     */
    public void setCodeCount(String codeCount) {
        this.codeCount = codeCount;
    }

    /**
     * @return the commentsCount
     */
    public String getCommentsCount() {
        return commentsCount;
    }

    /**
     * @param commentsCount the commentsCount to set
     */
    public void setCommentsCount(String commentsCount) {
        this.commentsCount = commentsCount;
    }

    @Override
    public String toString() {
        return BeanGeneralToStringMethod.beanToString(this);
    }

}
