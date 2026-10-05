/****************/
// Mã sinh viên: 202418841
// Họ tên: Dương Thị Quỳnh Anh
/****************/
package projectteam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Lớp đại diện cho một Nhóm Dự Án (ProjectTeam).
 * Thiết kế theo mô hình quan hệ Kết tập (Aggregation):
 * - Nhóm dự án CHỈ LIÊN KẾT (không sở hữu độc quyền) đến các nhân sự và trưởng nhóm.
 * - Một nhân sự có thể tồn tại độc lập với nhóm và có thể tham gia nhiều nhóm dự án khác nhau.
 * - Khi nhóm dự án bị giải tán/hủy, các đối tượng nhân sự bên trong KHÔNG bị hủy theo.
 */
public class ProjectTeam {
 private String projectCode;
 private String projectName;
 private Employee leader; // Liên kết không sở hữu đến trưởng nhóm
 private final List<Employee> members = new ArrayList<>(); // Danh sách liên kết không sở hữu

 /**
 * Constructor nạp chồng phiên bản 1: Tạo nhóm dự án chưa có trưởng nhóm.
 * 
 * @param projectCode Mã dự án (không được rỗng).
 * @param projectName Tên dự án (không được rỗng).
 */
 public ProjectTeam(String projectCode, String projectName) {
 setProjectCode(projectCode);
 setProjectName(projectName);
 this.leader = null;
 System.out.printf("[Dự án] Khởi tạo nhóm dự án [%s - %s] (Chưa có trưởng nhóm).\n",
 this.projectCode, this.projectName);
 }

 /**
 * Constructor nạp chồng phiên bản 2: Thiết lập trưởng nhóm và tự động đưa trưởng nhóm vào danh sách thành viên.
 * 
 * @param projectCode Mã dự án.
 * @param projectName Tên dự án.
 * @param leader Trưởng nhóm (không được null).
 */
 public ProjectTeam(String projectCode, String projectName, Employee leader) {
 setProjectCode(projectCode);
 setProjectName(projectName);
 if (leader == null) {
 throw new IllegalArgumentException("Lỗi: Trưởng nhóm chỉ định không được null.");
 }
 this.members.add(leader);
 this.leader = leader;
 System.out.printf("[Dự án] Khởi tạo nhóm dự án [%s - %s] với Trưởng nhóm: %s.\n",
 this.projectCode, this.projectName, leader.getFullName());
 }

 // ================= GETTER / SETTER =================

 public String getProjectCode() {
 return projectCode;
 }

 public void setProjectCode(String projectCode) {
 if (projectCode == null || projectCode.trim().isEmpty()) {
 throw new IllegalArgumentException("Lỗi bất biến: Mã dự án không được để trống.");
 }
 this.projectCode = projectCode.trim();
 }

 public String getProjectName() {
 return projectName;
 }

 public void setProjectName(String projectName) {
 if (projectName == null || projectName.trim().isEmpty()) {
 throw new IllegalArgumentException("Lỗi bất biến: Tên dự án không được để trống.");
 }
 this.projectName = projectName.trim();
 }

 public Employee getLeader() {
 return leader;
 }

 public List<Employee> getMembers() {
 return Collections.unmodifiableList(members);
 }

 // ================= NẠP CHỒNG PHƯƠNG THỨC addMember() =================

 /**
 * Nạp chồng phiên bản 1: Thêm một nhân sự thông thường vào nhóm dự án.
 * Bất biến: Không có hai thành viên cùng mã trong một nhóm.
 * 
 * @param employee Nhân sự cần thêm.
 * @return true nếu thêm thành công, false nếu bị trùng hoặc không hợp lệ.
 */
 public boolean addMember(Employee employee) {
 return addMember(employee, false);
 }

 /**
 * Nạp chồng phiên bản 2: Thêm nhân sự vào nhóm và có tùy chọn bổ nhiệm làm trưởng nhóm ngay.
 * Quy tắc:
 * - Không thêm trùng nhân sự (dựa trên mã id).
 * - Nếu makeLeader == true, nhân sự được thêm và trở thành trưởng nhóm mới.
 * - Trưởng nhóm cũ vẫn là thành viên của nhóm.
 * 
 * @param employee Nhân sự cần thêm.
 * @param makeLeader true nếu muốn bổ nhiệm làm trưởng nhóm mới.
 * @return true nếu thao tác thành công.
 */
 public boolean addMember(Employee employee, boolean makeLeader) {
 if (employee == null) {
 System.out.println(" [Từ chối] Đối tượng nhân sự là null.");
 return false;
 }

 boolean alreadyMember = contains(employee.getId());

 if (alreadyMember) {
 if (makeLeader) {
 // Đã là thành viên, chỉ cần thăng chức lên leader
 this.leader = employee;
 System.out.printf(" [Bổ nhiệm] Thành viên %s (%s) đã có trong nhóm và được bổ nhiệm làm Trưởng nhóm mới.\n",
 employee.getFullName(), employee.getId());
 return true;
 } else {
 System.out.printf(" [Từ chối] Nhân sự %s (Mã %s) đã tồn tại trong nhóm dự án %s. Không thêm trùng.\n",
 employee.getFullName(), employee.getId(), projectCode);
 return false;
 }
 }

 // Chưa có trong nhóm => thêm vào
 members.add(employee);
 System.out.printf(" [Thêm thành viên] Đã thêm %s (Mã: %s) vào nhóm dự án %s.\n",
 employee.getFullName(), employee.getId(), projectCode);

 if (makeLeader) {
 Employee oldLeader = this.leader;
 this.leader = employee;
 if (oldLeader != null) {
 System.out.printf(" [Bổ nhiệm] %s trở thành Trưởng nhóm mới. Trưởng nhóm cũ (%s) vẫn là thành viên nhóm.\n",
 employee.getFullName(), oldLeader.getFullName());
 } else {
 System.out.printf(" [Bổ nhiệm] %s trở thành Trưởng nhóm dự án.\n", employee.getFullName());
 }
 }
 return true;
 }

 /**
 * Kiểm tra nhân sự có trong nhóm theo mã hay không.
 * 
 * @param employeeId Mã nhân sự cần kiểm tra.
 * @return true nếu tồn tại trong nhóm.
 */
 public boolean contains(String employeeId) {
 if (employeeId == null) return false;
 for (Employee emp : members) {
 if (emp.getId().equalsIgnoreCase(employeeId.trim())) {
 return true;
 }
 }
 return false;
 }

 /**
 * Xóa một thành viên ra khỏi nhóm dự án.
 * Ràng buộc bất biến: KHÔNG ĐƯỢC XÓA TRƯỞNG NHÓM khi chưa chọn trưởng nhóm thay thế!
 * 
 * @param employeeId Mã nhân sự cần xóa.
 * @return true nếu xóa thành công, false nếu thao tác bị từ chối.
 */
 public boolean removeMember(String employeeId) {
 if (employeeId == null || employeeId.trim().isEmpty()) {
 return false;
 }

 // Kiểm tra xem người muốn xóa có phải là trưởng nhóm hiện tại không
 if (leader != null && leader.getId().equalsIgnoreCase(employeeId.trim())) {
 System.out.printf(" [TỪ CHỐI THAO TÁC] Không thể xóa nhân sự %s vì đang là TRƯỞNG NHÓM của dự án %s! " +
 "Cần bổ nhiệm trưởng nhóm thay thế trước khi xóa.\n", employeeId, projectCode);
 return false;
 }

 for (int i = 0; i < members.size(); i++) {
 if (members.get(i).getId().equalsIgnoreCase(employeeId.trim())) {
 Employee removed = members.remove(i);
 System.out.printf(" [Xóa thành viên] Đã xóa nhân sự %s (%s) ra khỏi nhóm dự án %s.\n",
 removed.getFullName(), removed.getId(), projectCode);
 return true;
 }
 }

 System.out.printf(" [Thông báo] Không tìm thấy nhân sự mã %s trong nhóm %s để xóa.\n",
 employeeId, projectCode);
 return false;
 }

 /**
 * Bổ nhiệm trưởng nhóm mới.
 * Ràng buộc: Trưởng nhóm mới phải được thêm vào nhóm nếu chưa phải là thành viên.
 * 
 * @param newLeader Trưởng nhóm mới.
 * @return true nếu đổi thành công.
 */
 public boolean changeLeader(Employee newLeader) {
 if (newLeader == null) {
 throw new IllegalArgumentException("Lỗi: Trưởng nhóm mới không được là null.");
 }
 if (!contains(newLeader.getId())) {
 members.add(newLeader);
 System.out.printf(" [Tự động thêm] Trưởng nhóm mới %s chưa có trong nhóm, đã tự động thêm vào danh sách thành viên.\n",
 newLeader.getFullName());
 }
 Employee oldLeader = this.leader;
 this.leader = newLeader;
 System.out.printf(" [Đổi Trưởng nhóm] Đã thay đổi trưởng nhóm dự án %s sang: %s (Trưởng nhóm cũ: %s vẫn là thành viên).\n",
 projectCode, newLeader.getFullName(), oldLeader != null ? oldLeader.getFullName() : "Không có");
 return true;
 }

 /**
 * Tính tổng chi phí nhân sự hàng tháng cho nhóm dự án.
 * Sử dụng lời gọi đa hình: gọi emp.calculateMonthlyCost() trên từng thành viên.
 * 
 * @return Tổng chi phí hàng tháng (VNĐ).
 */
 public double calculateTotalMonthlyCost() {
 double total = 0.0;
 for (Employee emp : members) {
 // Lời gọi đa hình
 total += emp.calculateMonthlyCost();
 }
 return total;
 }

 /**
 * Hiển thị danh sách và thông tin nhóm bằng lời gọi đa hình.
 */
 public void displayTeam() {
 System.out.println("==========================================================================================");
 System.out.printf(" THÔNG TIN NHÓM DỰ ÁN: [%s] %s\n", projectCode, projectName);
 System.out.println("==========================================================================================");
 System.out.printf("Trưởng nhóm hiện tại: %s\n",
 leader != null ? String.format("%s (Mã: %s)", leader.getFullName(), leader.getId()) : "Chưa chỉ định");
 System.out.printf("Tổng số thành viên : %d người\n", members.size());
 System.out.println("------------------------------------------------------------------------------------------");
 System.out.println("DANH SÁCH THÀNH VIÊN VÀ CHI PHÍ HÀNG THÁNG (GỌI ĐA HÌNH):");
 for (int i = 0; i < members.size(); i++) {
 Employee emp = members.get(i);
 boolean isLead = (leader != null && leader.getId().equalsIgnoreCase(emp.getId()));
 System.out.printf(" [%02d]%s ", i + 1, isLead ? " [LEADER]" : " ");
 // Gọi phương thức ảo displayInfo() đa hình
 emp.displayInfo();
 }
 System.out.println("------------------------------------------------------------------------------------------");
 System.out.printf("TỔNG CHI PHÍ NHÂN SỰ HÀNG THÁNG CỦA DỰ ÁN: %,.0f VNĐ\n", calculateTotalMonthlyCost());
 System.out.println("==========================================================================================\n");
 }

 /**
 * Giải tán / Hủy nhóm dự án (Mô phỏng quan hệ kết tập Aggregation).
 * Chỉ giải phóng danh sách liên kết nội bộ của nhóm dự án.
 * Tuyệt đối KHÔNG HỦY các đối tượng Employee độc lập bên ngoài.
 */
 public void disbandTeam() {
 System.out.printf("[Hủy nhóm] Nhóm dự án [%s - %s] đã được giải tán. Toàn bộ liên kết thành viên được giải phóng.\n",
 projectCode, projectName);
 this.members.clear();
 this.leader = null;
 }
}
