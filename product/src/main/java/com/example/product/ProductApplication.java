package com.example.product;
import com.example.product.entity.Book;
import com.example.product.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.example.product")
@EnableJpaRepositories("com.example.product")
@EntityScan("com.example.product.entity")
@SpringBootApplication
@EnableCaching
public class ProductApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @Autowired
    private BookRepository repository;

    @Override
    public void run(String... args) {

        //log.info("StartApplication...");
        repository.save(new Book(1L,"JavaProduct"));
        repository.save(new Book(2L,"NodeProduct"));
        repository.save(new Book(3L,"PythonProduct"));


        System.out.println("\nfindAll()");
        repository.findAll().forEach(x -> System.out.println(x));

        System.out.println("\nfindById(1L)");
        repository.findById(1l).ifPresent(x -> System.out.println(x));

        System.out.println("\nfindByName('Node')");
        repository.findByName("Node").forEach(x -> System.out.println(x));

    }
}
