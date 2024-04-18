package com.Manager.Service.service;

import com.Manager.Service.dto.AddressDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="ADDRESS-SERVICE")
public interface AddressFeignClient {
    @RequestMapping(value="/address/", method = RequestMethod.POST)
    public AddressDTO createAddressData(AddressDTO addressDTO);

    @RequestMapping(value="/address/", method = RequestMethod.PUT)
    public AddressDTO updateAddressData(AddressDTO addressDTO);

    @RequestMapping(value="/address/{addressId}", method = RequestMethod.GET)
    public AddressDTO getAddressDataById(@PathVariable("addressId") Long addressId);

    @RequestMapping(value="/address/{addressId}", method = RequestMethod.DELETE)
    public Boolean deleteAddressDataById(@PathVariable("addressId") Long addressId);

}
