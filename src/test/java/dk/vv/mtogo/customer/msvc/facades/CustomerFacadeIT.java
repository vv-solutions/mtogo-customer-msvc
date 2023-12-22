package dk.vv.mtogo.customer.msvc.facades;

import dk.vv.common.data.transfer.objects.common.AddressDTO;
import dk.vv.common.data.transfer.objects.customer.CustomerDTO;
import dk.vv.mtogo.customer.msvc.repositories.CustomerRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
class CustomerFacadeIT {
    @Inject
    protected Flyway flyway;

    @Inject
    protected CustomerFacade classUnderTest;

    @Inject
    protected CustomerRepository customerRepository;
    @BeforeEach
    public void before() {
        flyway.migrate();
    }


    @AfterEach
    public void restoreDatabase() {
        flyway.clean();
    }



    @Test
    @Transactional
    void when_create_new_customer_size_of_all_customers_should_be_6(){
        //Arrange
        CustomerDTO customerDTO = new CustomerDTO(){{
            this.setEmail("test@test.dk");
            this.setFirstName("Lars");
            this.setLastName("Larsen");
        }};

        //ACT
        classUnderTest.createCustomer(customerDTO);

        //Assert
        Assertions.assertEquals(6, customerRepository.findAll().list().size());
    }

    @Test
    @Transactional
    void when_create_new_customer_customer_should_be_active(){
        //Arrange
        CustomerDTO customerDTO = new CustomerDTO(){{
            this.setEmail("test@test.dk");
            this.setFirstName("Lars");
            this.setLastName("Larsen");
        }};

        //ACT
        classUnderTest.createCustomer(customerDTO);

        //Assert
        Assertions.assertTrue(customerRepository.findById((long) 6).isActive());
    }

    @Test
    @Transactional
    void when_create_customer_with_1_addresses_size_of_address_should_be_1(){
        //Arrange

        AddressDTO address = new AddressDTO(){{
            this.setAddress("ulrikkenborg alle 33");
            this.setZipCode(2800);
            this.setCity("Kgs. Lyngby");
        }};
        CustomerDTO customerDTO = new CustomerDTO(){{
            this.setEmail("test@test.dk");
            this.setFirstName("Lars");
            this.setLastName("Larsen");
            this.addAddress(address);
        }};

        //ACT
        classUnderTest.createCustomer(customerDTO);

        //Assert
        Assertions.assertEquals(1, customerRepository.findById((long)6).getAddresses().size());
    }

    @Test
    @Transactional
    void when_create_new_address_for_customer_5_customer_5_should_have_2_address(){
        //Arrange
        int customerId = 5;
        AddressDTO address = new AddressDTO(){{
            this.setAddress("ulrikkenborg alle 33");
            this.setZipCode(2800);
            this.setCity("Kgs. Lyngby");
        }};

        //ACT
        classUnderTest.createNewAddress(5,address);


        //Assert
        Assertions.assertEquals(2, customerRepository.findById((long)customerId).getAddresses().size());
    }

    @Test
    @Transactional
    void When_delete_customer_active_should_be_false_and_name_and_email_should_be_anonymized(){
        //Arrange
        int customerId = 3;

        //ACT
        classUnderTest.deleteCustomer(customerId);

        //Assert
        var customer = customerRepository.findById((long) customerId);

        Assertions.assertEquals("anon",customer.getFirstName());
        Assertions.assertEquals("anon",customer.getLastName());
        Assertions.assertFalse(customer.isActive());


    }

    @Test
    @Transactional
    void when_update_customer_4_with_new_first_name_first_name_should_be_set(){

        //Arrange
        CustomerDTO customerDTO = new CustomerDTO(){{
            this.setId(4);
            this.setFirstName("Hans");
        }};

        //ACT
        var updated = classUnderTest.updateCustomer(customerDTO);

        //Assert
        Assertions.assertEquals("Hans",updated.getFirstName());

    }

    @Test
    @Transactional
    void when_update_customer_4_with_new_last_name_last_name_should_be_set(){

        //Arrange
        CustomerDTO customerDTO = new CustomerDTO(){{
            this.setId(4);
            this.setLastName("Hansen");
        }};

        //ACT
        var updated = classUnderTest.updateCustomer(customerDTO);

        //Assert
        Assertions.assertEquals("Hansen",updated.getLastName());

    }

}