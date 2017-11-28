package ict.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ict.bean.QuestionBean;
import ict.db.QuestionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hong
 */
@WebServlet(name="QuestionController",urlPatterns = {"/QuestionController"})
public class QuestionController extends HttpServlet {
    QuestionDB db = null;
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        processRequest(req,res);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        processRequest(req, resp);

    }
    @Override
    public void init(){
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new QuestionDB(dbUrl, dbUser, dbPassword);
    }


        protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
             res.setContentType("text/html;charset=UTF-8");
             String action = req.getParameter("action");
             if(action.equals("create"))
                 createQuestion(req,res);
             else if(action.equals("list"))
                   showAllQuestion(req,res);
             else if(action.equals("delete"))
                 deleteQuestion(req,res);
             else if(action.equals("update"))
                 updateQuestion(req,res);
             else if(action.equals("view"))
                 showOneQuestion(req,res);
                     
        }
        
        protected void createQuestion(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
            String question = req.getParameter("qName");
            String optA = req.getParameter("optA");
            String optB = req.getParameter("optB");
            String optC = req.getParameter("optC");
            String ans = req.getParameter("corrAns");
            
            boolean isSuccess = db.createQuestion(question, optA, optB, optC, ans);
            PrintWriter out = res.getWriter();
            if(isSuccess)
                out.print("<script type='text/javascript'>alert('Added successful');</script>");                
        }
        protected void deleteQuestion(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
            String id = req.getParameter("id");
            boolean isSuccess = db.deleteQuestion(id);
            PrintWriter out = res.getWriter();
            if(isSuccess)
                out.print("Delete successful");  
        }
        
        protected void updateQuestion(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
            
        }
        
        protected void showOneQuestion(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
            QuestionBean qBean = new QuestionBean();
            String targetURL = "question_viewone.jsp";
            qBean = db.getOneQuestion(req.getParameter("id"));
            req.setAttribute("qBean", qBean);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/" + targetURL);
            rd.forward(req, res);
        }
        
        protected void showAllQuestion(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
            ArrayList<QuestionBean> _question = new ArrayList<QuestionBean>();
            String targetURL = "question_view.jsp";
            _question = db.getAllQuestion("1");
            req.setAttribute("questionList",_question);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/" + targetURL);
            rd.forward(req, res);
        }
 }
        


