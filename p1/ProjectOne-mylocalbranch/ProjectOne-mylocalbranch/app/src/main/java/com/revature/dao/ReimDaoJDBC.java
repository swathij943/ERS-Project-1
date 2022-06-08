package com.revature.dao;

import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimDaoJDBC implements IReimDao {
    public static final ConnectionSingleton cs = ConnectionSingleton.getConnectionSingleton();
    private static final Logger logger = LoggerFactory.getLogger(UserDaoJDBC.class);


    @Override
    public void createReim(Reimbursement r) {
        Connection c = cs.getConnection();

        String sql = "insert into reimbursement (amount, description, reimbursement_author, reimbursement_type) values " +
                "("+r.getAmount()+", '"+r.getDescription()+"', "+r.getReimbursement_author()+", "+r.getReimbursement_type()+");";

        try {
            Statement s = c.createStatement();
            s.execute(sql);
            s.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            logger.info("#6. New Reimbursement request created!");
        }
    }

    @Override
    public List<Reimbursement> getReim(int userId) {
        Connection c = cs.getConnection();
        String sql = "select submitted_date, reimbursement_id, users.first_name, users.last_name , amount, description, reimbursement_type.type, reimbursement_status.status , reimbursement_resolver, resolved_date from reimbursement\n" +
                "join users on users.user_id = reimbursement_author\n" +
                "join reimbursement_type on reimbursement.reimbursement_type = reimbursement_type.type_id\n" +
                "join reimbursement_status on reimbursement.reimbursement_status = reimbursement_status.status_id\n" +
                "where reimbursement_author = "+userId+";";
        try{
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet rs = p.executeQuery();

            List<Reimbursement> aList = new ArrayList<>();

            while(rs.next()){
                String author = rs.getString("first_name") + " " + rs.getString("last_name");
                Reimbursement r = new Reimbursement(rs.getInt(2), rs.getDouble(5), rs.getTimestamp(1), rs.getTimestamp(10), rs.getString(6), author, rs.getString(9), rs.getString(8), rs.getString(7));

                aList.add(r);
            }

            return aList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reimbursement> getAllPendingReim() {
        Connection c = cs.getConnection();
        String sql = "select submitted_date, reimbursement_id, users.first_name, users.last_name , amount, description, reimbursement_type.type, reimbursement_status.status from reimbursement\n" +
                "join users on users.user_id = reimbursement_author\n" +
                "join reimbursement_type on reimbursement.reimbursement_type = reimbursement_type.type_id\n" +
                "join reimbursement_status on reimbursement.reimbursement_status = reimbursement_status.status_id\n" +
                "where reimbursement_status = 1;";

        try {
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet rs = p.executeQuery();

            List<Reimbursement> aList = new ArrayList<>();

            while (rs.next()) {
                String author = rs.getString("first_name") + " " + rs.getString("last_name");
                //int reimbursement_id, double amount, Timestamp submitted_date, String description, String author, String status, String type
                Reimbursement r = new Reimbursement(rs.getInt("reimbursement_id"), rs.getDouble("amount"), rs.getTimestamp("submitted_date"), rs.getString("description"), author, rs.getString("status"), rs.getString("type"));
                aList.add(r);
            }

            return aList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void denyReim(int reimId) {
        Connection c = cs.getConnection();
        String sql = "update reimbursement set reimbursement_status = 3 where reimbursement_id = "+reimId+";";
        try {
            PreparedStatement p = c.prepareStatement(sql);
            p.executeUpdate();
            logger.info("Reimbursement with id "+reimId+" has been denied");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void approveReim(int reimId) {
        Connection c = cs.getConnection();
        String sql = "update reimbursement set reimbursement_status = 2 where reimbursement_id = "+reimId+";";
        try {
            PreparedStatement p = c.prepareStatement(sql);
            p.executeUpdate();
            logger.info("Reimbursement with id "+reimId+" has been approved");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
