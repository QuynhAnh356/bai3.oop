/****************/
// Mã sinh viên: 202418841
// Họ tên: Dương Thị Quỳnh Anh
/****************/
package payroll;

/**
 * Lớp đại diện cho nhân viên hưởng lương theo giờ làm việc (HourlyEmployee).
 * Có chế độ tính làm thêm giờ (Overtime) với hệ số 1.5 khi số giờ làm vượt quá 160 giờ.
 */
public class HourlyEmployee extends Employee {
 public static final double STANDARD_HOURS_LIMIT = 160.0;
 public static final double OVERTIME_RATE_MULTIPLIER = 1.5;
 public static final double MAX_HOURS_LIMIT = 250.0;

 private double hourlyRate;
 private double workedHours;

 /**
 * Constructor rút gọn: Nhận thông tin cơ bản, đơn giá giờ và số giờ làm; phòng ban mặc định "Unassigned".
 * 
 * @param employeeId Mã nhân sự.
 * @param fullName Họ tên nhân sự.
 * @param hourlyRate Đơn giá giờ làm việc (> 0).
 * @param workedHours Số giờ làm việc trong tháng (0 đến 250).
 */
 public HourlyEmployee(String employeeId, String fullName, double hourlyRate, double workedHours) {
 this(employeeId, fullName, "Unassigned", hourlyRate, workedHours);
 }

 /**
 * Constructor đầy đủ thông tin nhân viên theo giờ.
 * 
 * @param employeeId Mã nhân sự.
 * @param fullName Họ tên nhân sự.
 * @param department Phòng ban công tác.
 * @param hourlyRate Đơn giá giờ làm việc (> 0).
 * @param workedHours Số giờ làm việc trong tháng (0 đến 250).
 */
 public HourlyEmployee(String employeeId, String fullName, String department,
 double hourlyRate, double workedHours) {
 super(employeeId, fullName, department);
 setHourlyRate(hourlyRate);
 setWorkedHours(workedHours);
 }

 public double getHourlyRate() {
 return hourlyRate;
 }

 public void setHourlyRate(double hourlyRate) {
 if (hourlyRate <= 0) {
 throw new IllegalArgumentException("Lỗi: Đơn giá giờ phải lớn hơn 0. Nhận: " + hourlyRate);
 }
 this.hourlyRate = hourlyRate;
 }

 public double getWorkedHours() {
 return workedHours;
 }

 public void setWorkedHours(double workedHours) {
 if (workedHours < 0 || workedHours > MAX_HOURS_LIMIT) {
 throw new IllegalArgumentException(String.format(
 "Lỗi: Số giờ làm trong tháng phải nằm trong khoảng [0, %.0f]. Nhận: %.2f",
 MAX_HOURS_LIMIT, workedHours));
 }
 this.workedHours = workedHours;
 }

 /**
 * Tính số giờ làm việc tiêu chuẩn (tối đa 160h).
 */
 public double getRegularHours() {
 return Math.min(workedHours, STANDARD_HOURS_LIMIT);
 }

 /**
 * Tính số giờ làm thêm (vượt quá ngưỡng 160h).
 */
 public double getOvertimeHours() {
 return Math.max(0.0, workedHours - STANDARD_HOURS_LIMIT);
 }

 /**
 * Tính lương cơ bản (bao gồm giờ thường và giờ vượt ngưỡng).
 * Không lưu riêng tiền làm thêm thành thuộc tính mà tính động từ trạng thái hiện có theo đúng yêu cầu đề bài.
 */
 public double calculateBasePay() {
 if (workedHours <= STANDARD_HOURS_LIMIT) {
 return workedHours * hourlyRate;
 } else {
 double regularPay = STANDARD_HOURS_LIMIT * hourlyRate;
 double overtimePay = (workedHours - STANDARD_HOURS_LIMIT) * hourlyRate * OVERTIME_RATE_MULTIPLIER;
 return regularPay + overtimePay;
 }
 }

 /**
 * Ghi đè phương thức tính thu nhập trước khấu trừ:
 * grossPay = basePay + monthlyBonus
 */
 @Override
 public double calculateGrossPay() {
 return calculateBasePay() + getMonthlyBonus();
 }

 @Override
 public String getEmployeeType() {
 return "Nhân viên Theo giờ (Hourly)";
 }

 @Override
 public void displayPayrollInfo() {
 System.out.printf("[Mã: %-5s | Họ tên: %-20s | Phòng: %-12s | Loại: %-20s]\n",
 getEmployeeId(), getFullName(), getDepartment(), "Hourly");
 System.out.printf(" + Đơn giá giờ: %,15.0f VNĐ/giờ\n", hourlyRate);
 System.out.printf(" + Số giờ làm : %15.1f giờ (Chuẩn: %.1fh, Tăng ca: %.1fh)\n",
 workedHours, getRegularHours(), getOvertimeHours());
 System.out.printf(" + Lương căn bản: %,13.0f VNĐ\n", calculateBasePay());
 System.out.printf(" + Thưởng : %,15.0f VNĐ\n", getMonthlyBonus());
 System.out.printf(" => THU NHẬP : %,15.0f VNĐ\n", calculateGrossPay());
 }
}
