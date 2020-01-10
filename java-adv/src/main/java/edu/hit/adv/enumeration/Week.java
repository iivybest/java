package edu.hit.adv.enumeration;

/**
 * <p>Week</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @date 2015年5月22日 - 下午4:44:19
 * @versino 1.0
 */
public enum Week {
    SUN, MON, TUE, WED, THI, FRI, SAT;

    public void mood() {
        String info = "";
        int ordinal = this.ordinal();
        switch (ordinal) {
            case 0:
                info = "hello Sunday";
                break;
            case 1:
                info = "hello Monday";
                break;
            case 2:
                info = "hello Tuesday";
                break;
            case 3:
                info = "hello Wednesday";
                break;
            case 4:
                info = "hello Thursday";
                break;
            case 5:
                info = "hello Friday";
                break;
            case 6:
                info = "hello Saturday";
                break;
            default:
                info = "...";
        }
        System.out.println(info);
    }

}
