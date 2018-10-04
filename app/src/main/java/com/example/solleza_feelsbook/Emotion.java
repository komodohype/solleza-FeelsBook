package com.example.solleza_feelsbook;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Emotion {
    private Date date;
    private String comment;

    private SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd'T'hh:mm:ss");

    public void setDate(Date date) {
        this.date = date;
    }

    public void setComment(String comment) throws ExceptionCommentTooLong {
        if(comment.length() > 100) {
            throw new ExceptionCommentTooLong();
        }
        this.comment = comment;
    }

    public Date getDate() {
        return this.date;
    }

    public String getComment() {
        return this.comment;
    }

}
