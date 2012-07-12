package org.dianlava.droplet.Models;

import static org.dianlava.droplet.Utils.ConstantUtil.*;

public class HabitStats {
	public int hs_id;
	public int u_no;
	public int hs_now;
	public int hs_suc;
	public int hs_fal;
	public int hs_crt;
	
	public HabitStats(int hs_id,int u_no,int hs_now,int hs_suc,int hs_fal,int hs_crt)
	{
		this.hs_id  = hs_id;
		this.u_no   = u_no;
		this.hs_now = hs_now;
		this.hs_suc = hs_suc;
		this.hs_fal = hs_fal;
		this.hs_crt = hs_crt;
	}
	public HabitStats(String str)
	{
		String[] ss = str.split("\\" + DELIMITER);
		this.hs_id  = Integer.parseInt(ss[0]);
		this.u_no   = Integer.parseInt(ss[1]);
		this.hs_now = Integer.parseInt(ss[2]);
		this.hs_suc = Integer.parseInt(ss[3]);
		this.hs_fal = Integer.parseInt(ss[4]);
		this.hs_crt = Integer.parseInt(ss[5]);
	}
	public String toString()
	{
		String result = null;
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(hs_id);
		stringBuffer.append(DELIMITER);
		stringBuffer.append(u_no);
		stringBuffer.append(DELIMITER);
		stringBuffer.append(hs_now);
		stringBuffer.append(DELIMITER);
		stringBuffer.append(hs_suc);
		stringBuffer.append(DELIMITER);
		stringBuffer.append(hs_fal);
		stringBuffer.append(DELIMITER);
		stringBuffer.append(hs_crt);
		result = stringBuffer.toString();
		return result;
	}
}
