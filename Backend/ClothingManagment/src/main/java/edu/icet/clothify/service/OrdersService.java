package edu.icet.clothify.service;

import edu.icet.clothify.dto.OrdersDto;
import edu.icet.clothify.entity.Orders;

import java.util.List;


public interface OrdersService{

    Orders updateOrders(Long id,Orders orders);
    Boolean deleteOrders(Long id);
    Boolean addOrder(OrdersDto orderDto);
    List<OrdersDto> getAllOrdersByCustomer(String customerName);

}
