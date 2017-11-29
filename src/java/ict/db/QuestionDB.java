/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.QuestionBean;
import ict.bean.QuizBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        public boolean createQuestion(String question,String optA,String optB,String optC,String ans,String quizID){
            boolean success = false;
            Connection cnt = null;
            PreparedStatement pre = null;
            
            
            try
        {
            cnt = getConnection();
            String preQueryStatement = "INSERT INTO question (question,QID,optA, optB, optC,ans) VALUES (?,?,?,?,?,?)";
            pre = cnt.prepareStatement(preQueryStatement);
            pre.setString(1, question);
            pre.setString(2,quizID);
            pre.setString(3, optA);
            pre.setString(4, optB);
            pre.setString(5, optC);
            pre.setString(6, ans);
       


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
        public boolean updateQuestion(String questID,String question,String optA,String optB,String optC,String corrAns){
            boolean isSuccess = false;
            Connection cnt = null;
            PreparedStatement pre = null;
            
            try{
                cnt = getConnection();

                String preQueString = "UPDATE question SET question=?,optA=?,optB=?,optC=?,ans=? WHERE questID=?";

                pre=cnt.prepareStatement(preQueString);
                pre.setString(1,question);
                pre.setString(2, optA);
                pre.setString(3,optB);
                pre.setString(4,optC);
                pre.setString(5,corrAns);
                pre.setInt(6,Integer.parseInt(questID));
                
                int rowCount= pre.executeUpdate();
                if(rowCount==1)
                    isSuccess=true;
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
            return isSuccess;
        }
        public boolean deleteQuestion(String id){
            boolean isSuccess = false;
            Connection cnt = null;
            PreparedStatement pre = null;
            
            try{
                cnt = getConnection();
                
                String preQueString = "Delete from question where questID = ?;";
                pre=cnt.prepareStatement(preQueString);
                pre.setString(1, id);
                int rowCount = pre.executeUpdate();
                if (rowCount >= 1)
            {
                isSuccess = true;
            }
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
            
            return isSuccess;
        }
        
        public QuestionBean getOneQuestion(String qid){
            QuestionBean qBean =null;
            Connection connect = null;
            PreparedStatement pStmt = null;
            try{
                connect = getConnection();
                String preQueString = "SELECT * FROM question WHERE questID = ?;";
                pStmt = connect.prepareStatement(preQueString);
                pStmt.setString(1,qid);

               
                ResultSet rs = null;
                rs=pStmt.executeQuery();
                 
               if(rs.next()) {
                qBean = new QuestionBean();
                qBean.setQuestID(rs.getString("questID"));
                qBean.setQID(rs.getInt("QID"));
                qBean.setOptA(rs.getString("optA"));
                qBean.setOptB(rs.getString("optB"));
                qBean.setOptC(rs.getString("optC"));
                qBean.setQuestion(rs.getString("question"));
                qBean.setAns(rs.getString("ans"));
            }
            pStmt.close();
            connect.close();
            connect.close();
            }
            
            catch(Exception e){
                e.printStackTrace();
            }
            return qBean;
        }
        public ArrayList<QuestionBean> getAllQuestion (String qid)
    {
        Connection connect = null;
        PreparedStatement pStmt = null;
        QuestionBean qBean = null;
        ArrayList<QuestionBean> _qBean = new ArrayList<QuestionBean>();
        try {
            connect = getConnection();
            String preQueryStatement = "SELECT * FROM question where QID = ?";
            pStmt = connect.prepareStatement(preQueryStatement);
            pStmt.setString(1,qid);

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
