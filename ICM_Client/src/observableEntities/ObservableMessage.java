package observableEntities;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;


/**
 * A message entity to show in javafx TableView.
 */
public class ObservableMessage
{
	
	/** The date sent. */
	private SimpleStringProperty dateSent;
	
	/** The msg content. */
	private SimpleStringProperty msgContent;
	
	/** The erase button. */
	private Button eraseButton;
	
	/** The index. */
	private String index;
	
	/**
	 * Instantiates a new observable message.
	 *
	 * @param index the index
	 * @param dateSent the date sent
	 * @param msgContent the msg content
	 */
	public ObservableMessage(String index, String dateSent,String msgContent)
	{
		this.setIndex(index);
		this.dateSent = new SimpleStringProperty(dateSent);
		this.msgContent = new SimpleStringProperty(msgContent);
		this.setEraseButton(new Button("Erase message"));
	}

	/**
	 * Gets the date sent.
	 *
	 * @return the date sent
	 */
	public String getDateSent()
	{
		return dateSent.get();
	}

	/**
	 * Sets the date sent.
	 *
	 * @param dateSent the new date sent
	 */
	public void setDateSent(String dateSent)
	{
		this.dateSent = new SimpleStringProperty(dateSent);
	}

	/**
	 * Gets the msg content.
	 *
	 * @return the msg content
	 */
	public String getMsgContent()
	{
		return msgContent.get();
	}

	/**
	 * Sets the msg content.
	 *
	 * @param msgContent the new msg content
	 */
	public void setMsgContent(String msgContent)
	{
		this.msgContent = new SimpleStringProperty(msgContent);
	}

	/**
	 * Gets the erase button.
	 *
	 * @return the erase button
	 */
	public Button getEraseButton() {
		return eraseButton;
	}

	/**
	 * Sets the erase button.
	 *
	 * @param eraseButton the new erase button
	 */
	public void setEraseButton(Button eraseButton) {
		this.eraseButton = eraseButton;
	}

	/**
	 * Gets the index.
	 *
	 * @return the index
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * Sets the index.
	 *
	 * @param index the new index
	 */
	public void setIndex(String index) {
		this.index = index;
	}

}
