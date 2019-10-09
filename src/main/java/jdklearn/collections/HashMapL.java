package jdklearn.collections;

import java.util.HashMap;

public class HashMapL {
    public static void main(String[] args) {
        HashMap<String,String> hashMap=new HashMap<>();
        Object o1=new Object();
        Object o2=new Object();
        System.out.println("o1.equals(o2):"+o1.equals(o2));
        System.out.println("o1==o2:"+(o1==o2));
        Person  p1=new Person("zs",1);
        Person  p2=new Person("zs",1);
        System.out.println("o1.equals(o2):"+p1.equals(p2));
        System.out.println("o1==o2:"+(o1==o2));
        String s1=new String("test");
        String s2=new String("test");
        System.out.println("s1:"+(s1.hashCode()));
        System.out.println("s2:"+(s2.hashCode()));
        System.out.println(s1.equals(s2)+" "+(s1==s2));
    }


}
class Person
{
    String name;
    int age;

    Person (String name,int age)
    {
        this.name=name;
        this.age=age;
    }
}