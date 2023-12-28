package dk.vv.mtogo.customer.msvc.api;


import dk.vv.common.data.transfer.objects.common.AddressDTO;
import dk.vv.mtogo.customer.msvc.facades.AddressFacade;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.*;

import java.util.List;

@GraphQLApi
@ApplicationScoped
public class AddressResource {


    private final AddressFacade addressFacade;


    @Inject
    public AddressResource( AddressFacade addressFacade) {
        this.addressFacade = addressFacade;
    }


    @Query("allAddresses")
    @Description("Get all Address")
    public List<AddressDTO> getAllCustomers() {
        return addressFacade.getAllAdress();
    }

    @Query("getAddressById")
    @Description("Get address by id")
    public AddressDTO getAddressById(int id) {
        return addressFacade.getAddressById(id);
    }

}
