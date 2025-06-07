package kr.co.gyeol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudService1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudService1Application.class, args);
    }

}
