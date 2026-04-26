package com.example.fee.service;

import com.example.fee.model.Receipt;
import com.example.fee.repository.ReceiptRepository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class FeeService {

 @Autowired
 private ReceiptRepository receiptRepository;

 // =========================
 // SAVE RECEIPT
 // =========================
 public Receipt collectFee(Receipt receipt) {
  return receiptRepository.save(receipt);
 }

 // =========================
 // FETCH RECEIPTS BY STUDENT
 // =========================
 public List<Receipt> getReceipts(Long studentId) {
  return receiptRepository.findByStudentId(studentId);
 }

 // =========================
 // GENERATE SKIPLY-STYLE PDF
 // =========================
 public byte[] generateReceiptPdf(Receipt receipt) throws Exception {

  ByteArrayOutputStream out = new ByteArrayOutputStream();
  Document document = new Document(PageSize.A4, 25, 25, 25, 25);
  PdfWriter.getInstance(document, out);
  document.open();

  // ---------- COLORS ----------
  Color SKIPLY_PURPLE = new Color(78, 63, 181);
  Color LIGHT_GRAY_BG = new Color(245, 245, 245);

  // ---------- FONTS ----------
  Font whiteBold14 = new Font(Font.HELVETICA, 14, Font.BOLD, Color.WHITE);
  Font bold10 = new Font(Font.HELVETICA, 10, Font.BOLD);
  Font normal10 = new Font(Font.HELVETICA, 10);
  Font footerFont = new Font(Font.HELVETICA, 8, Font.ITALIC);

  // ================= HEADER =================
  PdfPTable headerTable = new PdfPTable(1);
  headerTable.setWidthPercentage(100);

  PdfPCell headerCell = new PdfPCell(new Phrase("Skiply", whiteBold14));
  headerCell.setBackgroundColor(SKIPLY_PURPLE);
  headerCell.setPadding(12);
  headerCell.setBorder(Rectangle.NO_BORDER);

  headerTable.addCell(headerCell);
  document.add(headerTable);

  // ================= SUCCESS BAR =================
  PdfPTable successTable = new PdfPTable(1);
  successTable.setWidthPercentage(100);

  PdfPCell successCell = new PdfPCell(
          new Phrase("Your transaction is Successful", whiteBold14)
  );
  successCell.setBackgroundColor(SKIPLY_PURPLE);
  successCell.setHorizontalAlignment(Element.ALIGN_CENTER);
  successCell.setPadding(8);
  successCell.setBorder(Rectangle.NO_BORDER);

  successTable.addCell(successCell);
  document.add(successTable);

  document.add(Chunk.NEWLINE);

  // ================= MESSAGE =================
  Paragraph message = new Paragraph(
          "Hi " + receipt.getStudentName() + ",\n\n" +
                  "Congratulations! Your payment transaction is successful.\n" +
                  "Kindly find the transaction details below:\n",
          normal10
  );
  document.add(message);

  document.add(Chunk.NEWLINE);

  // ================= MAIN CONTENT =================
  PdfPTable mainTable = new PdfPTable(2);
  mainTable.setWidthPercentage(100);
  mainTable.setWidths(new float[]{2, 4});

  // Logo placeholder
  PdfPCell logoCell = new PdfPCell(new Phrase("SKIPLY\nLOGO", bold10));
  logoCell.setFixedHeight(90);
  logoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
  logoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
  logoCell.setBackgroundColor(LIGHT_GRAY_BG);
  logoCell.setBorder(Rectangle.NO_BORDER);

  // Transaction details
  PdfPCell detailsCell = new PdfPCell();
  detailsCell.setPadding(10);
  detailsCell.setBackgroundColor(LIGHT_GRAY_BG);
  detailsCell.setBorder(Rectangle.NO_BORDER);

  detailsCell.addElement(new Paragraph("Transaction Details", bold10));
  detailsCell.addElement(new Paragraph("Receipt ID : " + receipt.getId(), normal10));
  detailsCell.addElement(new Paragraph("Student Name : " + receipt.getStudentName(), normal10));
  detailsCell.addElement(new Paragraph("Student ID : " + receipt.getStudentId(), normal10));
  detailsCell.addElement(new Paragraph("Payment Date : " + receipt.getPaymentDate(), normal10));
  detailsCell.addElement(new Paragraph("Card Type : Mastercard", normal10));

  mainTable.addCell(logoCell);
  mainTable.addCell(detailsCell);

  document.add(mainTable);
  document.add(Chunk.NEWLINE);

  // ================= PURCHASE DETAILS =================
  PdfPTable purchaseTable = new PdfPTable(2);
  purchaseTable.setWidthPercentage(100);
  purchaseTable.setWidths(new float[]{4, 2});

  addRow(purchaseTable, "Tuition Fees", "AED " + receipt.getAmount(), bold10, normal10);
  addRow(purchaseTable, "Total", "AED " + receipt.getAmount(), bold10, bold10);

  document.add(purchaseTable);

  document.add(Chunk.NEWLINE);

  // ================= FOOTER =================
  Paragraph footer = new Paragraph(
          "This is an automated email, please do not reply.\n" +
                  "For any query, please contact support@skiply.ae",
          footerFont
  );
  footer.setAlignment(Element.ALIGN_CENTER);
  document.add(footer);

  document.close();
  return out.toByteArray();
 }

 // =========================
 // TABLE ROW HELPER
 // =========================
 private void addRow(PdfPTable table,
                     String label,
                     String value,
                     Font labelFont,
                     Font valueFont) {

  PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
  labelCell.setPadding(8);
  labelCell.setBorder(Rectangle.BOX);

  PdfPCell valueCell = new PdfPCell(new Phrase(value, valueFont));
  valueCell.setPadding(8);
  valueCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
  valueCell.setBorder(Rectangle.BOX);

  table.addCell(labelCell);
  table.addCell(valueCell);
 }
}
