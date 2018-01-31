package com.client.ebulid;

import java.util.Vector;

import com.travelsky.ebuild.clientapi.reshop.Agency;
import com.travelsky.ebuild.clientapi.reshop.DateTime;
import com.travelsky.ebuild.clientapi.reshop.FareInterface;
import com.travelsky.ebuild.clientapi.reshop.HeaderIn;
import com.travelsky.ebuild.clientapi.reshop.HeaderInType;
import com.travelsky.ebuild.clientapi.reshop.Input;
import com.travelsky.ebuild.clientapi.reshop.NewHeader;
import com.travelsky.ebuild.clientapi.reshop.NewRequest;
import com.travelsky.ebuild.clientapi.reshop.Option;
import com.travelsky.ebuild.clientapi.reshop.OriginDestinationInfo;
import com.travelsky.ebuild.clientapi.reshop.OriginHeader;
import com.travelsky.ebuild.clientapi.reshop.OriginalRequest;
import com.travelsky.ebuild.clientapi.reshop.Output;
import com.travelsky.ebuild.clientapi.reshop.Passenger;
import com.travelsky.ebuild.clientapi.reshop.RMK;
import com.travelsky.ebuild.clientapi.reshop.ReShopRequest;
import com.travelsky.ebuild.clientapi.reshop.ReissueFlight;
import com.travelsky.ebuild.clientapi.reshop.ReissueSector;
import com.travelsky.ebuild.clientapi.reshop.Request;

public class ReShopDemo {
	public static void main(String[] args) {
		try {
			Request request = new Request();
//			FlightShoppping fs = new FlightShoppping();
			Reshop reshop = new Reshop();
			reshop.setAppName("3uairetn1");
			reshop.setAgentInfo("HAK969","0","9");
			reshop.setConnectionInfo("10.221.136.60", 6891);
			
			Input input = new Input();
			//设置Headin
			input.setHeaderIn(getHeaderIn());
			Request req = new Request();
			ReShopRequest reshoprequest = new ReShopRequest();
			reshoprequest.setNewRequest(getNewRequest());
			reshoprequest.setOriginalRequest(getOriginalRequest());
			req.setReShopRequest(reshoprequest);
			input.setRequest(req);
			FareInterface service = new FareInterface();
			service.setInput(input);
			
			Output outPut = reshop.doReshop(service);
			System.out.println(outPut.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static HeaderIn getHeaderIn(){
		HeaderIn headerIn = new HeaderIn();
		
		NewHeader newheader = new NewHeader();
		HeaderInType newHt = new HeaderInType();
		newHt.setSysCode("ICS");
		newHt.setChannelID("3UET");
		newHt.setChannelType("B2C");
		Agency agency = new Agency();
		agency.setOfficeId("SCH779");
		agency.setPid("11996");
		agency.setCity("CTU");
		newHt.setAgency(agency);
		newHt.setLanguage("CN");
		newHt.setCommandType("RESHOP");
		newheader.setHeaderInType(newHt);
		headerIn.setNewHeader(newheader);
		
		OriginHeader originheader = new OriginHeader();
		HeaderInType oriHt = new HeaderInType();
		oriHt.setSysCode("ICS");
		oriHt.setChannelID("3UET");
		oriHt.setChannelType("B2C");
		Agency agencytwo = new Agency();
		agencytwo.setOfficeId("SCH779");
		agencytwo.setPid("11996");
		agencytwo.setCity("CTU");
		oriHt.setAgency(agencytwo);
		oriHt.setLanguage("CN");
		oriHt.setCommandType("RESHOP");
		originheader.setHeaderInType(oriHt);
		headerIn.setOriginHeader(originheader);
		return headerIn;
	}
	
	public static NewRequest getNewRequest(){
		NewRequest newRequest = new NewRequest();
		// OD封装信息
		Vector odv = new Vector();
		OriginDestinationInfo od = new OriginDestinationInfo();
		od.setOri("CTU");
		od.setDes("HGH");
		od.setDepartureDate("09JAN18");
		od.setIsReissue("Y");
		odv.add(od);
		newRequest.setOD(odv);
		// 其他信息
		TravelPreferences travelPreference = new TravelPreferences();
		travelPreference.setCabinClass(null);
		Passenger passenger = new Passenger();
		passenger.setNumber(0);
		passenger.setType("AD");
		travelPreference.setPassenger(passenger);
		newRequest.setTravelPreference(travelPreference);
		return newRequest;
	}
	public static OriginalRequest getOriginalRequest(){
		OriginalRequest orireq = new OriginalRequest();
		// 普通类型信息
		orireq.setIssueCarrier("3U");// 出票航空公司二字代码
		orireq.setTicketType("ARL");
		orireq.setAutoIssueIndicator("Y");// 自动出票标识
		orireq.setVoluntaryChangedTag("1");// 是否自愿变更1：自愿变更
															// 0：非自愿变更
		DateTime issueDateTime = new DateTime();// 出票时间
		issueDateTime.setDate("26DEC17");
		issueDateTime.setTime("2227");
		orireq.setIssueDateTime(issueDateTime);
		orireq.setFn("FCNY1010.00/SCNY1010.00/C0.00/XCNY50.00/TCNY50.00CN/TEXEMPTYQ/ACNY1060.00/P1");
		orireq.setFc("CTU 3U HGH 1010.00L CNY1010.00END");
		orireq.setFp("CASH,CNY");
		RMK rmk =new RMK();
		rmk.setOt("RMK OT/0/3753/0-13U2773P1HGH");//shopping 返回的OT
		orireq.setRmk(rmk);
		// 旅客封装信息
		Passenger passenger = new Passenger();
		passenger.setNumber(0);
		passenger.setType("AD");
		orireq.setPassenger(passenger);
		// 航班封装信息
		Vector rsv = new Vector();
		ReissueSector reissueSector = new ReissueSector();
		reissueSector.setBookingClass("L");
		reissueSector.setReservationState("HK");
		reissueSector.setReservationNum(1);
		ReissueFlight reissueFlight = new ReissueFlight();
		reissueFlight.setCarrier("3U");
		reissueFlight.setFlightNumber(8991);
		reissueFlight.setFlightNumberSuffix(null);
		reissueFlight.setOriAirport("CTU");
		reissueFlight.setOriCity(null);
		reissueFlight.setDesAirport("HGH");
		reissueFlight.setDesCity(null);
		DateTime deptDateTime = new DateTime();
		deptDateTime.setDate("27DEC17");
		deptDateTime.setTime("0700");
		reissueFlight.setDeptDateTime(deptDateTime);
		DateTime arrivalDateTime = new DateTime();
		arrivalDateTime.setDate("27DEC17");
		arrivalDateTime.setTime("0940");
		reissueFlight.setArrivalDateTime(arrivalDateTime);
		reissueFlight.setNumStops("0");
		reissueFlight.setEquipmentType("321");
		reissueFlight.setOperatingCarrier("3U");
		reissueFlight.setOperatingFlightNumber(0);
		reissueSector.setFlight(reissueFlight);
		reissueSector.setUseState("0");
		reissueSector.setChangeState("Y");
		reissueSector.setFareBasis("L");
		reissueSector.setTimeOfVoluntaryChanged(0);
		reissueSector.setTimeofInvoluntaryChanged(null);
		
		DateTime cancelDateTime = new DateTime();
		cancelDateTime.setDate("02JAN18");
		cancelDateTime.setTime("1124");
		reissueSector.setCancelDateTime(cancelDateTime);
		rsv.add(reissueSector);
		orireq.setSector(rsv);
		return orireq;
	}
	
	public static Option getOption(){
		Option option = new Option();
		option.setIsAvNeeded("Y");
		option.setIsPSnNeeded("Y");
		option.setIsPsAvBindsNeeded("Y");
		option.setIsFaresNeeded("Y");
		option.setRuleTypeNeeded("ALL");
		option.setFormat("SIM");
		return option;
	}
}
