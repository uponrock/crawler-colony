package com.dang.crawler.resources.mysql.model;

import java.util.Date;

/**
 * Created by mi on 2017/5/3.
 */
public class CrawlerJob {
    private String jobId;
    private String name;
    private long period;
    private Date nextStartDate;
    private char status;
    private int priority;
    private int maxThread;
    private String note;
    private int projectId;
    private int sourceId;
    private Date createDate;

    ////////////////////////////////////////////////////


    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public Date getNextStartDate() {
        return nextStartDate;
    }

    public void setNextStartDate(Date nextStartDate) {
        this.nextStartDate = nextStartDate;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getMaxThread() {
        return maxThread;
    }

    public void setMaxThread(int maxThread) {
        this.maxThread = maxThread;
    }

    @Override
    public String toString() {
        return "CrawlerJob{" +
                "jobId='" + jobId + '\'' +
                ", name='" + name + '\'' +
                ", period=" + period +
                ", nextStartDate=" + nextStartDate +
                ", status=" + status +
                ", priority=" + priority +
                ", maxThread=" + maxThread +
                ", note='" + note + '\'' +
                ", projectId=" + projectId +
                ", sourceId=" + sourceId +
                ", createDate=" + createDate +
                '}';
    }
}