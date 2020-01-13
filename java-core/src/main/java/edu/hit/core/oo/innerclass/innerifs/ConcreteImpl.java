package edu.hit.core.oo.innerclass.innerifs;

/**
 * <p>ConcreteIml1</p>
 * <p>Description : </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2015年8月11日 - 上午8:32:57
 */
public class ConcreteImpl implements InnerIfs {

    private static class Struture implements InnerIfs.Structure {
        @Override
        public String getStructureArg() {
            return "InnerIfs 特有数据结构 - 参数为: Adidas";
        }
    }

    @Override
    public Structure launch() {
        return new Struture();
    }


}
