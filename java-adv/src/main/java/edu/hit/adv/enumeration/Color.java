package edu.hit.adv.enumeration;

/**
 * <p> description: Color enumeration
 * <br>---------------------------------------------------------
 * <br> 所有枚举类都继承了 Enum 的方法，下面我们详细介绍这些方法。
 * <br>	    (1) ordinal(): 返回枚举值在枚举类种的顺序。这个顺序根据枚举值声明的顺序而定。
 * <br>         Color.RED.ordinal();   //返回结果：3
 * <br>	        Color.BLUE.ordinal();  //返回结果：1
 * <br>	    (2) compareTo()方法: Enum实现了java.lang.Comparable接口，因此可以比较象与指定对象的顺序。
 * <br>	        Enum中的compareTo返回的是两个枚举值的顺序之差。当然，前提是两个枚举值必须属于同一个枚举类，
 * <br>	        否则会抛出ClassCastException()异常。(具体可见源代码)
 * <br>	        Color.RED.compareTo(Color.BLUE);  //返回结果 -1
 * <br>	    (3)	values()方法： 静态方法，返回一个包含全部枚举值的数组。
 * <br>	        Color[] colors = Color.values();
 * <br>	        for(Color c : colors) System.outS.print(c + ", "); // 返回结果：RED,BLUE,BLACK YELLOW,GREEN,
 * <br>     (4) toString()方法： 返回枚举常量的名称。
 * <br>         Color c = Color.RED;
 * <br>	        System.out.println(c);   //返回结果: RED
 * <br>	    (5) valueOf()方法： 这个方法和toString方法是相对应的，返回带指定名称的指定枚举类型的枚举常量。
 * <br>	        Color.valueOf("BLUE");   //返回结果: Color.BLUE
 * <br>	    (6) equals()方法： 比较两个枚举类对象的引用。
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author Ivybest (ivybestdev@163.com)
 * @version 1.0
 * @date 2016/7/26 09:04
 */
public enum Color {
    /**
     * black
     */
    BLACK(0, 0, 0),
    /**
     * blue
     */
    BLUE(0, 0, 255),
    /**
     * green
     */
    GREEN(0, 255, 0),
    /**
     * red
     */
    RED(255, 0, 0),
    /**
     * yellow
     */
    YELLOW(255, 255, 0);

    /**
     * custom fields
     */
    private int redValue;
    private int greenValue;
    private int blueValue;

    /**
     * constructor
     *
     * @param redValue   red Value
     * @param greenValue green Value
     * @param blueValue  blue Value
     */
    Color(int redValue, int greenValue, int blueValue) {
        this.redValue = redValue;
        this.greenValue = greenValue;
        this.blueValue = blueValue;
    }

    @Override
    public String toString() {
        return Color.this.name() + "-(" + this.redValue + ", " + this.greenValue + ", " + this.blueValue + ")";
    }

}









