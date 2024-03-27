package edu.icet.clothify.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.config.ResourceNotFoundException;
import edu.icet.clothify.dto.CartDto;
import edu.icet.clothify.entity.Cart;
import edu.icet.clothify.repository.CartRepository;
import edu.icet.clothify.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    ObjectMapper mapper;

     final CartRepository cartRepository;
     @Autowired
     public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


//    @Override
//    public Cart addCart(Cart cart) {
//        return cartRepository.save(cart);
//    }

    @Override
    public Boolean addCart(CartDto cartDto) {
         Cart cart=mapper.convertValue(cartDto, Cart.class);
        Cart saved = cartRepository.save(cart);
        return saved.getId() != null;
    }
    

    @Override
    public Cart upadateCart(Long id, Cart cart) {
        if (!cartRepository.existsById(id)){
            throw new ResourceNotFoundException("Cart item not found : "+id);
        }
        cart.setId(id);
        return cartRepository.save(cart);
    }

    @Override
    public void deleteCart(Long id) {
//        cartRepository.deleteById(id);
        if (cartRepository.existsById(id)){
            cartRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("card info not available for this id to delete: "+id);
        }

    }
}
