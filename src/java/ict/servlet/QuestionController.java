package ict.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ict.db.QuestionDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hong
 */
@WebServlet(name="QuestionController",urlPatterns = {"/qController"})
public class QuestionController extends HttpServlet {
    QuestionDB db = null;
    protected void doPost(HttpServletRequest req, HttpServletResponse res){
        
        
    }
    @Override
    public void init(){
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new QuestionDB(dbUrl, dbUser, dbPassword);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}
