package test.fanxing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TAndWenhao {
    public static void main(String[] args) throws Exception {

        List<Integer> ls=new ArrayList<>();
        ls.add(1);
        ls.add(3);
        print(ls);
        printT(ls);
    }

    public static void print(List<?> al) {
        Iterator<?> it = al.iterator();
        while (it.hasNext()) {
            //? t=it.next();
            System.out.println(it.next());
        }
    }
    public  static <T>  void printT(List<T> al) {
        Iterator<T> it = al.iterator();
        while (it.hasNext()) {
            T t=it.next();
            System.out.println(t.getClass());
            System.out.println(t);

        }
    }
}
