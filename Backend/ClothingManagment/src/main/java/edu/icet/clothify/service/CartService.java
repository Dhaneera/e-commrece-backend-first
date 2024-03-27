package edu.icet.clothify.service;

import edu.icet.clothify.dto.CartDto;
import edu.icet.clothify.dto.StockDto;
import edu.icet.clothify.entity.Cart;
import edu.icet.clothify.entity.Stock;
import org.springframework.stereotype.Service;


public interface CartService {
//    public Cart addCart(Cart cart);
    public Boolean addCart(CartDto cartDto);
    public Cart upadateCart(Long id, Cart cart);
    void deleteCart(Long id);


}
