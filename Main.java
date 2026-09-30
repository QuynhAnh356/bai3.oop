/****************/
// Mã sinh viên: 202418841
// Họ tên: Dương Thị Quỳnh Anh
/****************/
package payroll;

/**
 * Chương trình kiểm thử cho Bài tập: HỆ THỐNG TÍNH LƯƠNG VÀ THƯỞNG NHÂN SỰ .
 * Thực thi các kịch bản chuẩn trong đề bài và 13 trường hợp kiểm thử biên/lỗi.
 */
public class Main {
 public static void main(String[] args) {
 System.out.println("==========================================================================");
 System.out.println(" BÀI TẬP: HỆ THỐNG TÍNH LƯƠNG VÀ THƯỞNG NHÂN SỰ ");
 System.out.println(" Sinh viên thực hiện: Dương Thị Quỳnh Anh - MSV: 202418841");
 System.out.println("==========================================================================\n");

 runStandardScenario();
 runEdgeAndErrorTestCases();
 }

 /**
 * Thực hiện kịch bản chuẩn theo dữ liệu kiểm thử mục C trong đề bài.
 */
 private static void runStandardScenario() {
 System.out.println("--------------------------------------------------------------------------");
 System.out.println(">>> PHẦN 1: THỰC THI BỘ DỮ LIỆU KIỂM THỬ CHUẨN ");
 System.out.println("--------------------------------------------------------------------------");

 // 1. Tạo kỳ bảng lương tháng 2026-09
 Payroll payroll = new Payroll("2026-09");

 // 2. Tạo nhân viên lương cố định E001
 SalariedEmployee e1 = new SalariedEmployee("E001", "Nguyễn Minh An", "Đào tạo", 15_000_000, 2_000_000);
 // Nạp chồng addBonus phiên bản 1: Số tiền cố định
 e1.addBonus(1_000_000);

 // 3. Tạo nhân viên theo giờ E002 (Không quá 160h)
 HourlyEmployee e2 = new HourlyEmployee("E002", "Trần Thu Bình", "Hỗ trợ", 100_000, 150);
 // Nạp chồng addBonus phiên bản 2: Số tiền kèm lý do
 e2.addBonus(500_000, "Hỗ trợ giải đáp khách hàng xuất sắc");

 // 4. Tạo nhân viên theo giờ E003 (Có giờ làm thêm > 160h: 170h)
 HourlyEmployee e3 = new HourlyEmployee("E003", "Lê Hoàng Chi", "Hỗ trợ", 100_000, 170);
 // Không có thưởng

 // 5. Tạo nhân viên kinh doanh E004
 SalesEmployee e4 = new SalesEmployee("E004", "Phạm Quốc Dũng", "Kinh doanh", 8_000_000, 200_000_000, 0.05);
 // Nạp chồng addBonus phiên bản 3: Tỷ lệ 2% của giá trị tham chiếu 50.000.000
 e4.addBonus(0.02, 50_000_000, "Vượt chỉ tiêu doanh số quý 3");

 // Thêm vào bảng lương
 payroll.addEmployee(e1);
 payroll.addEmployee(e2);
 payroll.addEmployee(e3);
 payroll.addEmployee(e4);

 // Hiển thị chi tiết từng nhân viên
 System.out.println("\n--- CHI TIẾT THU NHẬP TỪNG NHÂN SỰ ---");
 e1.displayPayrollInfo();
 System.out.println();
 e2.displayPayrollInfo();
 System.out.println();
 e3.displayPayrollInfo();
 System.out.println();
 e4.displayPayrollInfo();
 System.out.println();

 // Hiển thị bảng lương tổng thể qua lời gọi đa hình
 payroll.displayPayroll();

 // Kiểm tra kết quả mong đợi
 double totalPayroll = payroll.calculateTotalPayroll();
 double supportDeptTotal = payroll.calculatePayrollByDepartment("Hỗ trợ");

 System.out.println(">>> ĐỐI SOÁT VỚI KẾT QUẢ MONG ĐỢI :");
 System.out.printf(" - Tổng bảng lương thực tế: %,.0f VNĐ | Kỳ vọng: 70.000.000 VNĐ => %s\n",
 totalPayroll, (totalPayroll == 70_000_000 ? "CHÍNH XÁC [PASSED]" : "SAI [FAILED]"));
 System.out.printf(" - Lương phòng Hỗ trợ thực tế: %,.0f VNĐ | Kỳ vọng: 33.000.000 VNĐ => %s\n",
 supportDeptTotal, (supportDeptTotal == 33_000_000 ? "CHÍNH XÁC [PASSED]" : "SAI [FAILED]"));
 
 System.out.println("\n--- MINH HỌA LỊCH SỬ THƯỞNG CỦA NHÂN VIÊN E004 ---");
 for (BonusRecord record : e4.getBonusHistory()) {
 System.out.println(" " + record);
 }
 System.out.println();
 }

 /**
 * Thực thi 13 test case kiểm thử biên và kiểm thử lỗi .
 */
 private static void runEdgeAndErrorTestCases() {
 System.out.println("--------------------------------------------------------------------------");
 System.out.println(">>> PHẦN 2: THỰC THI 13 KIỂM THỬ BIÊN VÀ BẮT LỖI ");
 System.out.println("--------------------------------------------------------------------------");

 int testId = 1;

 // TC 1: Mã nhân sự rỗng
 runTest(testId++, "Tạo nhân viên với mã rỗng", () -> {
 new SalariedEmployee("", "Nguyễn Văn A", 10_000_000);
 });

 // TC 2: Họ tên nhân sự rỗng
 runTest(testId++, "Tạo nhân viên với họ tên để trống (null hoặc spaces)", () -> {
 new HourlyEmployee("E100", " ", 100_000, 100);
 });

 // TC 3: Phòng ban rỗng trong constructor đầy đủ
 runTest(testId++, "Tạo nhân viên với phòng ban rỗng", () -> {
 new SalariedEmployee("E101", "Trần Văn B", "", 10_000_000, 1_000_000);
 });

 // TC 4: Thưởng số tiền âm hoặc = 0 (addBonus v1)
 runTest(testId++, "Thêm thưởng số tiền âm (-500.000 VNĐ)", () -> {
 Employee emp = new SalariedEmployee("E102", "Lê Văn C", 10_000_000);
 emp.addBonus(-500_000);
 });

 // TC 5: Thưởng kèm lý do nhưng lý do rỗng (addBonus v2)
 runTest(testId++, "Thêm thưởng với lý do rỗng", () -> {
 Employee emp = new SalariedEmployee("E103", "Phạm Văn D", 10_000_000);
 emp.addBonus(1_000_000, " ");
 });

 // TC 6: Thưởng theo tỷ lệ vượt ngưỡng 0.5 (addBonus v3: rate = 0.6)
 runTest(testId++, "Thêm thưởng với tỷ lệ rate = 0.6 (> 0.5)", () -> {
 Employee emp = new SalariedEmployee("E104", "Hoàng Văn E", 10_000_000);
 emp.addBonus(0.6, 10_000_000, "Thưởng đặc biệt");
 });

 // TC 7: Thưởng theo tỷ lệ với referenceAmount <= 0
 runTest(testId++, "Thưởng theo tỷ lệ với giá trị tham chiếu âm (-10.000.000)", () -> {
 Employee emp = new SalariedEmployee("E105", "Đỗ Văn F", 10_000_000);
 emp.addBonus(0.1, -10_000_000, "Thưởng doanh thu");
 });

 // TC 8: Lương cố định tháng âm ở SalariedEmployee
 runTest(testId++, "SalariedEmployee với lương tháng âm (-15.000.000)", () -> {
 new SalariedEmployee("E106", "Vũ Văn G", -15_000_000);
 });

 // TC 9: Phụ cấp trách nhiệm âm ở SalariedEmployee
 runTest(testId++, "SalariedEmployee với phụ cấp trách nhiệm âm (-2.000.000)", () -> {
 new SalariedEmployee("E107", "Ngô Văn H", "Kế toán", 10_000_000, -2_000_000);
 });

 // TC 10: Số giờ làm vượt quá ngưỡng tối đa 250h ở HourlyEmployee
 runTest(testId++, "HourlyEmployee với số giờ làm 260 giờ (> 250h)", () -> {
 new HourlyEmployee("E108", "Đinh Văn I", 100_000, 260);
 });

 // TC 11: Tỷ lệ hoa hồng vượt quá ngưỡng 0.3 ở SalesEmployee
 runTest(testId++, "SalesEmployee với tỷ lệ hoa hồng 0.4 (> 0.3)", () -> {
 new SalesEmployee("E109", "Bùi Văn K", 8_000_000, 0.4);
 });

 // TC 12: Thêm nhân sự trùng mã vào Payroll
 runTest(testId++, "Thêm 2 nhân sự trùng mã 'E001' vào cùng một kỳ Payroll", () -> {
 Payroll p = new Payroll("2026-09");
 p.addEmployee(new SalariedEmployee("E001", "An", 10_000_000));
 p.addEmployee(new HourlyEmployee("E001", "Trùng Mã", 100_000, 100));
 });

 // TC 13: Xử lý an toàn khi bảng lương rỗng
 System.out.printf("[Test Case %02d] Xử lý an toàn khi bảng lương rỗng (0 nhân sự): ", testId++);
 try {
 Payroll emptyPayroll = new Payroll("2026-10");
 double total = emptyPayroll.calculateTotalPayroll();
 Employee highest = emptyPayroll.findHighestPaidEmployee();
 if (total == 0.0 && highest == null) {
 System.out.println("THÀNH CÔNG [PASSED] - Tổng lương = 0, HighestPaid = null.");
 } else {
 System.out.println("THẤT BẠI [FAILED].");
 }
 } catch (Exception ex) {
 System.out.println("LỖI NGOẠI LỆ: " + ex.getMessage());
 }

 System.out.println("--------------------------------------------------------------------------\n");
 }

 private static void runTest(int id, String testName, Runnable action) {
 System.out.printf("[Test Case %02d] %-55s : ", id, testName);
 try {
 action.run();
 System.out.println("THẤT BẠI [FAILED] - Hệ thống không ném ngoại lệ như mong đợi.");
 } catch (IllegalArgumentException | IllegalStateException ex) {
 System.out.println("THÀNH CÔNG [PASSED] - Đã chặn đúng: \"" + ex.getMessage() + "\"");
 } catch (Exception ex) {
 System.out.println("LỖI KHÁC [UNEXPECTED]: " + ex.getClass().getSimpleName() + " - " + ex.getMessage());
 }
 }
}
