package snsinternaltransfer.sns.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Transfer {

    private int id;
    private int from;
    private int to;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date sendingDate;
    private Item item;
    private double totalPrice;
    private int itemCode;
    private String senderName;
    private double amount;

    public Transfer() {
    }

    public Transfer(int from, int to, Date sendingDate, Item item, double totalPrice, int itemCode, String senderName, double amount) {
        this.from = from;
        this.to = to;
        this.sendingDate = sendingDate;
        this.item = item;
        this.totalPrice = totalPrice;
        this.itemCode = itemCode;
        this.senderName = senderName;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public Date getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(Date sendingDate) {
        this.sendingDate = sendingDate;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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