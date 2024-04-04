package edu.icet.clothify.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.config.ResourceNotFoundException;
import edu.icet.clothify.dto.CustomerDto;
import edu.icet.clothify.dto.OrdersDto;
import edu.icet.clothify.entity.Orders;
import edu.icet.clothify.repository.OrdersRepository;
import edu.icet.clothify.service.CartService;
import edu.icet.clothify.service.CustomerService;
import edu.icet.clothify.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartService cartService;

    public OrdersServiceImpl(OrdersRepository ordersRepository){
        this.ordersRepository=ordersRepository;
    }


    @Override
    public Orders updateOrders(Long id, Orders orders) {
        if (!ordersRepository.existsById(id)){
            throw new ResourceNotFoundException("Order is not valid to given id : "+id);
        }
        orders.setId(id);
        return ordersRepository.save(orders);
    }

    @Override
    public Boolean deleteOrders(Long id) {
        if (ordersRepository.existsById(id)){
            ordersRepository.deleteById(id);
            return true;
        }else {
            throw new ResourceNotFoundException("order is not available for this id to delete: "+id);
        }
    }

    @Override
    public Boolean addOrder(OrdersDto orderDto) {

        Orders orders = mapper.convertValue(orderDto, Orders.class);
        Orders saved = ordersRepository.save(orders);
        return saved.getId()!=null ;
    }


}
