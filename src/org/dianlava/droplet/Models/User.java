package org.dianlava.droplet.Models;

import static org.dianlava.droplet.Utils.ConstantUtil.*;

public class User {
	public int u_no;
	public int u_renrenID;
	public int u_sinaID;
	public int u_tencentID;
	public int u_doubanID;
	public int h_id;
	public int u_score;
	public int u_sex;
	public int u_useday;
	public String u_name;
	
	public User(int u_no,int u_renrenID,int u_sinaID,int u_tencentID,int u_doubanID,int h_id,int u_score,int u_sex,int u_useday,String u_name)
	{
		this.u_no        = u_no;
		this.u_name      = u_name;
		this.u_renrenID  = u_renrenID;
		this.u_sinaID    = u_sinaID;
		this.u_tencentID = u_tencentID;
		this.u_doubanID  = u_doubanID;
		this.h_id        = h_id;
		this.u_score     = u_score;
		this.u_sex       = u_sex;
		this.u_name      = u_name;
		this.u_useday       = u_useday;
	}
	public User(String str)
	{
		String[] ss = str.split("\\" + DELIMITER);
		this.u_no        = Integer.parseInt(ss[0]);
		this.u_renrenID  = Integer.parseInt(ss[1]);
		this.u_sinaID    = Integer.parseInt(ss[2]);
		this.u_tencentID = Integer.parseInt(ss[3]);
		this.u_doubanID  = Integer.parseInt(ss[4]);
		this.h_id        = Integer.parseInt(ss[5]);
		this.u_score     = Integer.parseInt(ss[6]);
		this.u_sex       = Integer.parseInt(ss[7]);
		this.u_useday    = Integer.parseInt(ss[8]);
		this.u_name      = ss[9];
	}
	public String toString()
	{
		String result = null;
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(u_no);
		stringBuffer.append(DELIMITER);
		stringBuffer.append(u_renrenID);
		stringBuffer.append(DELIMITER);
		stringBuffer.append(u_sinaID);
		stringBuffer.append(DELIMITER);
		stringBuffer.append(u_tencentID);
		stringBuffer.append(DELIMITER);
		stringBuffer.append(u_doubanID);
		stringBuffer.append(DELIMITER);
		stringBuffer.append(h_id);
		stringBuffer.append(DELIMITER);
		stringBuffer.append(u_score);
		stringBuffer.append(DELIMITER);
		stringBuffer.append(u_sex);
		stringBuffer.append(DELIMITER);
		stringBuffer.append(u_useday);
		stringBuffer.append(DELIMITER);
		stringBuffer.append(u_name);
		result = stringBuffer.toString();
		return result;
	}
}
