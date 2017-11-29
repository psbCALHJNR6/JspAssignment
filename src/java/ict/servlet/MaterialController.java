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
import ict.bean.MaterialBean;
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
        showAllCourse(req, res);
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

        String action = req.getParameter("action");
        if (action.equals("delete")) {
            delete(req, res);
            showAllCourse(req, res);
        }
        if (action.equals("change")) {
            update(req, res);
        }
        showAllCourse(req, res);

        //showMaterial(req,res);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int mid=Integer.parseInt(req.getParameter("id"));
        String filename = "";
        filename = req.getParameter("file");
        File f = new File(getServletContext().getRealPath("/uu") + File.separator + filename);
       try{
        f.delete();
       }
       catch(Exception ex){
           
       }

        boolean isSuccess = db.deleteMaterialByID(mid);
        //System.out.print(isSuccess);
        //res.sendRedirect("MaterialController?action=mlist");

    }
protected void update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int mid=Integer.parseInt(req.getParameter("id"));
        int status=Integer.parseInt(req.getParameter("status"));
        
        
        

        boolean isSuccess = db.updateMaterialByID(mid,status);
        //System.out.print(isSuccess);
        

    }
    protected void createMaterial(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Part filePart = req.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        String uploadPath = getServletContext().getRealPath("/uu") + File.separator + fileName;
        System.out.print(uploadPath);
        InputStream fileContent = filePart.getInputStream();
        OutputStream out = new FileOutputStream(new File(uploadPath));
        int read = 0;
        final byte[] bytes = new byte[1024];

        while ((read = fileContent.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }

        String desc = req.getParameter("desc");
        //String name = req.getParameter("name");
        System.out.println(req.getParameter("cid"));
        int cid = Integer.parseInt(req.getParameter("cid"));

        boolean isSuccess = db.createMaterial(cid, fileName, desc,1);
        //System.out.print(isSuccess);
        
    }

    protected void showAllCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<CourseBean> _courses = new ArrayList<CourseBean>();
        String targetURL = "";
        _courses = db.getAllCourse();
        request.setAttribute("courselist", _courses);
        ArrayList<MaterialBean> _m = new ArrayList<MaterialBean>();
//        //String targetURL = "";
        //int id=Integer.parseInt(request.getParameter("id"));
        _m = db.showAllMaterial();
        request.setAttribute("matelist", _m);
        targetURL = "material_management.jsp";

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);

    }

    protected void showMaterial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<MaterialBean> _m = new ArrayList<MaterialBean>();
        String targetURL = "";
        //int id=Integer.parseInt(request.getParameter("id"));
        _m = db.showAllMaterial();
        request.setAttribute("matelist", _m);
        targetURL = "material_management.jsp";
        System.out.print("s");
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
