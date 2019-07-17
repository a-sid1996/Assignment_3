package ClientPackage;

import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import _ServerPackage.Common_Inteface;
import _ServerPackage.Common_IntefaceHelper;

public class Test {
	
	static Common_Inteface montreal_obj;
	static Common_Inteface ottawa_obj;
	static Common_Inteface toronto_obj;
	
	public static void perform1() {

		String newEventID = "OTWE110619";
		String newEventType = "Conference";
		String oldEventType = "Seminar";
		String oldEventID = "MTLE230719";
		String customerID = "TORC1111";

		System.out.println(toronto_obj.swapEvent(customerID, oldEventType, oldEventID, newEventType, newEventID) + "---T1");

	}
	
	public static void perform2() {

		String newEventID = "MTLA090619";
		String newEventType = "Conference";
		String oldEventType = "Conference";
		String oldEventID = "OTWE110619";
		String customerID = "TORC1111";

		System.out.println(toronto_obj.swapEvent(customerID, oldEventType, oldEventID, newEventType, newEventID) + "---T2");

	}
	
	public static void perform3() {

		String customerID = "MTLC1111";
		String eventType = "MTLE230719";
		String eventID = "Seminar";
		System.out.println(montreal_obj.bookEvent(customerID, eventID, eventType) + "---T3");

	}
	
	public static void perform4() {

		String customerID = "MTLC1111";
		String eventType = "Conference";
		String eventID = "MTLA090619";
		System.out.println(montreal_obj.bookEvent(customerID, eventID, eventType) + "---T4");

	}
	
	
	public static void perform5() {

		String newEventID = "MTLA080619";
		String newEventType = "Conference";
		String oldEventType = "Conference";
		String oldEventID = "OTWE110619";
		String customerID = "TORC1112";

		System.out.println(toronto_obj.swapEvent(customerID, oldEventType, oldEventID, newEventType, newEventID) + "---T5");

	}

	
	public static void perform6() {

		String newEventID = "MTLA080619";
		String newEventType = "Conference";
		String oldEventType = "Conference";
		String oldEventID = "OTWE110619";
		String customerID = "OTWC1111";

		System.out.println(ottawa_obj.swapEvent(customerID, oldEventType, oldEventID, newEventType, newEventID) + "---T6");

	}

	public static void main(String args[]) throws Exception {
		
		Properties props = new Properties();

		props.put("org.omg.CORBA.ORBInitialPort", "1050");
		props.put("org.omg.CORBA.ORBInitialHost", "localhost");

		ORB orb = ORB.init(args, props);

		org.omg.CORBA.Object objRef;

		objRef = orb.resolve_initial_references("NameService");

		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		montreal_obj = Common_IntefaceHelper.narrow(ncRef.resolve_str("MTL"));
		ottawa_obj = Common_IntefaceHelper.narrow(ncRef.resolve_str("OTW"));
		toronto_obj = Common_IntefaceHelper.narrow(ncRef.resolve_str("TOR"));

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

/*		Runnable task5 = () -> {

			perform5();
		};

		Runnable task6 = () -> {

			perform6();
		};

		new Thread(task5).start();
		new Thread(task6).start();
*/		
	}

}
