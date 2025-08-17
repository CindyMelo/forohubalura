package com.desafio.forohubalura.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "El nombre es obligatorio")
    private String nombre;
    @NotNull(message = "El correo electrónico es obligatorio")
    @Email(message = "Debe ser un correo electrónico valido ")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "El campo clave no puede ser nulo")
    @Size(min = 8, max = 20)
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$",
            message = "La contraseña debe tener al menos una mayúscula, una minúscula, " +
                    "un número y un carácter especial")
    private String clave;

}
