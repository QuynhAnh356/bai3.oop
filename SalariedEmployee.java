/****************/
// Mã sinh viên: 202418841
// Họ tên: Dương Thị Quỳnh Anh
/****************/
package payroll;


public class SalariedEmployee extends Employee {
 private double monthlySalary;
 private double responsibilityAllowance;


 public SalariedEmployee(String employeeId, String fullName, double monthlySalary) {
 this(employeeId, fullName, "Unassigned", monthlySalary, 0.0);
 }


 public SalariedEmployee(String employeeId, String fullName, String department,
 double monthlySalary, double responsibilityAllowance) {
 super(employeeId, fullName, department);
 setMonthlySalary(monthlySalary);
 setResponsibilityAllowance(responsibilityAllowance);
 }

 public double getMonthlySalary() {
 return monthlySalary;
 }

 public void setMonthlySalary(double monthlySalary) {
 if (monthlySalary < 0) {
 throw new IllegalArgumentException("Lỗi: Lương tháng không được âm. Nhận: " + monthlySalary);
 }
 this.monthlySalary = monthlySalary;
 }

 public double getResponsibilityAllowance() {
 return responsibilityAllowance;
 }

 public void setResponsibilityAllowance(double responsibilityAllowance) {
 if (responsibilityAllowance < 0) {
 throw new IllegalArgumentException("Lỗi: Phụ cấp trách nhiệm không được âm. Nhận: " + responsibilityAllowance);
 }
 this.responsibilityAllowance = responsibilityAllowance;
 }

 /**
 * Ghi đè phương thức tính thu nhập trước khấu trừ:
 * grossPay = monthlySalary + responsibilityAllowance + monthlyBonus
 */
 @Override
 public double calculateGrossPay() {
 return monthlySalary + responsibilityAllowance + getMonthlyBonus();
 }

 @Override
 public String getEmployeeType() {
 return "Nhân viên Lương cố định (Salaried)";
 }

 @Override
 public void displayPayrollInfo() {
 System.out.printf("[Mã: %-5s | Họ tên: %-20s | Phòng: %-12s | Loại: %-20s]\n",
 getEmployeeId(), getFullName(), getDepartment(), "Salaried");
 System.out.printf(" + Lương tháng: %,15.0f VNĐ\n", monthlySalary);
 System.out.printf(" + Phụ cấp TN : %,15.0f VNĐ\n", responsibilityAllowance);
 System.out.printf(" + Thưởng : %,15.0f VNĐ\n", getMonthlyBonus());
 System.out.printf(" => THU NHẬP : %,15.0f VNĐ\n", calculateGrossPay());
 }
}
