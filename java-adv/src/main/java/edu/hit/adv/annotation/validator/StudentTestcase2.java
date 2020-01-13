package edu.hit.adv.annotation.validator;

import edu.hit.adv.annotation.validator.exception.FieldValidateException;
import org.junit.*;

/**
 * <p>Test</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2016年2月18日-上午9:36:03
 */
public class StudentTestcase2 {
    private Student student;

    @Before
    public void before() {
        this.student = new Student();
        this.student.setName("Edu");
        this.student.setAddr("Hebei");
        this.student.setGender(1);
        System.out.println("====================");
    }

    @After
    public void after() {
        System.out.println("-----------------");
    }

    @Test
    public void test() {
        boolean valid = false;
        try {
            valid = FieldAnnotationValidator.validate(this.student);
        } catch (FieldValidateException e) {
            e.printStackTrace();
            valid = false;
        }
        System.out.println("===============validate result " + valid);
    }

    @Ignore
    @Test(timeout = 1000,
            expected = Exception.class
    )
    public void test2() throws Exception {
        System.out.println("test2");
        Assert.assertEquals("0", "0");
    }

}
