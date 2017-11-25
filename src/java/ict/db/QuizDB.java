/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.QuizBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author hong
 */
public class QuizDB
{

    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public QuizDB(String dburl, String dbUser, String dbPassword)
    {
        this.dburl = dburl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public Connection getConnection() throws SQLException, IOException
    {
//        System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception ex)
        {

        }
        return DriverManager.getConnection(dburl, dbUser, dbPassword);

    }
    
    public ArrayList<QuizBean> getAllQuiz()
    {
        Connection connect = null;
        PreparedStatement pStmt = null;
        QuizBean qBean = null;
        ArrayList<QuizBean> _qBean = new ArrayList<QuizBean>();
        try {
            connect = getConnection();
            String preQueryStatement = "SELECT * FROM quiz";
            pStmt = connect.prepareStatement(preQueryStatement);

            ResultSet rs = null;
            rs = pStmt.executeQuery();
            while (rs.next()) {
                qBean = new QuizBean();
                // set the record detail to the user bean
                
                qBean.setQID(rs.getInt("QID"));
                qBean.setDuration(rs.getInt("duration"));
                qBean.setStartDate(rs.getString("startDate"));
                qBean.setEndDate(rs.getString("endDate"));
                qBean.setAttemptTime(rs.getInt("attemptTime"));
                qBean.setCid(rs.getInt("cid"));
                qBean.setDescription(rs.getString("description"));
                
                _qBean.add(qBean);
            }
            pStmt.close();
            connect.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return _qBean;
    }
    
    public QuizBean queryQuizByID(int id) {
        Connection connect = null;
        PreparedStatement pStmt = null;
        QuizBean qBean = null;
        try {
            connect = getConnection();
            String preQueryStatement = "SELECT * FROM quiz WHERE QID =?";
            pStmt = connect.prepareStatement(preQueryStatement);
            pStmt.setInt(1, id);
            ResultSet rs = null;
            rs = pStmt.executeQuery();

            if (rs.next()) {
                qBean = new QuizBean();
                // set the record detail to the customer bean
                qBean.setQID(rs.getInt("QID"));
                qBean.setDuration(rs.getInt("duration"));
                qBean.setStartDate(rs.getString("startDate"));
                qBean.setEndDate(rs.getString("endDate"));
                qBean.setAttemptTime(rs.getInt("attemptTime"));
                qBean.setCid(rs.getInt("cid"));
                qBean.setDescription(rs.getString("description"));
            }
            pStmt.close();
            connect.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return qBean;
    }
    
    public boolean addQuiz(int duration, int attemptTime, String startDate, String endDate, int cid, String description)
    {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try
        {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO quiz (duration, attemptTime, startDate, endDate, cid, description) VALUES (?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, duration);
            pStmnt.setInt(2, attemptTime);
            pStmnt.setString(3, startDate);
            pStmnt.setString(4, endDate);
            pStmnt.setInt(5, cid);
            pStmnt.setString(6, description);

            int rowCount = pStmnt.executeUpdate();

            if (rowCount >= 1)
            {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        }
        catch (SQLException ex)
        {
            while (ex != null)
            {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public boolean updateQuiz(int id, int duration, int attemptTime, String startDate, String endDate, int cid, String description) {
        Connection connect = null;
        PreparedStatement pStmt = null;
        boolean isSuccess = false;

        try {            
            connect = getConnection();
            String preQueryStatement = "UPDATE quiz set duration = ?, attemptTime=?, startDate=?, endDate=?, cid=?, description=? WHERE QID =?";
            pStmt = connect.prepareStatement(preQueryStatement);

            pStmt.setInt(1, duration);
            pStmt.setInt(2, attemptTime);
            pStmt.setString(3, startDate);
            pStmt.setString(4, endDate);
            pStmt.setInt(5, cid);
            pStmt.setString(6, description);
            pStmt.setInt(7, id);
            
            if (pStmt.executeUpdate() >= 1) {
                isSuccess = true;
            }

            pStmt.close();
            connect.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
//    public ArrayList<QuizBean> getStudentQuizList(int id)
//    {
//        Connection connect = null;
//        PreparedStatement pStmt = null;
//        QuizBean qBean = null;
//        ArrayList<QuizBean> _qBean = new ArrayList<QuizBean>();
//        try {
//            connect = getConnection();
//            String preQueryStatement = "SELECT * FROM quiz";
//            pStmt = connect.prepareStatement(preQueryStatement);
//
//            ResultSet rs = null;
//            rs = pStmt.executeQuery();
//            while (rs.next()) {
//                qBean = new QuizBean();
//                // set the record detail to the user bean
//                
//                qBean.setQID(rs.getInt("QID"));
//                qBean.setDuration(rs.getInt("duration"));
//                qBean.setStartDate(rs.getString("startDate"));
//                qBean.setEndDate(rs.getString("endDate"));
//                qBean.setAttemptTime(rs.getInt("attemptTime"));
//                qBean.setCid(rs.getInt("cid"));
//                qBean.setDescription(rs.getString("description"));
//                
//                _qBean.add(qBean);
//            }
//            pStmt.close();
//            connect.close();
//        } catch (SQLException ex) {
//            while (ex != null) {
//                ex.printStackTrace();
//                ex = ex.getNextException();
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return _qBean;
//    }
    
    

}
