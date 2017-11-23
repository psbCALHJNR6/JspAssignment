/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

/**
 *
 * @author hong
 */
import ict.db.MaterialDB;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hong
 */
@WebServlet(name="MaterialController",urlPatterns = {"/mController"})
public class MaterialController extends HttpServlet{
    MaterialDB db=null;
       protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        processRequest(req,res);
    }
    @Override
    public void init(){
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new MaterialDB(dbUrl, dbUser, dbPassword);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

        protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
             res.setContentType("text/html;charset=UTF-8");
             String action = req.getParameter("action");
             if("action".equals("create")){
                 createQuestion(req,res);
             }
        }
        
        protected void createQuestion(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
            String question = req.getParameter("question");
            String optA = req.getParameter("optA");
            String optB = req.getParameter("optB");
            String optC = req.getParameter("optC");
            String optD = req.getParameter("optD");
            String ans = req.getParameter("ans");
            
            boolean isSuccess = db.createMaterial( optC, optD, ans);
             
        }
    
    
    
    
    
}
