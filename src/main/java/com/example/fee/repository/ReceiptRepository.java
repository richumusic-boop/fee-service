package com.example.fee.repository;

import com.example.fee.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

 // Find all receipts for a specific student
 List<Receipt> findByStudentId(Long studentId);
}
