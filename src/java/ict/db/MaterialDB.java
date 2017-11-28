/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.CourseBean;
import ict.bean.MaterialBean;
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
public class MaterialDB {
     private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";
    public MaterialDB(String dburl, String dbUser, String dbPassword){
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
        public boolean createMaterial(int cid,String mateName,String mateDesc){
           
         boolean success = false;
            Connection cnt = null;
            PreparedStatement pre = null;
            
            
            try
        {
            cnt = getConnection();
            String preQueryStatement = "INSERT INTO material (cid,mateName,mateDesc) VALUES (?,?,?)";
            pre = cnt.prepareStatement(preQueryStatement);
            pre.setInt(1, cid);
            pre.setString(2, mateName);
            pre.setString(3, mateDesc);
            

            int rowCount = pre.executeUpdate();

            if (rowCount >= 1)
            {
                success = true;
            }
            pre.close();
            cnt.close();
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
            
            return success;
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
        
        public boolean deleteMaterialByID(int id) {
        boolean isSuccess=false;
        Connection connect = null;
        PreparedStatement pStmt = null;
       
        try {
            connect = getConnection();
            String preQueryStatement = "DELETE FROM material WHERE mateID =?";
            pStmt = connect.prepareStatement(preQueryStatement);
            pStmt.setInt(1, id);
            ResultSet rs = null;
            int count=pStmt.executeUpdate();
            
            if(count>0){
            isSuccess=true;
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
        
        
         public ArrayList<MaterialBean> queryMaterialByCID(int id) {
        Connection connect = null;
        PreparedStatement pStmt = null;
        ArrayList<MaterialBean> _cBean = new ArrayList<MaterialBean>();
        MaterialBean mBean = null;
        try {
            connect = getConnection();
            String preQueryStatement = "SELECT * FROM material WHERE cid =?";
            pStmt = connect.prepareStatement(preQueryStatement);
            pStmt.setInt(1, id);
            ResultSet rs = null;
            rs = pStmt.executeQuery();

            while(rs.next()) {
                mBean = new MaterialBean();
                // set the record detail to the customer bean
                mBean.setCid(rs.getInt("cid"));
                mBean.setMateName(rs.getString("mateName"));
                mBean.setMateDesc(rs.getString("mateDesc"));
                _cBean.add(mBean);
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
         public ArrayList<MaterialBean> showAllMaterial() {
        Connection connect = null;
        PreparedStatement pStmt = null;
        ArrayList<MaterialBean> _cBean = new ArrayList<MaterialBean>();
        MaterialBean mBean = null;
        try {
            connect = getConnection();
            String preQueryStatement = "SELECT * FROM material";
            pStmt = connect.prepareStatement(preQueryStatement);
            //pStmt.setInt(1, id);
            ResultSet rs = null;
            rs = pStmt.executeQuery();

            while(rs.next()) {
                mBean = new MaterialBean();
                // set the record detail to the customer bean
                mBean.setCid(rs.getInt("cid"));
                mBean.setMid(rs.getInt("mateID"));
                mBean.setMateName(rs.getString("mateName"));
                mBean.setMateDesc(rs.getString("mateDesc"));
                //System.out.print(rs.getString("mateName"));
                _cBean.add(mBean);
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
}
