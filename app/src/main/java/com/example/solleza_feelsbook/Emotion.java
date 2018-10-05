package com.example.solleza_feelsbook;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * @TODO: decompose to subclasses: Anger, Fear, Joy, Love, Sadness, Surprise
 */
public class Emotion {
    private String type;
    private Date date;
    private String comment = new String("");

    private SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd'T'hh:mm:ss");

    public void setType(String type) {
        this.type = type;
    }

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

    public String getDateString() {
        return ft.format(this.date);
    }

    public String toString() {
        return getDateString() + " | " + this.type + " | " + this.comment;
    }
}
