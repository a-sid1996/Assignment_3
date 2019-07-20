package ServerPackage;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


/**
 * The Interface Common_Inteface.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Common_Interface {
	
	/**
	 * Gets the booking schedule.
	 *
	 * @param customerID the customer ID
	 * @return the booking schedule
	 */
	public String getBookingSchedule(String customerID);

	/**
	 * Book event.
	 *
	 * @param customerID the customer ID
	 * @param eventID    the event ID
	 * @param eventType  the event type
	 * @return the string
	 */
	public String bookEvent(String customerID, String eventID, String eventType);

	/**
	 * Cancel event.
	 *
	 * @param customerID the customer ID
	 * @param eventID    the event ID
	 * @param eventType  the event type
	 * @return the string
	 */
	public String cancelEvent(String customerID, String eventID, String eventType);

	/**
	 * Adds the event.
	 *
	 * @param eventID         the event ID
	 * @param eventType       the event type
	 * @param bookingCapacity the booking capacity
	 * @return the string
	 */
	public String addEvent(String eventID, String eventType, String bookingCapacity);

	/**
	 * Removes the event.
	 *
	 * @param eventID   the event ID
	 * @param eventType the event type
	 * @return the string
	 */
	public String removeEvent(String eventID, String eventType);

	/**
	 * List event availability.
	 *
	 * @param eventType the event type
	 * @return the string
	 */
	public String listEventAvailability(String eventType);

	/**
	 * Swap event.
	 *
	 * @param customerID   the customer ID
	 * @param oldEventType the old event type
	 * @param oldEventID   the old event ID
	 * @param newEventType the new event type
	 * @param newEventID   the new event ID
	 * @return the string
	 */
	public String swapEvent(String customerID, String oldEventType, String oldEventID, String newEventType,
			String newEventID);

}
