package edu.icet.clothify.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.config.ResourceNotFoundException;
import edu.icet.clothify.dto.CartDto;
import edu.icet.clothify.entity.Cart;
import edu.icet.clothify.entity.Customer;
import edu.icet.clothify.entity.Orders;
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




    @Override
    public Boolean addCart(CartDto cartDto) {

        Cart cart = Cart.builder()
                .qty(cartDto.getQty())
                .completed(cartDto.isCompleted()).stockId(cartDto.getStockId()).productTot(cartDto.getProductTot()).id(cartDto.getId()).customerId(cartDto.getCustomerId()).build();
        Cart saved = cartRepository.save(cart);
        return saved.getId() != null;
    }



    @Override
    public Cart upadateCart(Long id, CartDto cartDto){
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
        List<Cart> carts = (List<Cart>) cartRepository.findAll(); // Retrieve directly as a list
        List<CartDto> cartDtos = new ArrayList<>();
        for (Cart cart : carts) {
            CartDto cartDto = new CartDto();
            cartDto.setId(cart.getId()); // Map common fields
            cartDto.setStockId(new Stock(cart.getId())); // Set stock ID directly
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
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            CartDto cartDto = new CartDto();
            cartDto.setId(cart.getId());
            cartDto.setStockId(new Stock(cart.getId()));
            return cartDto;
        }
        return null;
    }
}