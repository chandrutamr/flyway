
package com.example.order.controller;

import com.example.order.entity.Book;
import com.example.order.entity.Order;
import com.example.order.repo.BookRepository;
import com.example.order.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private BookRepository bookRepository;


    @GetMapping("/order")
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

    @PostMapping("/order")
    public Boolean saveOrders(){
       try{
           bookRepository.save(new Book(1L,"chandru"));
           bookRepository.save(new Book(2L,"surya"));
           bookRepository.save(new Book(3L,"test"));
           return true;
       }catch (Exception e){
            e.printStackTrace();
            return false;
       }

    }
}
