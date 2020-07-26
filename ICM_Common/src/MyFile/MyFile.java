package MyFile;

import java.io.Serializable;
/**
 * This class is file entity
 *
 */
public class MyFile implements Serializable {
	
	/** The Description. */
	private String Description=null;
	
	/** The file name. */
	private String fileName=null;	
	
	/** The size. */
	private int size=0;
	
	/** The mybytearray. */
	public  byte[] mybytearray;
	
	/** The request ID. */
	private int requestID;
	
	
	/**
	 * Inits the array.
	 *
	 * @param size the size
	 */
	public void initArray(int size)
	{
		mybytearray = new byte [size];	
	}
	
	/**
	 * Instantiates a new my file.
	 *
	 * @param fileName the file name
	 * @param requestID the request ID
	 */
	public MyFile( String fileName, int requestID) {
		this.fileName = fileName;
		this.requestID=requestID;
	}
	
	
	/**
	 * Gets the request ID.
	 *
	 * @return the request ID
	 */
	public int getRequestID() {
		return requestID;
	}

	/**
	 * Sets the request ID.
	 *
	 * @param requestID the new request ID
	 */
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Gets the mybytearray.
	 *
	 * @return the mybytearray
	 */
	public byte[] getMybytearray() {
		return mybytearray;
	}
	
	/**
	 * Gets the mybytearray.
	 *
	 * @param i the i
	 * @return the mybytearray
	 */
	public byte getMybytearray(int i) {
		return mybytearray[i];
	}

	/**
	 * Sets the mybytearray.
	 *
	 * @param mybytearray the new mybytearray
	 */
	public void setMybytearray(byte[] mybytearray) {
		
		for(int i=0;i<mybytearray.length;i++)
		this.mybytearray[i] = mybytearray[i];
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		Description = description;
	}	
}

