package org.dianlava.droplet.Models;

import java.sql.Blob;

public class Habit {
	public int ht_id;
	public int u_no;
	public int ht_tag;
	public int ht_insday;
	public int a_id;
	public int ht_score;
	public int ht_praise;
	public int ht_hot;
	public String ht_title;
	public String ht_crttime;
	public String ht_daystate;
	public String ht_remind;
	public Blob   ht_bgpath;
	
	public Habit(int ht_id,int u_no,int ht_tag,int ht_insday,int a_id,int ht_score,int ht_praise,int ht_hot,String ht_title,
			String ht_crttime,String ht_daystate,String ht_remind,Blob ht_bgpath)
	{
		this.ht_id       = ht_id;
		this.u_no        = u_no;
		this.ht_tag      = ht_tag;
		this.ht_insday   = ht_insday;
		this.a_id        = a_id;
		this.ht_score    = ht_score;
		this.ht_praise   = ht_praise;
		this.ht_hot      = ht_hot;
		this.ht_title    = ht_title;
		this.ht_crttime  = ht_crttime;
		this.ht_daystate = ht_daystate;
		this.ht_remind   = ht_remind;
		this.ht_bgpath   = ht_bgpath;
	}
	public Habit(String str)
	{
		String[] ss = str.split("\\" + Constant.DELIMITER);
		this.ht_id       = Integer.parseInt(ss[0]);
		this.u_no        = Integer.parseInt(ss[1]);
		this.ht_tag      = Integer.parseInt(ss[2]);
		this.ht_insday   = Integer.parseInt(ss[3]);
		this.a_id        = Integer.parseInt(ss[4]);
		this.ht_score    = Integer.parseInt(ss[5]);
		this.ht_praise   = Integer.parseInt(ss[6]);
		this.ht_hot      = Integer.parseInt(ss[7]);
		this.ht_title    = ss[8];
		this.ht_crttime  = ss[9];
		this.ht_daystate = ss[10];
		this.ht_remind   = ss[11];
	}
	public String toString()
	{
		String result = null;
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(ht_id);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(u_no);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(ht_tag);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(ht_insday);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(a_id);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(ht_score);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(ht_praise);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(ht_hot);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(ht_title);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(ht_crttime);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(ht_daystate);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(ht_remind);
		result = stringBuffer.toString();
		return result;
	}
}
