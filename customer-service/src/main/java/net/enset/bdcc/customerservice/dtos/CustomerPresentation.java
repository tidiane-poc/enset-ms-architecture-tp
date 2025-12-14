package net.enset.bdcc.customerservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPresentation {
    private Long code;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
}
