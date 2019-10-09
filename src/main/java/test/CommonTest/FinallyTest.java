package test.CommonTest;

public class FinallyTest {
    public static void main(String[] args) {
        System.out.println(finalc());
    }

    private static int  finalc()
    {
        try
        {
            ec();
            return 0;
        }
        catch (Exception e)
        {
            System.out.println("CATCH"+e.getMessage());
            System.out.println("return");
            return 1;
        }
        finally {
            System.out.println("finally");
        }
    }

    private static void  ec() throws Exception {
        throw new Exception("test");
    }
}
