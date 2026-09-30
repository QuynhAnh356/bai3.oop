/****************/
// Mã sinh viên: 202418841
// Họ tên: Dương Thị Quỳnh Anh
/****************/
package payroll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Lớp quản lý bảng lương (Payroll) cho một kỳ lương cụ thể của doanh nghiệp.
 * Payroll KHÔNG kế thừa từ Employee (quan hệ 'has-a' chứ không phải 'is-a').
 * Toàn bộ tính toán lương đều được gọi đa hình thông qua lớp cơ sở Employee.
 */
public class Payroll {
 private String period;
 private final List<Employee> employees = new ArrayList<>();

 /**
 * Khởi tạo bảng lương cho một kỳ lương xác định.
 * 
 * @param period Kỳ lương (ví dụ: "2026-09", không được rỗng).
 */
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

 /**
 * Thêm một nhân sự vào bảng lương.
 * Ràng buộc: Không được thêm trùng mã nhân sự trong cùng một kỳ lương.
 * 
 * @param employee Đối tượng nhân sự cần thêm.
 * @return true nếu thêm thành công.
 * @throws IllegalArgumentException nếu nhân sự rỗng hoặc mã nhân sự đã tồn tại.
 */
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

 /**
 * Tìm kiếm nhân sự trong bảng lương theo mã nhân sự.
 * 
 * @param employeeId Mã nhân sự cần tìm.
 * @return Đối tượng Employee nếu tìm thấy, ngược lại trả về null.
 */
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

 /**
 * Tính tổng chi phí lương của toàn bộ nhân sự trong kỳ lương.
 * Áp dụng tính đa hình: Gọi phương thức calculateGrossPay() trên từng đối tượng Employee.
 * Tuyệt đối không dùng chuỗi if/else hay switch-case để kiểm tra loại nhân sự.
 * 
 * @return Tổng chi phí bảng lương (VNĐ).
 */
 public double calculateTotalPayroll() {
 double total = 0.0;
 for (Employee emp : employees) {
 // Lời gọi đa hình (Polymorphic call)
 total += emp.calculateGrossPay();
 }
 return total;
 }

 /**
 * Tính tổng chi phí lương của một phòng ban cụ thể.
 * 
 * @param department Tên phòng ban cần tổng hợp.
 * @return Tổng lương của phòng ban đó (VNĐ).
 */
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

 /**
 * Tìm nhân sự có thu nhập trước khấu trừ cao nhất trong kỳ.
 * Xử lý an toàn khi danh sách nhân sự rỗng.
 * 
 * @return Đối tượng Employee có thu nhập cao nhất, hoặc null nếu danh sách rỗng.
 */
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
