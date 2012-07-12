package org.dianlava.droplet.Interfaces;

/**
 * 所有Activity需要实现的接口
 * 
 * @author FreeTymeKiyan
 * @date 2012-07-12
 * @version 1.0
 * */
public interface ActivityInterface {
	
	/**
	 * 初始化页面的View
	 * */
	public void init();
	
	/**
	 * 判断页面的入口
	 * */
	public void judgeEntry();

	/**
	 * 处理View需要的数据
	 * */
	public void processData();
	
	/**
	 * 加载View需要的数据
	 * */
	public void loadData();
	
	/**
	 * 根据被观察者发出的提示刷新页面
	 * */
	public void refeshView();
}