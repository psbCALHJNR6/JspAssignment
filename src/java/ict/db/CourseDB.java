/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.CourseBean;
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
 * @author hong
 */
public class CourseDB
{

    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public CourseDB(String dburl, String dbUser, String dbPassword)
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
    
    public CourseBean queryCourseByID(int id) {
        Connection connect = null;
        PreparedStatement pStmt = null;
        CourseBean cBean = null;
        try {
            connect = getConnection();
            String preQueryStatement = "SELECT * FROM course WHERE cid =?";
            pStmt = connect.prepareStatement(preQueryStatement);
            pStmt.setInt(1, id);
            ResultSet rs = null;
            rs = pStmt.executeQuery();

            if (rs.next()) {
                cBean = new CourseBean();
                // set the record detail to the customer bean
                cBean.setCid(rs.getInt("cid"));
                cBean.setcName(rs.getString("cname"));
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
        return cBean;
    }
    
    public ArrayList<UserInfo> getAllCourseStu(int courseID){
        Connection connect = null;
        PreparedStatement pStmt = null;
        UserInfo uBean = null;
        ArrayList<UserInfo> _uBeans = new ArrayList<UserInfo>();
        try {
            connect = getConnection();
            String preQueryStatement = "SELECT user.* FROM courseregistration, user where courseregistration.uid = user.id and courseregistration.cid = ?";
            pStmt = connect.prepareStatement(preQueryStatement);
            pStmt.setInt(1, courseID);
            ResultSet rs = null;
            rs = pStmt.executeQuery();

            if (rs.next()) {
                uBean = new UserInfo();
                // set the record detail to the customer bean
                uBean.setId(rs.getInt("id"));
                uBean.setUsername(rs.getString("username"));
                uBean.setRole(rs.getString("role"));
                uBean.setEmail(rs.getString("email"));
                _uBeans.add(uBean);
                
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
        return _uBeans;
    }
     public ArrayList<CourseBean> getAllCourse()
    {
        Connection connect = null;
        PreparedStatement pStmt = null;
        CourseBean cBean = null;
        ArrayList<CourseBean> _cBean = new ArrayList<CourseBean>();
        try {
            connect = getConnection();
            String preQueryStatement = "SELECT * FROM course";
            pStmt = connect.prepareStatement(preQueryStatement);

            ResultSet rs = null;
            rs = pStmt.executeQuery();
            while (rs.next()) {
                cBean = new CourseBean();
                // set the record detail to the user bean
                cBean.setCid(rs.getInt("cid"));
                cBean.setcName(rs.getString("cname"));
                _cBean.add(cBean);
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
        return _cBean;
    }
    public ArrayList<CourseBean> getCourseforStu(int id)
    {System.out.print(id);
        Connection connect = null;
        PreparedStatement pStmt = null;
        CourseBean cBean = null;
        ArrayList<CourseBean> _cBean = new ArrayList<CourseBean>();
        try {
            connect = getConnection();
            String preQueryStatement = "SELECT * FROM courseregistration,course WHERE courseregistration.cid=course.cid and courseregistration.uid=?";
            pStmt = connect.prepareStatement(preQueryStatement);
            pStmt.setInt(1, id);
            ResultSet rs = null;
            rs = pStmt.executeQuery();
            while (rs.next()) {
                cBean = new CourseBean();
                // set the record detail to the user bean
                cBean.setCid(rs.getInt("cid"));
                cBean.setcName(rs.getString("cname"));
                //System.out.print(rs.getString("cname"));
                _cBean.add(cBean);
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
        return _cBean;
    }
    
    public boolean addCourse(String username)
    {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try
        {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO course (CNAME) VALUES (?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, username);

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
    
    public ArrayList<UserInfo> getCourseStudent(int courseID)
    {
        Connection connect = null;
        PreparedStatement pStmt = null;
        UserInfo uBean = null;
        ArrayList<UserInfo> _uBean = new ArrayList<UserInfo>();
        try
        {
            connect = getConnection();
            String preQueryStatement = "SELECT * FROM courseregistration where cid = ?";
            pStmt = connect.prepareStatement(preQueryStatement);
            pStmt.setInt(1, courseID);

            ResultSet rs = null;
            rs = pStmt.executeQuery();
            while (rs.next())
            {
                preQueryStatement = "SELECT * FROM user where id = ?";
                pStmt = connect.prepareStatement(preQueryStatement);
                pStmt.setInt(1, rs.getInt("uid"));
                ResultSet rs2 = null;
                rs2 = pStmt.executeQuery();
                while (rs2.next())
                {
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
        return _uBean;
    }
    
    public boolean deleteAllStudent(int courseID)
    {
        Connection connect = null;
        PreparedStatement pStmt = null;
        boolean isSuccess = false;

        try
        {
            connect = getConnection();
            String preQueryStatement = "DELETE FROM courseregistration WHERE cid =?";
            pStmt = connect.prepareStatement(preQueryStatement);

            pStmt.setInt(1, courseID);

            if (pStmt.execute())
            {
                isSuccess = true;
            }

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
        return isSuccess;
    }
    
    public boolean isCourseStudent(int courseID, int stuID)
    {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isValid = false;

        try
        {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM courseregistration WHERE cid=? and uid=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, courseID);
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
    
    public boolean addCourseStudent(int courseID, int stuID)
    {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try
        {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO courseregistration (cid, uid) VALUES (?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, courseID);
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
}
