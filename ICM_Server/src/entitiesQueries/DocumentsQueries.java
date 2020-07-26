package entitiesQueries;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.SqlConnection;
import entities.DBMessage;
import entities.DBMessage.DBAction;
import entities.Document;

/**
 * This class hold all the queries for documents table
 *
 */
public class DocumentsQueries {
	
	/**
	 * Insert doc to DB.
	 *
	 * @param doc the doc
	 * @param icmDB the icm DB
	 * @return the DB message
	 */
	public static DBMessage InsertDocToDB(Document doc, SqlConnection icmDB) {//this function insert request to request's table
		String query = "INSERT INTO icm_db.documents (requestNumber, path) VALUES ('" + doc.getIdRequest() + "','" + doc.getPath() + "')";
		int msg =icmDB.executeUpdate(query);
		DBMessage returnMsg=new DBMessage(DBAction.Files, msg);
		return returnMsg;

	}
	
	/**
	 * Open the file.
	 *
	 * @param IDRequest the ID request
	 * @param icmDB the icm DB
	 * @return the DB message
	 */
	public static DBMessage OpenTheFile(int IDRequest, SqlConnection icmDB) {//this function insert request to request's table
		String query = "SELECT path FROM icm_db.documents WHERE requestNumber='"+IDRequest+"'";
		String msg = null;
		DBMessage returnMsg=null;
		ResultSet rs =icmDB.executeQuery(query);
		
		try {
			if(rs.next()) {
				msg=rs.getString(1);
				returnMsg=new DBMessage(DBAction.OpenFile, msg);
			}
			else {
				 returnMsg=new DBMessage(DBAction.OpenFile, null);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return returnMsg;

	}
}
