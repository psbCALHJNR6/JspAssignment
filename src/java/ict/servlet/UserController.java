/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.UserInfo;
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
@WebServlet(name = "UserController", urlPatterns =
{
    "/getUser"
})
public class UserController extends HttpServlet
{

    UserDB db;

    @Override
    public void init() throws ServletException
    {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new UserDB(dbUrl, dbUser, dbPassword);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=UTF-8");

        String action = req.getParameter("action");

        if ("list".equals(action))
        {
            showAllUser(req, resp);

        }
        else if ("create".equals(action))
        {
            createUser(req, resp);
        }
        else if ("delete".equals(action))
        {
            deleteUser(req, resp);
        }
        else if ("editform".equals(action))
        {
            editForm(req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        processRequest(req, resp);

    }

    protected void showAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ArrayList<UserInfo> _users = new ArrayList<UserInfo>();
        String targetURL = "";
        _users = db.getAllUser();
        req.setAttribute("userlist", _users);
        targetURL = "admin_userlist.jsp";

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(req, resp);
    }

    protected void createUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        String email = req.getParameter("email");

        boolean isSucces = db.addUser(username, password, role, email);
        
        resp.sendRedirect("getUser?action=list");	
    }
    
    protected void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int id = Integer.parseInt(req.getParameter("id"));
        db.deleteUser(id);
        
        
        resp.sendRedirect("getUser?action=list");
    }
    
    protected void editForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int id = Integer.parseInt(req.getParameter("id"));
        
        UserInfo _userBean = new UserInfo();
        _userBean = db.queryUserByID(id);
        
        String targetURL = "";
        req.setAttribute("userDetail", _userBean);
        targetURL = "admin_edituser.jsp";

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(req, resp);
        
    }
    
    

}
