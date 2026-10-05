/****************/
// Mã sinh viên: 202418841
// Họ tên: Dương Thị Quỳnh Anh
/****************/
package projectteam;

/**
 * Lớp đại diện cho một nhân sự thông thường trong tổ chức.
 * Được thiết kế để quản lý độc lập và có thể tham gia vào nhiều dự án khác nhau (Aggregation).
 */
public class Employee {
 private String id;
 private String fullName;
 private double baseSalary;

 /**
 * Constructor mặc định:
 * id = "UNKNOWN", fullName = "Unnamed employee", baseSalary = 0
 */
 public Employee() {
 this("UNKNOWN", "Unnamed employee", 0.0);
 }

 /**
 * Constructor 2 tham số: Khởi tạo với mã và họ tên, lương cơ bản mặc định = 0.
 * Áp dụng constructor ủy quyền.
 * 
 * @param id Mã nhân sự (không được rỗng).
 * @param fullName Họ tên nhân sự (không được rỗng).
 */
 public Employee(String id, String fullName) {
 this(id, fullName, 0.0);
 }

 /**
 * Constructor đầy đủ 3 tham số.
 * 
 * @param id Mã nhân sự (không được rỗng).
 * @param fullName Họ tên nhân sự (không được rỗng).
 * @param baseSalary Lương cơ bản (>= 0).
 */
 public Employee(String id, String fullName, double baseSalary) {
 setId(id);
 setFullName(fullName);
 setBaseSalary(baseSalary);
 System.out.printf("[Vòng đời - Khởi tạo] Tạo mới Employee: [%s - %s]\n", this.id, this.fullName);
 }

 // ================= GETTER / SETTER VÀ KIỂM TRA BẤT BIẾN =================

 public String getId() {
 return id;
 }

 public void setId(String id) {
 if (id == null || id.trim().isEmpty()) {
 throw new IllegalArgumentException("Lỗi bất biến: Mã nhân sự không được để trống.");
 }
 this.id = id.trim();
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

 public double getBaseSalary() {
 return baseSalary;
 }

 public void setBaseSalary(double baseSalary) {
 if (baseSalary < 0) {
 throw new IllegalArgumentException("Lỗi bất biến: Lương cơ bản không được âm. Nhận: " + baseSalary);
 }
 this.baseSalary = baseSalary;
 }

 // ================= NẠP CHỒNG PHƯƠNG THỨC increaseSalary() =================

 /**
 * Nạp chồng phiên bản 1: Tăng lương cơ bản theo một số tiền cố định.
 * 
 * @param amount Số tiền tăng (phải > 0).
 */
 public void increaseSalary(double amount) {
 if (amount <= 0) {
 throw new IllegalArgumentException("Lỗi quy ước: Giá trị tăng lương phải dương. Nhận: " + amount);
 }
 this.baseSalary += amount;
 System.out.printf(" [Tăng lương] Nhân sự %s được tăng %,.0f VNĐ (Cố định). Lương mới: %,.0f VNĐ\n",
 id, amount, baseSalary);
 }

 /**
 * Nạp chồng phiên bản 2: Tăng lương theo tỷ lệ phần trăm (nếu byPercentage == true)
 * hoặc theo số tiền cố định (nếu byPercentage == false).
 * 
 * @param value Giá trị tăng (phải > 0).
 * @param byPercentage true nếu tính theo %, false nếu tính theo số tiền cố định.
 */
 public void increaseSalary(double value, boolean byPercentage) {
 if (value <= 0) {
 throw new IllegalArgumentException("Lỗi quy ước: Giá trị tăng lương phải dương. Nhận: " + value);
 }
 if (byPercentage) {
 double increaseAmount = this.baseSalary * (value / 100.0);
 this.baseSalary += increaseAmount;
 System.out.printf(" [Tăng lương] Nhân sự %s được tăng %.2f%% (+%,.0f VNĐ). Lương mới: %,.0f VNĐ\n",
 id, value, increaseAmount, baseSalary);
 } else {
 increaseSalary(value);
 }
 }

 // ================= CÁC PHƯƠNG THỨC ĐA HÌNH (VIRTUAL TRONG JAVA) =================

 /**
 * Tính tổng chi phí nhân sự hàng tháng. Mặc định bằng lương cơ bản.
 * Phương thức ảo (Virtual), lớp con có thể ghi đè.
 * 
 * @return Chi phí hàng tháng (VNĐ).
 */
 public double calculateMonthlyCost() {
 return baseSalary;
 }

 /**
 * Hiển thị thông tin nhân sự.
 */
 public void displayInfo() {
 System.out.printf("[Nhân sự] Mã: %-6s | Họ tên: %-20s | Lương cơ bản: %,14.0f VNĐ | Chi phí tháng: %,14.0f VNĐ\n",
 id, fullName, baseSalary, calculateMonthlyCost());
 }

 @Override
 public String toString() {
 return String.format("%s (%s)", fullName, id);
 }
}
