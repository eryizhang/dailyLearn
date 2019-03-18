package test.designMode.enentobserver;

import java.util.EventListener;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/14 11:00
 * @Description:
 */
class DoorNameListener implements EventListener {
    public void doorEvent(Door1Event event) {
        Door1 door = (Door1) event.getSource();
        System.out.println("I got a new name,named \"" + door.getName() + "\"");
    }
}
