package test.javabases;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;

/**
 * @Auther: zhangyahui
 * @Date: 2019/1/17 14:36
 * @Description:
 */
public class EnumTest {
    public enum Size{ SMALL, MEDIUM, LARGE, EXTRA_LARGE };//常量枚举（简单实用）
    public static void main(String[] args) {
        System.out.println(Size.SMALL);
        System.out.println(Color.BLANK);
        System.out.println(Color.getColorByIndex(1));
        System.out.println(Color.getColorByName("白色"));
        System.out.println(Color.getIndexByName("白色"));
        System.out.println(Color.getNameByIndex(1));
        // EnumSet的使用
        EnumSet<Color> weekSet = EnumSet.allOf(Color.class);
        for (Color color : weekSet) {
            System.out.println(color);
        }
        // EnumMap的使用
        EnumMap<Color, String> weekMap = new EnumMap(Color.class);
        weekMap.put(Color.GREEN, "绿");
        weekMap.put(Color.BLANK, "白");
        for (Iterator<Map.Entry<Color, String>> iter = weekMap.entrySet().iterator(); iter.hasNext();) {
            Map.Entry<Color, String> entry = iter.next();
            System.out.println(entry.getKey().name() + ":" + entry.getValue());
        }
    }
}

enum Color {
    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
    // 成员变量：枚举RED("红色", 1)括号中的属性来定义
    private String name;
    private int index;
    // 构造方法：传递所有的属性
    private Color(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 1.通过编号得到名字
    public static String getNameByIndex(int index) {
        for (Color c : Color.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
    // 2.通过编号得到枚举
    public static Color getColorByIndex(int index) {
        for (Color c : Color.values()) {
            if (c.getIndex() == index) {
                return c;
            }
        }
        return null;
    }
    // 3.通过名字得到编号
    public static int getIndexByName(String name) {
        for (Color c : Color.values()) {
            if (c.getName() == name) {
                return c.index;
            }
        }
        return -1;
    }
    // 通过名字得到枚举
    public static Color getColorByName(String name) {
        for (Color c : Color.values()) {
            if (c.getName() == name) {
                return c;
            }
        }
        return null;
    }
    //得到枚举的名字
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    //得到枚举的编号
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    // 覆盖方法：设置自定义枚举输出（一般不用）
//    @Override
//    public String toString() {
//        return this.index + "_" + this.name;
//    }
}
