/****************/
// Mã sinh viên: 202418841
// Họ tên: Dương Thị Quỳnh Anh
/****************/
package projectteam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ProjectTeam {
 private String projectCode;
 private String projectName;
 private Employee leader; // Liên kết không sở hữu đến trưởng nhóm
 private final List<Employee> members = new ArrayList<>(); // Danh sách liên kết không sở hữu

 public ProjectTeam(String projectCode, String projectName) {
 setProjectCode(projectCode);
 setProjectName(projectName);
 this.leader = null;
 System.out.printf("[Dự án] Khởi tạo nhóm dự án [%s - %s] (Chưa có trưởng nhóm).\n",
 this.projectCode, this.projectName);
 }


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


 public boolean addMember(Employee employee) {
 return addMember(employee, false);
 }

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


 public void disbandTeam() {
 System.out.printf("[Hủy nhóm] Nhóm dự án [%s - %s] đã được giải tán. Toàn bộ liên kết thành viên được giải phóng.\n",
 projectCode, projectName);
 this.members.clear();
 this.leader = null;
 }
}
