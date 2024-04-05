package com.dzungyb.learning_spring_boot;

import com.dzungyb.learning_spring_boot.configuration.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class LearningSpringBootApplication implements CommandLineRunner {
    @Autowired
    private Properties properties;

    public static void main(String[] args) {
        SpringApplication.run(LearningSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Email: " + properties.getEmail());
        System.out.println("Password: " + properties.getPassword());
        System.out.println("username: " + properties.getUsername());
        int id = 1;
        for (String author : properties.getAuthors()) {
            System.out.println("Author" + (id++) + ": " + author);
        }
    }
}

/*  implements CommanlineRunner
1) Khởi tạo ứng dụng: SpringBoot sẽ bắt đầu bằng việc khởi tạo các thành phần cần thiết, cấu hình các bean và context ứng dung.
2) Tự động gọi phương thức run: Sau khi context của ứng dụng đã được khởi tạo, SpringBoot sẽ tự động gọi phương thức run của bean được chứa trong context.
3) Thực thi logic trong phương thức run: các logic trong phương thức run sẽ được thực thi. Cho phép thực hiện các công việc cần thiết trước khi ứng dụng bắt đầu chạy chính thức.
như khởi tạo dữ liệu, cấu hình các thông số, kết nối với các service khác, ...
4) Chạy ứng dụng: Sau khi phương thức run đã được thực thi, ứng dụng sẽ bắt đầu chạy chính thức.

 */