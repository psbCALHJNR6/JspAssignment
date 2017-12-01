/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.CourseBean;
import ict.bean.QuestionBean;
import ict.bean.QuizBean;
import ict.bean.ResultBean;
import ict.bean.UserInfo;
import ict.db.CourseDB;
import ict.db.QuizDB;
import ict.db.ResultDB;
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
 * @author psb
 */
@WebServlet(name = "QuizController", urlPatterns =
{
    "/QuizController"
})
public class QuizController extends HttpServlet
{

    QuizDB db;
    CourseDB cdb;
    ResultDB rdb;

    @Override
    public void init() throws ServletException
    {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new QuizDB(dbUrl, dbUser, dbPassword);
        cdb = new CourseDB(dbUrl, dbUser, dbPassword);
        rdb = new ResultDB(dbUrl, dbUser, dbPassword);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String action = request.getParameter("action");

        if (action.equals("list"))
        {
            showAllQuiz(request, response);
        }
        else if (action.equals("createForm"))
        {
            createForm(request, response);
        }
        else if (action.equals("create"))
        {
            createQuiz(request, response);
        }
        else if (action.equals("stuquizlist"))
        {
            studentQuizList(request, response);
        }
        else if (action.equals("editForm"))
        {
            editForm(request, response);
        }
        else if (action.equals("edit"))
        {
            editQuiz(request, response);
        }
        else if (action.equals("startquiz"))
        {
            startQuiz(request, response);
        }
        else if (action.equals("submitquiz"))
        {
            submitQuiz(request, response);
        }
        else if (action.equals("stu_quizresult"))
        {
            showStudentResult(request, response);
        }
        else if (action.equals("quizresult"))
        {
            showQuizResult(request, response);
        }
    }

    protected void createQuiz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        int duration = Integer.parseInt(request.getParameter("duration"));
        int attemptTime = Integer.parseInt(request.getParameter("attemptime"));
        String startDate = request.getParameter("startdate");
        String endDate = request.getParameter("enddate");
        int cid = Integer.parseInt(request.getParameter("cid"));
        String description = request.getParameter("description");

        boolean isSucces = db.addQuiz(duration, attemptTime, startDate, endDate, cid, description);
        
        PrintWriter out = response.getWriter();
        out.print("<script type='text/javascript'>alert('Create successful');</script>");
        response.sendRedirect("QuizController?action=list");
        
               
    }

    protected void showAllQuiz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ArrayList<QuizBean> _quizs = new ArrayList<QuizBean>();
        String targetURL = "";
        _quizs = db.getAllQuiz();
        request.setAttribute("quizlist", _quizs);
        targetURL = "teacher_quizlist.jsp";

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }

    protected void createForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ArrayList<CourseBean> _courses = new ArrayList<CourseBean>();
        _courses = cdb.getAllCourse();
        String targetURL = "";
        request.setAttribute("courselist", _courses);
        targetURL = "teacher_createquiz.jsp";

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }

    protected void studentQuizList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ArrayList<QuizBean> _quizs = new ArrayList<QuizBean>();
        String targetURL = "";
        _quizs = db.getStudentQuiz(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("quizlist", _quizs);
        targetURL = "student_quizlist.jsp";

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }

    protected void editForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        int id = Integer.parseInt(req.getParameter("id"));

        QuizBean _quizBean = new QuizBean();
        _quizBean = db.queryQuizByID(id);

        ArrayList<CourseBean> _courses = new ArrayList<CourseBean>();
        _courses = cdb.getAllCourse();

        String targetURL = "";
        req.setAttribute("quizDetail", _quizBean);
        req.setAttribute("courselist", _courses);
        //get quiz student..........
        ArrayList<UserInfo> quizStudent = db.getQuizStudent(id);
        req.setAttribute("quizstudent", quizStudent);

        ArrayList<UserInfo> nQuizStudent = db.queryUserByRole("STUDENT");
        req.setAttribute("nQuizstudent", nQuizStudent);

        targetURL = "teacher_editquiz.jsp";

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(req, resp);

    }

    protected void editQuiz(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("qid"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        int attemptTime = Integer.parseInt(request.getParameter("attemptime"));
        String startDate = request.getParameter("startdate");
        String endDate = request.getParameter("enddate");
        int cid = Integer.parseInt(request.getParameter("cid"));
        String description = request.getParameter("description");
        String[] _stuIDs = request.getParameterValues("student");

        db.deleteAllStudent(id);
        if (_stuIDs != null)
        {

            for (int i = 0; i < _stuIDs.length; i++)
            {
                int stuID = Integer.parseInt(_stuIDs[i]);

                if (!db.isQuizStudent(id, stuID))
                {
                    db.addQuizStudent(id, stuID);
                }
            }
        }

        boolean isSuccess = false;

        isSuccess = db.updateQuiz(id, duration, attemptTime, startDate, endDate, cid, description);

        if (isSuccess)
        {
            PrintWriter out = resp.getWriter();
        out.print("<script type='text/javascript'>alert('Update successful');</script>");
            resp.sendRedirect("QuizController?action=editForm&id=" + id);
        }
    }

    private void startQuiz(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int quizID = Integer.parseInt(req.getParameter("quizID"));
        int stuID = Integer.parseInt(req.getParameter("stuID"));
        PrintWriter out = resp.getWriter();
        
        out.print("<script type=\"text/javascript\">alert('Hello');</script>");
        QuizBean qBean = db.queryQuizByID(quizID);
        if(db.attemptTime(quizID, stuID) >= qBean.getAttemptTime()){        //no attemptTimt
             resp.sendRedirect("QuizController?action=stuquizlist&id="+stuID);
        }

        ArrayList<QuestionBean> _questions = new ArrayList<QuestionBean>();
        _questions = db.getQuizQuestions(quizID);

        QuizBean _quizBean = new QuizBean();
        _quizBean = db.queryQuizByID(quizID);

        String targetURL = "";
        req.setAttribute("quizDetail", _quizBean);
        req.setAttribute("quizQuestions", _questions);

        targetURL = "quiz.jsp";

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(req, resp);
    }

    private void submitQuiz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int stuID = Integer.parseInt(request.getParameter("stuID"));
        int quizID = Integer.parseInt(request.getParameter("quizID"));
        int qNum = Integer.parseInt(request.getParameter("qNum"));
        int qMark = 100 / qNum;
        int totalMark = 0;
        String stuAns = "";
        String correctAns = "";
        String targetURL = "";
        
        for (int i = 1; i <= qNum; i++)
        {
            stuAns = request.getParameter("answer" + i);
            correctAns = request.getParameter("correctAnswer" + i);
            if(stuAns == null || correctAns == null){
                continue;
            }
            if (stuAns.equals(correctAns))
            {
                totalMark += qMark;
            }

        }
        
        QuizBean qBean = db.queryQuizByID(quizID);
        
        if(db.attemptTime(quizID, stuID) < qBean.getAttemptTime()){
            db.addQuizRecord(quizID, stuID, totalMark);
        }
        
        if(totalMark >= 75){
            targetURL = "student_createquestion.jsp";
            request.setAttribute("mark", totalMark);
            request.setAttribute("quizID", quizID);
            request.setAttribute("quizDetail", qBean);

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/" + targetURL);
            rd.forward(request, response);
        }
        
        System.out.print(stuID);
        System.out.print(totalMark);
//        
        int [] marks = db.queryQuizMarkByID(quizID);
        
        request.setAttribute("highest", marks[0]);
        request.setAttribute("lowest", marks[1]);
        request.setAttribute("average", marks[2]);
        request.setAttribute("canAttemptTime", qBean.getAttemptTime() - db.attemptTime(quizID, stuID));
        
        

        targetURL = "student_quizresult.jsp";

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
        
        

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

    private void showStudentResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int stuID = Integer.parseInt(request.getParameter("stuID"));
        int quizID = Integer.parseInt(request.getParameter("quizID"));
        int [] marks = new int[3];
        try{
            marks = rdb.queryStudentQuizMark(quizID, stuID);
        }catch(Exception ex){
            response.sendRedirect("/noquizresult.jsp");
            return;
        }
        
        QuizBean qBean = db.queryQuizByID(quizID);
        ArrayList<ResultBean> quizScores = db.getStudentQuizScores(quizID, stuID);
        request.setAttribute("highest", marks[0]);
        request.setAttribute("lowest", marks[1]);
        request.setAttribute("average", marks[2]);
        request.setAttribute("canAttemptTime", qBean.getAttemptTime() - db.attemptTime(quizID, stuID));
        request.setAttribute("scores", quizScores);
        
        request.setAttribute("quizDetail", qBean);
        
        String targetURL = "";

        targetURL = "student_quizresult.jsp";

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }

    private void showQuizResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int quizID = Integer.parseInt(request.getParameter("quizID"));
        int [] marks = new int[3];
        try{
            marks = rdb.queryQuizMarkByID(quizID);
        }catch(Exception ex){
            response.sendRedirect("/noquizresult.jsp");
            return;
        }
        
        QuizBean qBean = db.queryQuizByID(quizID);
        ArrayList<ResultBean> quizScores = db.getQuizScores(quizID);
        request.setAttribute("highest", marks[0]);
        request.setAttribute("lowest", marks[1]);
        request.setAttribute("average", marks[2]);
        request.setAttribute("quizDetail", qBean);
        request.setAttribute("scores", quizScores);
        
        String targetURL = "";

        targetURL = "quizresult.jsp";

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }

}
