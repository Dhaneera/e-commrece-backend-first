package edu.icet.clothify.service;

import edu.icet.clothify.dto.OrdersDto;
import edu.icet.clothify.entity.Orders;

import java.util.List;


public interface OrdersService{

    Orders updateOrders(Long id,OrdersDto ordersDto);
    Boolean deleteOrders(Long id);
    Boolean addOrder(OrdersDto orderDto);
    List<OrdersDto> getAllOrdersByCustomer(String customerName);

}
