package _ServerPackage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;


/**
 * The Class Toronto_Server.
 */
public class Toronto_Server {

	/** The logger. */
	public static Logger logger = Logger.getLogger("TorontoServer");

	/** The fh. */
	public static FileHandler fh = null;

	/**
	 * The main method.
	 *
	 * @throws Exception the exception
	 */

	public static void serverRecieve() throws Exception {
		DatagramSocket socket = null;

		try {
			byte[] data = new byte[1024];
			Toronto_Class act = new Toronto_Class();
			socket = new DatagramSocket(6002);

			while (true) {
				DatagramPacket incoming = new DatagramPacket(data, data.length);
				socket.receive(incoming);

				String incoming_message = new String(incoming.getData(), 0, incoming.getLength());

				String[] separate = incoming_message.split("\\s");

				if (separate.length == 2) {
					act.remote_removeEvent(incoming_message, incoming);
				} else if (separate.length == 1) {
					act.remote_list(incoming_message, incoming);
				} else if (separate[3].equalsIgnoreCase("check")) {
					act.updateBooking(incoming_message, incoming);
				} else if (separate[3].equalsIgnoreCase("cancel")) {
					act.cancelBooking(incoming_message, incoming);
				} else if(separate[3].equalsIgnoreCase("swap")) {

				}

			}

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				socket.close();

			}
		}

	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String args[]) throws Exception {

		Runnable taskUDP = () -> {

			try {
				serverRecieve();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		};

		new Thread(taskUDP).start();

		Toronto_Class stub3 = new Toronto_Class();
	
		System.out.println(" Toronot server has been started");

		try {
			// This block configure the logger with handler and formatter
			fh = new FileHandler(Paths.get(".").toAbsolutePath().normalize().toString() + "\\log\\TorontoServer.log",
					true);

			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			// to remove the console handler *******
			logger.setUseParentHandlers(false);
			// the following statement is used to log any messages
			logger.info("Inside the Toronto Server");

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

//		System.out.println("Application Terminating ...");
		// properties value to help the ORB
		Properties props = new Properties();
		props.put("org.omg.CORBA.ORBInitialPort", "1050");
		props.put("org.omg.CORBA.ORBInitialHost", "localhost");
			// create and initialize the ORB
		ORB orb = ORB.init(args, props);
		// get reference to rootpoa & activate the POAManager
		POA rootpoa = (POA) orb.resolve_initial_references("RootPOA");
		rootpoa.the_POAManager().activate();
		// create servant and register it with the ORB

		stub3.setORB(orb);
		// get object reference from the servant
		org.omg.CORBA.Object ref = rootpoa.servant_to_reference(stub3);
		// and cast the reference to a CORBA reference
		Common_Inteface href = Common_IntefaceHelper.narrow(ref);

		// get the root naming context
		// NameService invokes the transient name service
		org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		// Use NamingContextExt, which is part of the
		// Interoperable Naming Service (INS) specification.
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		// bind the Object Reference in Naming
		String name = "TOR";
		NameComponent path[] = ncRef.to_name(name);
		ncRef.rebind(path, href);
		System.out.println("Montreal Serevr ready and listening ...  ...");
		// wait for invocations from clients
		orb.run();


		
		
	}

}
