package edu.icet.clothify.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.config.ResourceNotFoundException;
import edu.icet.clothify.dto.CartDto;
import edu.icet.clothify.entity.Cart;
import edu.icet.clothify.entity.Stock;
import edu.icet.clothify.repository.CartRepository;
import edu.icet.clothify.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
    public Cart upadateCart(Long id, CartDto cartDto) {
        if (!cartRepository.existsById(id)){
            throw new ResourceNotFoundException("Cart item not found : "+id);
        }
        Cart existingCart =cartRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer not found with this id: " + id));

        BeanUtils.copyProperties(cartDto,existingCart,"id");
        return cartRepository.save(existingCart);
    }



    @Override
    public List<CartDto> getAllCartDetails() {
        Iterable<Cart> cartIterable = cartRepository.findAll();
        Iterator<Cart> cartIterator = cartIterable.iterator();
        List<CartDto> cartDtos = new ArrayList<>();
        while(cartIterator.hasNext()){
            Cart cart = cartIterator.next();
            CartDto cartDto=mapper.convertValue(cart , CartDto.class);
            cartDtos.add(cartDto);
        }
        return cartDtos;
    }

    @Override
    public boolean updateStatus(long id) {
        Optional<Cart> byId = cartRepository.findById(id);
        if (byId.isPresent()){
         Cart cart =  byId.get();
            cartRepository.deleteById(id);
            cart.setCompleted(true);
            return true;

        }
        return false;
    }

    @Override
    public CartDto getCartById(long id) {
        Optional<Cart> byId = cartRepository.findById(id);
        if (byId.isPresent()){
            Cart cart=byId.get();
            CartDto cartDto=mapper.convertValue(cart,CartDto.class);
            cartDto.setStockId(Stock.builder().id(cart.getId()).build());
            cartDto.setStockId(cart.getStockId());
            return cartDto;

        }
        return null;
    }


}
