/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

import java.io.Serializable;

/**
 *
 * @author hong
 */
public class MaterialBean implements Serializable
{
        private int cid = 0;
 
    private String mateName = "";
    private String mateDesc ="";
    public MaterialBean(){}
    public MaterialBean(int cid,String mateName,String mateDesc){
        this.cid=cid;
        this.mateName=mateName;
        this.mateDesc=mateDesc;
    }
    public void setCid(int cid){this.cid=cid;}
     public void setMateName(String mateName){this.mateName=mateName;}
      public void setMateDesc(String mateDesc){this.mateDesc=mateDesc;}
      
      
      public int getCid(){return cid;}
      public String getMateName(){return mateName;}
      public String getMateDesc(){return mateDesc;}
      
    
}
