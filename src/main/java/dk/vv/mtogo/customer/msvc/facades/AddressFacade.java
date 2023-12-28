package dk.vv.mtogo.customer.msvc.facades;

import dk.vv.common.data.transfer.objects.common.AddressDTO;
import dk.vv.mtogo.customer.msvc.pojos.Address;
import dk.vv.mtogo.customer.msvc.repositories.AddressRepository;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

public class AddressFacade {


    private final AddressRepository repository;

    @Inject
    public AddressFacade(AddressRepository repository) {
        this.repository = repository;
    }


    public AddressDTO getAddressById(int id){
        return repository.findById((long) id).toDto();
    }

    public List<AddressDTO> getAllAdress(){
        return repository.findAll().stream().map(Address::toDto).collect(Collectors.toList());
    }

}
