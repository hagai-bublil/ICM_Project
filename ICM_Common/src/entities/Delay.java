package entities;

// TODO: Auto-generated Javadoc
/**
 * The Class Delay.
 */
public class Delay 
{
	
	/** The delay SN. */
	private int delaySN;
	
	/** The stage manager ID. */
	private String stageManagerID;
	
	/** The stage name. */
	private String stageName;
	
	/** The request number. */
	private int requestNumber;
	
	/** The system name. */
	private String systemName;
	
	/** The length of delay. */
	private int lengthOfDelay; //at days

	/**
	 * Instantiates a new delay.
	 *
	 * @param stageManagerID the stage manager ID
	 * @param stageName the stage name
	 * @param requestNumber the request number
	 * @param systemName the system name
	 */
	public Delay(String stageManagerID, String stageName, String requestNumber, String systemName) 
	{
		this.stageManagerID = stageManagerID;
		this.stageName = stageName;
		this.requestNumber = Integer.parseInt(requestNumber);
		this.systemName = systemName;
	}
	
	/**
	 * Gets the delay SN.
	 *
	 * @return the delay SN
	 */
	public int getDelaySN() {
		return delaySN;
	}
	
	/**
	 * Sets the delay SN.
	 *
	 * @param delaySN the new delay SN
	 */
	public void setDelaySN(int delaySN) {
		this.delaySN = delaySN;
	}
	
	/**
	 * Gets the stage manager ID.
	 *
	 * @return the stage manager ID
	 */
	public String getStageManagerID() {
		return stageManagerID;
	}
	
	/**
	 * Sets the stage manager ID.
	 *
	 * @param stageManagerID the new stage manager ID
	 */
	public void setStageManagerID(String stageManagerID) {
		this.stageManagerID = stageManagerID;
	}
	
	/**
	 * Gets the stage name.
	 *
	 * @return the stage name
	 */
	public String getStageName() {
		return stageName;
	}
	
	/**
	 * Sets the stage name.
	 *
	 * @param stageName the new stage name
	 */
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	
	/**
	 * Gets the request number.
	 *
	 * @return the request number
	 */
	public int getRequestNumber() {
		return requestNumber;
	}
	
	/**
	 * Sets the request number.
	 *
	 * @param requestNumber the new request number
	 */
	public void setRequestNumber(int requestNumber) {
		this.requestNumber = requestNumber;
	}
	
	/**
	 * Gets the system name.
	 *
	 * @return the system name
	 */
	public String getSystemName() {
		return systemName;
	}
	
	/**
	 * Sets the system name.
	 *
	 * @param systemName the new system name
	 */
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	
	/**
	 * Gets the length of delay.
	 *
	 * @return the length of delay
	 */
	public int getLengthOfDelay() {
		return lengthOfDelay;
	}
	
	/**
	 * Sets the length of delay.
	 *
	 * @param lengthOfDelay the new length of delay
	 */
	public void setLengthOfDelay(int lengthOfDelay) {
		this.lengthOfDelay = lengthOfDelay;
	}
}
