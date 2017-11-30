/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.CourseBean;
import ict.bean.MaterialBean;
import ict.bean.UserInfo;
import ict.db.CourseDB;
import ict.db.MaterialDB;
import ict.db.UserDB;
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
@WebServlet(name = "CourseController", urlPatterns =
{
    "/CourseController"
})
public class CourseController extends HttpServlet
{

    CourseDB db;
    MaterialDB db2;
    UserDB udb;

    @Override
    public void init() throws ServletException
    {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new CourseDB(dbUrl, dbUser, dbPassword);
        db2 = new MaterialDB(dbUrl, dbUser, dbPassword);
        udb = new UserDB(dbUrl, dbUser, dbPassword);
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            String action = request.getParameter("action");
            if (action.equals("create"))
            {
                createCourse(request, response);
            }
            else if (action.equals("list"))
            {
                showAllCourse(request, response);
            }
            else if (action.equals("coursedetail"))
            {
                courseDetail(request, response);
            }
            else if (action.equals("courses"))
            {

                courseForStudent(request, response);
            }
            else if (action.equals("regcourse"))
            {

                regCourse(request, response);
            }
        }
    }

    protected void createCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username = request.getParameter("coursename");

        boolean isSucces = db.addCourse(username);
        PrintWriter out = response.getWriter();
        out.print("<script type='text/javascript'>alert('Create successful');</script>");
        response.sendRedirect("CourseController?action=list");
    }

    protected void courseForStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        ArrayList<CourseBean> _courses = new ArrayList<CourseBean>();
        _courses = db.getCourseforStu(id);
        ArrayList<MaterialBean> _mate = new ArrayList<MaterialBean>();
        request.setAttribute("courses", _courses);
        _mate = db2.showAllMaterial();
        request.setAttribute("mate", _mate);
        //response.sendRedirect("student_courses.jsp");
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + "student_courses.jsp");
        rd.forward(request, response);
    }

    protected void showAllCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ArrayList<CourseBean> _courses = new ArrayList<CourseBean>();
        String targetURL = "";
        _courses = db.getAllCourse();
        request.setAttribute("courselist", _courses);
        targetURL = "teacher_courselist.jsp";

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }

    protected void courseDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ArrayList<UserInfo> _students = new ArrayList<UserInfo>();
        String targetURL = "";
        CourseBean cBean = db.queryCourseByID(Integer.parseInt(request.getParameter("courseID")));
        _students = db.getAllCourseStu(Integer.parseInt(request.getParameter("courseID")));
        request.setAttribute("studentlist", _students);
        request.setAttribute("coursedetail", cBean);
       


        ArrayList<UserInfo> nCourseStudent = udb.queryUserByRole("STUDENT");
        request.setAttribute("nCourseStudent", nCourseStudent);

        targetURL = "teacher_coursedetail.jsp";

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

    private void regCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("cid"));
        String[] _stuIDs = request.getParameterValues("student");

        db.deleteAllStudent(id);
        if (_stuIDs != null)
        {

            for (int i = 0; i < _stuIDs.length; i++)
            {
                int stuID = Integer.parseInt(_stuIDs[i]);

                if (!db.isCourseStudent(id, stuID))
                {
                    db.addCourseStudent(id, stuID);
                }
            }
        }
        response.sendRedirect("CourseController?action=coursedetail&courseID=" + id);
    }

}
