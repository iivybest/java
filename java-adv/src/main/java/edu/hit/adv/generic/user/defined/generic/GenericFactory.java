package edu.hit.adv.generic.user.defined.generic;

import java.util.Date;

/**
 * <p>GenericFactory</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年4月24日 - 上午10:15:47
 */
public class GenericFactory<T> {
    private T secrecy;

    public T getSecrecy() {
        return secrecy;
    }

    public void setSecrecy(T secrecy) {
        this.secrecy = secrecy;
    }

    public static void main(String[] args) {
        GenericFactory<String> gf = new GenericFactory<String>();
        gf.setSecrecy("Scholes");
        String info = gf.getSecrecy();
        System.out.println(info);

        System.out.println("==========================");

        GenericFactory<Date> gf2 = new GenericFactory<Date>();
        gf2.setSecrecy(new Date());
        Date d = gf2.getSecrecy();
        System.out.println(d);
    }
}
