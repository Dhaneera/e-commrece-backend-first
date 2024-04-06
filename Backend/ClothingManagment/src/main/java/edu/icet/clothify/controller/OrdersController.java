package edu.icet.clothify.controller;

import edu.icet.clothify.dto.OrdersDto;
import edu.icet.clothify.entity.Orders;
import edu.icet.clothify.entity.Product;
import edu.icet.clothify.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrdersController {
    @Autowired
    OrdersService ordersService;
    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }


    @PostMapping("/add")
    public boolean addOrder(@RequestBody OrdersDto ordersDto){
        return ordersService.addOrder(ordersDto);
    }

    @GetMapping("/getAllByCustomer/{customerName}")
    public ResponseEntity<List<OrdersDto>> getAllOrdersByCustomer(@PathVariable String customerName){
        List<OrdersDto> orders = ordersService.getAllOrdersByCustomer(customerName);

        if (orders !=null && !orders.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
    }
    @DeleteMapping("/delete/{id}")
    public Boolean deleteOrdersById(@PathVariable Long id){
        return ordersService.deleteOrders(id);
    }

    @PutMapping("/update/{id}")
    public Orders updateProduct(@PathVariable Long id, @RequestBody OrdersDto ordersDto){
        return ordersService.updateOrders(id,ordersDto);
    }
}
