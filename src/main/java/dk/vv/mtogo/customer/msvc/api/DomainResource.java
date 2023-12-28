package dk.vv.mtogo.customer.msvc.api;


import dk.vv.common.data.transfer.objects.common.AddressDTO;
import dk.vv.common.data.transfer.objects.customer.CustomerDTO;
import dk.vv.mtogo.customer.msvc.facades.AddressFacade;
import dk.vv.mtogo.customer.msvc.facades.CustomerFacade;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.graphql.*;

import java.util.List;

@GraphQLApi
@ApplicationScoped
public class DomainResource {

    private final CustomerFacade customerFacade;

    private final AddressFacade addressFacade;


    @Inject
    public DomainResource(CustomerFacade customerFacade, AddressFacade addressFacade) {
        this.customerFacade = customerFacade;
        this.addressFacade = addressFacade;
    }


    @Query("allCustomers")
    @Description("Gets all customers")
    public List<CustomerDTO> getAllCustomers() {
        return customerFacade.getAllCustomers();
    }

    @Query("createCustomer")
    @Description("Create customer")
    @Mutation
    @Transactional
    public CustomerDTO createCustomer(@Name("customer") CustomerDTO customerDTO) {
        return customerFacade.createCustomer(customerDTO);
    }

    @Query("deleteCustomer")
    @Description("Delete customer")
    @Mutation
    @Transactional
    public CustomerDTO deleteCustomer(@Name("id") int id) {
        return customerFacade.deleteCustomer(id);
    }

    @Query("addAddress")
    @Description("Add address to customer")
    @Mutation
    @Transactional
    public AddressDTO createCustomer(@Name("customerId") int id, AddressDTO addressDTO) {
        return customerFacade.createNewAddress(id,addressDTO);
    }

    @Query("getCustomerById")
    @Description("Get customer by id")
    public CustomerDTO getCustomerById(@Name("customerId") int id) {
        return customerFacade.getCustomerById(id);
    }

}
