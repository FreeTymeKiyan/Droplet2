package org.dianlava.droplet.Models;

import static org.dianlava.droplet.Utils.ConstantUtil.*;

public class Friend {
	public int f_id;
	public int u_nox;
	public int u_noy;
	public String f_date;
	
	public Friend(int f_id,int u_nox,int u_noy,String f_date)
	{
		this.f_id   = f_id;
		this.u_nox  = u_nox;
		this.u_noy  = u_noy;
		this.f_date = f_date;
	}
	public Friend(String str)
	{
		String[] ss = str.split("\\" + DELIMITER);
		this.f_id   = Integer.parseInt(ss[0]);
		this.u_nox  = Integer.parseInt(ss[1]);
		this.u_noy  = Integer.parseInt(ss[2]);
		this.f_date = ss[3];
	}
	public String toString()
	{
		String result = null;
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(f_id);
		stringBuffer.append(DELIMITER);
		stringBuffer.append(u_nox);
		stringBuffer.append(DELIMITER);
		stringBuffer.append(u_noy);
		stringBuffer.append(DELIMITER);
		stringBuffer.append(f_date);
		result = stringBuffer.toString();
		return result;
	}

}
