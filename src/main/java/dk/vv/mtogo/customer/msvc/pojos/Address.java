package dk.vv.mtogo.customer.msvc.pojos;

import dk.vv.common.data.transfer.objects.common.AddressDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "zip_code")
    private int zipCode;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_stamp")
    private LocalDateTime createStamp;

    // ========= Constructors ========
    public Address() {
    }

    public Address(AddressDTO addressDTO) {
        this.id =addressDTO.getId();
        this.zipCode = addressDTO.getZipCode();
        this.city = addressDTO.getCity();
        this.address = addressDTO.getAddress();
    }

    // ========= Methods ========
    public AddressDTO toDto(){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCity(this.city);
        addressDTO.setId(this.id);
        addressDTO.setZipCode(this.zipCode);
        addressDTO.setAddress(this.address);

        return addressDTO;
    }

    // ========= Getters and Setters ========
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getCreateStamp() {
        return createStamp;
    }

    public void setCreateStamp(LocalDateTime createStamp) {
        this.createStamp = createStamp;
    }


}
