package com.dzungyb.learning_spring_boot.repository;

import com.dzungyb.learning_spring_boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUserName(String userName);

    Optional<User> findByUserName(String userName);
}

/*
1) Kiểu Optional là một lớp được giới thiệu trong Java 8, nó giúp tránh được việc trả về giá trị null.
: là một container object chứa một giá trị hoặc không chứa bất kỳ giá trị nào. Cung cấp một số phương thức để thực hiện
các hoạt trên giá trị bên trong một cách an toàn mà không cần phải lo lắng về NullPointerException.
+) isPresent()
+) get()
+) orElse  |  orElseGet
+) orElseThrow

2) Kiểu var
: là một từ khóa được sử dụng để suy luận kiểu
+) Phải cung cấp giá trị khởi tạo cho biến đó.

 */
