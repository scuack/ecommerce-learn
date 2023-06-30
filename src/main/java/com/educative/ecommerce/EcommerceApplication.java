package com.educative.ecommerce;

import com.educative.ecommerce.entity.Category;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {

		SpringApplication.run(EcommerceApplication.class, args);
		Category category1 = new Category(null, "Cars", "best cars", "https://loscoches.com/wp-content/uploads/2021/04/carros-deportivos-potencia.jpg");
	}

}
