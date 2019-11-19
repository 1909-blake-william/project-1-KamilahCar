package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
//import com.revature.models.User;
import com.revature.util.ConnectionUtil;
import java.sql.Timestamp;
public class ReimbursementInDatabase implements ReimbursementDao {
	Reimbursement extractReimbursement(ResultSet rs) throws SQLException {
		int id = rs.getInt("REIMB_ID");
		int amount = rs.getInt("AMOUNT");
		int statusId = rs.getInt("REIMB_STATUS_ID");
		int typeId = rs.getInt("REIMB_TYPE_ID");
		int resolver = rs.getInt("REIMB_RESOLVER");
		int author = rs.getInt("REIMB_AUTHOR");
		Timestamp submitted = rs.getTimestamp("REIMB_SUBMITTED");
		Timestamp resolved = rs.getTimestamp("REIMB_RESOLVED");
		String description = rs.getString("REIMB_DESCRIPTION");
		
		return new Reimbursement(id, amount, submitted, resolved, description, author, resolver, statusId, typeId);
	}
	@Override
	public List<Reimbursement> findAll() {
		// TODO Auto-generated method stub
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		try(Connection employeeDatabase = ConnectionUtil.getConnection()){
			String findSelection = "SELECT * FROM ERS_REIMBURSEMENT";
			PreparedStatement ps = employeeDatabase.prepareStatement(findSelection);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				reimbursements.add(extractReimbursement(rs));
			}
			return reimbursements;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<Reimbursement> findByAuthor(int author){
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		try(Connection employeeDatabase = ConnectionUtil.getConnection()){
			//String findSelection = "SELECT * FROM ERS_REIMBURSEMENT WHERE author = ?";
			String joinSelection ="SELECT * FROM ERS_REIMBURSEMENT r "
					+ "LEFT JOIN ERS_USERS u ON (r.REIMB_AUTHOR = u.ERS_USERS_ID) "
					+ "WHERE REIMB_AUTHOR = ?";
			
			PreparedStatement ps = employeeDatabase.prepareStatement(joinSelection);
			ps.setInt(1, author);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reimbursements.add(extractReimbursement(rs));
			}
			return reimbursements;
		} catch(SQLException e) {
			return null;
		}
	}
	@Override
	public List <Reimbursement> findById(int id) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		try(Connection employeeDatabase = ConnectionUtil.getConnection()){
			String findSelection = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_ID = ?";
			PreparedStatement ps = employeeDatabase.prepareStatement(findSelection);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reimbursements.add(extractReimbursement(rs));
			}
			return reimbursements;
		} catch(SQLException e) {
			return null;
		}
	}
	@Override
	public int save(Reimbursement currentReimbursement) {
		// TODO Auto-generated method stub
		
		/*int id, int statusId, int typeId, int amount, int author, int resolver, Timestamp submitted,
		Timestamp resolved, String description*/
		/*REIMB_ID, AMOUNT,REIMB_SUBMITTED, REIMB_RESOLVED, REIMB_DESCRIPTION, 
		REIMB_AUTHOR, REIMB_RESOLVER, REIMB_STATUS_ID NUMBER,REIMB_TYPE_ID*/
		try(Connection employeeDatabase = ConnectionUtil.getConnection()){
			String addSelection = "INSERT INTO ERS_REIMBURSEMENT(REIMB_ID, REIMB_AMOUNT, "
					+ "REIMB_SUBMITTED, REIMB_RESOLVED, REIMB_DESCRIPTION"
					+ "REIMB_AUTHOR, REIMB_RESOLVER, REIMB_STATUS_ID, REIMB_TYPE_ID) "
					+ "VALUES(REIMB_ID_SEQ.nextval,?,?,?,?,?,?,?,?)";
			
			PreparedStatement ps = employeeDatabase.prepareStatement(addSelection);
			ps.setInt(1, currentReimbursement.getAmount());
			ps.setTimestamp(2, currentReimbursement.getSubmitted());
			ps.setTimestamp(3, currentReimbursement.getResolved());
			ps.setString(4, currentReimbursement.getDescription());
			ps.setInt(5, currentReimbursement.getAuthor());
			ps.setInt(6, currentReimbursement.getResolver());
			//Status id will be pending when first saved
			//pending id = 4
			//ps.setInt(2, currentReimbursement.getStatusId());
			ps.setInt(7, 4);
			ps.setInt(8, currentReimbursement.getTypeId());
			return ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int updateStatus(Reimbursement changeStatus) {
		// TODO Auto-generated method stub
		
		try(Connection employeeDatabase = ConnectionUtil.getConnection()){
			String addSelection = "UPDATE ERS_REIMBURSEMENT WHERE REIMB_STATUS_ID=?";
			PreparedStatement ps = employeeDatabase.prepareStatement(addSelection);
			ps.setInt(1, changeStatus.getStatusId());
			return ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public List <Reimbursement> findByStatus(int statusId) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		try(Connection employeeDatabase = ConnectionUtil.getConnection()){
			String joinSelection = "SELECT * FROM ERS_REIMBURSEMENT r LEFT JOIN "
					+ "ERS_REIMBURSEMENT_STATUS s "
					+ "ON (r.REIMB_STATUS_ID = s.REIMB_STATUS_ID) WHERE REIMB_STATUS_ID=?";
			
			PreparedStatement psJoin = employeeDatabase.prepareStatement(joinSelection);
			ResultSet rs = psJoin.executeQuery();
			while (rs.next()) {
				reimbursements.add(extractReimbursement(rs));
			}
			
			return reimbursements;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int updateAuthor(Reimbursement changeAuthor) {
		try(Connection employeeDatabase = ConnectionUtil.getConnection()){
			String addSelection = "UPDATE ERS_REIMBURSEMENT WHERE REIMB_AUTHOR=?";
			PreparedStatement ps = employeeDatabase.prepareStatement(addSelection);
			ps.setInt(1, changeAuthor.getAuthor());
			return ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int updateResolver(Reimbursement changeResolver) {
		try(Connection employeeDatabase = ConnectionUtil.getConnection()){
			String addSelection = "UPDATE ERS_REIMBURSEMENT WHERE REIMB_AUTHOR=?";
			PreparedStatement ps = employeeDatabase.prepareStatement(addSelection);
			ps.setInt(1, changeResolver.getResolver());
			return ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
