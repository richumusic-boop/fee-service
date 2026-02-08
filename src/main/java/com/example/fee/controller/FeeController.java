package com.example.fee.controller;

import com.example.fee.model.Receipt;
import com.example.fee.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fees")
public class FeeController {

 @Autowired
 private FeeService service;

 // Collect fee and generate PDF
 @PostMapping("/collect")
 public ResponseEntity<byte[]> collect(@RequestBody Receipt r) throws Exception {
  Receipt saved = service.collectFee(r);
  byte[] pdf = service.generateReceiptPdf(saved);

  return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=receipt.pdf")
          .contentType(MediaType.APPLICATION_PDF)
          .body(pdf);
 }

 // Get all receipts for a student
 @GetMapping("/receipts/{studentId}")
 public List<Receipt> receipts(@PathVariable Long studentId) {
  return service.getReceipts(studentId);
 }
}
