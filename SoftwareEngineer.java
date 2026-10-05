/****************/
// Mã sinh viên: 202418841
// Họ tên: Dương Thị Quỳnh Anh
/****************/
package projectteam;

/**
 * Lớp đại diện cho Kỹ sư phần mềm (SoftwareEngineer), kế thừa từ Employee.
 * Bổ sung thông tin về ngôn ngữ lập trình chính và phụ cấp kỹ thuật.
 */
public class SoftwareEngineer extends Employee {
 private String primaryLanguage;
 private double technicalAllowance;

 /**
 * Constructor rút gọn: Khởi tạo với ngôn ngữ chính, lương cơ bản và phụ cấp mặc định = 0.
 * 
 * @param id Mã nhân sự.
 * @param fullName Họ tên.
 * @param primaryLanguage Ngôn ngữ lập trình chính (không được rỗng).
 */
 public SoftwareEngineer(String id, String fullName, String primaryLanguage) {
 this(id, fullName, 0.0, primaryLanguage, 0.0);
 }

 /**
 * Constructor đầy đủ 5 tham số của SoftwareEngineer.
 * 
 * @param id Mã nhân sự.
 * @param fullName Họ tên.
 * @param baseSalary Lương cơ bản (>= 0).
 * @param primaryLanguage Ngôn ngữ lập trình chính (không được rỗng).
 * @param technicalAllowance Phụ cấp kỹ thuật (>= 0).
 */
 public SoftwareEngineer(String id, String fullName, double baseSalary,
 String primaryLanguage, double technicalAllowance) {
 super(id, fullName, baseSalary);
 setPrimaryLanguage(primaryLanguage);
 setTechnicalAllowance(technicalAllowance);
 }

 public String getPrimaryLanguage() {
 return primaryLanguage;
 }

 public void setPrimaryLanguage(String primaryLanguage) {
 if (primaryLanguage == null || primaryLanguage.trim().isEmpty()) {
 throw new IllegalArgumentException("Lỗi ràng buộc: Ngôn ngữ lập trình chính không được để trống.");
 }
 this.primaryLanguage = primaryLanguage.trim();
 }

 public double getTechnicalAllowance() {
 return technicalAllowance;
 }

 public void setTechnicalAllowance(double technicalAllowance) {
 if (technicalAllowance < 0) {
 throw new IllegalArgumentException("Lỗi ràng buộc: Phụ cấp kỹ thuật không được âm. Nhận: " + technicalAllowance);
 }
 this.technicalAllowance = technicalAllowance;
 }

 /**
 * Ghi đè phương thức tính tổng chi phí nhân sự:
 * Tổng chi phí = Lương cơ bản + Phụ cấp kỹ thuật
 */
 @Override
 public double calculateMonthlyCost() {
 return getBaseSalary() + technicalAllowance;
 }

 /**
 * Ghi đè phương thức hiển thị thông tin để làm nổi bật đặc thù kỹ sư phần mềm.
 */
 @Override
 public void displayInfo() {
 System.out.printf("[Kỹ sư PM] Mã: %-6s | Họ tên: %-20s | Lương CB: %,14.0f VNĐ | Ngôn ngữ: %-10s | Phụ cấp: %,11.0f VNĐ | Chi phí: %,14.0f VNĐ\n",
 getId(), getFullName(), getBaseSalary(), primaryLanguage, technicalAllowance, calculateMonthlyCost());
 }
}
