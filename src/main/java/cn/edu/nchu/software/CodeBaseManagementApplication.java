package cn.edu.nchu.software;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;

@MapperScan(basePackages = { "cn.edu.nchu.software.mapper" })
@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
public class CodeBaseManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeBaseManagementApplication.class, args);
	}

}
