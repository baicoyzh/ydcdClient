package com.ccic.ydcd.common.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionUtils extends org.apache.commons.lang.StringUtils {
	// private static final Log log = LogFactory.getLog(FunctionUtils.class);
	private static java.util.Random rd = new java.util.Random();

	public static String pwdstrs = ":3!@XSK#$GyTuVB%2^&C*t(dfD)_+?rFHJs6/>4iop9A5LZ 8a.<,{zUIxc}[]'|ERY\\10qwPeg7hjklNMv=-bnm~`QWO";

	// 得到字符串的字节长度
	public static int strLength(final String str) {
		return str.trim().getBytes().length;
	}

	// 比较两个给定日期的先后
	// 0 : 日期相同 大于0 : dts在tde之后 小于0：dts在tde之前
	public static int dateCompareTo(Date dts, Date dte) {
		return dts.compareTo(dte);
	}

	// 日期对象dt生成制定格式style的字符串
	public static String getDateFormat(Date dt, String pattern) {
		SimpleDateFormat formater = new SimpleDateFormat(pattern, Locale.getDefault());
		return formater.format(dt);
	}

	public static String getCurrentDate(String pattern) {
		return getDateFormat(new Date(), pattern);
	}

	// 产生从min到max的随机数
	public static int rand(int min, int max) {
		return (rd.nextInt() >>> 1) % (max - min + 1) + min;
	}

	public static boolean isBlank(final String str) {
		return (str == null) || (str.trim().length() <= 0);
	}

	public static boolean isBlank(final Object[] objs) {
		return (objs == null) || (objs.length <= 0);
	}

	public static boolean isBlank(final Serializable obj) {
		return obj == null;
	}

	// 字符串是否比要求的最短长度还要短
	// 字符串为空，minLen小于1返回false，长度小于指定的长度返回true
	public static boolean lessMinLength(final String str, final int minLen) {
		// if(isBlank(str) || minLen < 1)
		// return false;
		// return MyUtils.strLength(str) < minLen;
		return (!(isBlank(str) || minLen < 1)) && (FunctionUtils.strLength(str) < minLen);

	}

	// 字符串是否比要求的最大长度还要长
	// 大于时返回，maxLen等于0返回true，其它情况返回false
	public static boolean overMaxLength(final String str, final int maxLen) {
		// if(isBlank(str) || maxLen < 0)
		// return false;
		// return MyUtils.strLength(str) > maxLen;

		return (!(isBlank(str) || maxLen < 0)) && (FunctionUtils.strLength(str) > maxLen);
	}

	public int hashCode() {
		int h = 0;
		int off = 0;
		String aaa = "aaa";
		char val[] = aaa.toCharArray();
		int len = aaa.length();

		for (int i = 0; i < len; i++) {
			h = 31 * h + val[off++];
		}

		return h;
	}

	static public void main(String[] args) throws Exception {
		System.out.println(org.apache.commons.lang.StringEscapeUtils.escapeJava("返回的报文"));
	}

	public static String rmb_to_cn(double rmbje) {
		String rmbdxje = new String("");

		String grmbje;
		String grmbje1;
		String grmbje2;
		String rmbz1;
		String rmbz2;
		String rmbb1;
		String rmbb2;
		String rmbb3;
		String tmp;
		String tmp1;
		String tmp2;
		int xsdwz = 0, llwz = 0, rmbl = 0, rmbl1 = 0;
		int rmbk = 0, rmbm = 0, rmbn = 0;
		// int is_int=0;

		if (Math.abs(rmbje) < 0.01) {
			return new String("零");
		}
		tmp = mystr(Math.abs(rmbje), 16, 2);
		tmp1 = subs(tmp, tmp.length() - 1, 2);
		// log.info("tmp1="+tmp1);
		if (tmp1.equals("00")) {
			rmbdxje = "整";
		}

		grmbje = alltrim(tmp);
		xsdwz = at(".", grmbje);

		grmbje1 = subs(grmbje, 1, xsdwz - 1);
		grmbje2 = subs(grmbje, xsdwz + 1, 2);
		// log.info("grmbje1="+grmbje1);
		// log.info("grmbje2="+grmbje2);
		grmbje = grmbje1 + grmbje2;
		while (at("00", grmbje) > 0) {
			llwz = at("00", grmbje);
			tmp = stuff(grmbje, llwz, 2, " 0");
			grmbje = tmp;
		}
		rmbz1 = "分角元拾佰仟万拾佰仟亿拾佰仟";
		rmbz2 = "零壹贰叁肆伍陆柒捌玖";
		rmbl = grmbje.length();
		rmbl1 = rmbl;
		rmbk = 0;
		while (rmbl > 0) {
			rmbk++;
			// rmbm=rmbk*2-1;
			rmbm = rmbk;
			rmbb1 = subs(grmbje, rmbl, 1);

			if (rmbb1.equals(" ") && rmbk == 3) {
				rmbb2 = " ";
				rmbb3 = "元";
			} else if (rmbb1.equals(" ") && rmbk == 7 && rmbl1 < 11) {
				rmbb2 = " ";
				rmbb3 = "万";
			} else if (rmbb1.equals(" ") && rmbk == 7 && rmbl1 >= 11) {

				// ?? if ( fmod(rmbje,100000000)/10000 >=1 )
				if ((rmbje % 100000000) / 10000 >= 1) {
					rmbb2 = " ";
					rmbb3 = "万";
				} else {
					rmbb2 = " ";
					rmbb3 = " ";
				}
			} else if (rmbb1.equals(" ") && rmbk == 11) {
				rmbb2 = " ";
				rmbb3 = "亿";
			} else if (rmbb1.equals(" ")) {
				rmbb2 = " ";
				rmbb3 = " ";
			} else if (rmbb1.equals("0") && rmbk == 1) {
				rmbb2 = " ";
				rmbb3 = " ";
			} else if (rmbb1.equals("0") && rmbk == 3) {
				rmbb2 = " ";
				rmbb3 = "元";
			} else if (rmbb1.equals("0") && rmbk == 7 && rmbl1 < 11) {
				rmbb2 = " ";
				rmbb3 = "万零";
			} else if (rmbb1.equals("0") && rmbk == 7 && rmbl1 >= 11) {
				// if ( fmod(rmbje,100000000)/10000>=1 )
				if ((rmbje % 100000000) / 10000 >= 1) {
					rmbb2 = " ";
					rmbb3 = "万零";
				} else {
					rmbb2 = " ";
					rmbb3 = "零";
				}
			} else if (rmbb1.equals("0") && rmbk == 11) {
				rmbb2 = " ";
				rmbb3 = "亿";
			} else if (rmbb1.equals("0")) {
				rmbb2 = "零";
				rmbb3 = "  ";
			} else {
				int nT = (int) myval(rmbb1);
				rmbn = nT + 1;
				rmbb2 = subs(rmbz2, rmbn, 1);
				rmbb3 = subs(rmbz1, rmbm, 1);
			}
			tmp = LeftTrim(rmbb2);
			tmp1 = LeftTrim(rmbb3);
			tmp2 = tmp + tmp1 + rmbdxje;

			rmbdxje = tmp2;
			rmbl--;
		}
		if (rmbje < 0) {
			tmp = "负" + rmbdxje;
			rmbdxje = tmp;
		}
		return rmbdxje;
	}

	public static String sjdx(double rmbje) {
		String rmbdxje = new String("");

		String grmbje;
		String grmbje1;
		String grmbje2;
		String rmbz1;
		String rmbz2;
		String rmbb1;
		String rmbb2;
		String rmbb3;
		String tmp;
		String tmp1;
		String tmp2;
		int xsdwz = 0, llwz = 0, rmbl = 0, rmbl1 = 0;
		int rmbk = 0, rmbm = 0, rmbn = 0;

		if (Math.abs(rmbje) < 0.01) {
			return new String("零");
		}
		tmp = mystr(Math.abs(rmbje), 16, 2);

		grmbje = alltrim(tmp);
		xsdwz = at(".", grmbje);

		grmbje1 = subs(grmbje, 1, xsdwz - 1);
		grmbje2 = subs(grmbje, xsdwz + 1, 2);
		grmbje = grmbje1 + grmbje2;
		while (at("00", grmbje) > 0) {
			llwz = at("00", grmbje);
			tmp = stuff(grmbje, llwz, 2, " 0");
			grmbje = tmp;
		}
		rmbz1 = "分角 十佰仟万拾佰仟亿拾佰仟";
		rmbz2 = "零一二三四五六七八九";
		rmbl = grmbje.length();
		rmbl1 = rmbl;
		rmbk = 0;
		while (rmbl > 0) {
			rmbk++;
			// rmbm=rmbk*2-1;
			rmbm = rmbk;
			rmbb1 = subs(grmbje, rmbl, 1);

			if (rmbb1.equals(" ") && rmbk == 3) {
				rmbb2 = " ";
				rmbb3 = "";
			} else if (rmbb1.equals(" ") && rmbk == 7 && rmbl1 < 11) {
				rmbb2 = " ";
				rmbb3 = "万";
			} else if (rmbb1.equals(" ") && rmbk == 7 && rmbl1 >= 11) {

				// ?? if ( fmod(rmbje,100000000)/10000 >=1 )
				if ((rmbje % 100000000) / 10000 >= 1) {
					rmbb2 = " ";
					rmbb3 = "万";
				} else {
					rmbb2 = " ";
					rmbb3 = " ";
				}
			} else if (rmbb1.equals(" ") && rmbk == 11) {
				rmbb2 = " ";
				rmbb3 = "亿";
			} else if (rmbb1.equals(" ")) {
				rmbb2 = " ";
				rmbb3 = " ";
			} else if (rmbb1.equals("0") && rmbk == 1) {
				rmbb2 = " ";
				rmbb3 = " ";
			} else if (rmbb1.equals("0") && rmbk == 3) {
				rmbb2 = " ";
				rmbb3 = "";
			} else if (rmbb1.equals("0") && rmbk == 7 && rmbl1 < 11) {
				rmbb2 = " ";
				rmbb3 = "万零";
			} else if (rmbb1.equals("0") && rmbk == 7 && rmbl1 >= 11) {
				// if ( fmod(rmbje,100000000)/10000>=1 )
				if ((rmbje % 100000000) / 10000 >= 1) {
					rmbb2 = " ";
					rmbb3 = "万零";
				} else {
					rmbb2 = " ";
					rmbb3 = "零";
				}
			} else if (rmbb1.equals("0") && rmbk == 11) {
				rmbb2 = " ";
				rmbb3 = "亿";
			} else if (rmbb1.equals("0")) {
				rmbb2 = "零";
				rmbb3 = "  ";
			} else {
				int nT = (int) myval(rmbb1);
				rmbn = nT + 1;
				rmbb2 = subs(rmbz2, rmbn, 1);
				rmbb3 = subs(rmbz1, rmbm, 1);
			}
			tmp = LeftTrim(rmbb2);
			tmp1 = LeftTrim(rmbb3);
			tmp2 = tmp + tmp1 + rmbdxje;

			rmbdxje = tmp2;
			rmbl--;
		}
		if (rmbje < 0) {
			tmp = "负" + rmbdxje;
			rmbdxje = tmp;
		}
		if (rmbje >= 10 && rmbje < 20)
			rmbdxje = rmbdxje.substring(1);
		return rmbdxje;
	}

	public static String subs(String sstr, int pos, int len) {
		String dstr, tmp;

		tmp = sstr.substring(0);
		if (pos > tmp.length())
			return tmp;

		dstr = tmp.substring(pos - 1);
		if (len > tmp.length() - pos) {
			for (int i = 0; i < len - dstr.length(); i++)
				dstr += " ";
		}
		return dstr.substring(0, len);
	}

	public static String alltrim(String str) {
		return leftRightTrim(str);
	}

	public static int at(String dstr, String sstr) {
		int ret;

		ret = sstr.indexOf(dstr);
		if (ret < 0)
			return -1;
		return (ret + 1);
	}

	public static String leftRightTrim(String str) {
		str = LeftTrim(str);
		str = RightTrim(str);
		return str;
	}

	public static String LeftTrim(String s) {
		int i_I = 0;
		int num = 0;

		if (s == null) {
			return null;
		}
		for (i_I = 0; i_I < s.length(); i_I++) {
			num = s.charAt(i_I);
			if ((num != 9) && (num != 10) && (num != 13) && (num != 32)) {
				break;
			}
		}
		return s.substring(i_I);
	}

	public static String RightTrim(String s) {
		int i_I = 0;
		int num = 0;

		if (s == null) {
			return "";
		}
		for (i_I = s.length() - 1; i_I >= 0; i_I--) {
			num = s.charAt(i_I);
			if ((num != 9) && (num != 10) && (num != 13) && (num != 32)) {
				break;
			}
		}
		return s.substring(0, i_I + 1);
	}

	public static String mystr(double l, int len, int dec) {
		String buf;

		double dbl = myround(l, dec);

		StringBuffer format = new StringBuffer();
		for (int i = 0; i < len - dec - 1; i++)
			format.append("#");

		format.append(".");
		for (int i = 0; i < dec; i++)
			format.append("0");

		DecimalFormat df = new DecimalFormat(format.toString());
		buf = df.format(dbl);
		return buf;
	}

	public static double myround(double l, int len) {
		String buf;
		double l1, l2 = 0;
		int i, j;

		DecimalFormat df = new DecimalFormat("0.000000000000");// sprintf(buf,"%.12f",l);
		buf = df.format(l);

		if (buf.charAt(buf.length() - 12 + len) > '4') {
			l2 = 1;
		}
		j = (len < 0) ? 0 : 1;

		char Chars[] = buf.toCharArray();

		for (i = buf.length() - 13 + len + j; i < buf.length(); i++)
			Chars[i] = '0';

		Chars[buf.length() - 13] = '.';
		buf = new String(Chars);

		l1 = myval(buf);
		if ((l2 > 0) && (len > 0)) {
			for (i = 0; i < len; i++)
				l2 /= 10;
		} else if ((l2 > 0) && (len < 0)) {
			for (i = 0; i < -1 * len; i++)
				l2 *= 10;
		}
		l1 += l2;
		return (l1);
	}

	public static double myval(String p) {
		int i, j, k, m, n;
		double l = 0, l1;

		for (i = 0, j = 0, k = 0, n = 0, l = 0; i < p.length(); i++) {
			if (p.charAt(i) == ' ') {
				if (j > 0)
					return (l);
				continue;
			}
			if (p.charAt(i) == '-') {
				if (j > 0) {
					return (0);
				} else
					j = k = 1;
				continue;
			}
			if (p.charAt(i) == ',')
				continue;
			if (p.charAt(i) == '.') {
				if (n > 0)
					return (l);
				j = n = 1;
				continue;
			}
			if (p.charAt(i) > '9' || p.charAt(i) < '0') {
				return (l);
			}
			j = 1;
			if (n > 0) {
				l1 = (double) (p.charAt(i) - '0');
				for (m = 0; m < n; m++)
					l1 /= 10;
				l += l1;
				n++;
			} else {
				l *= 10;
				l += p.charAt(i) - '0';
			}
		}
		if (k > 0)
			l *= -1;
		return (l);
	}

	public static String stuff(String dstr, int pos, int len, String sstr) {
		StringBuffer tmp = new StringBuffer();
		int i, j;

		for (i = 0, j = 0; i < pos - 1; i++)
			if (dstr.charAt(i) > 128)
				j ^= 1;
		if (j > 0)
			pos--;
		if (pos < 1)
			return tmp.toString();
		if (pos > 1)
			tmp.append(dstr.substring(0, pos - 1));

		tmp.append(sstr);
		for (i = pos, j = 0; i < len; i++)
			if (dstr.charAt(i) > 128)
				j ^= 1;

		tmp.append(dstr.substring(pos + len - 1 + j, dstr.length()));
		String tmp1 = tmp.toString();
		int ll = dstr.length() + sstr.length() - len;

		return tmp1.substring(0, ll);
	}

	public static boolean IsEmptyStr(String s) {
		int i_StringLength = 0, i_I = 0;
		int num = 0;

		if (s == null) {
			return true;
		}
		if (s.length() == 0) {
			return true;
		}
		i_StringLength = s.length();
		for (i_I = 0; i_I < i_StringLength; i_I++) {
			num = s.charAt(i_I);
			if ((num != 9) && (num != 10) && (num != 13) && (num != 32)) {
				return false;
			}
			/*
			 * c_s = s.charAt(i_I); if ((c_s != ' ') && (c_s != '\t') && (c_s !=
			 * '\n')) return false;
			 */
		}
		return true;
	}

	public static String encryptStr(String pwd) {
		return encryptStr(pwd, pwdstrs);
	}

	public static String encryptStr(String pwd, String sourcestr) {
		if (IsEmptyStr(pwd)) {
			System.out.println("空串不能加密");
			return "";
		}
		String strs = sourcestr;
		char pwds[] = pwd.toCharArray();
		String posi[] = new String[pwds.length];
		for (int x = 0; x < pwds.length; x++) {
			int n = strs.indexOf(pwds[x]);
			if (n < 0) {
				System.out.println(pwds[x]);
				System.out.println("有不能显示字符存在，加密失败");
				return "";
			}

			if (n < 10) {
				posi[x] = "0" + n;
			} else {
				posi[x] = "" + n;
			}

		}

		pwd = "";
		for (int x = 0; x < posi.length; x++) {
			pwd = pwd + posi[x];
		}
		return pwd;
	}

	public static String unEncryptStr(String pwd) {
		return unEncryptStr(pwd, pwdstrs);
	}

	public static String unEncryptStr(String pwd, String sourcestr) {
		if (IsEmptyStr(pwd)) {
			System.out.println("空串，解密失败");
			return pwd;
		}
		int len = pwd.length();
		if ((len % 2) != 0) {
			System.out.println("原串有错误，解密失败");
			return "";
		}
		int m = 0;
		String strs = sourcestr;
		char unpwd[] = new char[pwd.length() / 2];
		for (int x = 0; x < unpwd.length; x++) {
			String tmps = pwd.substring(x * 2, x * 2 + 2);
			try {
				m = Integer.parseInt(tmps);
			} catch (Exception e) {
				System.out.println("原串有错误，解密失败");
				return "";
			}
			unpwd[x] = strs.charAt(m);
		}

		pwd = "";
		for (int x = 0; x < unpwd.length; x++) {
			pwd = pwd + unpwd[x];
		}
		return pwd;

	}

	public static String cutNull(Object str) {
		if (str == null) {
			return "";
		}
		return String.valueOf(str);
	}

	public static double cutNullDou(Object dou) {
		if (dou == null) {
			return 0.0;
		}
		return Double.parseDouble(String.valueOf(dou));
	}

	/**
	 * 功 能：判断中文个数 输入参数：str 输出参数：count 作 者：陈绍东 作成时间：2009.09.09 修 改： 修改时间：
	 */
	public static int checkByteLen(String str) {
		int count = 0;
		byte[] bstr = new byte[8];
		String checkStr = str;
		String subStr;
		for (int i = 0; i < str.length(); i++) {
			subStr = checkStr.substring(i, i + 1);
			bstr = subStr.getBytes();
			count = count + (bstr.length - 1);
		}
		// System.out.println("长度为：" + count);
		return count;
	}

	/**
	 * 两个日期之间间隔天数
	 * 
	 * @param t1
	 * @param t2
	 * @return
	 * @throws ParseException
	 */
	public static int getBetweenDays(String begDate, String endDate) throws Exception {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return new Long((format.parse(endDate).getTime() - format.parse(begDate).getTime()) / (1000 * 60 * 60 * 24))
				.intValue();
	}

	/**
	 * 验证日期格式
	 * 
	 * @param sDate
	 * @return
	 */
	public static boolean isValidDate(String sDate) {
		String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
		String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
				+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
				+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
				+ "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		if ((sDate != null)) {
			Pattern pattern = Pattern.compile(datePattern1);
			Matcher match = pattern.matcher(sDate);
			if (match.matches()) {
				pattern = Pattern.compile(datePattern2);
				match = pattern.matcher(sDate);
				return match.matches();
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 在身份证里面取出生日期
	 * 
	 * @param id
	 * @return
	 */
	public static String getIDBirthday(String id) {
		String result = "";
		if (id == null || "".equals(id)) {
			return null;
		}
		if (id.length() == 15) {
			result = id.substring(6, 12);
			result = "19" + result;
		} else {
			result = id.substring(6, 14);
		}
		return result;
	}

	/**
	 * YYYYMMdd 在月份和年份之间增加间隔符
	 * 
	 * @param dts
	 *            yyyyMMdd
	 * @param pattern
	 *            间隔符
	 * @return
	 */
	public static String getformtime(String dts, String pattern) {
		String dates = null;
		if (dts == null) {
			dates = null;
			return dates;
		}
		String year = dts.substring(0, 4);
		String month = dts.substring(4, 6);
		String day = dts.substring(6, 8);
		dates = year + pattern + month + pattern + day;
		return dates;
	}

	/**
	 * 将业务来源由3位转换为4位 杨守波 2012-08-29
	 * 
	 * @param oldBussinessNature
	 *            旧渠道业务来源
	 * @return newBussinessNature 新渠道业务来源
	 */
	public static String getBusinessNature(String oldBussinessNature) throws Exception {
		String newBussinessNature = "";
		if (oldBussinessNature == "001" || "001".equals(oldBussinessNature) || oldBussinessNature == "005"
				|| "005".equals(oldBussinessNature)) {
			newBussinessNature = "0101";
		} else if (oldBussinessNature == "002" || "002".equals(oldBussinessNature)) {
			newBussinessNature = "1202";
		} else if (oldBussinessNature == "004" || "004".equals(oldBussinessNature)) {
			newBussinessNature = "0301";
		} else if (oldBussinessNature == "100" || "100".equals(oldBussinessNature)) {
			newBussinessNature = "2401";
		} else if (oldBussinessNature == "200" || "200".equals(oldBussinessNature)) {
			newBussinessNature = "2101";
		} else if (oldBussinessNature == "301" || "301".equals(oldBussinessNature)) {
			newBussinessNature = "2301";
		} else if (oldBussinessNature == "302" || "302".equals(oldBussinessNature)) {
			newBussinessNature = "2201";
		} else if (oldBussinessNature == "303" || "303".equals(oldBussinessNature)) {
			newBussinessNature = "2202";
		} else if (oldBussinessNature == "304" || "304".equals(oldBussinessNature)) {
			newBussinessNature = "2304";
		} else if (oldBussinessNature == "305" || "305".equals(oldBussinessNature) || oldBussinessNature == "306"
				|| "306".equals(oldBussinessNature) || oldBussinessNature == "310" || "310".equals(oldBussinessNature)
				|| oldBussinessNature == "312" || "312".equals(oldBussinessNature)) {
			newBussinessNature = "2105";
		} else if (oldBussinessNature == "307" || "307".equals(oldBussinessNature)) {
			newBussinessNature = "2302";
		} else if (oldBussinessNature == "308" || "308".equals(oldBussinessNature)) {
			newBussinessNature = "2104";
		} else if (oldBussinessNature == "309" || "309".equals(oldBussinessNature)) {
			newBussinessNature = "2203";
		} else if (oldBussinessNature == "311" || "311".equals(oldBussinessNature)) {
			newBussinessNature = "2103";
		} else if (oldBussinessNature == "313" || "313".equals(oldBussinessNature)) {
			newBussinessNature = "2204";
		} else if (oldBussinessNature == "400" || "400".equals(oldBussinessNature)) {
			newBussinessNature = "2102";
		}
		return newBussinessNature;

	}
}