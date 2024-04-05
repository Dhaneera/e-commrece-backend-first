package edu.icet.clothify.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.config.ResourceNotFoundException;
import edu.icet.clothify.dto.CartDto;
import edu.icet.clothify.dto.CustomerDto;
import edu.icet.clothify.dto.OrdersDto;
import edu.icet.clothify.entity.BillingInfo;
import edu.icet.clothify.entity.Cart;
import edu.icet.clothify.entity.Customer;
import edu.icet.clothify.entity.Orders;
import edu.icet.clothify.repository.OrdersRepository;
import edu.icet.clothify.service.CartService;
import edu.icet.clothify.service.CustomerService;
import edu.icet.clothify.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        CustomerDto customerDto = customerService.getCustomerByName(orderDto.getCustomer().getName());
        CartDto cartDto = cartService.getCartById(orderDto.getCart().getId());

        Orders ordersToSave=mapper.convertValue(orderDto,Orders.class);
        ordersToSave.setCart(Cart.builder().id(orderDto.getCart().getId()).build());
        ordersToSave.setCustomer(Customer.builder().id(customerDto.getId()).build());
//        ordersToSave.setBillingInfo(BillingInfo.builder().id(orderDto.getBillingInfo().getId()).build());
        Orders saved = ordersRepository.save(ordersToSave);
        return saved.getId() != null;
    }

    @Override
    public List<OrdersDto> getAllOrdersByCustomer(String customerName) {
        Optional<CustomerDto> customerDtoOptional = Optional.ofNullable(customerService.getCustomerByName(customerName));

        return customerDtoOptional.map(CustomerDto::getId)
                .map(ordersRepository::findByCustomerId)
                .orElseGet(Collections::emptyList) // Return empty list if customer not found
                .stream()
                .map(this::convertOrderToOrderDto)
                .collect(Collectors.toList());

    }

    private OrdersDto convertOrderToOrderDto(Orders orders) {

        OrdersDto orderDto = new OrdersDto();
        orderDto.setId(orders.getId());
        orderDto.setAddress(orders.getAddress());
        orderDto.setPhone(orders.getPhone());
        orderDto.setTax(orders.getTax());
        orderDto.setCharge(orders.getCharge());
        orderDto.setZipCode(orders.getZipCode());
        orderDto.setTot(orders.getTot());
        orderDto.setCity(orders.getCity());

        return orderDto;
    }


}
