package org.dianlava.droplet.Models;

public class Activity {
	public int a_id;
	public int a_join;
	public int a_joinbonus;
	public int a_finishbonus;
	public String a_des;
	public String a_title;
	
	public Activity(int a_id,int a_join,int a_joinbonus,int a_finishbonus,String a_des,String a_title) {
		this.a_id          = a_id;
		this.a_join        = a_join;
		this.a_joinbonus   = a_joinbonus;
		this.a_finishbonus = a_finishbonus;
		this.a_des         = a_des;
		this.a_title       = a_title;
	}
	
	public Activity(String str) {
		String[] ss = str.split("\\" + Constant.DELIMITER);
		this.a_id          = Integer.parseInt(ss[0]);
		this.a_join        = Integer.parseInt(ss[1]);
		this.a_joinbonus   = Integer.parseInt(ss[2]);
		this.a_finishbonus = Integer.parseInt(ss[3]);
		this.a_des         = ss[4];
		this.a_title       = ss[5];
	}
	public String toString()
	{
		String result = null;
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(a_id);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(a_join);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(a_joinbonus);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(a_finishbonus);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(a_des);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(a_title);
		result = stringBuffer.toString();
		return result;
	}
}
