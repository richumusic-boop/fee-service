package com.example.fee.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "receipts")
@Schema(description = "Details about the student fee receipt")
public class Receipt {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Schema(description = "Unique ID of the receipt", example = "1")
 private Long id;

 @Schema(description = "Student ID", example = "101")
 private Long studentId;

 @Schema(description = "Name of the student", example = "John Doe")
 private String studentName;

 @Schema(description = "Fee amount paid", example = "1000.50")
 private Double amount;

 @Schema(description = "Currency of payment", example = "USD")
 private String currency;

 @Schema(description = "Payment date in YYYY-MM-DD", example = "2026-02-08")
 private String paymentDate;

 @Schema(description = "Reference number for payment", example = "REF123456")
 private String referenceNumber;

 @Schema(description = "Type of card used for payment", example = "VISA")
 private String cardType;

 @Schema(description = "Masked card number", example = "**** **** **** 1234")
 private String maskedCardNumber;

 public Receipt() {}

 public Receipt(Long studentId,
                String studentName,
                Double amount,
                String currency,
                String paymentDate,
                String referenceNumber,
                String cardType,
                String maskedCardNumber) {
  this.studentId = studentId;
  this.studentName = studentName;
  this.amount = amount;
  this.currency = currency;
  this.paymentDate = paymentDate;
  this.referenceNumber = referenceNumber;
  this.cardType = cardType;
  this.maskedCardNumber = maskedCardNumber;
 }

 // --- Getters & Setters ---
 public Long getId() { return id; }
 public void setId(Long id) { this.id = id; }

 public Long getStudentId() { return studentId; }
 public void setStudentId(Long studentId) { this.studentId = studentId; }

 public String getStudentName() { return studentName; }
 public void setStudentName(String studentName) { this.studentName = studentName; }

 public Double getAmount() { return amount; }
 public void setAmount(Double amount) { this.amount = amount; }

 public String getCurrency() { return currency; }
 public void setCurrency(String currency) { this.currency = currency; }

 public String getPaymentDate() { return paymentDate; }
 public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }

 public String getReferenceNumber() { return referenceNumber; }
 public void setReferenceNumber(String referenceNumber) { this.referenceNumber = referenceNumber; }

 public String getCardType() { return cardType; }
 public void setCardType(String cardType) { this.cardType = cardType; }

 public String getMaskedCardNumber() { return maskedCardNumber; }
 public void setMaskedCardNumber(String maskedCardNumber) { this.maskedCardNumber = maskedCardNumber; }
}
