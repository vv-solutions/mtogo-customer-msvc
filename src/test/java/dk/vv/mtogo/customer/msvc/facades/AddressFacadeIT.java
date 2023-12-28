package dk.vv.mtogo.customer.msvc.facades;

import dk.vv.common.data.transfer.objects.common.AddressDTO;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
class AddressFacadeIT {
    @Inject
    protected Flyway flyway;

    @Inject
    protected AddressFacade classUnderTest;

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
    void when_get_address_by_id_with_id_5_zipcode_should_be_87654(){
        //Arrange
        int addressId = 5;

        //ACT
        AddressDTO addressDTO = classUnderTest.getAddressById(addressId);

        //Assert
        Assertions.assertEquals(87654, addressDTO.getZipCode());
    }

}