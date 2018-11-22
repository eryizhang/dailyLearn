package test.CommonTest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/12 14:26
 * @version: 1.0
 */
public class TestCommon {
    public static void main(String[] args) {
        //TestCommon.arrayListPreAdd();
        TestCommon.arrayListPOSTAdd();
    }

    private static void arrayListPreAdd() {
        Integer[] ay = { 1, 2, 3, 5, 6 };
        List<Integer> asList1 = Arrays.asList(ay);
        List<Integer> asList = new LinkedList<>(asList1);
        for (int i = 0; i < asList.size(); i++) {
            System.out.println("if之前" + i + "   " + asList.get(i));
            if (asList.get(i) == 5) {
                asList.add(i, 4);
                System.out.println("if中" + i + "   " + asList.get(i));
                i++;

            }

            System.out.println("if之后" + i + "   " + asList.get(i));
        }
    }

    private static void arrayListPOSTAdd() {
        Integer[] ay = { 1, 2, 3, 5, 6 };
        List<Integer> asList1 = Arrays.asList(ay);
        List<Integer> asList = new LinkedList<>(asList1);
        for (int i = 0; i < asList.size(); i++) {
            System.out.println("if之前" + i + "   " + asList.get(i));
            if (asList.get(i) == 3 || asList.get(i) == 4) {
                asList.add(i + 1, 4);
                System.out.println("if中" + i + "   " + asList.get(i));
                i++;
            }

            System.out.println("if之后" + i + "   " + asList.get(i));
        }
    }
}
