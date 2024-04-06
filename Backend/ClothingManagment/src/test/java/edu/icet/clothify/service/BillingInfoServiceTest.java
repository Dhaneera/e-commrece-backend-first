package edu.icet.clothify.service;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.config.ResourceNotFoundException;
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
import edu.icet.clothify.service.impl.CartServiceImpl;
import edu.icet.clothify.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Objects;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.never;
import static org.springframework.test.web.client.ExpectedCount.times;

@DisplayName("BillingInfo Service Testing")
public class BillingInfoServiceTest {



    @Mock
    CustomerServiceImpl customerService;
    @InjectMocks
    BillingInfoServiceImpl billingInfoService;



    @Mock
    private BillingInfoRepository billingInfoRepository;

    @Mock
    ObjectMapper mapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }





    @Test
    @Order(2)
    public void testUpdateBillingInfo_Success() {
        // Given
        Long id = 1L;
        BillingInfoDto billingInfoDto = new BillingInfoDto();
        // Set properties of billingInfoDto as required for testing

        BillingInfo existingBillingInfo = new BillingInfo();
        // Set properties of existingBillingInfo to simulate an existing record

        // Mock behavior of billingInfoRepository
        when(billingInfoRepository.existsById(id)).thenReturn(true);
        when(billingInfoRepository.findById(id)).thenReturn(Optional.of(existingBillingInfo));
        when(billingInfoRepository.save(any(BillingInfo.class))).thenAnswer(invocation -> invocation.getArgument(0)); // Return the passed argument as saved entity

        // When
        BillingInfo updatedBillingInfo = billingInfoService.updateBillingInfo(id, billingInfoDto);

        // Then
        assertNotNull(updatedBillingInfo);
        assertEquals(existingBillingInfo, updatedBillingInfo); // Ensure the returned object is the same as the passed object

        // Optionally, verify that properties were copied from DTO to existing billingInfo
        verify(billingInfoRepository).existsById(id);
        verify(billingInfoRepository).findById(id);
        verify(billingInfoRepository).save(any(BillingInfo.class)); // Ensure save method was called once
        // Add assertions or verifications as needed for properties copied using BeanUtils.copyProperties()

    }

    @Test
    public void testUpdateBillingInfo_ResourceNotFoundException() {
        // Given
        Long id = 1L;
        BillingInfoDto billingInfoDto = new BillingInfoDto();
        // Set properties of billingInfoDto as required for testing

        // Mock behavior of billingInfoRepository
        when(billingInfoRepository.existsById(id)).thenReturn(false);

        // When and Then
        assertThrows(ResourceNotFoundException.class, () -> billingInfoService.updateBillingInfo(id, billingInfoDto));

    }

}
