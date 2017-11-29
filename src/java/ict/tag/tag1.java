/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.tag;

/**
 *
 * @author dogkonghong
 */
import javax.servlet.jsp.*;
import java.io.*;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class tag1 extends SimpleTagSupport {
    private String link;
    private String desc;
    public void doTag() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();
// calculate the result
// display the result
            
            out.println("<li><h1><a href='"+link+"'>"+desc+"</a></h1></li>");
        } catch (Exception e) {
  
        }

    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    
}