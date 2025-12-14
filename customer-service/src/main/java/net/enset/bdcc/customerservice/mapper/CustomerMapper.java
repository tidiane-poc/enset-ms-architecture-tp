package net.enset.bdcc.customerservice.mapper;

import net.enset.bdcc.customerservice.dtos.CustomerCreate;
import net.enset.bdcc.customerservice.dtos.CustomerPresentation;
import net.enset.bdcc.customerservice.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerCreate customerCreate){
        return Customer.builder()
                .name(customerCreate.getName())
                .tel(customerCreate.getTel())
                .email(customerCreate.getEmail())
                .address(customerCreate.getAddress())
                .build();
    }
    public CustomerPresentation toPresentation(Customer customer){
        return CustomerPresentation.builder()
                .code(customer.getId())
                .name(customer.getName())
                .phoneNumber(customer.getTel())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .build();
    }
}
