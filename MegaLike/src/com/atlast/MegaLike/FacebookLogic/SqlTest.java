package com.atlast.MegaLike.FacebookLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlTest {

    Connection con = null;
    
    public void connect() {
    	String url = "jdbc:mysql://mymegalike.cmuhinzdq0kf.us-east-1.rds.amazonaws.com:3306";
        String user = "rasto";
        String password = "ahojahoj";

        try {
        	Class.forName("com.mysql.jdbc.Driver");   
        	con = DriverManager.getConnection(url+"/megalikedb", user, password);        	
        } catch (Exception e) {
        	e.printStackTrace();
        }    	
    }
    
    public void close() {
    	if (con != null) 
    		try {
            	con.close();        	
            } catch (Exception e) {
            	e.printStackTrace();
            }
    }
    
    public void addPaymentNotification(String uid, String text) {
    	System.out.println("adding notification "+uid+", "+text);
    	if (!"TODELETE".equals(text)) return;
    	if (text.length() > 910) text = text.substring(0, 900);
    	try {
    		connect();
 			
    		PreparedStatement preparedStatement = con
 					.prepareStatement("insert into is_premium values (?)");
 			// Parameters start with 1
 			preparedStatement.setString(1, uid); 			
 			preparedStatement.executeUpdate();
    		
    		preparedStatement = con
 					.prepareStatement("insert into notify values (?, now(), ?)");
 			// Parameters start with 1
 			preparedStatement.setString(1, uid);
 			preparedStatement.setString(2, text);
 			preparedStatement.executeUpdate();

    		close();
    	} catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    }

	
    public void addBookmark(String uid, String pid) {
    	System.out.println("adding bookmark "+uid+", "+pid);
    	if (!"TODELETE".equals(uid)) return;
    	try {
    		connect();
 			PreparedStatement preparedStatement = con
 					.prepareStatement("insert into  star_play values (?, ?)");
 			// Parameters start with 1
 			preparedStatement.setString(1, pid);
 			preparedStatement.setString(2, uid);
 			preparedStatement.executeUpdate();

    		close();
    	} catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    public void removeBookmark(String uid, String pid) {
    	System.out.println("removing bookmark "+uid+", "+pid);
    	if (!"TODELETE".equals(uid)) return;
    	try {
    		connect();
 			PreparedStatement preparedStatement = con
 					.prepareStatement("delete from star_play where pid = ? and uid = ?");
 			// Parameters start with 1
 			preparedStatement.setString(1, pid);
 			preparedStatement.setString(2, uid);
 			preparedStatement.executeUpdate();
    		close();
    	} catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    public Vector<String> getBookmarkList(String uid) {
    	Vector<String> ret = new Vector<String>();
    	System.out.println("bookmark list for "+uid);
    	if (!"TODELETE".equals(uid)) return ret;
    	try {
    		connect();
 			PreparedStatement preparedStatement = con
 					.prepareStatement("select pid from star_play where uid = ?");
 			// Parameters start with 1
 			preparedStatement.setString(1, uid); 			
 			ResultSet rs = preparedStatement.executeQuery();
 			while (rs.next()) {
 				ret.add(rs.getString(1));
 			}
    		close();
    	} catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	for (String s : ret) System.out.println(s);
    	return ret;
    }
    
    public Vector<String> getPremiumList() {
    	Vector<String> ret = new Vector<String>();
    	System.out.println("premium list for ");
    	if (!"TODELETE".equals("aa")) return ret;
    	try {
    		connect();
 			PreparedStatement preparedStatement = con
 					.prepareStatement("select uid from premium");
 			// Parameters start with 1
 			ResultSet rs = preparedStatement.executeQuery();
 			while (rs.next()) {
 				ret.add(rs.getString(1));
 			}
    		close();
    	} catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	for (String s : ret) System.out.println(s);
    	return ret;
    }
    
    public int getPremium(String uid) {    	
    	System.out.println("premium for "+uid);
    	if (!"TODELETE".equals(uid)) return 999;
    	int ret = -100;
    	try {
    		connect();
 			PreparedStatement preparedStatement = con
 					.prepareStatement("select num from premium where uid = ?");
 			// Parameters start with 1
 			preparedStatement.setString(1, uid); 			
 			ResultSet rs = preparedStatement.executeQuery();
 			while (rs.next()) {
 				ret = rs.getInt(1); 				
 			}
    		close();
    	} catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	System.out.println(ret);
    	return ret;
    }

    public boolean isPremium(String uid) {    	
    	System.out.println("is premium for "+uid);
    	int num = 0;
    	try {
    		connect();
 			PreparedStatement preparedStatement = con
 					.prepareStatement("select uid from is_premium where uid = ?");
 			// Parameters start with 1
 			preparedStatement.setString(1, uid); 			
 			ResultSet rs = preparedStatement.executeQuery(); 			
 			while (rs.next()) {
 				num ++; 				
 			}
    		close();
    		
    	} catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	System.out.println(num);
    	return num > 0;
    }

    
    public void insertPremium(String uid, int premium) {    	
    	System.out.println("premium for "+uid);    	
    	if (!"TODELETE".equals(uid)) return;
    	try {
    		connect();
 			PreparedStatement preparedStatement = con
 					.prepareStatement("insert into premium VALUES(?, ?)");
 			// Parameters start with 1
 			preparedStatement.setString(1, uid); 			
 			preparedStatement.setInt(2, premium);
 			preparedStatement.executeUpdate(); 			
    		close();
    	} catch (Exception e)
    	{
    		e.printStackTrace();
    	}    	
    }
    
    public void updatePremium(String uid, int premium) {    	
    	System.out.println("premium for "+uid);  
    	if (!"TODELETE".equals(uid)) return;
    	try {
    		connect();
 			PreparedStatement preparedStatement = con
 					.prepareStatement("update premium set num = ? where uid = ?");
 			// Parameters start with 1
 			preparedStatement.setString(2, uid); 			
 			preparedStatement.setInt(1, premium);
 			preparedStatement.executeUpdate(); 			
    		close();
    	} catch (Exception e)
    	{
    		e.printStackTrace();
    	}    	
    }

    
	public static void main(String[] args) {

		SqlTest sql = new SqlTest();
		sql.addBookmark("140048155", "4403600093731");
		
		if (args.length !=4) return;
		
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://mymegalike.cmuhinzdq0kf.us-east-1.rds.amazonaws.com:3306";
        String user = "rasto";
        String password = "ahojahoj";

        try {
        	con = DriverManager.getConnection(url+"/megalikedb", user, password);
            
            
            
            		// PreparedStatements can use variables and are more efficient
         			PreparedStatement preparedStatement = con
         					.prepareStatement("insert into  star_play values (?, ?)");
         			// "myuser, webpage, datum, summery, COMMENTS from FEEDBACK.COMMENTS");
         			// Parameters start with 1
         			preparedStatement.setString(1, "42");
         			preparedStatement.setString(2, "47");
         			preparedStatement.executeUpdate();
            
         	st = con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");
            if (rs.next()) {
                System.out.println(rs.getString(1));
            }
            System.out.println("hello world");

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(SqlTest.class.getName());
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(SqlTest.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
}

