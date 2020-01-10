package hit.guide.logger.logback.biz.einv;

import org.ivy.util.common.UniSeqUtil;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.locks.LockSupport;

/**
 * <p> description:
 * <br>--------------------------------------------------------
 * <br> 电票版式生成业务日志模拟
 * <br>--------------------------------------------------------
 * <br>Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author Ivybest (ivybestdev@163.com)
 * @version 1.0
 * @className EInvGenBizLogEmulator
 * @date 2019/9/6 13:45
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EInvGenBizLogEmulator {
    private static Logger log = LoggerFactory.getLogger("biz.einv");

    private final String uid = UniSeqUtil.generateUniSeq("EINV");
    private Random random = new Random();
    private int takeuptime;

    @Before
    public void setUp() {
        log.debug("====>{logger: {}, {}}", log.hashCode(), log.toString());
        this.takeuptime = 10 + this.random.nextInt(90);
    }

    @Test
    public void einv_04_genFix() {
        String bizmoudle = "版式生成";
        LockSupport.parkUntil(System.currentTimeMillis() + takeuptime);
        this.log.info("{\"tid\": \"{}\", \"msg\": \"{}\", \"takeuptime\": \"{}\"}", uid, bizmoudle, takeuptime);
    }

    @Test
    public void einv_05_eSealSign() {
        String bizmoudle = "签章";
        LockSupport.parkUntil(System.currentTimeMillis() + takeuptime);
        this.log.info("{\"tid\": \"{}\", \"msg\": \"{}\", \"takeuptime\": \"{}\"}", uid, bizmoudle, takeuptime);
    }

    @Test
    public void einv_06_delivery() {
        String bizmoudle = "交付";
        LockSupport.parkUntil(System.currentTimeMillis() + takeuptime);
        this.log.info("{\"tid\": \"{}\", \"msg\": \"{}\", \"takeuptime\": \"{}\"}", uid, bizmoudle, takeuptime);
    }

}





