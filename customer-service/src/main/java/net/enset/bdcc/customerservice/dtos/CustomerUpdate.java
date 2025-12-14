package net.enset.bdcc.customerservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdate {
    @NotBlank(message = "Le nom du client est obligatoire")
    private String name;
    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    private String tel;
    @NotBlank(message = "L'adresse email est obligatoire")
    @Email
    private String email;
    private String address;
}
