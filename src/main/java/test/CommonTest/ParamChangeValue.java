package test.CommonTest;

// 基本数据类型
public class ParamChangeValue {
    public static void main(String[] args) {


        int s = 1;
        System.out.println("args = [" + s + "]");
        change(s);
        System.out.println("args = [" + s + "]");
        new ParamChangeValue().changea(s);
        System.out.println("args = [" + s + "]");
        Score score = new Score();
        score.setValue(1);
        System.out.println("args = [" + score.getValue() + "]");
        change(score);
        System.out.println("after args = [" + score.getValue() + "]");
        String s1 = "test1";
        System.out.println("args = [" + s1 + "]");
        change(s1);
        System.out.println("args = [" + s1 + "]");

        System.out.println();

    }


    private static void change(String i){
        i = i + " test value";
    }
    private static void change(Score score){
        score.setValue(2);
    }
    private static void change(int i){
        i = i* 5;
    }

    private  void changea(int i){
        i = i* 5;
    }

    public static class Score{
        private int value;

        public int getValue()  {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}

