Yêu cầu: 
* Eclipse 4.7.0.
* Git: link download: https://git-scm.com/downloads.  

Sau khi code xong phần của mình mà muốn lưu thì:
* Bước 1: Bật git bash ở project folder.
* Bước 2: git add . để add tất cả những code đã làm lên stage.
* Bước 3: git commit -m "<Lời nhắn>" để commit tất cả các công việc đã làm.
* Bước 4: Sau khi commit xong chúng ta pull công việc ở trên repo xuống local để đồng bộ hóa code với máy tính của mình.
* Bước 5: Fix conflict (nếu có) trong khi chúng ta pull ở repo github về, khi fix conflict cố gắng liên hệ với người code phần đang bị conflict với mình để thống nhất bỏ phần nào, thêm phần nào. Sau khi fix conflict thực hiện các bước:  
  * Bước 1: git add . để add tất cả những gì đã fix.
  * Bước 2: git commit -m "<Lời nhắn>" một lần nữa để lưu lại những gì đã fix. Nhớ đặt lời nhắn về những gì đã fix.
* Bước 6: git push để push local repo lên repo ở github.
Và chúng ta đã hoàn thành công việc push bài lên repo để lưu lại những gì chúng ta đã làm.  

Big update:  
*  Chia component Jpanel và Jlabel ra thành 2 class. Tất cả xử lí chuột được đưa vào class DrawContainer.
*  Bổ sung hệ trục tọa độ. Trước khi vẽ hình hay biến đổi thì phải dùng hàm convertToCoordinatePoints để biến đổi từ một điểm tọa độ thường sang hệ tọa độ người dùng.
*  Bổ sung trong class các hình một ArrayList chứa các điểm để vẽ được hình đó. Bổ sung thêm luôn getter/setter cho ArrayList này.  
