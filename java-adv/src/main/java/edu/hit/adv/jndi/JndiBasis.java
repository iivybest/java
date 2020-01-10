package edu.hit.adv.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Basis
 *
 * @author miao.xl
 * @version 1.0
 * @date 2014年11月27日 - 下午6:03:50
 */
public class JndiBasis {
    public static void main(String[] args) {
        String initial_contex_factory = Context.INITIAL_CONTEXT_FACTORY;
        String provider_url = Context.PROVIDER_URL;
        System.out.println("initial_contex_factory - " + initial_contex_factory);
        System.out.println("provider_url - " + provider_url);

        try {
            Context ctx = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }
}
