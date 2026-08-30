package parking.model;

import java.time.LocalDateTime;
public class Payment{

    //payment information
    private int paymentId;
    private int sessionId;
    private double amount;
    private String paymentMethod;
    private LocalDateTime paymentDate;
    private String paymentStatus;

    //default constructer
    public Payment(
        int paymentId,
        int sessionId,
        double amount,
        String paymentMethod,
        LocalDateTime paymentDate,
        String paymentStatus ){
   
    this.paymentId = paymentId;
    this.sessionId = sessionId;
    this.amount = amount;
    this.paymentMethod = paymentMethod;
    this.paymentDate = paymentDate;
    this.paymentStatus = paymentStatus;
}

//get paymentid
public int getPaymentId(){
    return paymentId;
}

//get parking session id
public int getSessionId(){
    return sessionId;
}

//get payment amount
public double getAmount(){
    return amount;
}

//get payment method
public String getPaymentMethod(){
    return paymentMethod;
}

//get payment date
public LocalDateTime getPaymentDate(){
return paymentDate;
}

//get payment status
public String getPaymentStatus(){
    return paymentStatus;
}



//change payment status
public void setPaymentStatus(String paymentStatus){
    this.paymentStatus = paymentStatus;
}
}