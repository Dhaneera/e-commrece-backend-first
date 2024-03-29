package edu.icet.clothify.dto;

import edu.icet.clothify.entity.Orders;
import edu.icet.clothify.util.enums.PaymentStatus;
import edu.icet.clothify.util.enums.PaymentType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    @NotNull
    @Valid
    private Long id;
    private Orders orders;
    @NotBlank(message = "Payment is null")
    private double paymentTotal;
    private PaymentStatus status;
    private PaymentType paymentType;
    private OrdersDto oder;

}
