package net.enset.bdcc.customerservice.services;

import net.enset.bdcc.customerservice.dtos.CustomPagination;
import net.enset.bdcc.customerservice.dtos.CustomerCreate;
import net.enset.bdcc.customerservice.dtos.CustomerPresentation;
import net.enset.bdcc.customerservice.dtos.CustomerUpdate;

public interface CustomerService {
    CustomerPresentation save(CustomerCreate customer);
    CustomerPresentation getById(Long id);
    void delete(Long id);
    CustomerPresentation update(Long id, CustomerUpdate customer);
    CustomPagination<CustomerPresentation> getAll(int page, int size);
    CustomPagination<CustomerPresentation> search(String query, int page, int size);
}
