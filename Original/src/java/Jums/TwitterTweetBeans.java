/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jums;

import java.util.Date;

/**
 *
 * @author kotaroh
 */
public class TwitterTweetBeans {

    private String text = "";
    private Date date = new Date();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
