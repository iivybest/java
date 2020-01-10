package edu.hit.core.oo.innerclass.innerifs;


/**
 * <p>Client</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年8月11日 - 上午8:34:05
 */
public class Client {
    public static void main(String[] args) {
        InnerIfs ifs = new ConcreteImpl();
        InnerIfs.Structure s = ifs.launch();
        System.out.println(s.getStructureArg());
    }
}
