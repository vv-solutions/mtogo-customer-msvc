package dk.vv.mtogo.customer.msvc;

import dk.vv.mtogo.customer.msvc.facades.AddressFacade;
import dk.vv.mtogo.customer.msvc.facades.CustomerFacade;
import dk.vv.mtogo.customer.msvc.repositories.AddressRepository;
import dk.vv.mtogo.customer.msvc.repositories.CustomerRepository;
import jakarta.enterprise.inject.Produces;

public class Producers {

    @Produces
    CustomerFacade getCustomerFacade(CustomerRepository customerRepository){
        return new CustomerFacade(customerRepository);
    }

    @Produces
    AddressRepository getAddressRepository(){
        return new AddressRepository();
    }
    @Produces
    CustomerRepository getCustomerRepository(){
        return new CustomerRepository();
    }

    @Produces
    AddressFacade getAddressFacade(AddressRepository addressRepository){
        return new AddressFacade(addressRepository);
    }


}
