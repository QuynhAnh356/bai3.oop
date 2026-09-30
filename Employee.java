/****************/
// Mã sinh viên: 202418841
// Họ tên: Dương Thị Quỳnh Anh
/****************/
package payroll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class Employee {
 private String employeeId;
 private String fullName;
 private String department;
 private double monthlyBonus;
 private final List<BonusRecord> bonusHistory = new ArrayList<>();


 public Employee(String employeeId, String fullName) {
 this(employeeId, fullName, "Unassigned");
 }


 public Employee(String employeeId, String fullName, String department) {
 setEmployeeId(employeeId);
 setFullName(fullName);
 setDepartment(department);
 this.monthlyBonus = 0.0;
 }

 // ================= GETTER / SETTER VÀ KIỂM TRA BẤT BIẾN =================

 public String getEmployeeId() {
 return employeeId;
 }

 public void setEmployeeId(String employeeId) {
 if (employeeId == null || employeeId.trim().isEmpty()) {
 throw new IllegalArgumentException("Lỗi bất biến: Mã nhân sự không được để trống.");
 }
 this.employeeId = employeeId.trim();
 }

 public String getFullName() {
 return fullName;
 }

 public void setFullName(String fullName) {
 if (fullName == null || fullName.trim().isEmpty()) {
 throw new IllegalArgumentException("Lỗi bất biến: Họ tên nhân sự không được để trống.");
 }
 this.fullName = fullName.trim();
 }

 public String getDepartment() {
 return department;
 }

 public void setDepartment(String department) {
 if (department == null || department.trim().isEmpty()) {
 throw new IllegalArgumentException("Lỗi bất biến: Phòng ban không được để trống.");
 }
 this.department = department.trim();
 }

 public double getMonthlyBonus() {
 return monthlyBonus;
 }

 protected void setMonthlyBonus(double monthlyBonus) {
 if (monthlyBonus < 0) {
 throw new IllegalArgumentException("Lỗi bất biến: Khoản thưởng không được mang giá trị âm. Nhận: " + monthlyBonus);
 }
 this.monthlyBonus = monthlyBonus;
 }

 public List<BonusRecord> getBonusHistory() {
 return Collections.unmodifiableList(bonusHistory);
 }

 // ================= NẠP CHỒNG PHƯƠNG THỨC addBonus() =================

 /**
 * Nạp chồng phiên bản 1: Thêm một khoản thưởng cố định.
 * Tái sử dụng phiên bản 2 với lý do mặc định.
 * 
 * @param amount Số tiền thưởng (phải > 0).
 */
 public void addBonus(double amount) {
 addBonus(amount, "Thưởng định kỳ / Thành tích chung");
 }

 /**
 * Nạp chồng phiên bản 2: Thêm khoản thưởng cố định kèm lý do cụ thể.
 * 
 * @param amount Số tiền thưởng (phải > 0).
 * @param reason Lý do khen thưởng (không được rỗng).
 */
 public void addBonus(double amount, String reason) {
 if (amount <= 0) {
 throw new IllegalArgumentException("Lỗi quy tắc: Số tiền thưởng phải lớn hơn 0. Nhận: " + amount);
 }
 if (reason == null || reason.trim().isEmpty()) {
 throw new IllegalArgumentException("Lỗi quy tắc: Lý do khen thưởng không được để trống.");
 }
 this.monthlyBonus += amount;
 this.bonusHistory.add(new BonusRecord(amount, reason.trim()));
 }

 /**
 * Nạp chồng phiên bản 3: Tính thưởng theo tỷ lệ của một giá trị tham chiếu kèm lý do.
 * 
 * @param rate Tỷ lệ thưởng (lớn hơn 0 và không quá 0.5, tương ứng 50%).
 * @param referenceAmount Giá trị tham chiếu để tính thưởng (phải > 0).
 * @param reason Lý do khen thưởng (không được rỗng).
 */
 public void addBonus(double rate, double referenceAmount, String reason) {
 if (rate <= 0 || rate > 0.5) {
 throw new IllegalArgumentException("Lỗi quy tắc: Tỷ lệ thưởng phải nằm trong khoảng (0, 0.5]. Nhận: " + rate);
 }
 if (referenceAmount <= 0) {
 throw new IllegalArgumentException("Lỗi quy tắc: Giá trị tham chiếu phải lớn hơn 0. Nhận: " + referenceAmount);
 }
 double calculatedBonus = rate * referenceAmount;
 String fullReason = String.format("%s (Tính theo tỷ lệ %.2f%% của %,.0f VNĐ)",
 reason != null ? reason.trim() : "", rate * 100, referenceAmount);
 addBonus(calculatedBonus, fullReason);
 }

 /**
 * Đặt lại thưởng khi bắt đầu kỳ lương mới.
 */
 public void resetBonus() {
 this.monthlyBonus = 0.0;
 this.bonusHistory.clear();
 }

 // ================= PHƯƠNG THỨC ĐA HÌNH (GHI ĐÈ Ở LỚP CON) =================

 /**
 * Tính tổng thu nhập trước khấu trừ (Gross Pay) trong tháng của nhân sự.
 * Lớp con bắt buộc phải ghi đè theo công thức đặc thù.
 * 
 * @return Thu nhập trước khấu trừ (VNĐ).
 */
 public abstract double calculateGrossPay();

 /**
 * Trả về tên định danh loại nhân sự (phục vụ hiển thị và thống kê đa hình).
 * 
 * @return Tên loại nhân sự.
 */
 public abstract String getEmployeeType();

 /**
 * Hiển thị thông tin chi tiết bảng lương của nhân sự.
 */
 public abstract void displayPayrollInfo();
}
