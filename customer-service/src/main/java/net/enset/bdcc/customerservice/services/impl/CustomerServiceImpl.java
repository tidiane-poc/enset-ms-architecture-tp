package net.enset.bdcc.customerservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.enset.bdcc.customerservice.dtos.CustomPagination;
import net.enset.bdcc.customerservice.dtos.CustomerCreate;
import net.enset.bdcc.customerservice.dtos.CustomerPresentation;
import net.enset.bdcc.customerservice.dtos.CustomerUpdate;
import net.enset.bdcc.customerservice.exceptions.CustomerAlreadyExistException;
import net.enset.bdcc.customerservice.exceptions.CustomerNotFoundException;
import net.enset.bdcc.customerservice.mapper.CustomerMapper;
import net.enset.bdcc.customerservice.repositories.CustomerRepository;
import net.enset.bdcc.customerservice.services.CustomerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    public static final String CUSTOMER_NOT_FOUND_MESSAGE = "Customer Not Found";
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerPresentation save(CustomerCreate customer) {
        log.info("Customer Create : {}",customer);
        var existsByTel = customerRepository.existsByTel(customer.getTel());
        if (existsByTel) throw new CustomerAlreadyExistException("Customer with tel : " + customer.getTel() + " already exists");
        var existsByEmail = customerRepository.existsByEmail(customer.getEmail());
        if (existsByEmail) throw new CustomerAlreadyExistException("Customer with email : " + customer.getEmail() + " already exists");

        var customerEntity = customerMapper.toCustomer(customer);
        var saved = customerRepository.save(customerEntity);
        log.info("Customer Saved successfully : {}",saved);
        return customerMapper.toPresentation(saved);
    }

    @Override
    public CustomerPresentation getById(Long id) {
        log.info("Retrieving Customer with id : {}", id);
        return customerRepository.findById(id)
                .map(customerMapper::toPresentation)
                .orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND_MESSAGE));
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Customer with id : {}", id);
        var existsById = customerRepository.existsById(id);
        if(!existsById) throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND_MESSAGE);
        customerRepository.deleteById(id);
        log.info("Customer Deleted successfully : {}",id);
    }

    @Override
    public CustomerPresentation update(Long id, CustomerUpdate customer) {
        log.info("Updating Customer with id : {}", id);
        var entity = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND_MESSAGE));
        entity.setName(customer.getName());
        entity.setTel(customer.getTel());
        entity.setEmail(customer.getEmail());
        entity.setAddress(customer.getAddress());
        var saved = customerRepository.save(entity);
        log.info("Customer Updated successfully : {}",saved);
        return customerMapper.toPresentation(saved);
    }

    @Override
    public CustomPagination<CustomerPresentation> getAll(int page, int size) {
        log.info("Retrieving all Customers fom page : {} and size :{}", page, size);
        var pageRequest = buildPageRequest(page,size);
        var pagination = customerRepository.findAll(pageRequest);
        return CustomPagination.<CustomerPresentation>builder()
                .page(page)
                .size(size)
                .totalRecords(pagination.getTotalElements())
                .totalPages(pagination.getTotalPages())
                .data(pagination.getContent().stream().map(customerMapper::toPresentation).toList())
                .build();
    }

    @Override
    public CustomPagination<CustomerPresentation> search(String query, int page, int size) {
        log.info("Searching Customer with query : {} fom page : {} and size :{}", query, page, size);
        var pageRequest = buildPageRequest(page,size);
        var pagination = customerRepository.findByQuery(query,pageRequest);
        return CustomPagination.<CustomerPresentation>builder()
                .page(page)
                .size(size)
                .totalRecords(pagination.getTotalElements())
                .totalPages(pagination.getTotalPages())
                .data(pagination.getContent().stream().map(customerMapper::toPresentation).toList())
                .build();
    }
    private PageRequest buildPageRequest(int page, int size){
        if(page<=1) page=0;
        else page--;

        if(size<1) size=10;
        if (size > 100) size = 100;
        return PageRequest.of(page,size);
    }
}
