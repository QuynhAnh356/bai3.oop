/****************/
// Mã sinh viên: 202418841
// Họ tên: Dương Thị Quỳnh Anh
/****************/
package projectteam;

/**
 * Chương trình kiểm thử cho Bài tập: NHÓM DỰ ÁN VÀ NHÂN SỰ .
 * Thực thi chính xác toàn bộ kịch bản 15 bước theo yêu cầu của đề bài.
 */
public class Main {
 public static void main(String[] args) {
 System.out.println("==========================================================================");
 System.out.println(" BÀI TẬP: QUẢN LÝ NHÓM DỰ ÁN VÀ NHÂN SỰ ");
 System.out.println(" Sinh viên thực hiện: Dương Thị Quỳnh Anh - MSV: 202418841");
 System.out.println("==========================================================================\n");

 run15StepsScenario();
 }

 private static void run15StepsScenario() {
 System.out.println(">>> BẮT ĐẦU THỰC THI KỊCH BẢN KIỂM THỬ 15 BƯỚC \n");

 // -------------------------------------------------------------
 // BƯỚC 1: Tạo hai Employee bằng hai constructor khác nhau
 // -------------------------------------------------------------
 System.out.println("--- [BƯỚC 1] Tạo hai Employee bằng hai constructor khác nhau ---");
 // Dùng constructor 2 tham số (lương mặc định = 0)
 Employee emp1 = new Employee("NV001", "Hoàng Văn Nam");
 emp1.setBaseSalary(12_000_000); // cập nhật lương hợp lệ
 // Dùng constructor đầy đủ 3 tham số
 Employee emp2 = new Employee("NV002", "Trần Thị Lan", 14_000_000);
 System.out.println();

 // -------------------------------------------------------------
 // BƯỚC 2: Tạo hai SoftwareEngineer bằng hai constructor khác nhau
 // -------------------------------------------------------------
 System.out.println("--- [BƯỚC 2] Tạo hai SoftwareEngineer bằng hai constructor khác nhau ---");
 // Dùng constructor rút gọn 3 tham số (lương và phụ cấp mặc định = 0)
 SoftwareEngineer se1 = new SoftwareEngineer("SE001", "Nguyễn Tuấn Anh", "Java");
 se1.setBaseSalary(18_000_000);
 se1.setTechnicalAllowance(3_000_000);
 // Dùng constructor đầy đủ 5 tham số
 SoftwareEngineer se2 = new SoftwareEngineer("SE002", "Phan Thanh Sơn", 22_000_000, "C#/.NET", 4_500_000);
 System.out.println();

 // -------------------------------------------------------------
 // BƯỚC 3: Tăng lương một nhân sự bằng số tiền cố định
 // -------------------------------------------------------------
 System.out.println("--- [BƯỚC 3] Tăng lương một nhân sự (NV001) bằng số tiền cố định ---");
 System.out.printf(" Lương trước khi tăng: %,.0f VNĐ\n", emp1.getBaseSalary());
 emp1.increaseSalary(2_000_000); // Phiên bản 1: Số tiền cố định
 System.out.println();

 // -------------------------------------------------------------
 // BƯỚC 4: Tăng lương một nhân sự khác theo phần trăm
 // -------------------------------------------------------------
 System.out.println("--- [BƯỚC 4] Tăng lương một nhân sự khác (SE001) theo tỷ lệ phần trăm (10%) ---");
 System.out.printf(" Lương cơ bản trước khi tăng: %,.0f VNĐ\n", se1.getBaseSalary());
 se1.increaseSalary(10.0, true); // Phiên bản 2: byPercentage = true
 System.out.println();

 // -------------------------------------------------------------
 // BƯỚC 5: Tạo nhóm dự án không có trưởng nhóm
 // -------------------------------------------------------------
 System.out.println("--- [BƯỚC 5] Tạo nhóm dự án thứ nhất không có trưởng nhóm ---");
 ProjectTeam teamAlpha = new ProjectTeam("PRJ-01", "Hệ thống Quản lý Bán lẻ (Alpha)");
 System.out.println();

 // -------------------------------------------------------------
 // BƯỚC 6: Thêm một nhân sự vào nhóm bằng addMember(employee)
 // -------------------------------------------------------------
 System.out.println("--- [BƯỚC 6] Thêm một nhân sự (NV001) vào nhóm bằng addMember(employee) ---");
 teamAlpha.addMember(emp1);
 teamAlpha.addMember(emp2);
 System.out.println();

 // -------------------------------------------------------------
 // BƯỚC 7: Thêm một kỹ sư bằng addMember(employee, true) để đặt làm trưởng nhóm
 // -------------------------------------------------------------
 System.out.println("--- [BƯỚC 7] Thêm một kỹ sư (SE002) bằng addMember(employee, true) đặt làm trưởng nhóm ---");
 teamAlpha.addMember(se2, true);
 System.out.println();

 // -------------------------------------------------------------
 // BƯỚC 8: Thử thêm lại một thành viên đã tồn tại (kiểm tra bất biến không trùng)
 // -------------------------------------------------------------
 System.out.println("--- [BƯỚC 8] Thử thêm lại thành viên đã tồn tại (NV001) vào nhóm ---");
 boolean addDuplicateResult = teamAlpha.addMember(emp1);
 System.out.printf(" Kết quả thao tác thêm trùng: %s\n\n",
 addDuplicateResult ? "ĐƯỢC CHẤP NHẬN (LỖI)" : "BỊ TỪ CHỐI (ĐÚNG BẤT BIẾN)");

 // -------------------------------------------------------------
 // BƯỚC 9: Hiển thị danh sách bằng lời gọi đa hình
 // -------------------------------------------------------------
 System.out.println("--- [BƯỚC 9] Hiển thị danh sách nhóm bằng lời gọi đa hình ---");
 teamAlpha.displayTeam();

 // -------------------------------------------------------------
 // BƯỚC 10: Tính tổng chi phí nhân sự hằng tháng
 // -------------------------------------------------------------
 System.out.println("--- [BƯỚC 10] Tính tổng chi phí nhân sự hằng tháng của nhóm dự án ---");
 double totalCostAlpha = teamAlpha.calculateTotalMonthlyCost();
 System.out.printf(" => Tổng chi phí hàng tháng của nhóm %s là: %,.0f VNĐ\n\n",
 teamAlpha.getProjectCode(), totalCostAlpha);

 // -------------------------------------------------------------
 // BƯỚC 11: Thử xóa trưởng nhóm hiện tại và kiểm tra thao tác bị từ chối
 // -------------------------------------------------------------
 System.out.println("--- [BƯỚC 11] Thử xóa trưởng nhóm hiện tại (SE002) và kiểm tra thao tác bị từ chối ---");
 boolean removeLeaderResult = teamAlpha.removeMember(se2.getId());
 System.out.printf(" Kết quả xóa trưởng nhóm hiện tại: %s\n\n",
 removeLeaderResult ? "XÓA THÀNH CÔNG (VI PHẠM QUY TẮC)" : "BỊ TỪ CHỐI THÀNH CÔNG (ĐÚNG RÀNG BUỘC)");

 // -------------------------------------------------------------
 // BƯỚC 12: Đổi trưởng nhóm rồi xóa người từng là trưởng nhóm
 // -------------------------------------------------------------
 System.out.println("--- [BƯỚC 12] Đổi trưởng nhóm sang SE001 rồi xóa người từng là trưởng nhóm cũ (SE002) ---");
 // Đổi trưởng nhóm sang kỹ sư SE001 (sẽ tự động đưa SE001 vào nhóm nếu chưa có)
 teamAlpha.changeLeader(se1);
 // Sau khi đổi trưởng nhóm, SE002 giờ là thành viên bình thường, có thể xóa hợp lệ
 boolean removeOldLeaderResult = teamAlpha.removeMember(se2.getId());
 System.out.printf(" Kết quả xóa cựu trưởng nhóm SE002 sau khi đã thay thế: %s\n\n",
 removeOldLeaderResult ? "XÓA HỢP LỆ THÀNH CÔNG" : "THẤT BẠI");

 // -------------------------------------------------------------
 // BƯỚC 13: Tạo một nhóm thứ hai và thêm một nhân sự đã có ở nhóm thứ nhất
 // (chứng minh quan hệ kết tập nhiều nhóm: Many-to-Many Aggregation)
 // -------------------------------------------------------------
 System.out.println("--- [BƯỚC 13] Tạo nhóm dự án thứ hai (Beta) và thêm nhân sự đã có ở nhóm một (NV001) ---");
 
 // Tạo biến để tham chiếu kiểm chứng sau khối lệnh
 Employee employeeSharedRef = emp1;

 // Bắt đầu một khối lệnh cục bộ (local scope) để mô phỏng vòng đời
 {
 System.out.println(" [Khối lệnh cục bộ bắt đầu]");
 ProjectTeam teamBeta = new ProjectTeam("PRJ-02", "Hệ thống AI Analytics (Beta)");
 // Thêm nhân sự NV001 (đã có ở nhóm Alpha) vào nhóm Beta
 teamBeta.addMember(employeeSharedRef);
 teamBeta.addMember(se2, true); // SE002 được đưa vào nhóm Beta làm Leader

 System.out.println("\n [Kiểm tra nhóm Beta trong khối lệnh]:");
 teamBeta.displayTeam();

 // -------------------------------------------------------------
 // BƯỚC 14: Hủy nhóm thứ hai bằng cách kết thúc một khối lệnh cục bộ
 // -------------------------------------------------------------
 System.out.println("--- [BƯỚC 14] Hủy nhóm thứ hai bằng cách giải tán và kết thúc khối lệnh cục bộ ---");
 teamBeta.disbandTeam();
 System.out.println(" [Khối lệnh cục bộ kết thúc - Biến teamBeta đã ra ngoài phạm vi (Out of Scope)]\n");
 }

 // -------------------------------------------------------------
 // BƯỚC 15: Chứng minh nhân sự của nhóm thứ hai vẫn tồn tại sau khi nhóm bị hủy
 // -------------------------------------------------------------
 System.out.println("--- [BƯỚC 15] Chứng minh nhân sự của nhóm thứ hai (NV001, SE002) vẫn tồn tại nguyên vẹn ---");
 System.out.println(" Nhân sự NV001 sau khi nhóm Beta bị hủy:");
 employeeSharedRef.displayInfo();
 System.out.println(" Nhân sự SE002 sau khi nhóm Beta bị hủy:");
 se2.displayInfo();

 System.out.println("\n => KẾT LUẬN QUAN HỆ KẾT TẬP (AGGREGATION):");
 System.out.println(" Các đối tượng Employee tồn tại hoàn toàn độc lập với ProjectTeam.");
 System.out.println(" Việc giải tán ProjectTeam chỉ hủy liên kết nội bộ, không làm ảnh hưởng hay hủy các Employee!");
 System.out.println("==========================================================================\n");
 }
}
