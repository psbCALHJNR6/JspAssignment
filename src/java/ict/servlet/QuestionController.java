package ict.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ict.bean.QuestionBean;
import ict.bean.QuizBean;
import ict.db.QuestionDB;
import ict.db.QuizDB;
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
    QuizDB qdb = null;
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
        qdb = new QuizDB(dbUrl, dbUser, dbPassword);
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
             else if(action.equals("view")){
                 this.showOneQuestion(req,res);
             }
             else if(action.equals("makeForm")){
                 makeForm(req,res);
             }
             else if(action.equals("assign"))
                 assignQuestion(req,res);
             
             else if(action.equals("stu_create")){
                 studentCreateQuestion(req,res);
             }
             else if(action.equals("updateAssign"))
                 updateAssign(req,res);
        }

        
        
        
       
        
        protected void updateAssign(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
            String [] allQ = req.getParameterValues("allQ");
//            String quizid = req.getParameter("quizID");
//            QuestionBean qBean;
//            for(int i=0;i<allQ.length;i++){
//                qBean = db.getQuestionFromPool(allQ[i]);
//                db.createQuestion(qBean.getQuestion(), qBean.getOptA(), qBean.getOptB(), qBean.getOptC(), qBean.getAns(), quizid);
//            }
            PrintWriter out = res.getWriter();
            out.print(db.getQuestionFromPool(allQ[0]).getQuestion());
            
        }
        
        
        protected void deleteQuestion(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
            String id = req.getParameter("id");
            boolean isSuccess = db.deleteQuestion(id);
            PrintWriter out = res.getWriter();
            if(isSuccess)
                out.print("<script type='text/javascript'>alert('Delete successful');</script>");
            else
                out.print("<script type='text/javascript'>alert('Cannot delete question');</script>");
            res.sendRedirect("QuestionController?action=list&id="+req.getParameter(id));
        }
 
        
        
        protected void updateQuestion(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
            PrintWriter out = res.getWriter();
           String questid = req.getParameter("questid");
           String question = req.getParameter("question");
           String optA = req.getParameter("optA");
           String optB = req.getParameter("optB");
           String optC = req.getParameter("optC");
           String corrAns = req.getParameter("corrAns");
           String quiz = req.getParameter("quiz");
           
           boolean isSuccess = db.updateQuestion(questid,question,optA,optB,optC,corrAns,quiz); 
           if(isSuccess)
            out.print("<script type='text/javascript'>alert('Update successful');</script>");
           else
            out.print("<script type='text/javascript'>alert('Update successful');</script>");
           
        }
        protected void createQuestion(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
            String question = req.getParameter("qName");
            String optA = req.getParameter("optA");
            String optB = req.getParameter("optB");
            String optC = req.getParameter("optC");
            String ans = req.getParameter("corrAns");
            
            boolean isSuccess = db.createIntoPool(question, optA, optB, optC, ans);
            PrintWriter out = res.getWriter();
            if(isSuccess)
                out.print("<script type='text/javascript'>alert('Added successful');</script>");   
            else{
                out.print(isSuccess+"<br>");
                out.print(req.getParameter("quiz"));
            }
            
        }
        
        protected void studentCreateQuestion(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
            String question = req.getParameter("qName");
            String stuid = req.getParameter("stuid");
            String optA = req.getParameter("optA");
            String optB = req.getParameter("optB");
            String optC = req.getParameter("optC");
            String ans = req.getParameter("corrAns");
            String quizID = req.getParameter("quiz");
            
            boolean isSuccess = db.createQuestion(question, optA, optB, optC, ans,quizID);
            PrintWriter out = res.getWriter();
            if(isSuccess)
                out.print("<script type='text/javascript'>alert('Added successful');</script>");   
            else{
                out.print(isSuccess+"<br>");
                out.print(req.getParameter("quiz"));
            }
            res.sendRedirect("QuizController?action=stuquizlist&id=" + stuid);
            
        }
        
        protected void showOneQuestion(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
                        PrintWriter out = res.getWriter();
            QuestionBean qBean = new QuestionBean();
            ArrayList<QuizBean> _quizBean = new ArrayList<QuizBean>();
            String targetURL = "question_viewone.jsp";
            
            _quizBean = qdb.getAllQuiz();
            String id = req.getParameter("id");  
            qBean = db.getOneQuestion(id);
            req.setAttribute("quizList",_quizBean);
            req.setAttribute("qBean", qBean);
            
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/" + targetURL);
           rd.forward(req, res);

        }
        
        protected void showAllQuestion(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
            ArrayList<QuestionBean> _question = new ArrayList<QuestionBean>();
            String targetURL = "question_view.jsp";
            _question = db.getAllQuestion(req.getParameter("id"));
            req.setAttribute("questionList",_question);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/" + targetURL);
            rd.forward(req, res);
        }
        
         protected void assignQuestion(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
                ArrayList<QuestionBean> _question = new ArrayList<QuestionBean>();
                String targetURL="question_assign.jsp";
                _question = db.getAllQuestionInPool();
                req.setAttribute("questionList",_question);
                 RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/" + targetURL);
            rd.forward(req, res);
        }
        
        protected void makeForm(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{

            String targetURL = "question_create.jsp";
            
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/" + targetURL);
            rd.forward(req, res);
            
        }
        
 }

        


