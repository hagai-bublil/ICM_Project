package entities;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Document.
 */
public class Document implements Serializable {
	
	/** The path. */
	private String path;
	
	/** The id request. */
	private int idRequest;
	
	/**
	 * Instantiates a new document.
	 *
	 * @param path the path
	 * @param idRequest the id request
	 */
	public Document(String path, int idRequest) {
		this.idRequest=idRequest;
		this.path=path;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets the path.
	 *
	 * @param path the new path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Gets the id request.
	 *
	 * @return the id request
	 */
	public int getIdRequest() {
		return idRequest;
	}

	/**
	 * Sets the id request.
	 *
	 * @param idRequest the new id request
	 */
	public void setIdRequest(int idRequest) {
		this.idRequest = idRequest;
	}

	
	

}
