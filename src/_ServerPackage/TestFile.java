package _ServerPackage;

import java.util.Properties;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

// import _ServerPackage.Common_Inteface;
// import _ServerPackage.Common_IntefaceHelper;


public class TestFile {

	static Common_Inteface montreal_obj;
	static Common_Inteface ottawa_obj;
	static Common_Inteface toronto_obj;

	protected void Setup() {

		String []args=null;
		Properties props = new Properties();
		props.put("org.omg.CORBA.ORBInitialPort", "1050");
		props.put("org.omg.CORBA.ORBInitialHost", "localhost");

		ORB orb = ORB.init(args, props);

		org.omg.CORBA.Object objRef;

		try {
			objRef = orb.resolve_initial_references("NameService");

			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			montreal_obj = Common_IntefaceHelper.narrow(ncRef.resolve_str("MTL"));
			ottawa_obj = Common_IntefaceHelper.narrow(ncRef.resolve_str("OTW"));
			toronto_obj = Common_IntefaceHelper.narrow(ncRef.resolve_str("TOR"));
		} catch (InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CannotProceed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.omg.CosNaming.NamingContextPackage.InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void MultipleSwap() {

		Runnable task = () -> {

			perform1();
		};

		Runnable task2 = () -> {

			perform2();
		};

		// starting the two task simultaneously
		new Thread(task).start();
		new Thread(task2).start();

	}

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

	
}
