package org.dianlava.droplet.Activities;

import org.dianlava.droplet.Interfaces.ActivityInterface;

import android.app.Activity;
import android.os.Bundle;

/**
 * 创建习惯页面
 * 
 * @author FreeTymeKiyan
 * @date 2012-07-11
 * @version 1.0 
 * */
public class CreateActivity extends Activity implements ActivityInterface{

	private boolean isActivty = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 创建习惯
	 * @author Stephen
	 * */
	private boolean crateHabit(){
		return false;
	}
	
	/**
	 * 判断是否为活动习惯
	 * */
	@Override
	public void judgeEntry() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 从服务器获取活动详情数据
	 * */
	@Override
	public void loadData() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 上传数据到服务器并刷新本地数据
	 * @author Stephen
	 * */
	private void refreshData(){
		
	}
	
	@Override
	public void processData() {
		// TODO Auto-generated method stub
		
	}
	
}
