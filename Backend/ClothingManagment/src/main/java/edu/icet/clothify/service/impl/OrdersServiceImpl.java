package edu.icet.clothify.service.impl;

import edu.icet.clothify.config.ResourceNotFoundException;
import edu.icet.clothify.entity.Orders;
import edu.icet.clothify.repository.OrdersRepository;
import edu.icet.clothify.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private final OrdersRepository ordersRepository;

    public OrdersServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Orders addOrders(Orders orders) {
        return ordersRepository.save(orders);
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
}
