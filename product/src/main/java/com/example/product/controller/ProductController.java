
package com.example.product.controller;


import com.example.product.entity.Book;
import com.example.product.repo.BookRepository;
import com.example.product.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private BookRepository bookRepository;


    @GetMapping("/product")
    public String getOrderNameById(@RequestParam("id") Long id){

        /*Optional<Order> order = repository.findById(id);
        return order.get().getName();*/

        System.out.println("\nfindAll()");
        bookRepository.findAll().forEach(x -> System.out.println(x));

        System.out.println("\nfindById(1L)");
        bookRepository.findById(1l).ifPresent(x -> System.out.println(x));

        System.out.println("\nfindByName('Node')");
         bookRepository.findByName("Node").forEach(x -> System.out.println(x));
         return "true";
    }

    @PostMapping("/product")
    public Boolean saveOrders(){
       try{
           bookRepository.save(new Book(1L,"chandruProduct"));
           bookRepository.save(new Book(2L,"suryaProduct"));
           bookRepository.save(new Book(3L,"testProduct"));
           return true;
       }catch (Exception e){
            e.printStackTrace();
            return false;
       }

    }
}
