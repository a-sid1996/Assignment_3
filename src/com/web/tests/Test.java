package com.web.tests;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.web.service.Common_Interface;

/**
 * The Class Test.
 */
public class Test {
	
	/** The montreal obj. */
	static Common_Interface montreal_obj;
	
	/** The ottawa obj. */
	static Common_Interface ottawa_obj;
	
	/** The toronto obj. */
	static Common_Interface toronto_obj;
	
	/**
	 * Perform 1.
	 */
	public static void perform1() {
		String newEventID = "OTWE110619";
		String newEventType = "Conference";
		String oldEventType = "Seminar";
		String oldEventID = "MTLE230719";
		String customerID = "TORC1111";
		System.out.println(toronto_obj.swapEvent(customerID, oldEventType, oldEventID, newEventType, newEventID) + "---T1");
	}
	
	/**
	 * Perform 2.
	 */
	public static void perform2() {
		String newEventID = "MTLA090619";
		String newEventType = "Conference";
		String oldEventType = "Conference";
		String oldEventID = "OTWE110619";
		String customerID = "TORC1111";
		System.out.println(toronto_obj.swapEvent(customerID, oldEventType, oldEventID, newEventType, newEventID) + "---T2");
	}
	
	/**
	 * Perform 3.
	 */
	public static void perform3() {

		String customerID = "MTLC1111";
		String eventType = "MTLE230719";
		String eventID = "Seminar";
		System.out.println(montreal_obj.bookEvent(customerID, eventID, eventType) + "---T3");

	}
	
	/**
	 * Perform 4.
	 */
	public static void perform4() {

		String customerID = "MTLC1111";
		String eventType = "Conference";
		String eventID = "MTLA090619";
		System.out.println(montreal_obj.bookEvent(customerID, eventID, eventType) + "---T4");

	}
	
	
	/**
	 * Perform 5.
	 */
	public static void perform5() {

		String newEventID = "MTLA080619";
		String newEventType = "Conference";
		String oldEventType = "Conference";
		String oldEventID = "OTWE110619";
		String customerID = "TORC1112";

		System.out.println(toronto_obj.swapEvent(customerID, oldEventType, oldEventID, newEventType, newEventID) + "---T5");

	}

	
	/**
	 * Perform 6.
	 */
	public static void perform6() {

		String newEventID = "MTLA080619";
		String newEventType = "Conference";
		String oldEventType = "Conference";
		String oldEventID = "OTWE110619";
		String customerID = "OTWC1111";

		System.out.println(ottawa_obj.swapEvent(customerID, oldEventType, oldEventID, newEventType, newEventID) + "---T6");

	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String args[]) throws Exception {
		
		URL montrealURL = new URL("http://localhost:8080/montreal?wsdl");
		QName montrealQName = new QName("http://impl.service.web.com/", "Montreal_ClassService");
		Service montrealService = Service.create(montrealURL, montrealQName);
		montreal_obj = montrealService.getPort(Common_Interface.class);

		URL ottawaURL = new URL("http://localhost:8081/ottawa?wsdl");
		QName ottawaQName = new QName("http://impl.service.web.com/", "Ottawa_ClassService");
		Service ottawaService = Service.create(ottawaURL, ottawaQName);
		ottawa_obj = ottawaService.getPort(Common_Interface.class);

		URL torontoURL = new URL("http://localhost:8082/toronto?wsdl");
		QName torontoQName = new QName("http://impl.service.web.com/", "Toronto_ClassService");
		Service torontoService = Service.create(torontoURL, torontoQName);
		toronto_obj = torontoService.getPort(Common_Interface.class);

		Runnable task = () -> {

			perform1();
		};

		Runnable task2 = () -> {

			perform2();
		};
		
		new Thread(task).start();
		new Thread(task2).start();

		Runnable task3 = () -> {

			perform3();
		};
		
		Runnable task4 = () -> {

			perform4();
		};
		
		new Thread(task3).start();
		new Thread(task4).start();
		
	}

}
