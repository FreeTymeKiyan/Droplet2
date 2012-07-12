package org.dianlava.droplet.Models;

public class Visit {
	public int v_id;
	public int u_no;
	public int v_no;
	public String v_date;
	
	public Visit(int v_id,int u_no,int v_no,String v_date)
	{
		this.v_id   = v_id;
		this.u_no   = u_no;
		this.v_no   = v_no;
		this.v_date = v_date;
	}
	public Visit(String str)
	{
		String[] ss = str.split("\\" + Constant.DELIMITER);
		this.v_id   = Integer.parseInt(ss[0]);
		this.u_no   = Integer.parseInt(ss[1]);
		this.v_no   = Integer.parseInt(ss[2]);
		this.v_date = ss[3];
	}
	public String toString()
	{
		String result = null;
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(v_id);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(u_no);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(v_no);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(v_date);
		result = stringBuffer.toString();
		return result;
	}
}
