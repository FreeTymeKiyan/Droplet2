package org.dianlava.droplet.Models;

public class ApplyFriend {
	public int af_id;
	public int u_nox;
	public int u_noy;
	
	public ApplyFriend(int af_id,int u_nox,int u_noy)
	{
		this.af_id = af_id;
		this.u_nox = u_nox;
		this.u_noy = u_noy;
	}
	public ApplyFriend(String str)
	{
		String[] ss = str.split("\\" + Constant.DELIMITER);
		this.af_id = Integer.parseInt(ss[0]);
		this.u_nox = Integer.parseInt(ss[1]);
		this.u_noy = Integer.parseInt(ss[2]);
	}
	public String toString()
	{
		String result = null;
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(af_id);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(u_nox);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(u_noy);
		result = stringBuffer.toString();
		return result;
	}

}
