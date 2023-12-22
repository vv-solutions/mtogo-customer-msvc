package dk.vv.mtogo.customer.msvc.facades;

import dk.vv.common.data.transfer.objects.common.AddressDTO;
import dk.vv.common.data.transfer.objects.customer.CustomerDTO;
import dk.vv.mtogo.customer.msvc.pojos.Address;
import dk.vv.mtogo.customer.msvc.pojos.Customer;
import dk.vv.mtogo.customer.msvc.repositories.CustomerRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerFacade {


    private final CustomerRepository repository;

    @Inject
    public CustomerFacade(CustomerRepository repository) {
        this.repository = repository;
    }


    @Transactional
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {

        Customer customer = new Customer(customerDTO);

        customer.setActive(true);

        repository.persist(customer);

        return customer.toDto();
    }

    @Transactional
    public AddressDTO createNewAddress(int customerId, AddressDTO addressDTO) {
        Address address = new Address(addressDTO);

        var customer = repository.findById((long) customerId);

        customer.addAddress(address);

        repository.persist(customer);

        return address.toDto();
    }

    public CustomerDTO deleteCustomer(int customerId) {
        var customer = repository.findById((long) customerId);

        customer.setEmail("anon@anon.com");
        customer.setLastName("anon");
        customer.setFirstName("anon");
        customer.setActive(false);

        repository.persist(customer);

        return customer.toDto();
    }

    @Transactional
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {

        var customer = repository.findById((long) customerDTO.getId());

        if (customerDTO.getFirstName()!= null && !customerDTO.getFirstName().isEmpty()) {
            customer.setFirstName(customerDTO.getFirstName());
        }
        if (customerDTO.getLastName() != null && !customerDTO.getLastName().isEmpty()) {
            customer.setLastName(customerDTO.getLastName());
        }
        return customer.toDto();
    }

    public List<CustomerDTO> getAllCustomers(){
        return repository.findAll().list().stream().map(Customer::toDto).collect(Collectors.toList());
    }

}
