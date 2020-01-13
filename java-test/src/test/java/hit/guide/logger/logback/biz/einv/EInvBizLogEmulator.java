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
 * <p> description: 电子发票业务日志记录模拟器
 * <br>--------------------------------------------------------
 * <br> 模拟电子发票开具日志记录情况
 * <br>--------------------------------------------------------
 * <br>Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @className EInvProcessor_1
 * @date 2019/9/6 13:45
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EInvBizLogEmulator {
    /* 使用指定名称的 logger */
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
    public void einv_01_verify() {
        String bizmoudle = "校验";
        LockSupport.parkUntil(System.currentTimeMillis() + takeuptime);
        log.info("{\"tid\": \"{}\", \"msg\": \"{}\", \"takeuptime\": \"{}\"}", uid, bizmoudle, takeuptime);
        log.debug("====>class:hashcode: {}", this.hashCode());
    }

    @Test
    public void einv_02_store() {
        String bizmoudle = "入库";
        LockSupport.parkUntil(System.currentTimeMillis() + takeuptime);
        log.info("{\"tid\": \"{}\", \"msg\": \"{}\", \"takeuptime\": \"{}\"}", uid, bizmoudle, takeuptime);
        log.debug("====>class:hashcode: {}", this.hashCode());
    }

    @Test
    public void einv_03_taxCode() {
        String bizmoudle = "赋码";
        LockSupport.parkUntil(System.currentTimeMillis() + takeuptime);
        log.info("{\"tid\": \"{}\", \"msg\": \"{}\", \"takeuptime\": \"{}\"}", uid, bizmoudle, takeuptime);
        log.debug("====>class:hashcode: {}", this.hashCode());
    }

}





