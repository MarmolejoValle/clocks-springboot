package utez.edu.mx.florever.modules.auth.dto;


import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class LoginRequestDTO {

    @NotNull
    private String email;
    @NotNull
    private String password;

    // Constructor vacío
    public LoginRequestDTO() {
    }

    // Constructor con todos los campos
    public LoginRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters y Setters


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
