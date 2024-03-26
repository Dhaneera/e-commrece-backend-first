package edu.icet.clothify.service;

import edu.icet.clothify.entity.Orders;

public interface OrdersService{
    Orders addOrders(Orders orders);
    Orders updateOrders(Long id,Orders orders);
    void deleteOrders(Long id);
}
