package com.web.service.impl;

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
import java.util.regex.Pattern;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.web.server.Montreal_Server;
import com.web.service.Common_Interface;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


/**
 * The Class Montreal_Class.
 */
@WebService(endpointInterface = "com.web.service.Common_Interface")

@SOAPBinding(style = SOAPBinding.Style.RPC)

public class Montreal_Class implements Common_Interface {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	
	/**
	 * Instantiates a new montreal class.
	 *
	 * @throws Exception the exception
	 */
	public Montreal_Class() throws Exception {
		super();
		initialize();
	}

	/** The login info. */
	//	public static HashMap<String, String> login_Info = new HashMap<String, String>();

	/** The Montreal hashmap. */
	public static HashMap<String, HashMap<String, ArrayList<String>>> Montreal_hashmap = new HashMap<String, HashMap<String, ArrayList<String>>>();

	/** The Montreal conference hashmap. */
	public static HashMap<String, ArrayList<String>> Montreal_Conference_hashmap = new HashMap<String, ArrayList<String>>();

	/** The Montreal seminar hashmap. */
	public static HashMap<String, ArrayList<String>> Montreal_Seminar_hashmap = new HashMap<String, ArrayList<String>>();

	/** The Montreal trade show hashmap. */
	public static HashMap<String, ArrayList<String>> Montreal_TradeShow_hashmap = new HashMap<String, ArrayList<String>>();

	/** The client montreal info. */
	public static HashMap<String, HashMap<String, ArrayList<String>>> client_Montreal_info = new HashMap<String, HashMap<String, ArrayList<String>>>();

	/** The client montreal info sub. */
	public static HashMap<String, ArrayList<String>> client_Montreal_info_sub = new HashMap<String, ArrayList<String>>();

	/** The client montreal info sub 1. */
	public static HashMap<String, ArrayList<String>> client_Montreal_info_sub1 = new HashMap<String, ArrayList<String>>();

	/** The socket. */
	public static DatagramSocket socket = null;

	/** The socket multi. */
	public static DatagramSocket socket_multi = null;

	/** The socket multi 1. */
	public static DatagramSocket socket_multi_1 = null;
	
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

		Montreal_Server.logger.info("Initilize Implementations ");
/*
		// Client #1
		ArrayList<String> conf = new ArrayList<String>();
		Collections.addAll(conf, "4", "MTLE100519", "MTLE200619", "OTWE100519", "TORE060619");
		client_Montreal_info_sub.put("Conference", conf);

		ArrayList<String> sem = new ArrayList<String>();
		Collections.addAll(sem, "2", "MTLE100519", "OTWA100519");
		client_Montreal_info_sub.put("Seminar", sem);

		ArrayList<String> trade = new ArrayList<String>();
		Collections.addAll(trade, "1", "OTWM100519");
		client_Montreal_info_sub.put("TradeShow", trade);
		client_Montreal_info.put("MTLC1111", client_Montreal_info_sub);

		// Client #2
		ArrayList<String> conf1 = new ArrayList<String>();
		Collections.addAll(conf1, "4", "MTLM080619", "MTLA010719", "MTLA040719", "TORE090619");
		client_Montreal_info_sub1.put("Conference", conf1);

		ArrayList<String> sem1 = new ArrayList<String>();
		Collections.addAll(sem1, "4", "MTLA060619", "MTLE020719", "OTWA070619", "TORM020719");
		client_Montreal_info_sub1.put("Seminar", sem1);

		ArrayList<String> tra1 = new ArrayList<String>();
		Collections.addAll(tra1, "1", "OTWE080619");
		client_Montreal_info_sub1.put("TradeShow", tra1);
		client_Montreal_info.put("MTLC2222", client_Montreal_info_sub1);

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
		conference1.put("MTLE070619", confData);
		conference1.put("MTLM080619", confData);
		conference1.put("MTLA090619", confData);
		seminar1.put("MTLA060619", semData);
		seminar1.put("MTLM080619", semData);
		trade1.put("MTLA060619", traData);
		trade1.put("MTLA090619", traData);

		// July events
		conference1.put("MTLA010719", confData);
		conference1.put("MTLM030719", confData);
		conference1.put("MTLA040719", confData);
		seminar1.put("MTLA010719", semData);
		seminar1.put("MTLE020719", semData);
		trade1.put("MTLA010719", traData);
		trade1.put("MTLA040719", traData);

		// new events
		conference1.put("MTLE100519", confData);
		conference1.put("MTLE200619", confData);
		seminar1.put("MTLE100519", semData);
		conference1.put("MTLE090619", confData);
		seminar1.put("MTLM020719", semData);
		trade1.put("MTLA080719", traData);
*/
		
		HashMap<String, ArrayList<String>> conference1 = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> seminar1 = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> trade1 = new HashMap<String, ArrayList<String>>();

		ArrayList<String> conf = new ArrayList<String>();
		ArrayList<String> sem = new ArrayList<String>();
		ArrayList<String> trade = new ArrayList<String>();
		
		conf.add(String.valueOf(0));
		sem.add(String.valueOf(0));
//		sem.add("1");
//		sem.add("MTLA090619");
//		Collections.addAll(sem, "1", "MTLA090619");
		trade.add(String.valueOf(0));
		
		client_Montreal_info_sub.put("TradeShow", trade);
		client_Montreal_info_sub.put("Conference", conf);
		client_Montreal_info_sub.put("Seminar", sem);
		client_Montreal_info.put("MTLC1111", client_Montreal_info_sub);

/*
		ArrayList<String> confData = new ArrayList<String>();
		ArrayList<String> semData = new ArrayList<String>();
		ArrayList<String> traData = new ArrayList<String>();

		Collections.addAll(confData, "2", "1");
		Collections.addAll(semData, "1", "1");
		Collections.addAll(traData, "2", "1");

		conference1.put("MTLA090619", confData);
		seminar1.put("MTLE230719", semData);
		trade1.put("MTLA080619", traData);
*/
		Montreal_hashmap.put("Conference", conference1);
		Montreal_hashmap.put("Seminar", seminar1);
		Montreal_hashmap.put("TradeShow", trade1);

		Montreal_Server.logger.info("Server is initilized.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Montreal_Server.Montreal_Interface#getBookingSchedule(java.lang.String)
	 */
	public String getBookingSchedule(String customerID) {

		ArrayList<String> buff = new ArrayList<String>();

		if (client_Montreal_info.containsKey(customerID)) {

			buff.add("Following is the booking schedule for the customer");
			if (client_Montreal_info.get(customerID).containsKey("Conference")) {
				if (!client_Montreal_info.get(customerID).get("Conference").isEmpty()) {
					buff.add("Conferences-");
					for (String det : client_Montreal_info.get(customerID).get("Conference")) {
						buff.add(det);
					}
				}
			}

			if (client_Montreal_info.get(customerID).containsKey("Seminar")) {
				if (!client_Montreal_info.get(customerID).get("Seminar").isEmpty()) {
					buff.add("Seminars-");
					for (String det : client_Montreal_info.get(customerID).get("Seminar")) {
						buff.add(det);
					}
				}
			}

			if (client_Montreal_info.get(customerID).containsKey("TradeShow")) {
				if (!client_Montreal_info.get(customerID).get("TradeShow").isEmpty()) {
					buff.add("Trade Shows-");
					for (String det : client_Montreal_info.get(customerID).get("TradeShow")) {
						buff.add(det);
					}
				}
			}

		} else {
			buff.add("The customerID entered is not in our records.");
		}
		
		return buff.toString().trim();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Montreal_Server.Montreal_Interface#bookEvent(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public String bookEvent(String customerID, String eventID, String eventType) {
System.out.println("IMHERE" +customerID+" "+ eventID+ " "+ eventType);
		if( client_Montreal_info.containsKey(customerID) ) {
			if (eventID.substring(0, 3).equalsIgnoreCase("TOR") || eventID.substring(0, 3).equalsIgnoreCase("OTW")) {

				HashMap<String, String> out_check = new HashMap<String, String>();

				for (int i = 1; i < client_Montreal_info.get(customerID).get("Conference").size(); i++) {

					if (Pattern.matches(".*TOR.*", client_Montreal_info.get(customerID).get("Conference").get(i))
							|| Pattern.matches(".*OTW.*", client_Montreal_info.get(customerID).get("Conference").get(i))) {

						if (out_check.containsKey(
								client_Montreal_info.get(customerID).get("Conference").get(i).substring(6, 8))) {
							int num = Integer.valueOf(out_check
									.get(client_Montreal_info.get(customerID).get("Conference").get(i).substring(6, 8)));
							num++;
							out_check.put(client_Montreal_info.get(customerID).get("Conference").get(i).substring(6, 8),
									String.valueOf(num));
						} else {
							out_check.put(client_Montreal_info.get(customerID).get("Conference").get(i).substring(6, 8),
									String.valueOf(1));
						}

					}

				}

				for (int i = 1; i < client_Montreal_info.get(customerID).get("Seminar").size(); i++) {

					if (Pattern.matches(".*TOR.*", client_Montreal_info.get(customerID).get("Seminar").get(i))
							|| Pattern.matches(".*OTW.*", client_Montreal_info.get(customerID).get("Seminar").get(i))) {

						if (out_check
								.containsKey(client_Montreal_info.get(customerID).get("Seminar").get(i).substring(6, 8))) {
							int num = Integer.valueOf(out_check
									.get(client_Montreal_info.get(customerID).get("Seminar").get(i).substring(6, 8)));
							num++;
							out_check.put(client_Montreal_info.get(customerID).get("Seminar").get(i).substring(6, 8),
									String.valueOf(num));
						} else {
							out_check.put(client_Montreal_info.get(customerID).get("Seminar").get(i).substring(6, 8),
									String.valueOf(1));
						}

					}

				}

				for (int i = 1; i < client_Montreal_info.get(customerID).get("TradeShow").size(); i++) {

					if (Pattern.matches(".*TOR.*", client_Montreal_info.get(customerID).get("TradeShow").get(i))
							|| Pattern.matches(".*OTW.*", client_Montreal_info.get(customerID).get("TradeShow").get(i))) {

						if (out_check.containsKey(
								client_Montreal_info.get(customerID).get("TradeShow").get(i).substring(6, 8))) {
							int num = Integer.valueOf(out_check
									.get(client_Montreal_info.get(customerID).get("TradeShow").get(i).substring(6, 8)));
							num++;
							out_check.put(client_Montreal_info.get(customerID).get("TradeShow").get(i).substring(6, 8),
									String.valueOf(num));
						} else {
							out_check.put(client_Montreal_info.get(customerID).get("TradeShow").get(i).substring(6, 8),
									String.valueOf(1));
						}

					}

				}

				if (out_check.containsKey(eventID.substring(6, 8))) {
					if (Integer.valueOf(out_check.get(eventID.substring(6, 8))) > 2) {
						return "User already registered for 3 events outside their city in " + eventID.substring(6, 8)
						+ " month.".trim();
					}
				}

				if (!client_Montreal_info.containsKey(customerID)) {
					return "customerID entered is invalid.".trim();
				} else {
					if (!client_Montreal_info.get(customerID).containsKey(eventType)) {
						return "eventType entered is invalid.".trim();
					} else if (client_Montreal_info.get(customerID).get(eventType).contains(eventID)) {
						return "customer already registered for this event.".trim();
					} else {

						if (eventID.substring(0, 3).equalsIgnoreCase("TOR")) {

							String response = null;
							try {
								response = check_with(customerID, eventType, eventID, "check", "TOR");

							} catch (Exception e) {
								e.printStackTrace();
							}
							//						System.out.println("resp "+response);
							if (response.equals("Y")) {

								int n = Integer.valueOf(client_Montreal_info.get(customerID).get(eventType).get(0));
								n++;
								client_Montreal_info.get(customerID).get(eventType).set(0, String.valueOf(n));
								client_Montreal_info.get(customerID).get(eventType).add(eventID);
								return "Event booked successfully.".trim();
							} else {
								return response.trim();
							}

						} else if (eventID.substring(0, 3).equalsIgnoreCase("OTW")) {

							String response = null;
							try {
								response = check_with(customerID, eventType, eventID, "check", "OTW");

							} catch (Exception e) {
								e.printStackTrace();
							}

							if (response.equals("Y")) {

								int n = Integer.valueOf(client_Montreal_info.get(customerID).get(eventType).get(0));
								n++;
								client_Montreal_info.get(customerID).get(eventType).set(0, String.valueOf(n));
								client_Montreal_info.get(customerID).get(eventType).add(eventID);
								return "Event booked successfully.".trim();
							} else {
								return response.trim();
							}

						}

					}
				}

//				return "HERE";

			} else if (eventID.substring(0, 3).equalsIgnoreCase("MTL")) {
				if (Montreal_hashmap.get(eventType.trim()).containsKey(eventID)) {
					if (Integer.valueOf(Montreal_hashmap.get(eventType).get(eventID).get(0)) > Integer
							.valueOf(Montreal_hashmap.get(eventType).get(eventID).get(1))) {

						if (client_Montreal_info.containsKey(customerID)) {

							if (client_Montreal_info.get(customerID).get(eventType).contains(eventID)) {
								return "Customer is already booked for this event".trim();
							}

							int val = Integer.parseInt(client_Montreal_info.get(customerID).get(eventType).get(0)) + 1;
							client_Montreal_info.get(customerID).get(eventType).remove(0);
							client_Montreal_info.get(customerID).get(eventType).add(0, Integer.toString(val));
							client_Montreal_info.get(customerID).get(eventType).add(eventID);

						} else {
							return "The customerID entered is not valid.".trim();
						}

						int val = Integer.parseInt(Montreal_hashmap.get(eventType).get(eventID).get(1)) + 1;
						Montreal_hashmap.get(eventType).get(eventID).remove(1);
						Montreal_hashmap.get(eventType).get(eventID).add(1, Integer.toString(val));
						return "Event booked successfully.".trim();

					} else {
						return "Sorry the " + eventType + " with " + eventID + " is full.".trim();
					}
				} else {
					return "There is no " + eventType + " corresponding to the eventID you entered.".trim();
				}
			}
			return "Entered information is invalid.".trim();

		} else {
			return "cutomerID entered is not valid.".trim();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Montreal_Server.Montreal_Interface#cancelEvent(java.lang.String,
	 * java.lang.String)
	 */
	public String cancelEvent(String customerID, String eventID, String eventType) {

		String resp = null;
		if( eventID.length() == 10 ) {
			if (eventID.substring(0, 3).equalsIgnoreCase("OTW") || eventID.substring(0, 3).equalsIgnoreCase("TOR")) {

				if (!client_Montreal_info.containsKey(customerID)) {
					resp = "customerID entered is not valid.".trim();
				} else {
					if (!client_Montreal_info.get(customerID).containsKey(eventType)) {
						resp = "EventType entered is invalid.".trim();
					} else {
						if (!client_Montreal_info.get(customerID).get(eventType).contains(eventID)) {
							resp = "Customer is not registered in event corresponding with entered eventID and evenType.".trim();
						} else {

							String response = null;

							if (eventID.substring(0, 3).equalsIgnoreCase("TOR")) {

								try {
									response = check_with(customerID, eventType, eventID, "cancel", "TOR");
								} catch (Exception e) {
									e.printStackTrace();
								}

								if (response.equalsIgnoreCase("N")) {

									client_Montreal_info.get(customerID).get(eventType).remove(eventID);
									int n = Integer.valueOf(client_Montreal_info.get(customerID).get(eventType).get(0)) - 1;
									client_Montreal_info.get(customerID).get(eventType).set(0, String.valueOf(n));

									return "Customer has been removed from this event.".trim();
								} else {
									return response.trim();
								}

							} else if (eventID.substring(0, 3).equalsIgnoreCase("OTW")) {

								try {
									response = check_with(customerID, eventType, eventID, "cancel", "OTW");
								} catch (Exception e) {
									e.printStackTrace();
								}

								if (response.equalsIgnoreCase("N")) {

									client_Montreal_info.get(customerID).get(eventType).remove(eventID);
									int n = Integer.valueOf(client_Montreal_info.get(customerID).get(eventType).get(0)) - 1;
									client_Montreal_info.get(customerID).get(eventType).set(0, String.valueOf(n));

									return "Customer has been removed from this event.".trim();
								} else {
									return response.trim();
								}

							}

						}
					}
				}

			} else if (eventID.substring(0, 3).equalsIgnoreCase("MTL")) {

				if (!client_Montreal_info.containsKey(customerID)) {
					resp = "customerID entered is not valid.".trim();
				} else {
					if (!client_Montreal_info.get(customerID).containsKey(eventType)) {
						resp = "EventType entered is invalid.".trim();
					} else {
						if (!client_Montreal_info.get(customerID).get(eventType).contains(eventID)) {
							resp = "Customer is not registered in event corresponding with entered eventID and eventType.".trim();
						} else {

							if (!Montreal_hashmap.get(eventType).containsKey(eventID)) {
								resp = "No event with entered eventID entered found.".trim();
							} else {

								int a = Integer.valueOf(Montreal_hashmap.get(eventType).get(eventID).get(1)) + 1;
								Montreal_hashmap.get(eventType).get(eventID).set(1, String.valueOf(a));
								client_Montreal_info.get(customerID).get(eventType).remove(eventID);
								int n = Integer.valueOf(client_Montreal_info.get(customerID).get(eventType).get(0)) - 1;
								client_Montreal_info.get(customerID).get(eventType).set(0, String.valueOf(n));

								resp = "Customer has been removed from this event.".trim();

							}
						}
					}
				}

			} else {
				resp = "Entered eventID is invalid.".trim();
			}
			return resp.trim();

		} else {
			return "Entered eventID is invalid.".trim();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Montreal_Server.Montreal_Interface#addEvent(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public String addEvent(String eventID, String eventType, String bookingCapacity) {

		String resp = null;

		if( eventType.equalsIgnoreCase("Seminar") || eventType.equalsIgnoreCase("Conference")
				|| eventType.equalsIgnoreCase("TradeShow") ) {
			if (eventID.substring(0, 3).equalsIgnoreCase("OTW") || eventID.substring(0, 3).equalsIgnoreCase("TOR")) {
				resp = "You cannot add this event since it's not in your city.".trim();
			} else if (eventID.substring(0, 3).equalsIgnoreCase("MTL")) {
				if( eventID.length() != 10) {
					resp = "Entered eventID is invalid.".trim();
				} else {
					
					if( Montreal_hashmap.get(eventType).containsKey(eventID) ) {
						int val = Integer.valueOf( Montreal_hashmap.get(eventType).get(eventID).get(0) ) + 1;
						Montreal_hashmap.get(eventType).get(eventID).remove(0);
						Montreal_hashmap.get(eventType).get(eventID).add(0, String.valueOf(val));
						resp = "Event was already added so booking capacity updated.".trim();
					} else {
						ArrayList<String> temp = new ArrayList<String>();
						Collections.addAll(temp, bookingCapacity, String.valueOf(0));
						Montreal_hashmap.get(eventType).put(eventID, temp);
						resp = "Event successfully added.".trim();
					}
					
				}
			}

		} else {
			resp = "Entered event type is invalid.".trim();
		}

		return resp.trim();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Montreal_Server.Montreal_Interface#removeEvent(java.lang.String,
	 * java.lang.String)
	 */
	public String removeEvent(String eventID, String eventType) {

		if( eventID.substring(0, 3).equalsIgnoreCase("MTL") ) {
			if (Montreal_hashmap.containsKey(eventType)) {
				if (Montreal_hashmap.get(eventType).containsKey(eventID)) {

					for (Map.Entry<String, HashMap<String, ArrayList<String>>> entry : client_Montreal_info.entrySet()) {
						if (entry.getValue().containsKey(eventType)) {

							if (entry.getValue().get(eventType).contains(eventID)) {

								entry.getValue().get(eventType).remove(eventID);
								int n = Integer.valueOf(entry.getValue().get(eventType).get(0)) - 1;
								entry.getValue().get(eventType).set(0, String.valueOf(n));
							}

						}
					}
					Montreal_hashmap.get(eventType).remove(eventID);
					check_with_otw(eventType, eventID);
					check_with_tor(eventType, eventID);
				
					return "The event was successfully removed.".trim();
				} else {
					return "The event with eventID you wanted to remove does not exist.".trim();
				}

			} else {
				return "The eventType you entered is invalid.".trim();
			}

		} else {
			if( eventID.substring(0, 3).equalsIgnoreCase("TOR") || eventID.substring(0, 3).equalsIgnoreCase("OTW") ) {
				return "You cannot remove event from this city.".trim();
			} else {
				return "Invalid eventID".trim();
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
		for (Map.Entry<String, HashMap<String, ArrayList<String>>> entry : client_Montreal_info.entrySet()) {
			if (entry.getValue().containsKey(info[0])) {

				if (entry.getValue().get(info[0]).contains(info[1])) {

					entry.getValue().get(info[0]).remove(info[1]);
					int n = Integer.valueOf(entry.getValue().get(info[0]).get(0)) - 1;
					entry.getValue().get(info[0]).set(0, String.valueOf(n));
				}

			}
		}
		String resp = null;
		resp = "done".trim();
		
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
	 * @see
	 * Montreal_Server.Montreal_Interface#listEventAvailability(java.lang.String)
	 */
	public String listEventAvailability(String eventType) {

		ArrayList<String> buff = new ArrayList<String>();
		if (Montreal_hashmap.containsKey(eventType)) {
			if (!Montreal_hashmap.get(eventType).isEmpty()) {
				for (HashMap.Entry<String, ArrayList<String>> entry : Montreal_hashmap.get(eventType).entrySet()) {
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

		String temp= new String();
		String temp1 = new String();
		try {
			temp = availability("no",eventType,1);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			temp1 = availability("no",eventType,2);
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

		return buff.toString().trim();
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
		if( !Montreal_hashmap.containsKey(eventType) ) {
			resp = "Invalid".trim();
		} else {
			for( Map.Entry<String, ArrayList<String>> entry : Montreal_hashmap.get(eventType).entrySet()) {
				
//				if( Integer.valueOf( entry.getValue().get(0) ) > Integer.valueOf( entry.getValue().get(1) ) ) {
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
			resp = "Invalid".trim();
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
	
	/**
	 * Cancel booking.
	 *
	 * @param data    the data
	 * @param message the message
	 */
	public void cancelBooking( String data, DatagramPacket message ) {
		

		String[] info = data.split("\\s");

		String resp = null;

		if (!Montreal_hashmap.containsKey(info[1])) {
			resp = "EventType entered is invalid.".trim();
		} else {
			if (!Montreal_hashmap.get(info[1]).containsKey(info[2])) {
				resp = "Event with eventID " + info[2] + " does not exist.".trim();
			} else {
				int b = Integer.valueOf(Montreal_hashmap.get(info[1]).get(info[2]).get(1)) - 1;
				Montreal_hashmap.get(info[1]).get(info[2]).remove(1);
				Montreal_hashmap.get(info[1]).get(info[2]).add(1, String.valueOf(b));
//				System.out.println(Montreal_hashmap);
				resp = "N".trim();
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
		if (Montreal_hashmap.containsKey(info[1])) {
			if (Montreal_hashmap.get(info[1]).containsKey(info[2])) {
				if (Integer.valueOf(Montreal_hashmap.get(info[1]).get(info[2]).get(0)) > Integer
						.valueOf(Montreal_hashmap.get(info[1]).get(info[2]).get(1))) {
					int n = Integer.valueOf(Montreal_hashmap.get(info[1]).get(info[2]).get(1));
					Montreal_hashmap.get(info[1]).get(info[2]).set(1, String.valueOf(n + 1));
					resp = "Y".trim();

				} else {
					resp = "Event you're trying to book is full.".trim();
				}
			} else {
				resp = "Event with eventID " + info[2] + " does not exist.".trim();
			}
		} else {
			resp = "EventType entered is invalid".trim();
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

					if( flag0 <=0 ) {
						socket = new DatagramSocket(6100);
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

		byte[] mtl_req = null;
		String mtl_request = customerID + " " + eventType + " " + eventID + " " + action + " " + "MTL".trim();

		try {

			mtl_req = mtl_request.getBytes();
			InetAddress host_mtl = InetAddress.getByName("localhost");
			int port = 0;
			if (eventID.substring(0, 3).equalsIgnoreCase("OTW")) {
				port = 6001;
			} else if (eventID.substring(0, 3).equalsIgnoreCase("TOR")) {
				port = 6002;
			}

			DatagramPacket mtl_query = new DatagramPacket(mtl_req, mtl_request.length(), host_mtl, port);
			socket.send(mtl_query);

		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}

		exec.shutdown();
		return result.get().trim();
	}

	/**
	 * Check with tor.
	 *
	 * @param eventType the event type
	 * @param eventID   the event ID
	 * @return the string
	 */
	public void check_with_tor(String eventType, String eventID)  {

		byte[] mtl_req = null;
		String mtl_request = eventType + " " + eventID;

		try {

			if( flag1 <= 0 ) {
				socket_multi = new DatagramSocket(6101);
				flag1++;
			}
			mtl_req = mtl_request.getBytes();
			InetAddress host_mtl = InetAddress.getByName("localhost");
			int port = 6002;

			DatagramPacket mtl_query = new DatagramPacket(mtl_req, mtl_request.length(), host_mtl, port);
			socket_multi.send(mtl_query);

		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
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
		
		byte[] mtl_req = null;
		String mtl_request = eventType + " " + eventID;
		
		try {
			if( flag2 <= 0 ) {
				socket_multi = new DatagramSocket(6102);
				flag2++;
			}

			mtl_req = mtl_request.getBytes();
			InetAddress host_mtl = InetAddress.getByName("localhost");
			int port = 6001;

			DatagramPacket mtl_query = new DatagramPacket(mtl_req, mtl_request.length(), host_mtl, port);
			socket_multi.send(mtl_query);

		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * Availability tor.
	 *
	 * @param eventID   the event ID
	 * @param eventType the event type
	 * @param i         the i
	 * @return the string
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException   the execution exception
	 */
	public String availability(String eventID, String eventType, int i) throws InterruptedException, ExecutionException {
		
		ExecutorService exec = Executors.newSingleThreadExecutor();
		Callable<String> call = new Callable<String>() {

			public String call() {
				String data = null;
				try {
					if( flag3 <= 0 ) {
						socket_availability_1 = new DatagramSocket(6103);						
						flag3++;
					}
					byte[] buff = new byte[1024];
					DatagramPacket req = null;
					
					while(true) {
						req = new DatagramPacket(buff, buff.length);
						socket_availability_1.receive(req);
						
						data = new String( req.getData(), 0, req.getLength() );
						
						if( data != null ) {
							break;
						}
						
					}
					
				} catch( SocketException e ) {
					e.printStackTrace();
				} catch( IOException e ) {
					e.printStackTrace();
				} finally {
					if( socket_availability_1 != null ) {
//						socket_availability.close();
					}
				}
				
				return data;
				
			}

		};
		Future<String> result = exec.submit(call);
		
		byte[] tor_req = null;
		String tor_request = new String();
		if(eventID.equalsIgnoreCase("no")) {
			tor_request = eventType;
		} else {
			tor_request = eventID + " " + eventType;
		}
		
		try {
			
			tor_req = tor_request.getBytes();
			InetAddress host_tor = InetAddress.getByName("localhost");
			int port = 0;
			if( i ==1 ) {
				port = 6002;
			} else if( i == 2) {
				port = 6001;
			}
			
			DatagramPacket tor_query = new DatagramPacket( tor_req, tor_request.length(), host_tor,  port);
			socket_availability_1.send(tor_query);
			
		} catch( SocketException e ) {
			e.printStackTrace();
		} catch( IOException e ) {
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
			return "Entered eventID is invalid.".trim();			
		}
		
		if( !( oldEventType.equalsIgnoreCase("TradeShow") || oldEventType.equalsIgnoreCase("Conference") || oldEventType.equalsIgnoreCase("Seminar") || newEventType.equalsIgnoreCase("TradeShow") || newEventType.equalsIgnoreCase("Conference") || newEventType.equalsIgnoreCase("Seminar") ) ) {
			return "Entered eventType is invalid".trim();
		}
		
		if( !( client_Montreal_info.containsKey(customerID) ) ) {
			return "customerID entered is invalid".trim();
		}
		
		if( !( client_Montreal_info.get(customerID).get(oldEventType).contains(oldEventID) ) ) {
			return "customer is not registered in entered event".trim();
		}
		
		String rep = bookEvent(customerID, newEventID, newEventType);
		if(rep.equalsIgnoreCase("Event booked successfully.")) {
			cancelEvent(customerID, oldEventID, oldEventType);
			return "Events swapped successfully".trim();
		} else {
			String res = cancelEvent(customerID, oldEventID, oldEventType);
			if(res.equalsIgnoreCase("Customer has been removed from this event.")) {
				String res1 = bookEvent(customerID, newEventID, newEventType);
				if(res1.equalsIgnoreCase("Event booked successfully.")) {
					return "Events swapped successfully".trim();
				} else {
					bookEvent(customerID, oldEventID, oldEventType);
					return "Events were not swapped since "+res1.trim();
				}
			} else {
				return res.trim();
			}
		}
		
/*		if( newEventID.substring(0, 3).equalsIgnoreCase("MTL") ) {

			
			if( !( Montreal_hashmap.get(newEventType).containsKey(newEventID) ) ) {
				return "There is no event scheduled with this eventID.";
			}
			
			if( Integer.valueOf(Montreal_hashmap.get(newEventType).get(newEventID).get(0)) <= Integer.valueOf(Montreal_hashmap.get(newEventType).get(newEventID).get(1)) ) {
				return "New event is already full.";
			}
			
			String reply = new String();
			
			if( oldEventID.substring(0, 3).equalsIgnoreCase("MTL") ) {

				cancelEvent(customerID, oldEventType, oldEventID);
				reply = "good";

			} else {
				if( oldEventID.substring(0, 3).equalsIgnoreCase("OTW") ) {
					try {
						reply = availability(oldEventID, oldEventType, 1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				} else {
					try {
						reply = availability(oldEventID, oldEventType, 2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				}
			}
			
			if( reply.equalsIgnoreCase("good") ) {
				String rep = bookEvent(customerID, newEventType, newEventID);
				if(rep.equalsIgnoreCase("Event booked successfully.")) {
					int a = Integer.valueOf( client_Montreal_info.get(customerID).get(oldEventType).get(0) ) - 1;
					client_Montreal_info.get(customerID).get(oldEventType).remove(0);
					client_Montreal_info.get(customerID).get(oldEventType).add(0, String.valueOf(a));
					return "Event swapped successfully";				
				} else {
					return rep;
				}
			} else {
				return reply;
			}
			
			
			
		} else if( newEventID.substring(0, 3).equalsIgnoreCase("OTW") ) {
			String rep = bookEvent(customerID, newEventType, newEventID);
			if(rep.equalsIgnoreCase("Event booked successfully.")) {
				
			} else {
				return rep;
			}
			
		} else if( newEventID.substring(0, 3).equalsIgnoreCase("TOR") ) {
			
		}
*/		
	}
	
}
