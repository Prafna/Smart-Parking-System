package parking.service;

import parking.dao.PaymentDAO;
import parking.model.Payment;

import java.time.LocalDateTime;

//this class handle payment business logic
public class PaymentService {

    //DAO object used to interact with the database
    private PaymentDAO paymentDAO;

    //constructer
    public PaymentService() {
        paymentDAO = new PaymentDAO();
    }

    //process payment
    public boolean makePayment(int sessionId, double amount, String paymentMethod) {
        //create a new payment object
        Payment payment = new Payment(0, sessionId, amount, paymentMethod, LocalDateTime.now(), "Completed");

        //save the payment using PaymentDAO
        return paymentDAO.createPayment(payment);
    }
    
}