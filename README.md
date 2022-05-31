# TTCS-Bai-2
Code chương trình bài 2: hệ thống lên lịch và hiển thị kết quả cho giải đấu cờ vua thế giới
Đường dẫn tới phần code của các tầng: 
  - Tầng DAO: src/dao (Sử dụng JDBC kết nối tới MySQL, là tầng trung gian giữa database và model sử dụng các câu lệnh SQL insert, update, select, ... để lấy thông tin từ database vào model và ngược lại cập nhập lại thông tin từ model vào database)
  - Tầng Model: src/model (Đóng gói thông tin về đối tượng, chứa các phương thức để sử lý nghiệp vụ bài toán).
  - Tầng View: src/view (Điều hướng theo sự kiện người dùng, view người dùng).
