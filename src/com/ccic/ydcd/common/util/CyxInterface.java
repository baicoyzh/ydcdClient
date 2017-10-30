package com.ccic.ydcd.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class CyxInterface {
	private int timeout = 30000;

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public String sendData(String xmlData, String strIp, int iPort) {
		Socket c = null;
		PrintWriter pw = null;
		BufferedReader br = null;

		BufferedReader in = null;
		InputStreamReader inputStreamReader = null;
		OutputStreamWriter outputStreamWriter = null;
		try {
			
			c = new Socket(strIp, iPort);// 乘意险接口
			c.setSoTimeout(timeout);// 设置超时
			inputStreamReader = new InputStreamReader(c.getInputStream());
			in = new BufferedReader(inputStreamReader);

			outputStreamWriter = new OutputStreamWriter(c.getOutputStream());
			pw = new PrintWriter(outputStreamWriter, true);
			
			pw.write(xmlData);
			pw.flush();

			String strFromServer = "";
			strFromServer = in.readLine();

			return strFromServer;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStreamReader != null) {
					inputStreamReader.close();
					inputStreamReader = null;
				}
				if (outputStreamWriter != null) {
					outputStreamWriter.close();
					outputStreamWriter = null;
				}
				if (in != null) {
					in.close();
					in = null;
				}
				if (pw != null) {
					pw.close();
					pw = null;
				}
				if (br != null) {
					br.close();
					br = null;
				}
				if (c != null) {
					c.close();
					c = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String encryptData(String xmlData, String strPassword) throws Exception {
		DESPlus.setKey(strPassword);
		return DESPlus.encrypt(xmlData);
	}

	public String decryptData(String xmlData, String strPassword) throws Exception {
		DESPlus.setKey(strPassword);
		return DESPlus.decrypt(xmlData);
	}

	public static void main(String[] args) throws Exception {
		try {
			String str = null;

			// (最新cyx接口退废单测试，TRANSFLAG为2的话，废单，3的话，退单)
			str = "<?xml version='1.0' encoding='GB18030'?><ROOT><TRANSDATA><SEQNO>145</SEQNO><DOCNO>222000110851026057</DOCNO><CSUSER>4401000003</CSUSER><RATIONCODE>ETA0130036</RATIONCODE><RATIONCOMCODE>44010000</RATIONCOMCODE><SERIALNO>9380</SERIALNO><OPERCODE>4401000003</OPERCODE><COMCODE>44010000</COMCODE><TRANSFLAG>2</TRANSFLAG><TRANSTIME>2013-11-27 15:22:32.0</TRANSTIME><SYSMODETIME>152232</SYSMODETIME><STARTDATE>2013-12-06</STARTDATE><ENDDATE>2013-12-07</ENDDATE><STARTHOUR></STARTHOUR><ENDHOUR></ENDHOUR><SUMPRIMUM></SUMPRIMUM><BBR><PNAME>姚明珠</PNAME><PSEX>1</PSEX><INSUREDVERIFYNO>588E2ED825639195</INSUREDVERIFYNO><PBIRTHDAY></PBIRTHDAY><ZJTYPE>01</ZJTYPE><ZJNO>331003198806172374</ZJNO><TELE></TELE></BBR><SYFS>2</SYFS><SYR></SYR><TICKET><CCNO></CCNO><KPNO></KPNO><ZWNO></ZWNO><PAMOUNT>null</PAMOUNT><STARTTIME>2013-12-06 15:22:32</STARTTIME><ENDTIME></ENDTIME><STARTSTATION>红太阳娱乐中心</STARTSTATION><ENDSTATION>上海浦东新区</ENDSTATION></TICKET><RATIONKIND><KINDCODE>11100029</KINDCODE><ITEMCODE>100060</ITEMCODE><AMOUNT>30000</AMOUNT><RATE>2</RATE><PREMIUM>2.0</PREMIUM></RATIONKIND><RATIONKIND><KINDCODE>11100029</KINDCODE><ITEMCODE>100059</ITEMCODE><AMOUNT>100000</AMOUNT><RATE>3</RATE><PREMIUM>3.0</PREMIUM></RATIONKIND></TRANSDATA>"
					+ "<TRANSDATA><SEQNO>145</SEQNO><DOCNO>222000110851026058</DOCNO><CSUSER>4401000003</CSUSER><RATIONCODE>ETA0130036</RATIONCODE><RATIONCOMCODE>44010000</RATIONCOMCODE><SERIALNO>9380</SERIALNO><OPERCODE>4401000003</OPERCODE><COMCODE>44010000</COMCODE><TRANSFLAG>3</TRANSFLAG><TRANSTIME>2013-11-27 15:22:32.0</TRANSTIME><SYSMODETIME>152232</SYSMODETIME><STARTDATE>2013-12-06</STARTDATE><ENDDATE>2013-12-07</ENDDATE><STARTHOUR></STARTHOUR><ENDHOUR></ENDHOUR><SUMPRIMUM></SUMPRIMUM><BBR><PNAME>NULL</PNAME><PSEX>1</PSEX><INSUREDVERIFYNO>588E2ED825639195</INSUREDVERIFYNO><PBIRTHDAY></PBIRTHDAY><ZJTYPE>01</ZJTYPE><ZJNO>341281198705038370</ZJNO><TELE></TELE></BBR><SYFS>2</SYFS><SYR></SYR><TICKET><CCNO></CCNO><KPNO></KPNO><ZWNO></ZWNO><PAMOUNT>null</PAMOUNT><STARTTIME>2013-12-06 15:22:32</STARTTIME><ENDTIME></ENDTIME><STARTSTATION>红太阳娱乐中心</STARTSTATION><ENDSTATION>上海浦东新区</ENDSTATION></TICKET><RATIONKIND><KINDCODE>11100029</KINDCODE><ITEMCODE>100060</ITEMCODE><AMOUNT>30000</AMOUNT><RATE>2</RATE><PREMIUM>2.0</PREMIUM></RATIONKIND><RATIONKIND><KINDCODE>11100029</KINDCODE><ITEMCODE>100059</ITEMCODE><AMOUNT>100000</AMOUNT><RATE>3</RATE><PREMIUM>3.0</PREMIUM></RATIONKIND></TRANSDATA></ROOT>";
			try {
				new CyxInterface().sendData(str, "10.1.31.110", 8601);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
