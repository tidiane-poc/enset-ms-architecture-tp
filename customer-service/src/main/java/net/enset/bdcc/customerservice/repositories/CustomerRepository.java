package net.enset.bdcc.customerservice.repositories;

import net.enset.bdcc.customerservice.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("""
            SELECT c FROM customers c
            WHERE c.name LIKE %:query%
              OR  c.tel  LIKE %:query%
              OR  c.email  LIKE %:query%
              OR  c.address  LIKE %:query%
            """)
    Page<Customer> findByQuery(String query, Pageable pageable);

    boolean existsByTel(String tel);
    boolean existsByEmail(String email);
}
