/****************/
// Mã sinh viên: 202418841
// Họ tên: Dương Thị Quỳnh Anh
/****************/
package payroll;

/**
 * Lớp đại diện cho nhân viên kinh doanh (SalesEmployee).
 * Thu nhập bao gồm lương cơ bản, hoa hồng dựa trên doanh số bán hàng và thưởng.
 */
public class SalesEmployee extends Employee {
 public static final double MAX_COMMISSION_RATE = 0.3;

 private double baseSalary;
 private double salesRevenue;
 private double commissionRate;

 /**
 * Constructor rút gọn: Nhận thông tin cơ bản, lương cơ bản và tỷ lệ hoa hồng; doanh số ban đầu = 0.
 * 
 * @param employeeId Mã nhân sự.
 * @param fullName Họ tên nhân sự.
 * @param baseSalary Lương cơ bản (>= 0).
 * @param commissionRate Tỷ lệ hoa hồng (0 đến 0.3).
 */
 public SalesEmployee(String employeeId, String fullName, double baseSalary, double commissionRate) {
 this(employeeId, fullName, "Unassigned", baseSalary, 0.0, commissionRate);
 }

 /**
 * Constructor đầy đủ thông tin nhân viên kinh doanh.
 * 
 * @param employeeId Mã nhân sự.
 * @param fullName Họ tên nhân sự.
 * @param department Phòng ban công tác.
 * @param baseSalary Lương cơ bản (>= 0).
 * @param salesRevenue Doanh số bán hàng (>= 0).
 * @param commissionRate Tỷ lệ hoa hồng (0 đến 0.3).
 */
 public SalesEmployee(String employeeId, String fullName, String department,
 double baseSalary, double salesRevenue, double commissionRate) {
 super(employeeId, fullName, department);
 setBaseSalary(baseSalary);
 setSalesRevenue(salesRevenue);
 setCommissionRate(commissionRate);
 }

 public double getBaseSalary() {
 return baseSalary;
 }

 public void setBaseSalary(double baseSalary) {
 if (baseSalary < 0) {
 throw new IllegalArgumentException("Lỗi: Lương cơ bản không được âm. Nhận: " + baseSalary);
 }
 this.baseSalary = baseSalary;
 }

 public double getSalesRevenue() {
 return salesRevenue;
 }

 public void setSalesRevenue(double salesRevenue) {
 if (salesRevenue < 0) {
 throw new IllegalArgumentException("Lỗi: Doanh số bán hàng không được âm. Nhận: " + salesRevenue);
 }
 this.salesRevenue = salesRevenue;
 }

 public double getCommissionRate() {
 return commissionRate;
 }

 public void setCommissionRate(double commissionRate) {
 if (commissionRate < 0.0 || commissionRate > MAX_COMMISSION_RATE) {
 throw new IllegalArgumentException(String.format(
 "Lỗi: Tỷ lệ hoa hồng phải nằm trong khoảng [0, %.2f]. Nhận: %.4f",
 MAX_COMMISSION_RATE, commissionRate));
 }
 this.commissionRate = commissionRate;
 }

 /**
 * Phương thức cập nhật doanh số có kiểm soát dữ liệu đầu vào.
 * 
 * @param newSalesRevenue Doanh số mới (phải >= 0).
 */
 public void updateSalesRevenue(double newSalesRevenue) {
 if (newSalesRevenue < 0) {
 throw new IllegalArgumentException("Lỗi cập nhật: Doanh số không được âm. Nhận: " + newSalesRevenue);
 }
 this.salesRevenue = newSalesRevenue;
 }

 /**
 * Tính tiền hoa hồng từ doanh số: salesRevenue * commissionRate
 */
 public double calculateCommission() {
 return salesRevenue * commissionRate;
 }

 /**
 * Ghi đè phương thức tính thu nhập trước khấu trừ:
 * grossPay = baseSalary + salesRevenue * commissionRate + monthlyBonus
 */
 @Override
 public double calculateGrossPay() {
 return baseSalary + calculateCommission() + getMonthlyBonus();
 }

 @Override
 public String getEmployeeType() {
 return "Nhân viên Kinh doanh (Sales)";
 }

 @Override
 public void displayPayrollInfo() {
 System.out.printf("[Mã: %-5s | Họ tên: %-20s | Phòng: %-12s | Loại: %-20s]\n",
 getEmployeeId(), getFullName(), getDepartment(), "Sales");
 System.out.printf(" + Lương cơ bản: %,15.0f VNĐ\n", baseSalary);
 System.out.printf(" + Doanh số : %,15.0f VNĐ (Hoa hồng: %.1f%%)\n",
 salesRevenue, commissionRate * 100);
 System.out.printf(" + Tiền hoa hồng: %,14.0f VNĐ\n", calculateCommission());
 System.out.printf(" + Thưởng : %,15.0f VNĐ\n", getMonthlyBonus());
 System.out.printf(" => THU NHẬP : %,15.0f VNĐ\n", calculateGrossPay());
 }
}
