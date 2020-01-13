/**
 * @author Ivybest imiaodev@163.com
 * @version 1.0
 * @date 2018年7月10日 下午4:12:12
 */
public class RegexTest {
    public static void main(String[] args) {
        String neglectedRegex = "^\\S*((/mgr/img/)|(/mgr/css/)|(/mgr/js/))\\S*";
        String url = "/ecc/mgr/css/up.png";
        System.out.println(url.matches(neglectedRegex));

    }
}
