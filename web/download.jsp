<%-- 
    Document   : download
    Created on : Nov 28, 2017, 12:15:20 PM
    Author     : dogkonghong
--%>
<%@page import="java.io.File" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%    
  String filename = "";   
  filename=request.getParameter("file");
  String filepath = "../build/web/uu/";   
  response.setContentType("APPLICATION/OCTET-STREAM");   
  response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
  
  java.io.FileInputStream fileInputStream=new java.io.FileInputStream(getServletContext().getRealPath("/uu") + File.separator + filename);  
            
  int i;   
  while ((i=fileInputStream.read()) != -1) {  
    out.write(i);   
  }   
  fileInputStream.close();   
%>   
</html>
