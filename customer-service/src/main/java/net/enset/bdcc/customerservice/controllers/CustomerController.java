package net.enset.bdcc.customerservice.controllers;

import lombok.RequiredArgsConstructor;
import net.enset.bdcc.customerservice.dtos.CustomPagination;
import net.enset.bdcc.customerservice.dtos.CustomerCreate;
import net.enset.bdcc.customerservice.dtos.CustomerPresentation;
import net.enset.bdcc.customerservice.dtos.CustomerUpdate;
import net.enset.bdcc.customerservice.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerPresentation> create(CustomerCreate customer){
        return ResponseEntity.ok(customerService.save(customer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerPresentation> getById(Long id){
        return ResponseEntity.ok(customerService.getById(id));
    }

    @GetMapping
    public ResponseEntity<CustomPagination<CustomerPresentation>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(customerService.getAll(page,size));
    }

    @GetMapping("/search")
    public ResponseEntity<CustomPagination<CustomerPresentation>> search(String query, int page, int size){
        return ResponseEntity.ok(customerService.search(query,page,size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerPresentation> put(@PathVariable Long id, @RequestBody CustomerUpdate customer){
        return ResponseEntity.ok(customerService.update(id,customer));
    }
}
