package edu.icet.clothify.controller;

import edu.icet.clothify.dto.OrdersDto;
import edu.icet.clothify.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrdersController {
    @Autowired
    OrdersService ordersService;

    @PostMapping("/add")
    public boolean addOrder(@RequestBody OrdersDto ordersDto){
        return ordersService.addOrder(ordersDto);
    }
}
