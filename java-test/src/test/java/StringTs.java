import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by eblly on 2017/5/11.
 */
public class StringTs {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private int count = 10;
    private int itemCount = 10_000_000;

    @Test
    public void testPerformance() {
        for (int i = 0; i < this.count; i++) this.performance(this.itemCount);
    }


    public void performance(int count) {
        String joint = "123abc";

        long startLong = System.currentTimeMillis();
//		String str = new String();
//		for (int i = 0; i < count; i++) str = str + joint;
//		logger.info("String joint count-[{}], takeup time-[{}]", count, System.currentTimeMillis() - startLong);
//		// ==================================================================
//		startLong = System.currentTimeMillis();
//		String str2 = new String();
//		for (int i = 0; i < count; i++) {
//			StringBuilder stringBuilder = new StringBuilder();
//			stringBuilder.append(str2).append(joint);
//			str2 = stringBuilder.toString();
//		}
//		logger.info("StringBuilder simulate String joint count-[{}], takeup time-[{}]", count, System.currentTimeMillis() - startLong);
//
//		// ==================================================================
//		startLong = System.currentTimeMillis();
        StringBuffer strBuff = new StringBuffer();
        for (int i = 0; i < count; i++) {
            strBuff.append("4444");
        }
        logger.info("StringBuffer joint count-[{}], takeup time-[{}]", count, System.currentTimeMillis() - startLong);
        // ==================================================================
        startLong = System.currentTimeMillis();
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            strBuilder.append("5555");
        }
        logger.info("StringBuilder joint count-[{}], takeup time-[{}]", count, System.currentTimeMillis() - startLong);
        logger.info("--------------------------------------------");
    }

}









