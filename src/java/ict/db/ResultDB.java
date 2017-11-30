/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

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
        UserInfo uBean = null;
        ArrayList<UserInfo> _uBean = new ArrayList<UserInfo>();
        try {
            connect = getConnection();
            String preQueryStatement = "SELECT * FROM result";
            pStmt = connect.prepareStatement(preQueryStatement);

            ResultSet rs = null;
            rs = pStmt.executeQuery();
            while (rs.next()) {
                uBean = new UserInfo();
                // set the record detail to the user bean
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
    
    
    
    
    
}
