package org.dianlava.droplet.Interfaces;

/**
 * 所有设置的页面需要实现的接口
 * 
 * @author FreeTymeKiyan
 * @date 2012-07-11
 * @version 1.0 
 * */
public interface PrefInterface {
	
	/**
	 * 设置提醒的状态
	 * */
	public void setReminderState();
	
	/**
	 * 设置提醒的时间
	 * */
	public void setReminderTime();
}