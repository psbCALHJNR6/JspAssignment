<%-- 
    Document   : teacher_main
    Created on : Nov 20, 2017, 10:54:29 PM
    Author     : psb
--%>

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
                <li><h1><a href="">Existing courses</a></h1></li>
                <li><h1><a href="">Create Course</a></h1></li>
                <li><h1><a href="">Existing quiz</a></h1></li>
                <li><h1><a href="">Create Quiz</a></h1></li>
                <li><h1><a href="">Material Management</a></h1></li>
            </ul>




        </div>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet' type='text/css'>
<div class="col-md-12 margin-bottom-30">
            Select Course<select><option></option></select>
            </div>
<div class="container">
    <div class="row">
    

    
        <div class="col-md-10 col-md-offset-1">

            <div class="panel panel-default panel-table">
              <div class="panel-heading">
                <div class="row">
                  <div class="col col-xs-6">
                    <h3 class="panel-title">Panel Heading</h3>
                  </div>
                  <div class="col col-xs-6 text-right">
                    <button type="button" class="btn btn-sm btn-primary btn-create">Create New</button>
                  </div>
                </div>
              </div>
              <div class="panel-body">
                <table class="table table-striped table-bordered table-list">
                  <thead>
                    <tr>
                        <th><em class="fa fa-cog"></em></th>
                        <th class="hidden-xs">ID</th>
                        <th>Name</th>
                        <th>Email</th>
                    </tr> 
                  </thead>
                  <tbody>
                          <tr>
                            <td align="center">
                              <a class="btn btn-default"><em class="fa fa-pencil"></em></a>
                              <a class="btn btn-danger"><em class="fa fa-trash"></em></a>
                            </td>
                            <td class="hidden-xs">1</td>
                            <td>John Doe</td>
                            <td>johndoe@example.com</td>
                          </tr>
                        </tbody>
                </table>
            
              </div>
              <div class="panel-footer">
                <div class="row">
                  <div class="col col-xs-4">Page 1 of 5
                  </div>
                  <div class="col col-xs-8">
                    <ul class="pagination hidden-xs pull-right">
                      <li><a href="#">1</a></li>
                      <li><a href="#">2</a></li>
                      <li><a href="#">3</a></li>
                      <li><a href="#">4</a></li>
                      <li><a href="#">5</a></li>
                    </ul>
                    <ul class="pagination visible-xs pull-right">
                        <li><a href="#">«</a></li>
                        <li><a href="#">»</a></li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>

</div></div></div>
        <form action="upload" method="POST">
     
        <div class="col-md-12 margin-bottom-30">
            Course ID:<input type="text" id='cid'  value="1"disabled>
            <label for="exampleInputFile">File input</label>
            <input type="file" id="exampleInputFile" name="file" > <br>
            Name<input type="text" class="form-control" id="name" name="name"value="">
            Description<input type="text" class="form-control" id="desc" name="desc"value="">
            <button type="submit" class="btn btn-primary">Upload</button>
        </div>
        </form>
      
    </body> 
    <footer></footer>
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
