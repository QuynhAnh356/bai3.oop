/****************/
// Mã sinh viên: 202418841
// Họ tên: Dương Thị Quỳnh Anh
/****************/
package payroll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Payroll {
 private String period;
 private final List<Employee> employees = new ArrayList<>();

 public Payroll(String period) {
 setPeriod(period);
 }

 public String getPeriod() {
 return period;
 }

 public void setPeriod(String period) {
 if (period == null || period.trim().isEmpty()) {
 throw new IllegalArgumentException("Lỗi: Kỳ lương không được để trống.");
 }
 this.period = period.trim();
 }

 public List<Employee> getEmployees() {
 return Collections.unmodifiableList(employees);
 }

 public boolean addEmployee(Employee employee) {
 if (employee == null) {
 throw new IllegalArgumentException("Lỗi: Không thể thêm nhân sự có giá trị null vào bảng lương.");
 }
 if (findEmployee(employee.getEmployeeId()) != null) {
 throw new IllegalArgumentException(String.format(
 "Lỗi ràng buộc: Nhân sự có mã '%s' đã tồn tại trong bảng lương kỳ %s.",
 employee.getEmployeeId(), period));
 }
 return employees.add(employee);
 }


 public Employee findEmployee(String employeeId) {
 if (employeeId == null || employeeId.trim().isEmpty()) {
 return null;
 }
 for (Employee emp : employees) {
 if (emp.getEmployeeId().equalsIgnoreCase(employeeId.trim())) {
 return emp;
 }
 }
 return null;
 }

 public double calculateTotalPayroll() {
 double total = 0.0;
 for (Employee emp : employees) {
 // Lời gọi đa hình (Polymorphic call)
 total += emp.calculateGrossPay();
 }
 return total;
 }

 public double calculatePayrollByDepartment(String department) {
 if (department == null || department.trim().isEmpty()) {
 return 0.0;
 }
 double deptTotal = 0.0;
 for (Employee emp : employees) {
 if (emp.getDepartment().equalsIgnoreCase(department.trim())) {
 deptTotal += emp.calculateGrossPay();
 }
 }
 return deptTotal;
 }

 public Employee findHighestPaidEmployee() {
 if (employees.isEmpty()) {
 return null;
 }
 Employee highest = employees.get(0);
 for (int i = 1; i < employees.size(); i++) {
 if (employees.get(i).calculateGrossPay() > highest.calculateGrossPay()) {
 highest = employees.get(i);
 }
 }
 return highest;
 }

 /**
 * Hiển thị bảng lương chi tiết và các số liệu tổng hợp thống kê.
 */
 public void displayPayroll() {
 System.out.println("==========================================================================================");
 System.out.printf(" BẢNG TỔNG HỢP LƯƠNG KỲ %s\n", period);
 System.out.println("==========================================================================================");
 if (employees.isEmpty()) {
 System.out.println("Danh sách nhân sự hiện đang rỗng. Chưa có dữ liệu bảng lương.");
 System.out.println("==========================================================================================");
 return;
 }

 System.out.printf("%-6s | %-20s | %-15s | %-24s | %-15s\n",
 "MÃ NV", "HỌ VÀ TÊN", "PHÒNG BAN", "LOẠI NHÂN SỰ", "THU NHẬP (VNĐ)");
 System.out.println("------------------------------------------------------------------------------------------");
 for (Employee emp : employees) {
 System.out.printf("%-6s | %-20s | %-15s | %-24s | %,15.0f\n",
 emp.getEmployeeId(), emp.getFullName(), emp.getDepartment(),
 emp.getEmployeeType(), emp.calculateGrossPay());
 }
 System.out.println("------------------------------------------------------------------------------------------");
 System.out.printf("TỔNG CHI PHÍ BẢNG LƯƠNG TOÀN DOANH NGHIỆP: %,15.0f VNĐ\n", calculateTotalPayroll());
 
 Employee highest = findHighestPaidEmployee();
 if (highest != null) {
 System.out.printf("NHÂN SỰ CÓ THU NHẬP CAO NHẤT: %s - %s (%,.0f VNĐ)\n",
 highest.getEmployeeId(), highest.getFullName(), highest.calculateGrossPay());
 }
 System.out.println("==========================================================================================\n");
 }
}
