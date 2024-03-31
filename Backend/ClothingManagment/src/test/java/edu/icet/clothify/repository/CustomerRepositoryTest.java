package edu.icet.clothify.repository;

import edu.icet.clothify.config.StockServiceException;
import edu.icet.clothify.entity.Customer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@DisplayName("Customer Repository Testing")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    StockRepository stockRepository;

    @Nested
    @Order(1)
    @DisplayName("Save repository")
    class SaveCustomer {

        @Test
        @Order(1)
        @DisplayName("Save Customer Repository")
        public void CustomerRepository_SaveCustomer_ReturnStockObject() {
            //Given
            Customer customer = Customer.builder().id(null).name("shehan").phone("0765462379").mail("wickramasuriya@gmail.com").build();

            //When
            Customer save = customerRepository.save(customer);
            //Then
            Assertions.assertEquals(customer.getId(), save.getId());



        }
    }

    @Nested
    @Order(2)
    @DisplayName("Update repository")
    class UpdateCustomer {

        @Test
        @Order(1)
        @DisplayName("Update Customer Repository")
        public void CartRepository_UpdateCustomer_ReturnStockObject() {
            //Given

            Customer customer = Customer.builder().id(null).name("shehan").phone("0765462379").mail("wickramasuriya@gmail.com").build();


            //When
            Customer saved = customerRepository.save(customer);
            saved.setName("Dashan");
            saved.setMail("dashanwd@gmail.com");
            saved.setPhone("0112731626");
            //Then
            Assertions.assertEquals(saved.getName(), "Dashan");
            Assertions.assertEquals(saved.getMail(), "dashanwd@gmail.com");
            Assertions.assertEquals(saved.getMail(), "0112731626");
        }
    }

    @Nested
    @Order(3)
    @DisplayName("Delete Repository")
    class ViewCustomer {

        @Test
        public void CategoryRepository_GetCategoryByName_ReturnCategory(){
            //Given
            Customer customer = Customer.builder().id(null).name("shehan").phone("0765462379").mail("wickramasuriya@gmail.com").build();

            //When
            Customer save=customerRepository.save(customer);
            Customer customer1=customerRepository.getByName(save.getName());
            //Then
            System.out.println(customer1);

            Assertions.assertEquals(customer.getName(),customer1.getName());
        }

    }

}
