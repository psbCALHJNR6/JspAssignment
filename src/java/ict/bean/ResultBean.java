/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

/**
 *
 * @author psb
 */
public class ResultBean
{
    private int qid;
    private UserInfo uid;
    private int score;
 
    public int getQid()
    {
        return qid;
    }

    public void setQid(int qid)
    {
        this.qid = qid;
    }

    public UserInfo getUid()
    {
        return uid;
    }

    public void setUid(UserInfo uid)
    {
        this.uid = uid;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }
    
   
    
}
