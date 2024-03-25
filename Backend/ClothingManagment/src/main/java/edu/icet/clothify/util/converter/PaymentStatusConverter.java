package edu.icet.clothify.util.converter;

import edu.icet.clothify.util.enums.PaymentStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class PaymentStatusConverter implements AttributeConverter<PaymentStatus,Integer> {


    @Override
    public Integer convertToDatabaseColumn(PaymentStatus paymentStatus) {
        return switch (paymentStatus){
            case COMPLETED -> 1;
            case INCOMPLETE ->2;
        };
    }

    @Override
    public PaymentStatus convertToEntityAttribute(Integer integer) {
        return switch (integer){
            case  1 ->  PaymentStatus.COMPLETED;
            case  2 ->  PaymentStatus.INCOMPLETE;
            default -> null;
        };
    }
}
