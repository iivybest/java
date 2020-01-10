package edu.hit.core.oo.innerclass.innerifs;

/**
 * <p>InnerIfs</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年8月11日 - 上午8:33:19
 */
public interface InnerIfs {

    InnerIfs.Structure launch();

    /**
     * <p>Structure</p>
     * <p>Description : 内部接口是外部接口特有的一个数据结构
     * 所以定义成内部接口
     * </p>
     *
     * @param <T>
     * @author miao.xl
     * @version 1.0
     * @date 2015年8月11日 - 上午9:07:44
     */
    interface Structure {
        String getStructureArg();
    }
}
