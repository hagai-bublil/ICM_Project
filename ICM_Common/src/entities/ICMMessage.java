package entities;

import java.text.SimpleDateFormat;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class ICMMessage.
 */
@SuppressWarnings("serial")
public class ICMMessage implements Serializable
{
	
	/** The id. */
	private String id;
	
	/** The date sending. */
	private Date dateSending;
	
	/** The message content. */
	private String messageContent;
	
	/** The recipient user type. */
	private String recipientUserType;
	
	/** The recipient user id. */
	private String recipientUserId;
	
	/**
	 * Instantiates a new ICM message.
	 *
	 * @param id the id
	 * @param dateSending the date sending
	 * @param messageContent the message content
	 * @param recipientUserType the recipient user type
	 * @param recipientUserId the recipient user id
	 */
	public ICMMessage(String id, String dateSending, String messageContent, String recipientUserType, String recipientUserId) //to add a new message to the data base
	{
		this.id = id;
		this.messageContent = messageContent;
		this.recipientUserType = recipientUserType;
		this.recipientUserId = recipientUserId;
/*		try {
			this.dateSending=new SimpleDateFormat("yyyy-MM-dd").parse(dateSending);
		} catch (ParseException e) 
		{
			e.printStackTrace();
		}  */
	}
	
	/**
	 * Instantiates a new ICM message.
	 *
	 * @param id the id
	 * @param dateSending the date sending
	 * @param messageContent the message content
	 */
	public ICMMessage(String id, String dateSending, String messageContent) //To display a message to a user with a specific ID
	{
		this.id = id;
		this.messageContent = messageContent;
		try {
			this.dateSending=new SimpleDateFormat("yyyy-MM-dd").parse(dateSending);
		} catch (ParseException e) 
		{
			e.printStackTrace();
		}  
	}	

	/**
	 * Instantiates a new ICM message.
	 *
	 * @param messageContent the message content
	 * @param recipientUserType the recipient user type
	 */
	public ICMMessage(String messageContent, String recipientUserType)
	{
		this.messageContent = messageContent;
		this.recipientUserType = recipientUserType;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Gets the message content.
	 *
	 * @return the message content
	 */
	public String getMessageContent()
	{
		return messageContent;
	}

	/**
	 * Sets the message content.
	 *
	 * @param messageContent the new message content
	 */
	public void setMessageContent(String messageContent)
	{
		this.messageContent = messageContent;
	}

	/**
	 * Gets the recipient user type.
	 *
	 * @return the recipient user type
	 */
	public String getRecipientUserType()
	{
		return recipientUserType;
	}

	/**
	 * Sets the recipient user type.
	 *
	 * @param recipientUserType the new recipient user type
	 */
	public void setRecipientUserType(String recipientUserType)
	{
		this.recipientUserType = recipientUserType;
	}

	/**
	 * Gets the recipient user id.
	 *
	 * @return the recipient user id
	 */
	public String getRecipientUserId()
	{
		return recipientUserId;
	}

	/**
	 * Sets the recipient user id.
	 *
	 * @param recipientUserId the new recipient user id
	 */
	public void setRecipientUserId(String recipientUserId)
	{
		this.recipientUserId = recipientUserId;
	}

	/**
	 * Gets the date sending.
	 *
	 * @return the date sending
	 */
	public Date getDateSending() {
		return dateSending;
	}

	/**
	 * Sets the date sending.
	 *
	 * @param dateSending the new date sending
	 */
	public void setDateSending(Date dateSending) {
		this.dateSending = dateSending;
	}	
}
