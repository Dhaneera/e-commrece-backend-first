package edu.icet.clothify.service;

import edu.icet.clothify.dto.OrdersDto;
import edu.icet.clothify.entity.Orders;


public interface OrdersService{

    Orders updateOrders(Long id,Orders orders);
    Boolean deleteOrders(Long id);
    boolean addOrder(OrdersDto orderDto);

}
