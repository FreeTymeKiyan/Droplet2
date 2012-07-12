package org.dianlava.droplet.Interfaces;

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
}
