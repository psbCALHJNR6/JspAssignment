<%-- 
    Document   : teacher_main
    Created on : Nov 20, 2017, 10:54:29 PM
    Author     : psb
--%>
<%@ taglib uri="/WEB-INF/tlds/ict.taglib.tld" prefix="ict"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teacher management</title>
        <jsp:include page="import.jsp" />
    </head>
    <body>

        <jsp:include page="navbar.jsp" />

        <div class="container">
           

            <ul>
                
                
                <ict:tag1 link="CourseController?action=list" desc="Existing courses"/>
                <ict:tag1 link="teacher_createcourse.jsp" desc="Create Course"/>
                <ict:tag1 link="QuizController?action=list" desc="Existing quiz"/>
                <ict:tag1 link="QuizController?action=createForm" desc="Create Quiz"/>
                <ict:tag1 link="MaterialController?action=mlist" desc="Materials"/>
                <ict:tag1 link="getUser?action=maintainForm&id=${userInfo.getId()}" desc="Edit Profile"/>
            </ul>
            



        </div>

        <jsp:include page="footer.jsp" />
    </body>
</html>




<!--<div class="row">
    <div class="col-md-12">
        BOX
        <div class="box">
            BOX HEADER
            <div class="box-header">
                BOX TITLE
                <div class="box-title pull-left">
                    <p><i class="glyphicon glyphicon-book"></i></p>
                    <p>title</p>
                </div>
            </div>
            end: BOX HEADER
            BOX CONTENT
            <div class="box-content">
                content

            </div>
            end: BOX CONTENT
        </div>
        end: BOX  
    </div>
</div>-->
