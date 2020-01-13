package edu.hit.adv.annotation.validator;

import edu.hit.adv.annotation.validator.exception.FieldValidateException;
import lombok.extern.slf4j.Slf4j;
import org.ivy.util.common.StringUtil;
import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * <p> description: student testcase one
 * <br>---------------------------------------------------------
 * <br>
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2016/2/18 09:36
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class StudentTestcase1 {
    private Student student;

    @Before
    public void setUp() {
        this.student = new Student();
        this.student.setName("Lavezzi");
        this.student.setAddr("Hebei");
        this.student.setGender(1);
    }

    @After
    public void tearDown() {
        log.debug("====split line------------------------");
    }

    @Test
    public void test_01() {
        boolean valid;
        try {
            valid = FieldAnnotationValidator.validate(this.student);
        } catch (FieldValidateException e) {
            log.error(StringUtil.getFullStackTrace(e));
            valid = false;
        }
        log.debug("{valid: {}}", valid);
    }

    @Ignore
    @Test(timeout = 1000, expected = Exception.class)
    public void test_02() throws Exception {
        log.debug("{method: {}}", "test_02");
        Assert.assertEquals("0", "0");
    }

}
