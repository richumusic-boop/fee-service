package com.example.fee.test;

import com.example.fee.model.Receipt;
import com.example.fee.repository.ReceiptRepository;
import com.example.fee.service.FeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FeeServiceTest {

    @Mock
    private ReceiptRepository receiptRepository;

    @InjectMocks
    private FeeService feeService;

    // =========================
    // TEST: SAVE RECEIPT
    // =========================
    @Test
    void collectFee_shouldSaveAndReturnReceipt() {

        Receipt receipt = new Receipt();
        receipt.setStudentId(1L);
        receipt.setStudentName("Farhan");
        receipt.setAmount(1500.0);
        receipt.setPaymentDate(String.valueOf(LocalDate.now()));

        when(receiptRepository.save(receipt)).thenReturn(receipt);

        Receipt savedReceipt = feeService.collectFee(receipt);

        assertNotNull(savedReceipt);
        assertEquals("Farhan", savedReceipt.getStudentName());
        assertEquals(1500.0, savedReceipt.getAmount());
        verify(receiptRepository, times(1)).save(receipt);
    }

    // =========================
    // TEST: FETCH RECEIPTS
    // =========================
    @Test
    void getReceipts_shouldReturnListOfReceipts() {

        Receipt r1 = new Receipt();
        r1.setStudentId(1L);
        r1.setAmount(1000.0);

        Receipt r2 = new Receipt();
        r2.setStudentId(1L);
        r2.setAmount(500.0);

        when(receiptRepository.findByStudentId(1L))
                .thenReturn(Arrays.asList(r1, r2));

        List<Receipt> receipts = feeService.getReceipts(1L);

        assertEquals(2, receipts.size());
        verify(receiptRepository, times(1))
                .findByStudentId(1L);
    }

    // =========================
    // TEST: GENERATE PDF
    // =========================
    @Test
    void generateReceiptPdf_shouldReturnPdfBytes() throws Exception {

        Receipt receipt = new Receipt();
        receipt.setId(101L);
        receipt.setStudentId(1L);
        receipt.setStudentName("Farhan");
        receipt.setAmount(2000.0);
        receipt.setPaymentDate(String.valueOf(LocalDate.now()));

        byte[] pdfBytes = feeService.generateReceiptPdf(receipt);

        assertNotNull(pdfBytes);
        assertTrue(pdfBytes.length > 0);
    }
}
