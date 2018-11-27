package snsinternaltransfer.sns.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Transfer {

    private int id;
    private String from;
    private String to;
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    // VI SKAL HUSKE AT MATCHE FORMAT MED HqTML

    private Date sendingDate;
    private String item;

    private String senderName;
    private double amount;

    public Transfer() {
    }

    public Transfer(String from, String to, Date sendingDate, String item, String senderName, double amount) {
        this.from = from;
        this.to = to;
        this.sendingDate = sendingDate;
        this.item = item;

        this.senderName = senderName;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(Date sendingDate) {
        this.sendingDate = sendingDate;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}