package dk.vv.mtogo.customer.msvc.pojos;

import dk.vv.common.data.transfer.objects.common.AddressDTO;
import dk.vv.common.data.transfer.objects.customer.CustomerDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Address> addresses = new HashSet<>();

    @Column(name = "active")
    private boolean active;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_stamp")
    private LocalDateTime createStamp;

    // ======= Constructors ========
    public Customer() {
    }

    public Customer(CustomerDTO customerDTO) {
        this.firstName = customerDTO.getFirstName();
        this.lastName = customerDTO.getLastName();
        this.email = customerDTO.getEmail();
        if(!customerDTO.getAddresses().isEmpty()){
            for (AddressDTO address : customerDTO.getAddresses()) {
                addAddress(new Address(address));
            }
        }
    }

    // ======= Methods ========
    public CustomerDTO toDto(){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(this.id);
        customerDTO.setFirstName(this.firstName);
        customerDTO.setLastName(this.lastName);
        customerDTO.setEmail(this.email);

        if(!this.addresses.isEmpty()){
            for (Address address : this.addresses) {
                customerDTO.addAddress(address.toDto());
            }
        }

        return customerDTO;
    }

    // ======= Getters and Setters ========
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreateStamp() {
        return createStamp;
    }

    public void setCreateStamp(LocalDateTime createStamp) {
        this.createStamp = createStamp;
    }

    public void addAddress(Address address){
        this.addresses.add(address);

        if(address.getCustomer() != this){
            address.setCustomer(this);
        }
    }
}
