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
import ict.bean.CourseBean;
import ict.db.MaterialDB;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import javax.servlet.http.Part;
import javax.servlet.annotation.*;
/**
 *
 * @author hong
 */
@MultipartConfig
@WebServlet(name = "MaterialController", urlPatterns = {"/MaterialController"})
public class MaterialController extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "upload";
    MaterialDB db = null;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        createMaterial(req, res);
    }

    @Override
    public void init() {
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
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");

        System.out.println("MCp");
        showAllCourse(req, res);
    }

    protected void createMaterial(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Part filePart = req.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        String uploadPath = getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
        InputStream fileContent = filePart.getInputStream();
        OutputStream out = new FileOutputStream(fileName);
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String filePath = uploadPath + File.separator + fileName;
        File storeFile = new File(filePath);

        String desc = req.getParameter("desc");
        String name = req.getParameter("name");
        System.out.println(req.getParameter("cid"));
        int cid = Integer.parseInt(req.getParameter("cid"));

        boolean isSuccess = db.createMaterial(cid, name, desc);
        String redirectURL = "MaterialController?action=mlist";
        res.sendRedirect(redirectURL);

    }

    protected void showAllCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<CourseBean> _courses = new ArrayList<CourseBean>();
        String targetURL = "";
        _courses = db.getAllCourse();
        request.setAttribute("courselist", _courses);
        targetURL = "material_management.jsp";

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("MCp2");
        processRequest(request, response);
    }

}
