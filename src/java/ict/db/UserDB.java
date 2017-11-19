/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

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
 * @author a1
 */
public class UserDB
{

    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public UserDB(String dburl, String dbUser, String dbPassword)
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

    public boolean isValidUser(String user, String pwd)
    {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isValid = false;

        try
        {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM user WHERE username=? and password=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, user);
            pStmnt.setString(2, pwd);

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

    public void CreateUserTable()
    {
        Statement stmnt = null;
        Connection cnnct = null;

        try
        {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS user ("
                    + "id varchar(5) NOT NULL, "
                    + "username varchar(25) NOT NULL, "
                    + "password varchar(25) NOT NULL, "
                    + "role varchar(25) NOT NULL, "
                    + "email varchar(255) NOT NULL, "
                    + "PRIMARY KEY(id)"
                    + ")";

            stmnt.execute(sql);
            stmnt.close();
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
    }

    public boolean addUser(String user, String pwd, String role, String email)
    {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try
        {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO user (USERNAME, PASSWORD, ROLE, EMAIL) VALUES (?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, user);
            pStmnt.setString(2, pwd);
            pStmnt.setString(3, role);
            pStmnt.setString(4, email);

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

    public String getUserRole(String username)
    {
        //...........
        return "student";
    }

    public ArrayList<UserInfo> getAllUser()
    {
        Connection connect = null;
        PreparedStatement pStmt = null;
        UserInfo uBean = null;
        ArrayList<UserInfo> _uBean = new ArrayList<UserInfo>();
        try {
            connect = getConnection();
            String preQueryStatement = "SELECT * FROM user";
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
    
    public UserInfo queryUserByUsername(String username) {
        Connection connect = null;
        PreparedStatement pStmt = null;
        UserInfo uBean = null;
        try {
            connect = getConnection();
            String preQueryStatement = "SELECT * FROM user WHERE username =?";
            pStmt = connect.prepareStatement(preQueryStatement);
            pStmt.setString(1, username);
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
        return uBean;
    }
    
    public boolean updateUser(String id, String username,String password, String role, String email) {
        Connection connect = null;
        PreparedStatement pStmt = null;
        boolean isSuccess = false;

        try {
            connect = getConnection();
            String preQueryStatement = "UPDATE user SET username = ?, role=?, password=?, email=? WHERE id =?";
            pStmt = connect.prepareStatement(preQueryStatement);

            pStmt.setString(1, username);
            pStmt.setString(2, role);
            pStmt.setString(3, password);
            pStmt.setString(4, email);
            pStmt.setString(6, id);

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

    public boolean deleteUser(int id) {
        Connection connect = null;
        PreparedStatement pStmt = null;
        boolean isSuccess = false;

        try {
            connect = getConnection();
            String preQueryStatement = "DELETE FROM user WHERE id =?";
            pStmt = connect.prepareStatement(preQueryStatement);

            pStmt.setInt(1, id);

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
}
