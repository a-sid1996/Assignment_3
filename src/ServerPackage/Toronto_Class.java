package ServerPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.regex.Pattern;


/**
 * The Class Toronto_Class.
 */
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * The Class Toronto_Class.
 */
@WebService(endpointInterface = "_ServerPackage.Common_interface")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Toronto_Class implements Common_Inteface{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new toronto class.
	 *
	 * @throws Exception the exception
	 */
	public Toronto_Class() throws Exception {
		super();
		initialize();
	}

	/** The logger. */
	public static Logger logger = Logger.getLogger("Toronto Server");

	/** The fh. */
	public static FileHandler fh = null;

	/** The login info. */
	public static HashMap<String, String> login_Info = new HashMap<String, String>();

	/** The Toronto hashmap. */
	public static HashMap<String, HashMap<String, ArrayList<String>>> Toronto_hashmap = new HashMap<String, HashMap<String, ArrayList<String>>>();

	/** The Toronto conference hashmap. */
	public static HashMap<String, ArrayList<String>> Toronto_Conference_hashmap = new HashMap<String, ArrayList<String>>();

	/** The Toronto seminar hashmap. */
	public static HashMap<String, ArrayList<String>> Toronto_Seminar_hashmap = new HashMap<String, ArrayList<String>>();

	/** The Toronto trade show hashmap. */
	public static HashMap<String, ArrayList<String>> Toronto_TradeShow_hashmap = new HashMap<String, ArrayList<String>>();

	/** The client toronto info. */
	public static HashMap<String, HashMap<String, ArrayList<String>>> client_Toronto_info = new HashMap<String, HashMap<String, ArrayList<String>>>();

	/** The client toronto info sub. */
	public static HashMap<String, ArrayList<String>> client_Toronto_info_sub = new HashMap<String, ArrayList<String>>();

	/** The client toronto info sub 1. */
	public static HashMap<String, ArrayList<String>> client_Toronto_info_sub1 = new HashMap<String, ArrayList<String>>();

	/** The socket. */
	public static DatagramSocket socket = null;

	/** The socket multi. */
	public static DatagramSocket socket_multi = null;
	
	/** The socket multi 1. */
	public static DatagramSocket socket_multi_1 = null;

	/** The socket availability. */
	public static DatagramSocket socket_availability = null;
	
	/** The socket availability 1. */
	public static DatagramSocket socket_availability_1 = null;
	
	/** The flag 0. */
	int flag0 = 0;
	
	/** The flag 1. */
	int flag1 = 0;
	
	/** The flag 2. */
	int flag2 = 0;
	
	/** The flag 3. */
	int flag3 = 0;
	
	/** The flag 4. */
	int flag4 = 0;


	/**
	 * Initialize.
	 */
	public void initialize() {
		// Client #1
		Toronto_Server.logger.info("Initilize Implementations ");
/*
		ArrayList<String> conf = new ArrayList<String>();
		Collections.addAll(conf, "3", "TORE100519", "TORE200619", "OTWE100519");
		client_Toronto_info_sub.put("Conference", conf);

		ArrayList<String> sem = new ArrayList<String>();
		Collections.addAll(sem, "2", "TORE100519", "OTWA100519");
		client_Toronto_info_sub.put("Seminar", sem);

		ArrayList<String> trade = new ArrayList<String>();
		Collections.addAll(trade, "1", "OTWM100519");
		client_Toronto_info_sub.put("TradeShow", trade);
		client_Toronto_info.put("TORC1111", client_Toronto_info_sub);

		// Client #2
		ArrayList<String> conf1 = new ArrayList<String>();
		Collections.addAll(conf1, "4", "TORE070619", "TORM080619", "TORA040719", "OTWE090619");
		client_Toronto_info_sub1.put("Conference", conf1);

		ArrayList<String> sem1 = new ArrayList<String>();
		Collections.addAll(sem1, "3", "TORA060619", "TORE020719", "OTWM020719");
		client_Toronto_info_sub1.put("Seminar", sem1);

		ArrayList<String> tra1 = new ArrayList<String>();
		Collections.addAll(tra1, "1", "MTLE080719");
		client_Toronto_info_sub1.put("TradeShow", tra1);
		client_Toronto_info.put("TORC2222", client_Toronto_info_sub1);

		// Conference Map
		HashMap<String, ArrayList<String>> conference1 = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> seminar1 = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> trade1 = new HashMap<String, ArrayList<String>>();

		// details about booking capacity and booked events
		ArrayList<String> confData = new ArrayList<String>();
		ArrayList<String> semData = new ArrayList<String>();
		ArrayList<String> traData = new ArrayList<String>();

		Collections.addAll(confData, "60", "40");
		Collections.addAll(semData, "30", "10");
		Collections.addAll(traData, "40", "20");

		// June events
		conference1.put("TORE060619", confData);
		conference1.put("TORA080619", confData);
		conference1.put("TORE090619", confData);
		seminar1.put("TORM070619", semData);
		seminar1.put("TORA080619", semData);
		seminar1.put("TORE200619", semData);
		trade1.put("TORM070619", traData);
		trade1.put("TORA080619", traData);
		trade1.put("TORE090619", traData);

		// July events
		conference1.put("TORM020719", confData);
		conference1.put("TORA030719", confData);
		seminar1.put("TORA010719", semData);
		seminar1.put("TORM020719", semData);
		seminar1.put("TORE030719", semData);
		trade1.put("TORE010719", traData);
		trade1.put("TORA030719", traData);
		trade1.put("TORE040719", traData);

		// new events
		conference1.put("TORE100519", confData);
		seminar1.put("TORA100519", semData);
		trade1.put("TORE080719", traData);
		seminar1.put("TORA060619", semData);
		seminar1.put("TORE020719", semData);
		seminar1.put("TORE100519", semData);
		conference1.put("TORE200619", confData);
		conference1.put("TORE070619", confData);
		conference1.put("TORM080619", confData);
		conference1.put("TORA040719", confData);

		Toronto_hashmap.put("Conference", conference1);
		Toronto_hashmap.put("Seminar", seminar1);
		Toronto_hashmap.put("TradeShow", trade1);
*/
		HashMap<String, ArrayList<String>> conference1 = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> seminar1 = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> trade1 = new HashMap<String, ArrayList<String>>();

		ArrayList<String> conf = new ArrayList<String>();
		ArrayList<String> sem = new ArrayList<String>();
		ArrayList<String> trade = new ArrayList<String>();
		

		conf.add(String.valueOf(0));
		sem.add(String.valueOf(0));
		trade.add(String.valueOf(0));

/*
		Collections.addAll(conf, "2", "OTWE080619", "MTLA090619");
		Collections.addAll(sem, "1", "MTLE230719");
		Collections.addAll(trade, "1", "MTLA080619");
*/		
		client_Toronto_info_sub.put("TradeShow", trade);
		client_Toronto_info_sub.put("Conference", conf);
		client_Toronto_info_sub.put("Seminar", sem);
		client_Toronto_info.put("TORC1111", client_Toronto_info_sub);
		
		Toronto_hashmap.put("Conference", conference1);
		Toronto_hashmap.put("Seminar", seminar1);
		Toronto_hashmap.put("TradeShow", trade1);

		Toronto_Server.logger.info("Server is initilized.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Toronto_Server.Toronto_Interface#getBookingSchedule(java.lang.String)
	 */
	public String getBookingSchedule(String customerID) {
		ArrayList<String> buff = new ArrayList<String>();

		if (client_Toronto_info.containsKey(customerID)) {

			buff.add("Following is the booking schedule for the customer");
			if (client_Toronto_info.get(customerID).containsKey("Conference")) {
				if (!client_Toronto_info.get(customerID).get("Conference").isEmpty()) {
					buff.add("Conferences-");
					for (String det : client_Toronto_info.get(customerID).get("Conference")) {
						buff.add(det);
					}
				}
			}

			if (client_Toronto_info.get(customerID).containsKey("Seminar")) {
				if (!client_Toronto_info.get(customerID).get("Seminar").isEmpty()) {
					buff.add("Seminars-");
					for (String det : client_Toronto_info.get(customerID).get("Seminar")) {
						buff.add(det);
					}
				}
			}

			if (client_Toronto_info.get(customerID).containsKey("TradeShow")) {
				if (!client_Toronto_info.get(customerID).get("TradeShow").isEmpty()) {
					buff.add("Trade Shows-");
					for (String det : client_Toronto_info.get(customerID).get("TradeShow")) {
						buff.add(det);
					}
				}
			}
//			System.out.println(Toronto_hashmap);
//			System.out.println(client_Toronto_info);

		} else {
			buff.add("The customerID entered is not in our records.");
		}

		return buff.toString();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Toronto_Server.Toronto_Interface#bookEvent(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public String bookEvent(String customerID, String eventID, String eventType) {

		if (eventID.substring(0, 3).equalsIgnoreCase("OTW") || eventID.substring(0, 3).equalsIgnoreCase("MTL")) {
			HashMap<String, String> out_check = new HashMap<String, String>();

			for (int i = 1; i < client_Toronto_info.get(customerID).get("Conference").size(); i++) {

				if (Pattern.matches(".*MTL.*", client_Toronto_info.get(customerID).get("Conference").get(i))
						|| Pattern.matches(".*OTW.*", client_Toronto_info.get(customerID).get("Conference").get(i))) {

					if (out_check.containsKey(
							client_Toronto_info.get(customerID).get("Conference").get(i).substring(6, 8))) {
						int num = Integer.valueOf(out_check
								.get(client_Toronto_info.get(customerID).get("Conference").get(i).substring(6, 8)));
						num++;
						out_check.put(client_Toronto_info.get(customerID).get("Conference").get(i).substring(6, 8),
								String.valueOf(num));
					} else {
						out_check.put(client_Toronto_info.get(customerID).get("Conference").get(i).substring(6, 8),
								String.valueOf(1));
					}

				}

			}

			for (int i = 1; i < client_Toronto_info.get(customerID).get("Seminar").size(); i++) {

				if (Pattern.matches(".*MTL.*", client_Toronto_info.get(customerID).get("Seminar").get(i))
						|| Pattern.matches(".*OTW.*", client_Toronto_info.get(customerID).get("Seminar").get(i))) {

					if (out_check
							.containsKey(client_Toronto_info.get(customerID).get("Seminar").get(i).substring(6, 8))) {
						int num = Integer.valueOf(out_check
								.get(client_Toronto_info.get(customerID).get("Seminar").get(i).substring(6, 8)));
						num++;
						out_check.put(client_Toronto_info.get(customerID).get("Seminar").get(i).substring(6, 8),
								String.valueOf(num));
					} else {
						out_check.put(client_Toronto_info.get(customerID).get("Seminar").get(i).substring(6, 8),
								String.valueOf(1));
					}

				}

			}

			for (int i = 1; i < client_Toronto_info.get(customerID).get("TradeShow").size(); i++) {

				if (Pattern.matches(".*MTL.*", client_Toronto_info.get(customerID).get("TradeShow").get(i))
						|| Pattern.matches(".*OTW.*", client_Toronto_info.get(customerID).get("TradeShow").get(i))) {

					if (out_check
							.containsKey(client_Toronto_info.get(customerID).get("TradeShow").get(i).substring(6, 8))) {
						int num = Integer.valueOf(out_check
								.get(client_Toronto_info.get(customerID).get("TradeShow").get(i).substring(6, 8)));
						num++;
						out_check.put(client_Toronto_info.get(customerID).get("TradeShow").get(i).substring(6, 8),
								String.valueOf(num));
					} else {
						out_check.put(client_Toronto_info.get(customerID).get("TradeShow").get(i).substring(6, 8),
								String.valueOf(1));
					}

				}

			}

			if (out_check.containsKey(eventID.substring(6, 8))) {
				if (Integer.valueOf(out_check.get(eventID.substring(6, 8))) > 2) {
					return "User already registered for 3 events outside their city in " + eventID.substring(6, 8)
						+ " month.";
				}
			}
			
//			System.out.println(out_check);
			
			if (!client_Toronto_info.containsKey(customerID)) {
				return "customerID entered is invalid.";
			} else {
				if (!client_Toronto_info.get(customerID).containsKey(eventType)) {
					return "eventType entered is invalid.";
				} else if (client_Toronto_info.get(customerID).get(eventType).contains(eventID)) {
					return "customer already registered for this event.";
				} else {

					if (eventID.substring(0, 3).equalsIgnoreCase("OTW")) {

						String response = null;
						try {
							response = check_with(customerID, eventType, eventID, "check", "OTW");

						} catch (Exception e) {
							e.printStackTrace();
						}

						if (response.equals("Y")) {

							int n = Integer.valueOf(client_Toronto_info.get(customerID).get(eventType).get(0));
							n++;
							client_Toronto_info.get(customerID).get(eventType).set(0, String.valueOf(n));
							client_Toronto_info.get(customerID).get(eventType).add(eventID);
//							System.out.println(client_Toronto_info);
							return "Event booked successfully.";
						} else {
							return response;
						}

					} else if (eventID.substring(0, 3).equalsIgnoreCase("MTL")) {

						String response = null;
						try {
							response = check_with(customerID, eventType, eventID, "check", "MTL");

						} catch (Exception e) {
							e.printStackTrace();
						}

						if (response.equals("Y")) {

							int n = Integer.valueOf(client_Toronto_info.get(customerID).get(eventType).get(0));
							n++;
							client_Toronto_info.get(customerID).get(eventType).set(0, String.valueOf(n));
							client_Toronto_info.get(customerID).get(eventType).add(eventID);
//							System.out.println(client_Toronto_info);
							return "Event booked successfully.";
						} else {
							return response;
						}

					}

				}
			}			
//			return "HERE";

		} else if (eventID.substring(0, 3).equalsIgnoreCase("TOR")) {
			if (Toronto_hashmap.get(eventType).containsKey(eventID)) {
				if (Integer.parseInt(Toronto_hashmap.get(eventType).get(eventID).get(0)) > Integer
						.parseInt(Toronto_hashmap.get(eventType).get(eventID).get(1))) {

					if (client_Toronto_info.containsKey(customerID)) {

						if (client_Toronto_info.get(customerID).get(eventType).contains(eventID)) {
							return "Customer is already booked for this event";
						}

						int val = Integer.parseInt(client_Toronto_info.get(customerID).get(eventType).get(0)) + 1;
						client_Toronto_info.get(customerID).get(eventType).remove(0);
						client_Toronto_info.get(customerID).get(eventType).add(0, Integer.toString(val));
						client_Toronto_info.get(customerID).get(eventType).add(eventID);

					} else {
						return "The customerID entered is not valid.";
					}

					int val = Integer.parseInt(Toronto_hashmap.get(eventType).get(eventID).get(1)) + 1;
					Toronto_hashmap.get(eventType).get(eventID).remove(1);
					Toronto_hashmap.get(eventType).get(eventID).add(1, Integer.toString(val));
					return "Event booked successfully.";

				} else {
					return "Sorry the " + eventType + " with " + eventID + " is full.";
				}
			} else {
				return "There is no " + eventType + " corresponding to the eventID you entered.";
			}
		}
		return "Entered information is invalid.";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Toronto_Server.Toronto_Interface#cancelEvent(java.lang.String,
	 * java.lang.String)
	 */
	public String cancelEvent(String customerID, String eventID, String eventType) {
		String resp = null;
		if( eventID.length() == 10 ) {
			if (eventID.substring(0, 3).equalsIgnoreCase("MTL") || eventID.substring(0, 3).equalsIgnoreCase("OTW")) {

				if (!client_Toronto_info.containsKey(customerID)) {
					resp = "customerID entered is not valid.";
				} else {
					if (!client_Toronto_info.get(customerID).containsKey(eventType)) {
						resp = "EventType entered is invalid.";
					} else {
						if (!client_Toronto_info.get(customerID).get(eventType).contains(eventID)) {
							resp = "Customer is not registered in event corresponding with entered eventID and evenType.";
						} else {

							String response = null;

							if (eventID.substring(0, 3).equalsIgnoreCase("MTL")) {

								try {
									response = check_with(customerID, eventType, eventID, "cancel", "MTL");
								} catch (Exception e) {
									e.printStackTrace();
								}

								if (response.equalsIgnoreCase("N")) {

									client_Toronto_info.get(customerID).get(eventType).remove(eventID);
									int n = Integer.valueOf(client_Toronto_info.get(customerID).get(eventType).get(0)) - 1;
									client_Toronto_info.get(customerID).get(eventType).set(0, String.valueOf(n));

//									System.out.println(Toronto_hashmap);
//									System.out.println(client_Toronto_info);
									return "Customer has been removed from this event.";
								} else {
									return response;
								}

							} else if (eventID.substring(0, 3).equalsIgnoreCase("OTW")) {

								try {
									response = check_with(customerID, eventType, eventID, "cancel", "OTW");
								} catch (Exception e) {
									e.printStackTrace();
								}

								if (response.equalsIgnoreCase("N")) {

									client_Toronto_info.get(customerID).get(eventType).remove(eventID);
									int n = Integer.valueOf(client_Toronto_info.get(customerID).get(eventType).get(0)) - 1;
									client_Toronto_info.get(customerID).get(eventType).set(0, String.valueOf(n));

//									System.out.println(Toronto_hashmap);
//									System.out.println(client_Toronto_info);
									return "Customer has been removed from this event.";
								} else {
									return response;
								}

							}

						}
					}
				}

			} else if (eventID.substring(0, 3).equalsIgnoreCase("TOR")) {

				if (!client_Toronto_info.containsKey(customerID)) {
					resp = "customerID entered is not valid.";
				} else {
					if (!client_Toronto_info.get(customerID).containsKey(eventType)) {
						resp = "EventType entered is invalid.";
					} else {
						if (!client_Toronto_info.get(customerID).get(eventType).contains(eventID)) {
							resp = "Customer is not registered in event corresponding with entered eventID and eventType.";
						} else {

							if (!Toronto_hashmap.get(eventType).containsKey(eventID)) {
								resp = "No event with entered eventID entered found.";
							} else {

								int a = Integer.valueOf(Toronto_hashmap.get(eventType).get(eventID).get(1)) + 1;
								Toronto_hashmap.get(eventType).get(eventID).set(1, String.valueOf(a));
								client_Toronto_info.get(customerID).get(eventType).remove(eventID);
								int n = Integer.valueOf(client_Toronto_info.get(customerID).get(eventType).get(0)) - 1;
								client_Toronto_info.get(customerID).get(eventType).set(0, String.valueOf(n));

//								System.out.println(Toronto_hashmap);
//								System.out.println(client_Toronto_info);
								resp = "Customer has been removed from this event.";

							}
						}
					}
				}

			} else {
				resp = "Entered eventID is invalid.";
			}
			return resp;
		} else {
			return "Entered eventID is invalid.";
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Toronto_Server.Toronto_Interface#addEvent(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public String addEvent(String eventID, String eventType, String bookingCapacity) {

		String resp = null;
//		System.out.println(Toronto_hashmap);

		if( eventType.equalsIgnoreCase("Seminar") || eventType.equalsIgnoreCase("Conference")
				|| eventType.equalsIgnoreCase("TradeShow") ) {
			if (eventID.substring(0, 3).equalsIgnoreCase("OTW") || eventID.substring(0, 3).equalsIgnoreCase("MTL")) {
				resp = "You cannot add this event since it's not in your city.";
			} else if (eventID.substring(0, 3).equalsIgnoreCase("TOR")) {
				if( eventID.length() != 10) {
					resp = "Entered eventID is invalid.";
				} else {
					
					if( Toronto_hashmap.get(eventType).containsKey(eventID) ) {
						int val = Integer.valueOf( Toronto_hashmap.get(eventType).get(eventID).get(0) ) + 1;
						Toronto_hashmap.get(eventType).get(eventID).remove(0);
						Toronto_hashmap.get(eventType).get(eventID).add(0, String.valueOf(val));
						resp = "Event was already added so booking capacity updated.";
					} else {
						ArrayList<String> temp = new ArrayList<String>();
						Collections.addAll(temp, bookingCapacity, String.valueOf(0));
						Toronto_hashmap.get(eventType).put(eventID, temp);
						resp = "Event successfully added.";
					}
					
				}
			}

		} else {
			resp = "Entered event type is invalid.";
		}

		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Toronto_Server.Toronto_Interface#removeEvent(java.lang.String,
	 * java.lang.String)
	 */
	public String removeEvent(String eventID, String eventType) {

		if( eventID.substring(0, 3).equalsIgnoreCase("TOR") ) {
			if (Toronto_hashmap.containsKey(eventType)) {
				if (Toronto_hashmap.get(eventType).containsKey(eventID)) {

					for (Map.Entry<String, HashMap<String, ArrayList<String>>> entry : client_Toronto_info.entrySet()) {
						if (entry.getValue().containsKey(eventType)) {

							if (entry.getValue().get(eventType).contains(eventID)) {

								entry.getValue().get(eventType).remove(eventID);
								int n = Integer.valueOf(entry.getValue().get(eventType).get(0)) - 1;
								entry.getValue().get(eventType).set(0, String.valueOf(n));
							}

						}
					}
					Toronto_hashmap.get(eventType).remove(eventID);
					check_with_otw(eventType, eventID);
					check_with_mtl(eventType, eventID);

					return "The event was successfully removed.";
				} else {
					return "The event with eventID you wanted to remove does not exist.";
				}

			} else {
				return "The eventType you entered is invalid.";
			}

		} else {
			if( eventID.substring(0, 3).equalsIgnoreCase("MTL") || eventID.substring(0, 3).equalsIgnoreCase("OTW") ) {
				return "You cannot remove event from this city.";
			} else {
				return "Invalid eventID";
			}
		}

	}

	/**
	 * Remote remove event.
	 *
	 * @param data    the data
	 * @param message the message
	 */
	public void remote_removeEvent(String data, DatagramPacket message) {

		String[] info = data.split("\\s");

		for (Map.Entry<String, HashMap<String, ArrayList<String>>> entry : client_Toronto_info.entrySet()) {

			if (entry.getValue().containsKey(info[0])) {

				if (entry.getValue().get(info[0]).contains(info[1])) {

					entry.getValue().get(info[0]).remove(info[1]);
					int n = Integer.valueOf(entry.getValue().get(info[0]).get(0)) - 1;
					entry.getValue().get(info[0]).set(0, String.valueOf(n));
				}

			}
		}
		
		String resp = null;
		resp = "done";
		
		byte[] resp_other = null;
		DatagramSocket socket_to_other = null;
		
		try {
			
			resp_other = resp.getBytes();
			socket_to_other = new DatagramSocket();
			
			InetAddress host = InetAddress.getByName("localhost");
			int port = message.getPort();
			
			DatagramPacket outgoing = new DatagramPacket( resp_other, resp.length(), host, port );
			socket_to_other.send(outgoing);
			
		} catch( SocketException e ) {
			e.printStackTrace();
		} catch( IOException e ) {
			e.printStackTrace();
		} finally{
			if( socket_to_other != null ) {
				socket_to_other.close();
			}
		}


	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Toronto_Server.Toronto_Interface#listEventAvailability(java.lang.String)
	 */
	public String listEventAvailability(String eventType) {

		ArrayList<String> buff = new ArrayList<String>();
		if (Toronto_hashmap.containsKey(eventType)) {
			if (!Toronto_hashmap.get(eventType).isEmpty()) {
				for (HashMap.Entry<String, ArrayList<String>> entry : Toronto_hashmap.get(eventType).entrySet()) {
					String id = entry.getKey() + " ";
					id = id + String.valueOf( Integer.parseInt(entry.getValue().get(0)) - Integer.parseInt(entry.getValue().get(1)) );
					buff.add(id);
				}
			} else {
//				buff.add("There are no " + eventType + "s scheduled.");
//				buff.add("Invalid");
			}
		}

		else {
//			buff.add("There are no " + eventType + "s scheduled.");
//			buff.add("Invalid");
		}

		String temp = new String();
		String temp1 = new String();
		try {
			temp = availability(eventType,1);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			temp1 = availability(eventType,2);
		} catch(Exception e) {
			e.printStackTrace();
		}

		if(!temp.equalsIgnoreCase("Invalid")) {
			String[] info = temp.split(",");
			for(int i=0; i<info.length; i++) {
				buff.add(info[i]);
			}
		}
		
		if(!temp1.equalsIgnoreCase("Invalid")) {
			String[] info1 = temp1.split(",");
			for(int i=0; i<info1.length; i++) {
				buff.add(info1[i]);
			}
		}		

		return buff.toString();

	}

	/**
	 * Remote list.
	 *
	 * @param eventType the event type
	 * @param message   the message
	 */
	public void remote_list(String eventType, DatagramPacket message) {

		ArrayList<String> a = new ArrayList<String>();
		String resp = null;
		if (!Toronto_hashmap.containsKey(eventType)) {
			resp = "Invalid";
		} else {
			for (Map.Entry<String, ArrayList<String>> entry : Toronto_hashmap.get(eventType).entrySet()) {

//				if (Integer.valueOf(entry.getValue().get(0)) > Integer.valueOf(entry.getValue().get(1))) {
					String id = entry.getKey() + " ";
					id = id + String.valueOf( Integer.parseInt(entry.getValue().get(0)) - Integer.parseInt(entry.getValue().get(1)) )+",";
					a.add(id);
//				}

			}
		}
				
		for( int i = 0; i<a.size(); i++ ) {
			if( resp != null ) {
				resp = resp + a.get(i);
			} else {
				resp = a.get(i);
			}
		}
		if(resp == null) {
			resp = "Invalid";
		}
		byte[] resp_other = null;
		DatagramSocket socket_to_other = null;

		try {

			resp_other = resp.getBytes();
			socket_to_other = new DatagramSocket();

			InetAddress host = InetAddress.getByName("localhost");
			int port = message.getPort();

			DatagramPacket outgoing = new DatagramPacket(resp_other, resp.length(), host, port);
			socket_to_other.send(outgoing);

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket_to_other != null) {
				socket_to_other.close();
			}
		}

	}

	/**
	 * Cancel booking.
	 *
	 * @param data    the data
	 * @param message the message
	 */
	public void cancelBooking(String data, DatagramPacket message) {

		String[] info = data.split("\\s");

		String resp = null;

		if (!Toronto_hashmap.containsKey(info[1])) {
			resp = "EventType entered is invalid.";
		} else {
			if (!Toronto_hashmap.get(info[1]).containsKey(info[2])) {
				resp = "Event with eventID " + info[2] + " does not exist.";
			} else {
				int b = Integer.valueOf(Toronto_hashmap.get(info[1]).get(info[2]).get(1)) - 1;
				Toronto_hashmap.get(info[1]).get(info[2]).remove(1);
				Toronto_hashmap.get(info[1]).get(info[2]).add(1, String.valueOf(b));
				resp = "N";
//				System.out.println("here1");
			}
		}

		byte[] resp_other = null;
		DatagramSocket socket_to_other = null;

		try {

			resp_other = resp.getBytes();
			socket_to_other = new DatagramSocket();

			InetAddress host = InetAddress.getByName("localhost");
			int port = message.getPort();

			DatagramPacket outgoing = new DatagramPacket(resp_other, resp.length(), host, port);
			socket_to_other.send(outgoing);

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket_to_other != null) {
				socket_to_other.close();
			}
		}

	}

	/**
	 * Update booking.
	 *
	 * @param data    the data
	 * @param message the message
	 */
	public void updateBooking(String data, DatagramPacket message) {

		String[] info = data.split("\\s");

		String resp = null;
		if (Toronto_hashmap.containsKey(info[1])) {
			if (Toronto_hashmap.get(info[1]).containsKey(info[2])) {
				if (Integer.valueOf(Toronto_hashmap.get(info[1]).get(info[2]).get(0)) > Integer
						.valueOf(Toronto_hashmap.get(info[1]).get(info[2]).get(1))) {
					int n = Integer.valueOf(Toronto_hashmap.get(info[1]).get(info[2]).get(1));
					Toronto_hashmap.get(info[1]).get(info[2]).set(1, String.valueOf(n + 1));
					resp = "Y";

				} else {
					resp = "Event you're trying to book is full.";
				}
			} else {
				resp = "Event with eventID " + info[2] + " does not exist.";
			}
		} else {
			resp = "EventType entered is invalid";
		}

		// return resp;

		byte[] resp_other = null;
		DatagramSocket socket_to_other = null;

		try {

			resp_other = resp.getBytes();
			socket_to_other = new DatagramSocket();

			InetAddress host = InetAddress.getByName("localhost");
			int port = message.getPort();

			DatagramPacket outgoing = new DatagramPacket(resp_other, resp.length(), host, port);
			socket_to_other.send(outgoing);

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket_to_other != null) {
				socket_to_other.close();
			}
		}

	}

	/**
	 * Check with.
	 *
	 * @param customerID the customer ID
	 * @param eventType  the event type
	 * @param eventID    the event ID
	 * @param action     the action
	 * @param city       the city
	 * @return the string
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException   the execution exception
	 */
	public String check_with(String customerID, String eventType, String eventID, String action, String city)
			throws InterruptedException, ExecutionException {

		ExecutorService exec = Executors.newSingleThreadExecutor();
		Callable<String> call = new Callable<String>() {

			public String call() {
				String data = null;
				try {

					if( flag0 <= 0 ) {
						socket = new DatagramSocket(6300);
						flag0++;
					}
					byte[] buff = new byte[1024];
					DatagramPacket req = null;

					while (true) {
						req = new DatagramPacket(buff, buff.length);
						socket.receive(req);

						data = new String(req.getData(), 0, req.getLength());

						if (data != null) {
							break;
						}

					}

				} catch (SocketException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (socket != null) {
//						socket.close();
					}
				}

				return data;

			}

		};
		Future<String> result = exec.submit(call);

		byte[] tor_req = null;
		String tor_request = customerID + " " + eventType + " " + eventID + " " + action + " " + "TOR";

		try {

			tor_req = tor_request.getBytes();
			InetAddress host_tor = InetAddress.getByName("localhost");
			int port = 0;
			if (eventID.substring(0, 3).equalsIgnoreCase("OTW")) {
				port = 6001;
			} else if (eventID.substring(0, 3).equalsIgnoreCase("MTL")) {
				port = 6000;
			}

			DatagramPacket tor_query = new DatagramPacket(tor_req, tor_request.length(), host_tor, port);
			socket.send(tor_query);

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		exec.shutdown();
		return result.get();
	}

	/**
	 * Check with mtl.
	 *
	 * @param eventType the event type
	 * @param eventID   the event ID
	 * @return the string
	 */
	public void check_with_mtl(String eventType, String eventID) {

		byte[] tor_req = null;
		String tor_request = eventType + " " + eventID;

		try {
			if( flag1 <= 0 ) {
				socket_multi = new DatagramSocket(6301);
				flag1++;
			}

			tor_req = tor_request.getBytes();
			InetAddress host_tor = InetAddress.getByName("localhost");
			int port = 6000;

			DatagramPacket tor_query = new DatagramPacket(tor_req, tor_request.length(), host_tor, port);
			socket_multi.send(tor_query);

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check with otw.
	 *
	 * @param eventType the event type
	 * @param eventID   the event ID
	 * @return the string
	 */
	public void check_with_otw(String eventType, String eventID) {

		byte[] tor_req = null;
		String tor_request = eventType + " " + eventID;

		try {
			if( flag2 <= 0 ) {
				socket_multi_1 = new DatagramSocket(6302);
				flag2++;
			}

			tor_req = tor_request.getBytes();
			InetAddress host_tor = InetAddress.getByName("localhost");
			int port = 6001;

			DatagramPacket tor_query = new DatagramPacket(tor_req, tor_request.length(), host_tor, port);
			socket_multi_1.send(tor_query);

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Availability mtl.
	 *
	 * @param eventType the event type
	 * @param i         the i
	 * @return the string
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException   the execution exception
	 */
	public String availability(String eventType, int i) throws InterruptedException, ExecutionException {

		ExecutorService exec = Executors.newSingleThreadExecutor();
		Callable<String> call = new Callable<String>() {

			public String call() {
				String data = null;
				try {

					if( flag3 <= 0 ) {
						socket_availability = new DatagramSocket(6303);						
						flag3++;
					}
					byte[] buff = new byte[1024];
					DatagramPacket req = null;

					while (true) {
						req = new DatagramPacket(buff, buff.length);
						socket_availability.receive(req);

						data = new String(req.getData(), 0, req.getLength());

						if (data != null) {
							break;
						}

					}

				} catch (SocketException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (socket_availability != null) {
//						socket_availability.close();
					}
				}

				return data;

			}

		};
		Future<String> result = exec.submit(call);

		byte[] tor_req = null;
		String tor_request = eventType;

		try {

			tor_req = tor_request.getBytes();
			InetAddress host_tor = InetAddress.getByName("localhost");
			int port = 6000;
			if( i ==1 ) {
				port = 6001;
			} else if( i == 2 ) {
				port = 6000;
			}

			DatagramPacket tor_query = new DatagramPacket(tor_req, tor_request.length(), host_tor, port);
			socket_availability.send(tor_query);

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		exec.shutdown();
		return result.get();
	}
	
	/* (non-Javadoc)
	 * @see _ServerPackage.Common_IntefaceOperations#swapEvent(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String swapEvent(String customerID, String oldEventType, String oldEventID, String newEventType, String newEventID) {

		if( !( oldEventID.substring(0, 3).equalsIgnoreCase("OTW") || oldEventID.substring(0, 3).equalsIgnoreCase("TOR") || oldEventID.substring(0, 3).equalsIgnoreCase("MTL") || newEventID.substring(0, 3).equalsIgnoreCase("OTW") || newEventID.substring(0, 3).equalsIgnoreCase("MTL") || newEventID.substring(0, 3).equalsIgnoreCase("TOR") ) ) {
			return "Entered eventID is invalid.";			
		}
		
		if( !( oldEventType.equalsIgnoreCase("TradeShow") || oldEventType.equalsIgnoreCase("Conference") || oldEventType.equalsIgnoreCase("Seminar") || newEventType.equalsIgnoreCase("TradeShow") || newEventType.equalsIgnoreCase("Conference") || newEventType.equalsIgnoreCase("Seminar") ) ) {
			return "Entered eventType is invalid";
		}
		
		if( !( client_Toronto_info.containsKey(customerID) ) ) {
			return "customerID entered is invalid";
		}
		
		if( !( client_Toronto_info.get(customerID).get(oldEventType).contains(oldEventID) ) ) {
			return "customer is not registered in entered event";
		}
		
		String rep = bookEvent(customerID, newEventID, newEventType);
		if(rep.equalsIgnoreCase("Event booked successfully.")) {
			cancelEvent(customerID, oldEventID, oldEventType);
			return "Events swapped successfully";
		} else {
			String res = cancelEvent(customerID, oldEventID, oldEventType);
			if(res.equalsIgnoreCase("Customer has been removed from this event.")) {
				String res1 = bookEvent(customerID, newEventID, newEventType);
				if(res1.equalsIgnoreCase("Event booked successfully.")) {
					return "Events swapped successfully";
				} else {
					bookEvent(customerID, oldEventID, oldEventType);
					return "Events were not swapped since "+res1;
				}
			} else {
				return res;
			}
		}

	}	
	
/*	public void remote_check(String data, DatagramPacket message) {
		String[] info = data.split("\\s");
		String resp = null;

		
		if( !Toronto_hashmap.containsKey(info[0]) ) {
			resp = "EventType entered is invalid";
		} else {
			if( !Toronto_hashmap.get(info[0]).containsKey(info[1]) ) {
				resp = "No such event scheduled.";
			} else {				
				int a = Integer.valueOf(Toronto_hashmap.get(info[1]).get(info[0]).get(1)) + 1;
				Toronto_hashmap.get(info[1]).get(info[0]).remove(1);
				Toronto_hashmap.get(info[1]).get(info[0]).add(1, String.valueOf(a));
				resp = "good";
				
			}
		}
		
		
		byte[] resp_other = null;
		DatagramSocket socket_to_other = null;
		
		try {			
			resp_other = resp.getBytes();
			socket_to_other = new DatagramSocket();
			
			InetAddress host = InetAddress.getByName("localhost");
			int port = message.getPort();
			
			DatagramPacket outgoing = new DatagramPacket( resp_other, resp.length(), host, port );
			socket_to_other.send(outgoing);
			
		} catch( SocketException e ) {
			e.printStackTrace();
		} catch( IOException e ) {
			e.printStackTrace();
		} finally{
			if( socket_to_other != null ) {
				socket_to_other.close();
			}
		}

	}


*/

}
