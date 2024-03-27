package edu.icet.clothify.controller;

import edu.icet.clothify.dto.CartDto;
import edu.icet.clothify.dto.CategoryDto;
import edu.icet.clothify.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
