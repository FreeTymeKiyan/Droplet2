package org.dianlava.droplet.Models;

public class Words {
	public int w_id;
	public int ht_id;
	public int w_no;
	public int w_type;
	public int w_praise;
	public String w_date;
	public String w_content;

	public Words(int w_id,int ht_id,int w_no,int w_type,int w_praise,String w_date,String w_content)
	{
		this.w_id      = w_id;
		this.ht_id     = ht_id;
		this.w_no      = w_no;
		this.w_type    = w_type;
		this.w_praise  = w_praise;
		this.w_date    = w_date;
		this.w_content = w_content;
	}
	public Words(String str)
	{
		String[] ss = str.split("\\" + Constant.DELIMITER);
		this.w_id      = Integer.parseInt(ss[0]);
		this.ht_id     = Integer.parseInt(ss[1]);
		this.w_no      = Integer.parseInt(ss[2]);
		this.w_type    = Integer.parseInt(ss[3]);
		this.w_praise  = Integer.parseInt(ss[4]);
		this.w_date    = ss[5];
		this.w_content = ss[6];
	}
	public String toString()
	{
		String result = null;
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(w_id);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(ht_id);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(w_no);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(w_type);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(w_praise);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(w_date);
		stringBuffer.append(Constant.DELIMITER);
		stringBuffer.append(w_content);
		result = stringBuffer.toString();
		return result;
	}
}
