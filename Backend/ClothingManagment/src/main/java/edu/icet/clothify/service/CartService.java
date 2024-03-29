package edu.icet.clothify.service;

import edu.icet.clothify.dto.CartDto;
import edu.icet.clothify.entity.Cart;

import java.util.List;


public interface CartService {

    public Boolean addCart(CartDto cartDto);
    public Cart upadateCart(Long id, CartDto cart);
    Boolean deleteCart(Long id);
    List<CartDto> getAllCartDetails();
}
