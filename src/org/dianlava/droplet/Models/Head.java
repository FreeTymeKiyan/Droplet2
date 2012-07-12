package org.dianlava.droplet.Models;

import java.sql.Blob;

public class Head {
	public int h_id;
	public int u_no;
	public Blob h_path;
	
	public Head(int h_id,int u_no,Blob h_path)
	{
		this.h_id   = h_id;
		this.u_no   = u_no;
		this.h_path = h_path;
	}
	public Head(String str)
	{
		String[] ss = str.split("\\" + Constant.DELIMITER);
		this.h_id   = Integer.parseInt(ss[0]);
		this.u_no   = Integer.parseInt(ss[1]);
	}
	public String toString()
	{
		String result = null;
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(h_id);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(u_no);
		result = stringBuffer.toString();
		return result;
	}
}
