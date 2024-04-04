package edu.icet.clothify.service;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.dto.BillingInfoDto;
import edu.icet.clothify.entity.BillingInfo;
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

        @Test
        @Order(1)
        @DisplayName("Save Category Service")
        public void BillingInfoService_SaveBillingInfo_ReturnObject(){
            //Given
            Long id = 1L;
            String phone="0777007987";
            String Address="mount";

            BillingInfo billingInfo = BillingInfo.builder().id(id).phone(phone).address(Address).orders(Orders.builder().id(null).customer(Customer.builder().id(null).build()).build()).build();
            BillingInfoDto billingInfoDto = BillingInfoDto.builder().id(id).phone(phone).address(Address).customer(null).build();


            //When
            when(billingInfoRepository.save(any())).thenReturn(billingInfo);
            when(objectMapper.convertValue(any(), (JavaType) any())).thenReturn(billingInfo);
            boolean isSaved=billingInfoService.createBillingInfo(billingInfoDto);
            //Then
            Assertions.assertTrue(isSaved);
        }

    }
}
