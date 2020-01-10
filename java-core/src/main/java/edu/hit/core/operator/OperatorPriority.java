package edu.hit.core.operator;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * <p>OperatorPriority</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @date 2015年7月14日 - 下午5:07:29
 * @versino 1.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OperatorPriority {

    @Test
    public void test_01_for() {
        outer:
        for (int i = 0; i < 10; i++) {
            System.out.println("--------------> i " + i);
            for (int j = 0; j < 5; j++) {
                if (j == 2) break outer;
                System.out.println("----> j " + j);
            }
        }
    }

    @Test
    public void test_02_equals() {
        System.out.println(5 == 5);
        System.out.println(97 == 'a');
    }


}
