package com.client.ebulid;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.travelsky.ibe.client.AV;
import com.travelsky.ibe.client.AvItem;
import com.travelsky.ibe.client.AvResult;
import com.travelsky.ibe.client.pnr.DETR;
import com.travelsky.ibe.client.pnr.DETRTKTResult;

public class AvDemo {
	public static void main(String[] args) {
		AV av = new AV();
		av.setConnectionInfo("10.221.136.60", 6891);//设置ip端口
		av.setAgentInfo("SCH115", "0", "20");//设置代理人信息
		av.setAppName("3uairetn1");//设置服务名称
		
		Calendar orgdate = Calendar.getInstance();
		orgdate.add(Calendar.DATE, 1);
		orgdate.set(Calendar.HOUR_OF_DAY,0);
		orgdate.set(Calendar.MINUTE,0);
		orgdate.set(Calendar.SECOND,0);
		try {
			AvResult result = av.getAvailability("CTU","SEA",orgdate.getTime());
			result.sortItems('p');
			System.out.println(result);
			for (int i = 0; i < result.getItemamount(); i++) {
				AvItem avItem = result.getItem(i);
				System.out.println("=================="+avItem.getSegmentnumber()+"====================");
				for (int j = 0; j < avItem.getSegmentnumber(); j++) {
					int hour = Integer.valueOf(avItem.getDeptime(j).substring(0, 2));
					System.out.println("查询信息如下：");
					System.out.print("出发城市："+avItem.getOrgcity(j)+"/"+"到达城市："+avItem.getDstcity(j));
					System.out.print("出发时间："+avItem.getDepdate(j)+"/"+avItem.getDeptime(j));
					System.out.println("航班号："+avItem.getAirline(j));
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}	
