package edu.icet.clothify.util.converter;


import edu.icet.clothify.util.enums.OrderStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;



@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus,Integer> {


    @Override
    public Integer convertToDatabaseColumn(OrderStatus orderStatus) {
        return switch (orderStatus){
            case ONGOING -> 1;
            case DELIVERED -> 2;
            case CANCELLED -> 3;
        };
    }

    @Override
    public OrderStatus convertToEntityAttribute(Integer integer) {
        return switch (integer) {
            case 1 -> OrderStatus.ONGOING;
            case 2 -> OrderStatus.DELIVERED;
            case 3 ->OrderStatus.CANCELLED;
            default -> null;
        };
    }
}
