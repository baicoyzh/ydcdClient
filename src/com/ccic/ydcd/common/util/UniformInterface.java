package com.ccic.ydcd.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class UniformInterface {

	static DESPlus desplus = new DESPlus("");
	private int timeout = 30000;

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String sendData(String xmlData, String strIp, int iPort) throws Exception {

		Socket c = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		BufferedReader in = null;
		InputStreamReader inputStreamReader = null;
		OutputStreamWriter outputStreamWriter = null;
		try {
			c = new Socket(strIp, iPort);
			c.setSoTimeout(timeout);
			inputStreamReader = new InputStreamReader(c.getInputStream());
			in = new BufferedReader(inputStreamReader);
			outputStreamWriter = new OutputStreamWriter(c.getOutputStream());
			pw = new PrintWriter(outputStreamWriter, true);
			// System.out.println("xmlData="+xmlData);
			pw.write(xmlData);
			pw.flush();
			// char temp[] = new char[5000];
			// String strFromServer = "";
			String backLine = "";

			// in.read(temp);// 读取返回数据
			// backLine = String.valueOf(temp);
			backLine = in.readLine();// 读取返回数据
			backLine = backLine.trim();
			// System.out.println("返回解密后报文:"+backLine);
            return backLine.trim();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			throw e;

		} catch (IOException e) {
			e.printStackTrace();
			throw e;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
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
	}

	public String encryptData(String xmlData, String strPassword) throws Exception {
		return DES.getEncrypt(strPassword.getBytes(), xmlData.getBytes());
	}

	public String decryptData(String xmlData, String strPassword) throws Exception {
		return DES.getUncoil(xmlData, strPassword.getBytes());
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

	}
}
