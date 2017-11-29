/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.QuizBean;
import ict.bean.ResultBean;
import ict.bean.UserInfo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author psb
 */
public class ResultDB
{
    
    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public ResultDB(String dburl, String dbUser, String dbPassword)
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
    
    public boolean addResult(int quizID, int stuID, int score)
    {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try
        {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO result (qid, uid, score) VALUES (?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, quizID);
            pStmnt.setInt(2, stuID);
            pStmnt.setInt(3, score);

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
    
    public ArrayList<ResultBean> getAllUser()
    {
        Connection connect = null;
        PreparedStatement pStmt = null;
        ResultBean rBean = null;
        ArrayList<ResultBean> _rBean = new ArrayList<ResultBean>();
        try {
            connect = getConnection();
            String preQueryStatement = "SELECT * FROM result";
            pStmt = connect.prepareStatement(preQueryStatement);

            ResultSet rs = null;
            rs = pStmt.executeQuery();
            while (rs.next()) {
                rBean = new ResultBean();
                // set the record detail to the user bean
                rBean.setQid(rs.getInt("qid"));
                rBean.setUid(rs.getInt("uid"));
                rBean.setScore(rs.getInt("score"));
                _rBean.add(rBean);
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
        return _rBean;
    }
    
    public ArrayList<ResultBean> queryResultByUid(int uid)
    {
        Connection connect = null;
        PreparedStatement pStmt = null;
        ResultBean rBean = null;
        ArrayList<ResultBean> _rBean = new ArrayList<ResultBean>();
        try {
            connect = getConnection();
            String preQueryStatement = "SELECT * FROM result where uid =?";
            pStmt = connect.prepareStatement(preQueryStatement);
            
            pStmt.setInt(1, uid);
            
            ResultSet rs = null;
            rs = pStmt.executeQuery();
            while (rs.next()) {
                rBean = new ResultBean();
                // set the record detail to the user bean
                rBean.setQid(rs.getInt("qid"));
                rBean.setUid(rs.getInt("uid"));
                rBean.setScore(rs.getInt("score"));
                _rBean.add(rBean);
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
        return _rBean;
    }
    
    public ArrayList<ResultBean> queryResultByQid(int qid)
    {
        Connection connect = null;
        PreparedStatement pStmt = null;
        ResultBean rBean = null;
        ArrayList<ResultBean> _rBean = new ArrayList<ResultBean>();
        try {
            connect = getConnection();
            String preQueryStatement = "SELECT * FROM result where qid =?";
            pStmt = connect.prepareStatement(preQueryStatement);
            
            pStmt.setInt(1, qid);
            
            ResultSet rs = null;
            rs = pStmt.executeQuery();
            while (rs.next()) {
                rBean = new ResultBean();
                // set the record detail to the user bean
                rBean.setQid(rs.getInt("qid"));
                rBean.setUid(rs.getInt("uid"));
                rBean.setScore(rs.getInt("score"));
                _rBean.add(rBean);
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
        return _rBean;
    }
    
    public int[] queryQuizMarkByID(int id)
    {
        Connection connect = null;
        PreparedStatement pStmt = null;
        QuizBean qBean = null;

        int[] marks = new int[3];
        try
        {
            connect = getConnection();
            String preQueryStatement = "SELECT * FROM result WHERE qid =?";
            pStmt = connect.prepareStatement(preQueryStatement);
            pStmt.setInt(1, id);
            ResultSet rs = null;
            rs = pStmt.executeQuery();
            int rowCount = 0;
            int totalMark = 0;
            int highest = 0;
            int average = 0;
            int lowest = 100;

            while (rs.next())
            {
                if (rs.getInt("score") > highest)
                {
                    highest = rs.getInt("score");
                }
                if (rs.getInt("score") < lowest)
                {
                    lowest = rs.getInt("score");
                }
                totalMark += rs.getInt("score");
                rowCount++;
            }
            average = totalMark / rowCount;
            marks[0] = highest;
            marks[1] = lowest;
            marks[2] = average;

            pStmt.close();
            connect.close();
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
        return marks;
    }

    public int[] queryStudentQuizMark(int quizID, int stuID)
    {
        Connection connect = null;
        PreparedStatement pStmt = null;
        QuizBean qBean = null;

        int[] marks = new int[3];
        try
        {
            connect = getConnection();
            String preQueryStatement = "SELECT * FROM result WHERE qid =? and uid = ?";
            pStmt = connect.prepareStatement(preQueryStatement);
            pStmt.setInt(1, quizID);
            pStmt.setInt(2, stuID);
            ResultSet rs = null;
            rs = pStmt.executeQuery();
            int rowCount = 0;
            int totalMark = 0;
            int highest = 0;
            int average = 0;
            int lowest = 100;

            while (rs.next())
            {
                if (rs.getInt("score") > highest)
                {
                    highest = rs.getInt("score");
                }
                if (rs.getInt("score") < lowest)
                {
                    lowest = rs.getInt("score");
                }
                totalMark += rs.getInt("score");
                rowCount++;
            }
            average = totalMark / rowCount;
            marks[0] = highest;
            marks[1] = lowest;
            marks[2] = average;

            pStmt.close();
            connect.close();
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
        return marks;
    }
    
    
    
    
    
    
    
    
    
    
}
