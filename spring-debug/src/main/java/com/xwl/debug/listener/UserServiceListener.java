package com.xwl.debug.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;

/**
 * @author xwl
 * @createdTime 2021/12/12 19:01
 * @description
 */
public class UserServiceListener {
	/**
	 * @EventListener 注解监听事件
	 * @param event
	 */
	@EventListener(classes = {ApplicationEvent.class})
	public void listen(ApplicationEvent event) {
		System.out.println("UserServiceListener#listen()方法==>监听到的事件：" + event);
	}
}
