<!-- Navigation -->
<jsp:useBean id="userInfo" scope="session" class="ict.bean.UserInfo" />
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" >

    <div class="container">

        <a class="navbar-brand" href="#">E-Learning</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
<!--            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">About</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Services</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contact</a>
                </li>
            </ul>-->
            
        </div>
    </div>
    <span class="nav navbar-nav navbar-right navbar-text">
        <% 
            if(userInfo.getUsername() != null){
                
                out.print("<i class=\"fa fa-user\"></i>" + userInfo.getUsername() +", Logged in as " + userInfo.getRole() + "<a class=\"\" href=\"main?action=logout\"> Logout</a>"); 
            }
        %>
    </span>
</nav>