package edu.icet.clothify.controller;

import edu.icet.clothify.dto.CartDto;
import edu.icet.clothify.dto.CustomerDto;
import edu.icet.clothify.entity.Cart;
import edu.icet.clothify.entity.Customer;
import edu.icet.clothify.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @PostMapping("/add")
    public boolean addCart(@RequestBody CartDto cartDto){
        return cartService.addCart(cartDto);
    }

    @GetMapping("/getAll")
    public List<CartDto> getAllCartDetails(){
        return cartService.getAllCartDetails();
    }

    @PutMapping("/update/{id}")
    public Cart updateCart(@PathVariable Long id, @RequestBody CartDto cartDto) {
        return cartService.upadateCart(id,cartDto);
    }
}
