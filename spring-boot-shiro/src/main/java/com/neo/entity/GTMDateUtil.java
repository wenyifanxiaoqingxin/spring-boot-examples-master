package com.neo.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class GTMDateUtil {
	/**
	 * GTM转本地时间
	 * 
	 * @param GTMDate
	 * @return
	 */
	@SuppressWarnings("unused")
	public String GTMToLocal(String GTMDate) {
		int tIndex = GTMDate.indexOf("T");
		String dateTemp = GTMDate.substring(0, tIndex);
		String timeTemp = GTMDate.substring(tIndex + 1, GTMDate.length() - 6);
		String convertString = dateTemp + " " + timeTemp;

		SimpleDateFormat format;
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		Date nowDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date result_date;
		long result_time = 0;

		if (null == GTMDate) {
			return GTMDate;
		} else {
			try {
				format.setTimeZone(TimeZone.getTimeZone("GMT00:00"));
				result_date = format.parse(convertString);
				result_time = result_date.getTime();
				format.setTimeZone(TimeZone.getDefault());
				String[] formtedDate = format.format(result_time).split("\\s+");

				//获取当前的时间
				String createdate = sdf.format(nowDate);

				if(createdate.equals(formtedDate[0])){
					return formtedDate[1];
				}else{
					return formtedDate[0];

				}


			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return GTMDate;
	}
	/**
	 * GTM转本地时间
	 *
	 * @param GTMDate
	 * @return
	 */
	@SuppressWarnings("unused")
	public String GTMToLocalNoSplit(String GTMDate) {
		//时间不需要分离时间
		int tIndex = GTMDate.indexOf("T");
		String dateTemp = GTMDate.substring(0, tIndex);
		String timeTemp = GTMDate.substring(tIndex + 1, GTMDate.length() - 6);
		String convertString = dateTemp + " " + timeTemp;

		SimpleDateFormat format;
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

		Date result_date;
		long result_time = 0;

		if (null == GTMDate) {
			return GTMDate;
		} else {
			try {
				format.setTimeZone(TimeZone.getTimeZone("GMT00:00"));
				result_date = format.parse(convertString);
				result_time = result_date.getTime();
				format.setTimeZone(TimeZone.getDefault());
				return format.format(result_time);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return GTMDate;
	}
	/***
	 * 转成格林威治时间 感觉用不到
	 */
	public String LocalToGTM(String LocalDate) {
		SimpleDateFormat format;
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		Date result_date;
		long result_time = 0;
		if (null == LocalDate) {
			return LocalDate;
		} else {
			try {
				format.setTimeZone(TimeZone.getDefault());
				result_date = format.parse(LocalDate);
				result_time = result_date.getTime();
				format.setTimeZone(TimeZone.getTimeZone("GMT00:00"));
				return format.format(result_time);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return LocalDate;
	}

	public Long DateStringToLong(String dateString) throws ParseException{
		dateString = dateString.replace("Z", " UTC");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
		return format.parse(dateString).getTime();
	}

	public static void main(String args[]) {

		GTMDateUtil GTMDateUtil = new GTMDateUtil();
		System.out.println(GTMDateUtil.GTMToLocal("2017-01-25T21:22:06.088Z"));

	}
}