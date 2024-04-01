package edu.icet.clothify.service;

import edu.icet.clothify.dto.CartDto;
import edu.icet.clothify.entity.Cart;

import java.util.List;


public interface CartService {

    public Boolean addCart(CartDto cartDto);
    public Cart upadateCart(Long id, CartDto cart);
    List<CartDto> getAllCartDetails();

    boolean updateStatus(long id);

    CartDto getCartById(long id);



}
