/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionDB {
    
    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";
    public QuestionDB(String dburl, String dbUser, String dbPassword){
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
        public boolean createQuestion(String question,String optA,String optB,String optC,String optD,String ans){
            boolean success = false;
            Connection cnt = null;
            PreparedStatement pre = null;
            
            
            try
        {
            cnt = getConnection();
            String preQueryStatement = "INSERT INTO question (question,QID,optA, optB, optC,optD,ans) VALUES (?,?,?,?,?,?,?)";
            pre = cnt.prepareStatement(preQueryStatement);
            pre.setString(1, question);
            pre.setInt(2,1);
            pre.setString(3, optA);
            pre.setString(4, optB);
            pre.setString(5, optC);
            pre.setString(6, optD);
            pre.setString(7, ans);

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
            catch(Exception e){
                e.printStackTrace();
            }
            
            return success;
        }
        
    
}
