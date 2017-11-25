/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author hong
 */
public class QuizBean implements Serializable{
    private int QID;
    private int duration;
    private String startDate;
    private String endDate;
    private int cid;
    private int attemptTime;
    private String description;

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getQID()
    {
        return QID;
    }

    public void setQID(int QID)
    {
        this.QID = QID;
    }

    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public int getCid()
    {
        return cid;
    }

    public void setCid(int cid)
    {
        this.cid = cid;
    }

    public int getAttemptTime()
    {
        return attemptTime;
    }

    public void setAttemptTime(int attemptTime)
    {
        this.attemptTime = attemptTime;
    }
    
    
}
