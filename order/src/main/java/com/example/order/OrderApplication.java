package com.example.order;
import com.example.order.entity.Book;
import com.example.order.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/*@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.order")
@EntityScan(basePackages = "com.example.order.entity.*")
*/
@ComponentScan("com.example.order")
@EnableJpaRepositories("com.example.order")
@EntityScan("com.example.order.entity")
@SpringBootApplication
public class OrderApplication implements  CommandLineRunner {


    @Autowired
    private BookRepository repository;


    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
        //displayAllBeans();
    }

    @Override
    public void run(String... args) {

        //log.info("StartApplication...");


        repository.save(new Book(1L,"JavaOrder"));
        repository.save(new Book(2L,"NodeOrder"));
        repository.save(new Book(3L,"PythonOrder"));


        System.out.println("\nfindAll()");
        repository.findAll().forEach(x -> System.out.println(x));

        System.out.println("\nfindById(1L)");
        repository.findById(1l).ifPresent(x -> System.out.println(x));

        System.out.println("\nfindByName('Node')");
        repository.findByName("Node").forEach(x -> System.out.println(x));

    }


}
