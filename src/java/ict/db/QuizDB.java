/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.QuestionBean;
import ict.bean.QuizBean;
import ict.bean.UserInfo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author psb
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
    
    public ArrayList<QuizBean> getStudentQuiz(int stuID)
    {
        Connection connect = null;
        PreparedStatement pStmt = null;
        QuizBean qBean = null;
        ArrayList<QuizBean> _qBean = new ArrayList<QuizBean>();
        try {
            connect = getConnection();
            String preQueryStatement = "SELECT quiz.* FROM quizregistration, quiz where quizregistration.qid = quiz.QID and uid = ?";
            pStmt = connect.prepareStatement(preQueryStatement);
            pStmt.setInt(1, stuID);
            
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
    
    public ArrayList<UserInfo> getQuizStudent(int quizID)
    {
        Connection connect = null;
        PreparedStatement pStmt = null;
        UserInfo uBean = null;
        ArrayList<UserInfo> _uBean = new ArrayList<UserInfo>();
        try {
            connect = getConnection();
            String preQueryStatement = "SELECT * FROM quizregistration where qid = ?";
            pStmt = connect.prepareStatement(preQueryStatement);
            pStmt.setInt(1, quizID);
            
            ResultSet rs = null;
            rs = pStmt.executeQuery();
            while (rs.next()) {
                preQueryStatement = "SELECT * FROM user where id = ?";
                pStmt = connect.prepareStatement(preQueryStatement);
                pStmt.setInt(1, rs.getInt("uid"));
                ResultSet rs2 = null;
                rs2 = pStmt.executeQuery();
                while(rs2.next()){
                    uBean = new UserInfo();
                    // set the record detail to the user bean
                    uBean.setId(rs2.getInt("id"));
                    uBean.setUsername(rs2.getString("username"));
                    uBean.setPassword(rs2.getString("password"));
                    uBean.setRole(rs2.getString("role"));
                    uBean.setEmail(rs2.getString("email"));
                    _uBean.add(uBean);
                }
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
        return _uBean;
    }
    
    public boolean isQuizStudent(int quizID, int stuID)
    {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isValid = false;

        try
        {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM quizregistration WHERE qid=? and uid=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, quizID);
            pStmnt.setInt(2, stuID);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            if (rs.next())
            {
                isValid = true;
            }

            return isValid;

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
        return isValid;
    }
    
    public boolean addQuizStudent(int quizID, int stuID)
    {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try
        {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO quizregistration (qid, uid) VALUES (?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, quizID);
            pStmnt.setInt(2, stuID);

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
    
    public ArrayList<UserInfo> getNonQuizStudent(int quizID)
    {
        Connection connect = null;
        PreparedStatement pStmt = null;
        Statement stmt = null;
        UserInfo uBean = null;
        ArrayList<UserInfo> _uBean = new ArrayList<UserInfo>();
        try {
            connect = getConnection();
            stmt = connect.createStatement();
            String preQueryStatement = "SELECT * FROM quizregistration where qid = ?";
            pStmt = connect.prepareStatement(preQueryStatement);
            pStmt.setInt(1, quizID);
            
            ResultSet rs = null;
            rs = pStmt.executeQuery();
            while (rs.next()) {
                preQueryStatement = "SELECT * FROM user where id = ?";
                pStmt = connect.prepareStatement(preQueryStatement);
                pStmt.setInt(1, rs.getInt("uid"));
                ResultSet rs2 = null;
                rs2 = pStmt.executeQuery();
                while(rs2.next()){
                    uBean = new UserInfo();
                    // set the record detail to the user bean
                    uBean.setId(rs2.getInt("id"));
                    uBean.setUsername(rs2.getString("username"));
                    uBean.setPassword(rs2.getString("password"));
                    uBean.setRole(rs2.getString("role"));
                    uBean.setEmail(rs2.getString("email"));
                    _uBean.add(uBean);
                }
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
        return _uBean;
    }
    
    public boolean deleteAllStudent(int quizID) {
        Connection connect = null;
        PreparedStatement pStmt = null;
        boolean isSuccess = false;

        try {
            connect = getConnection();
            String preQueryStatement = "DELETE FROM quizregistration WHERE qid =?";
            pStmt = connect.prepareStatement(preQueryStatement);

            pStmt.setInt(1, quizID);

            if (pStmt.execute()) {
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
    
    public ArrayList<UserInfo> queryUserByRole(String role) {
        Connection connect = null;
        PreparedStatement pStmt = null;
        UserInfo uBean = null;
        ArrayList<UserInfo> _uBean = new ArrayList<UserInfo>();
        
        try {
            connect = getConnection();
            String preQueryStatement = "SELECT * FROM user WHERE role =?";
            pStmt = connect.prepareStatement(preQueryStatement);
            pStmt.setString(1, role);
            ResultSet rs = null;
            rs = pStmt.executeQuery();

            if (rs.next()) {
                uBean = new UserInfo();
                // set the record detail to the customer bean
                uBean.setId(rs.getInt("id"));
                uBean.setUsername(rs.getString("username"));
                uBean.setPassword(rs.getString("password"));
                uBean.setRole(rs.getString("role"));
                uBean.setEmail(rs.getString("email"));
                _uBean.add(uBean);
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
        return _uBean;
    }
    
    public ArrayList<QuestionBean> getQuizQuestions(int quizID)
    {
        Connection connect = null;
        PreparedStatement pStmt = null;
        QuestionBean qBean = null;
        ArrayList<QuestionBean> _qBean = new ArrayList<QuestionBean>();
        try {
            connect = getConnection();
            String preQueryStatement = "SELECT * FROM question where QID = ?";
            pStmt = connect.prepareStatement(preQueryStatement);
            pStmt.setInt(1, quizID);
            
            ResultSet rs = null;
            rs = pStmt.executeQuery();
            while (rs.next()) {
                qBean = new QuestionBean();
                
                qBean.setQuestID(rs.getString("questID"));
                qBean.setQID(rs.getInt("QID"));
                qBean.setOptA(rs.getString("optA"));
                qBean.setOptB(rs.getString("optB"));
                qBean.setOptC(rs.getString("optC"));
                qBean.setQuestion(rs.getString("question"));
                qBean.setAns(rs.getString("ans"));
                
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
    
    

}
