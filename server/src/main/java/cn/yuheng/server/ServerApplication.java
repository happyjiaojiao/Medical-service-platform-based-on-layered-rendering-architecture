package cn.yuheng.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
//		SpringApplication.run(ServerApplication.class, args);
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/applicationContest.xml");
	}

}
