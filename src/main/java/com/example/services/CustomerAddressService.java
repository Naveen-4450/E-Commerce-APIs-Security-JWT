package com.example.services;

import com.example.exceptionhandling.CustomerNotFoundException;
import com.example.exceptionhandling.ResourceNotFoundException;
import com.example.models.dbModels.CustomerAddresses;
import com.example.models.dbModels.Customers;
import com.example.models.dtoModels.CustomerAddressDto;
import com.example.repositories.CustomerAddressRepository;
import com.example.repositories.CustomersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerAddressService
{
    @Autowired
    private CustomerAddressRepository custAddRepo;

    @Autowired
    private CustomersRepository custRepo;


    public ResponseEntity<CustomerAddresses> addingAddress(int custId, CustomerAddressDto cADto)
    {
        Optional<Customers> customer = custRepo.findById(custId);
        if (customer.isPresent()) {
            CustomerAddresses custAdd = new CustomerAddresses();
            custAdd.setAddLine(cADto.getAddLine());
            custAdd.setCity(cADto.getCity());
            custAdd.setPincode(cADto.getPincode());
            custAdd.setState(cADto.getState());
            custAdd.setCountry(cADto.getCountry());
            custAdd.setCustomerId(customer.get());
            CustomerAddresses savedAddress = custAddRepo.save(custAdd);

            log.info("Address added successfully for Customer ID:{}",custId);
            return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
        }else {
            log.error("Customer not found with ID:{}",custId);
            throw new CustomerNotFoundException("Customer Not Found with Your Id---> "+custId);
        }
    }

    public ResponseEntity<CustomerAddressDto> getOneAddress(int addId) {
         CustomerAddresses address = custAddRepo.findByAddId(addId);
         if(address != null){
            CustomerAddressDto addressDto = convertToDto(address);
            log.info("Address found with ID:{}",addId);
             return new ResponseEntity<>(addressDto,HttpStatus.FOUND);
         }else{
             log.error("Address not found with ID: {}", addId);
            throw new ResourceNotFoundException("Address Not Found with your id---> "+addId);
         }
    }


    public ResponseEntity<List<CustomerAddressDto>> getAllAddresses(int custId)
    {
        boolean customerExists = custRepo.existsById(custId);
        if(customerExists) {
            List<CustomerAddresses> addresses = custAddRepo.findByCustomerIdCustId(custId);
            List<CustomerAddressDto> addressDtos = addresses.stream()
                    .map(this::convertToDto).collect(Collectors.toList());

            log.info("Retrieved '{}' addresses for customer ID:{}",addressDtos.size(),custId);
            return new ResponseEntity<>(addressDtos, HttpStatus.OK);
        }else{
            log.error("Customer not found with ID : {}",custId);
            throw new CustomerNotFoundException("Customer Not Found with Your Id---> "+custId);
        }
    }



    public ResponseEntity<String> updateAddress(int addId, CustomerAddressDto cADto)
    {
        CustomerAddresses address = custAddRepo.findByAddId(addId);
        if(address != null){

            address.setAddLine(cADto.getAddLine());
            address.setCity(cADto.getCity());
            address.setPincode(cADto.getPincode());
            address.setState(cADto.getState());
            address.setCountry(cADto.getCountry());

            custAddRepo.save(address);
            log.info("Address with ID: {} updated successfully",addId);
            return new ResponseEntity<>("Address Updated Successfully", HttpStatus.OK);
        }else{
            log.error("address not found with ID: {}",addId);
            throw new ResourceNotFoundException("Address Not Found with your id---> "+addId);
        }
    }


    public ResponseEntity<String> deleteAddress(int addId)
    {
        CustomerAddresses address = custAddRepo.findByAddId(addId);
        if(address != null){

            custAddRepo.delete(address);
            log.info("Address with ID: {} deleted successfully", addId);
            return new ResponseEntity<>("Your Address is Deleted Successfully",HttpStatus.OK);
        }else{
            log.warn("Attempt to delete address with ID: {} failed - address not found", addId);
            return new ResponseEntity<>("Address Not Found with your id---> "+addId,HttpStatus.NOT_FOUND);
        }
    }


    public CustomerAddressDto convertToDto(CustomerAddresses add)
    {
        CustomerAddressDto dto = new CustomerAddressDto();
        dto.setAddId(add.getAddId());
        dto.setAddLine(add.getAddLine());
        dto.setCity(add.getCity());
        dto.setPincode(add.getPincode());
        dto.setState(add.getState());
        dto.setCountry(add.getCountry());
        dto.setCustomerId(add.getCustomerId().getCustId());

        log.debug("Converted CustomerAddress entity to DTO for address ID: {}",add.getAddId());
        return dto;
    }



}
