package cn.yuheng.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:applicationContext.xml"})
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
//		context.
    }

}
