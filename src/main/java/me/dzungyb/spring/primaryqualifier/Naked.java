package me.dzungyb.spring.primaryqualifier;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("naked")
//@Primary
// @Primary có tác dụng chọn ra Bean mặc định khi có nhiều Bean cùng loại.
public class Naked implements Outfit {
    @Override
    public void wear() {
        System.out.println("Đang không mặc gì");
    }
}
