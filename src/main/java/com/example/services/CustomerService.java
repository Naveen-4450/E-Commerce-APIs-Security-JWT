package com.example.services;

import com.example.models.dbModels.Credentials;
import com.example.models.dbModels.Customers;
import com.example.models.dtoModels.CustomersDto;
import com.example.repositories.CredentialRepository;
import com.example.repositories.CustomersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.plaf.metal.OceanTheme;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService
{
    @Autowired
    private CustomersRepository customersRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CredentialRepository credRepo;

    public ResponseEntity<Customers> addingCustomer(CustomersDto cDto)
    {
        log.info("Adding new Customer with UserName: {}",cDto.getUserName());
        Customers c = new Customers();
        c.setFirstName(cDto.getFirstName());
        c.setLastName(cDto.getLastName());
        c.setUserName(cDto.getUserName());
        c.setPassword(cDto.getPassword());
        c.setMobile(cDto.getMobile());
        c.setEmail(cDto.getEmail());
        c.setCreatedAt(new Date());
        c.setUpdatedAt(new Date());
        Customers savedCustomer = customersRepo.save(c);
        log.info("Customer Added Successfully with ID: {}",savedCustomer.getCustId());

            Credentials cred = new Credentials();
            cred.setUsername(cDto.getUserName());
            cred.setPassword(passwordEncoder.encode(cDto.getPassword()));
            cred.setRoles("CUSTOMER");
            credRepo.save(cred);
            log.info("Customer Credentials also saved in Credential Table");

        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }


    public ResponseEntity<Customers> getCustomer(int custId)
    {
        log.info("Fetching Customer with ID: {}",custId);

        Optional<Customers> opt = customersRepo.findById(custId);
        if(opt.isPresent()) {
            log.info("Customer found with ID:{}",custId);
            return new ResponseEntity<>(opt.get(),HttpStatus.FOUND);
        }else {
            log.warn("Customer Not Found with Id:{}",custId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<List<Customers>> getAllCustomers() {
        log.info("Fetching All Customers");
        List<Customers> list = customersRepo.findAll();
        log.info("Number of Customers found: {}",list.size());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }



    public ResponseEntity<Customers> updateCustomer(int custId, CustomersDto cDto) {
        log.info("Updating Customer Details with ID:{}",custId);
        Optional<Customers> opt = customersRepo.findById(custId);
        if (opt.isPresent()) {
            Customers c = opt.get();
            c.setFirstName(cDto.getFirstName());
            c.setLastName(cDto.getLastName());
            c.setUserName(cDto.getUserName());          //c.setPassword(cDto.getPassword());// password can't update
            c.setMobile(cDto.getMobile());
            c.setEmail(cDto.getEmail());
            c.setUpdatedAt(new Date());
            Customers updatedCustomer = customersRepo.save(c);
            log.info("Customer Updated Successfully with ID:{} ",updatedCustomer.getCustId());
            return new ResponseEntity<>(updatedCustomer,HttpStatus.OK);
        }else{
            log.warn("Customer not found with ID: {}", custId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<String> deleteCustomer(int custId) {
        log.info("Deleting Customer with ID:{}",custId);
        Optional<Customers> opt = customersRepo.findById(custId);

        if(opt.isPresent()){
            customersRepo.deleteById(custId);
            log.info("Customer deleted successfully with ID: {}", custId);
            return new ResponseEntity<>("Customer Details Deleted Successfully",HttpStatus.OK);
        }else {
            log.warn("Customer is not found with ID: {}", custId);
            return new ResponseEntity<>("Customer Not Found with your Id--->"+custId,HttpStatus.NOT_FOUND);
        }
    }


}
