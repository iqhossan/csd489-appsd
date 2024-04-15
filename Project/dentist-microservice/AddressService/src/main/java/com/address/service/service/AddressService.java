package com.address.service.service;
        import com.address.service.model.Address;

        import java.util.List;

public interface AddressService {

    Address createAddress(Address address);
    Address updateAddress(Address address);
    List<Address> getAllAddress();
    Address getAddressById(Long addressId);
    boolean deleteAddress(Long addressId);
}