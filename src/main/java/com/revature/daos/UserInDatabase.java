package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.util.*;

public class UserInDatabase implements UserDao {
	User extractUser(ResultSet rs) throws SQLException{
		int id = rs.getInt("ERS_USERS_ID");
		String username = rs.getString("ERS_USERNAME");
		String password = rs.getString("ERS_PASSWORD");
		String firstname = rs.getString("USER_FIRSTNAME");
		String lastname = rs.getString("USER_LASTNAME");
		String email = rs.getString("USER_EMAIL");
		int roleId = rs.getInt("USER_ROLE_ID");
		//id, roleId, String userName, String userPass, String firstName, String lastName, String email
		return new User(id, username, password, firstname, lastname, email, roleId);
		
	}
	@Override
	public User findByUsername(String username) {
		try(Connection employeeDatabase = ConnectionUtil.getConnection()){
			String findSelection = "SELECT * FROM ERS_USERS WHERE "
					+ "ERS_USERNAME = ?";
			PreparedStatement ps = employeeDatabase.prepareStatement(findSelection);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractUser(rs);
			} else {
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findByUsernameAndPassword(String userName, String userPass) {
		// TODO Auto-generated method stub
		try(Connection employeeDatabase = ConnectionUtil.getConnection()){
			String findSelection = "SELECT * FROM ERS_USERS WHERE "
					+ "ERS_USERNAME = ? AND ERS_PASSWORD = ?";
			PreparedStatement ps = employeeDatabase.prepareStatement(findSelection);
			ps.setString(1, userName);
			ps.setString(2, userPass);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractUser(rs);
			} else {
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}
	/*@Override
	public int save(User currentUser) {
		try(Connection employeeDatabase = ConnectionUtil.getConnection()){
			String addSelection = "INSERT INTO ERS_USERS(ers_users_id, ERS_USERNAME, ERS_PASSWORD, USER_FIRSTNAME, USER_LASTNAME, USER_EMAIL, user_role_id) " + 
					"VALUES (USER_ID_SEQ.nextval,?,?,?,?,?,?)";
			PreparedStatement ps = employeeDatabase.prepareStatement(addSelection);
			ps.setString(1, currentUser.getUserName());
			ps.setString(2, currentUser.getUserPass());
			ps.setString(3, currentUser.getFirstName());
			ps.setString(4, currentUser.getLastName());
			ps.setString(5, currentUser.getEmail());
			ps.setInt(6, currentUser.getRoleId());
			return ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}*/
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ERS_USERS";

			PreparedStatement ps = c.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<User> users = new ArrayList<User>();
			while (rs.next()) {
				users.add(extractUser(rs));
			}
			return users;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
