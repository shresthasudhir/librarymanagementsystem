package application.model;

import java.sql.*;

public class User {
	public static boolean validateUser(String username, String password) {
		boolean status = false;

		try {
			Connection con = Database.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ? and password = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			status = rs.next();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	public static int getUserStatus(String username) {
		int statusId = 0;
		try {
			Connection con = Database.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT status FROM users WHERE username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				statusId = rs.getInt("status"); 
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return statusId;
	}
	
	public static int getUserId(String username) {
		int userId = 0;
		try {
			Connection con = Database.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT id FROM users WHERE username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				userId = rs.getInt("id"); 
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return userId;
	}
	
	public static String getUserFNameLName(int id) {
		String userFirstName = "";
		String userLastName = "";
		try {
			Connection con = Database.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT firstname,lastname FROM users WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				userFirstName = rs.getString("firstname");
				userLastName = rs.getString("lastname");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return userFirstName + " " + userLastName;
	}
}
