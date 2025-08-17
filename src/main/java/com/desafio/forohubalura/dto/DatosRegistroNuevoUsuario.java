package com.desafio.forohubalura.dto;

import jakarta.validation.constraints.*;

public record DatosRegistroNuevoUsuario(
        @NotNull(message = "El nombre es obligatorio")
        String nombre,

        @NotNull(message = "El correo electrónico es obligatorio")
        @Email(message = "Debe ser un correo electrónico válido")
        String email,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$",
                message = "La contraseña debe tener al menos una mayúscula, una minúscula, " +
                        "un número y un carácter especial")
        String clave) {
}
