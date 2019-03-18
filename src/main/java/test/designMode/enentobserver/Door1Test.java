package test.designMode.enentobserver;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/14 11:01
 * @Description:
 */
public class Door1Test {
    public static void main(String[] args) {

        Door1 door = new Door1();
        door.setStateListener(new DoorStateListener());
        door.setNameListener(new DoorNameListener());
        // 开门
        door.setState("open");
        System.out.println("我已经进来了");
        // 关门
        door.setState("close");
        // 改名
        door.setName("dengzy");
        Object obj=new Object();

    }
}
