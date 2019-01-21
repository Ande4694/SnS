package snsinternaltransfer.sns.models;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Transfer {

    private int id;
    private String from;
    private String to;
    private int fromInt;
    private int toInt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date sendingDate;
    private String item;
    private double totalPrice;
    private int itemCode;
    private String senderName;
    private double amount;

    public Transfer() {
    }

    public Transfer(int id, int fromInt, int toInt, Date sendingDate, String item, double totalPrice, int itemCode, String senderName, double amount) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.sendingDate = sendingDate;
        this.item = item;
        this.totalPrice = totalPrice;
        this.itemCode = itemCode;
        this.senderName = senderName;
        this.amount = amount;
    }

    public Transfer(int id, String from, String to, Date sendingDate, String item, double totalPrice, int itemCode, String senderName, double amount) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.sendingDate = sendingDate;
        this.item = item;
        this.totalPrice = totalPrice;
        this.itemCode = itemCode;
        this.senderName = senderName;
        this.amount = amount;
    }

    public Transfer(String from, String to, Date sendingDate, String item, String senderName, double amount) {
        this.from = from;
        this.to = to;
        this.sendingDate = sendingDate;
        this.item = item;

        this.senderName = senderName;
        this.amount = amount;
    }

    public int getFromInt() {
        return fromInt;
    }

    public void setFromInt(int fromInt) {
        this.fromInt = fromInt;
    }

    public int getToInt() {
        return toInt;
    }

    public void setToInt(int toInt) {
        this.toInt = toInt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
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