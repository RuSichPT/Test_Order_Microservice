package com.github.RuSichPT.TestOrderMicroservice.entities;

import java.util.Date;

public class Session {
    private int id;

    private String sessionId;

    private Date startTime;

    private int timeoutMinutes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getTimeoutMinutes() {
        return timeoutMinutes;
    }

    public void setTimeoutMinutes(int timeoutMinutes) {
        this.timeoutMinutes = timeoutMinutes;
    }

    public boolean checkTimeout() {
        Date currentDate = new Date();

        return (currentDate.getTime() - startTime.getTime() ) > (long) getTimeoutMinutes() * 60 * 1000;
    }
}
