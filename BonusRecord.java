/****************/
// Mã sinh viên: 202418841
// Họ tên: Dương Thị Quỳnh Anh
/****************/
package payroll;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Lớp biểu diễn một bản ghi thưởng (Bonus Record) trong lịch sử thưởng của nhân viên.
 * Giúp quản lý minh bạch các lần cộng thưởng thay vì chỉ lưu tổng số tiền.
 */
public class BonusRecord {
 private final double amount;
 private final String reason;
 private final LocalDateTime timestamp;

 /**
 * Khởi tạo bản ghi thưởng với số tiền và lý do.
 * 
 * @param amount Số tiền thưởng (phải > 0).
 * @param reason Lý do thưởng (không được rỗng).
 */
 public BonusRecord(double amount, String reason) {
 if (amount <= 0) {
 throw new IllegalArgumentException("Số tiền thưởng phải lớn hơn 0. Giá trị nhận được: " + amount);
 }
 if (reason == null || reason.trim().isEmpty()) {
 throw new IllegalArgumentException("Lý do thưởng không được để trống.");
 }
 this.amount = amount;
 this.reason = reason.trim();
 this.timestamp = LocalDateTime.now();
 }

 public double getAmount() {
 return amount;
 }

 public String getReason() {
 return reason;
 }

 public LocalDateTime getTimestamp() {
 return timestamp;
 }

 @Override
 public String toString() {
 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
 return String.format("[Thời gian: %s | Thưởng: %,.0f VNĐ | Lý do: %s]",
 timestamp.format(formatter), amount, reason);
 }
}
