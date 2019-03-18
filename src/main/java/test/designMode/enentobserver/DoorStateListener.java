package test.designMode.enentobserver;

import java.util.EventListener;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/14 11:00
 * @Description:
 */
class DoorStateListener implements EventListener {
    public void doorEvent(Door1Event event) {
        if (event.getValue() != null && event.getValue().equals("open")) {
            System.out.println("门1打开");
        } else {
            System.out.println("门1关闭");
        }
    }
}
