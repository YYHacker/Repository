package com.client.ebulid;

import java.util.Calendar;

import com.travelsky.ibe.client.*;

public class EBTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AV av = new AV();
		av.setConnectionInfo("10.221.136.60", 8080);
		av.setAgentInfo("SCH115", "0", "20");
		av.setAppName("3uairetn1");
		try {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			Calendar sCal = Calendar.getInstance();
			sCal.add(Calendar.DATE, 1);
			sCal.set(Calendar.HOUR_OF_DAY, 11);
			sCal.set(Calendar.MINUTE, 59);
			sCal.set(Calendar.SECOND, 59);
			Calendar eCal = Calendar.getInstance();
			eCal.add(Calendar.DATE, 1);
			eCal.set(Calendar.HOUR_OF_DAY,12);
			eCal.set(Calendar.MINUTE, 59);
			eCal.set(Calendar.SECOND, 59);
			AvResult avResult = av.getAvailability("CTU","SEA", cal.getTime());
			System.out.println(avResult.getItemamount());
			for(int i = 0 ;i < avResult.getItemamount();i++){
				AvItem item = avResult.getItem(i);
				System.out.println(item.getSegmentnumber());
				for (int j = 0; j < item.getSegmentnumber(); j++) {
					if(!(item.getDepdate(j).getTime() >= sCal.getTime().getTime() && item.getDepdate(j).getTime() <= eCal.getTime().getTime())){
						System.out.print("出发:"+item.getOrgcity(j) + ",");
						System.out.print("到达:"+item.getDstcity(j) + ",");
						System.out.print("出发时间:"+item.getDepdate(j) + ",");
						System.out.println("航班号:"+item.getAirline(j) + ";");
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
