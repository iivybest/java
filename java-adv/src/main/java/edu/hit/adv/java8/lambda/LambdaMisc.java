package edu.hit.adv.java8.lambda;

import edu.hit.adv.java8.functionalinterface.CheckAble;
import edu.hit.adv.java8.functionalinterface.CreateAble;

/**
 * <p>LambdaMisc</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2016年7月5日-上午11:34:00
 */
public class LambdaMisc {
    public static void main(String[] args) {
        // CreateAble 普通实现
        CreateAble workshop = new CreateAble() {
            @Override
            public void create() {
                System.out.println("workshop implements createable's create method...");
            }
        };


        // lambda 表达式实现 CreatAble 接口写法
        CreateAble factory = () -> System.out.println("lambda expression implements createable's create method...");

        CheckAble qa = createable -> System.out.println("qa check -- " + ((CreateAble) createable).readme());

        workshop.create();
        factory.create();
        qa.check(workshop);
        qa.check(() -> System.out.println("create"));

        new Thread(() -> System.out.println("create")).start();


    }

}
	
	
	
	
	