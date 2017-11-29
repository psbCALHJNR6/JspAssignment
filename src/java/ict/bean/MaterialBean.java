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
    private int mid=0;
    private String mateName = "";
    private String mateDesc ="";
    private int visibility;
    public MaterialBean(){}
    public MaterialBean(int cid,String mateName,String mateDesc,int visibility){
        this.cid=cid;
        this.mateName=mateName;
        this.mateDesc=mateDesc;
        this.visibility = visibility;
    }
    public void setCid(int cid){this.cid=cid;}

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }
    
     public void setMateName(String mateName){this.mateName=mateName;}
      public void setMateDesc(String mateDesc){this.mateDesc=mateDesc;}
      public void setMid(int mid){this.mid=mid;}
      public int getMid(){return mid;}
      public int getCid(){return cid;}
      public String getMateName(){return mateName;}
      public String getMateDesc(){return mateDesc;}
      
    
}
