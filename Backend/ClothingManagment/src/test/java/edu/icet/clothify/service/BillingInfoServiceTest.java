package edu.icet.clothify.service;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.dto.BillingInfoDto;
import edu.icet.clothify.dto.CategoryDto;
import edu.icet.clothify.dto.CustomerDto;
import edu.icet.clothify.dto.OrdersDto;
import edu.icet.clothify.entity.BillingInfo;
import edu.icet.clothify.entity.Category;
import edu.icet.clothify.entity.Customer;
import edu.icet.clothify.entity.Orders;
import edu.icet.clothify.repository.BillingInfoRepository;
import edu.icet.clothify.service.impl.BillingInfoServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("BillingInfo Service Testing")
public class BillingInfoServiceTest {

    @Mock
    BillingInfoRepository billingInfoRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    BillingInfoServiceImpl billingInfoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Nested
    @Order(1)
    @DisplayName("Save Service")
    class BillingInfoServiceSave{

        @Nested
        @Order(1)
        @DisplayName(" save Service")
        class CategoryServiceSave {
            @Test
            @Order(1)
            @DisplayName("Save Category Service")
            public void CategoryService_SaveCategory_ReturnObject(){
                //Given
                BillingInfo billingInfo = BillingInfo.builder().id(1L).phone("0777007987").address("mount-lavinia").orders(Orders.builder().id(null).build()).customer(Customer.builder().id(null).build()).build();
                BillingInfoDto billingInfoDto = BillingInfoDto.builder().id(1L).phone("077007987").address("mount-lavinia").order(OrdersDto.builder().id(null).build()).customer(CustomerDto.builder().id(null).build()).build();
                //When
                when(billingInfoRepository.save(any())).thenReturn(billingInfo);
                boolean isSaved=billingInfoService.createBillingInfo(billingInfoDto);
                when(objectMapper.convertValue(any(), (JavaType) any())).thenReturn(billingInfo);
                //Then
                Assertions.assertTrue(isSaved);
            }
        }

    }
}
