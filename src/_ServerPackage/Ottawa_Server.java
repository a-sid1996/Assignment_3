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
 * The Class Ottawa_Server.
 */
public class Ottawa_Server {

	/** The logger. */
	public static Logger logger = Logger.getLogger("OttawaServer");

	/** The fh. */
	public static FileHandler fh = null;

	/**
	 * The main method.
	 *
	 * @throws Exception the exception
	 */

	/*
	 * public static void serverRecieve() throws Exception {
	 * 
	 * DatagramSocket aSocketServerOttawa = null;
	 * 
	 * try { Ottawa_Class ottawa_obj = new Ottawa_Class(); aSocketServerOttawa = new
	 * DatagramSocket(8085); byte[] buffer = new byte[1024];// to stored the
	 * received data from the client.
	 * 
	 * System.out.println("Server Started............");
	 * 
	 * while (true) {// non-terminating loop as the server is always in listening
	 * mode. DatagramPacket request = new DatagramPacket(buffer, buffer.length); //
	 * Server waits for the request to // come
	 * aSocketServerOttawa.receive(request);// request received
	 * 
	 * String message; message = new String(request.getData(), 0,
	 * request.getLength()); String[] initial = message.split("\\s");
	 * 
	 * if (initial[1].equals("MTL")) {
	 * 
	 * String messageSent = ottawa_obj.replySeatAvailability(message);
	 * 
	 * byte[] messageMontreal = null; DatagramSocket aSocketServerMontreal = null;
	 * try { messageMontreal = messageSent.getBytes(); aSocketServerMontreal = new
	 * DatagramSocket();
	 * 
	 * InetAddress hostOttawa = InetAddress.getLocalHost(); int serverPortOttawa =
	 * request.getPort();
	 * 
	 * DatagramPacket requestOttawa = new DatagramPacket(messageMontreal,
	 * messageSent.length(), hostOttawa, serverPortOttawa);
	 * aSocketServerMontreal.send(requestOttawa); } catch (SocketException e) {
	 * System.out.println("Socket Exception: " + e.getMessage()); } catch
	 * (IOException e) { System.out.println("IO Exception: " + e.getMessage()); }
	 * finally { if (aSocketServerMontreal != null) { aSocketServerMontreal.close();
	 * } }
	 * 
	 * } else if (initial[1].equals("TOR")) {
	 * 
	 * String messageSent = ottawa_obj.replySeatAvailability(message);
	 * 
	 * byte[] messageToronto = null; DatagramSocket aSocketServerToronto = null; try
	 * { messageToronto = messageSent.getBytes(); aSocketServerToronto = new
	 * DatagramSocket();
	 * 
	 * InetAddress hostOttawa = InetAddress.getLocalHost(); int serverPortOttawa =
	 * request.getPort();
	 * 
	 * DatagramPacket requestMontreal = new DatagramPacket(messageToronto,
	 * messageSent.length(), hostOttawa, serverPortOttawa);
	 * aSocketServerToronto.send(requestMontreal);
	 * 
	 * } catch (SocketException e) { System.out.println("Socket Exception: " +
	 * e.getMessage()); } catch (IOException e) {
	 * System.out.println("IO Exception: " + e.getMessage()); } finally { if
	 * (aSocketServerToronto != null) { aSocketServerToronto.close(); } }
	 * 
	 * } else if (initial.length >= 5) { System.out.println(message); }
	 * 
	 * // else { // // }
	 * 
	 * } } catch (SocketException e) { System.out.println("Socket: " +
	 * e.getMessage()); } catch (IOException e) { System.out.println("IO: " +
	 * e.getMessage()); } finally { if (aSocketServerOttawa != null)
	 * aSocketServerOttawa.close(); }
	 * 
	 * }
	 */

	public static void serverReceive() throws Exception {
		DatagramSocket socket = null;
		try {
			byte[] data = new byte[1024];
			Ottawa_Class act = new Ottawa_Class();
			socket = new DatagramSocket(6001);

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
				serverReceive();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		};

		new Thread(taskUDP).start();

		Ottawa_Class stub2 = new Ottawa_Class();
	
		System.out.println(" Ottawa server has been started");

		try {
			// This block configure the logger with handler and formatter
			fh = new FileHandler(Paths.get(".").toAbsolutePath().normalize().toString() + "\\log\\OttawaServer.log",
					true);

			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			// to remove the console handler *******
			logger.setUseParentHandlers(false);
			// the following statement is used to log any messages
			logger.info("Inside the Ottawa Server");

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	//	System.out.println("Application Terminating ...");
		// properties value to help the ORB
		Properties props = new Properties();
		props.put("org.omg.CORBA.ORBInitialPort", "1050");
		props.put("org.omg.CORBA.ORBInitialHost", "localhost");
			ORB orb = ORB.init(args, props);
		// ORB orb = ORB.init(args, null);
		// get reference to rootpoa & activate the POAManager
		POA rootpoa = (POA) orb.resolve_initial_references("RootPOA");
		rootpoa.the_POAManager().activate();
		// create servant and register it with the ORB

		stub2.setORB(orb);
		// get object reference from the servant
		org.omg.CORBA.Object ref = rootpoa.servant_to_reference(stub2);
		// and cast the reference to a CORBA reference
		Common_Inteface href = Common_IntefaceHelper.narrow(ref);

		// get the root naming context
		// NameService invokes the transient name service
		org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		// Use NamingContextExt, which is part of the
		// Interoperable Naming Service (INS) specification.
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		// bind the Object Reference in Naming
		String name = "OTW";
		NameComponent path[] = ncRef.to_name(name);
		ncRef.rebind(path, href);
		System.out.println("Montreal Serevr ready and listening ...  ...");
		// wait for invocations from clients
		orb.run();


		
		
	}
}
