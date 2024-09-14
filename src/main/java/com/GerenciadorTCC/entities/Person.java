package com.GerenciadorTCC.entities;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {

    public Person() {
    }
    public Person(long id){
        this.id = id;
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message="O nome é obrigatório")
    @Column(nullable = false)
    private String name;
    @NotBlank(message="O email é obrigatório")
    @Email(message="O email deve ser válido")
    @Column(nullable = false,length=50)
    private String email;
    @NotBlank(message="A senha é obrigatória")
    @Max(value=50, message="A senha deve ter no máximo 50 caracteres")
    @Min(value=8, message="A senha deve ter no mínimo 8 caracteres")
    @Column(nullable = false,length=50)
    private String password;
    @CPF(message="O CPF é inválido")
    @NotBlank(message="O CPF é obrigatório")
    @Column(nullable = false,length=16)
    private String cpf;
    @Pattern(regexp = "[0-9]{2}.[0-9]{3}.[0-9]{3}-[0-9]{2}", message = "O RG deve estar no formato XX.XXX.XXX-XX")
    @NotBlank(message="O RG é obrigatório")
    @Column(nullable = false,length=16)
    private String rg;
    @NotBlank(message="O telefone é obrigatório")
    @Pattern(regexp = "\\([0-9]{2}\\)[0-9]{4,5}-[0-9]{4}", message = "O telefone deve estar no formato (XX)XXXXX-XXXX")
    @Column(nullable = false,length=16)
    private String phone;
    @NotBlank(message="O endereço é obrigatório")
    @Max(value=500, message="O endereço deve ter no máximo 500 caracteres")
    @Column(nullable = false,length=500)
    private String address;
    @NotNull(message="A data de nascimento é obrigatória")
    @Past(message="A data de nascimento deve ser passada")
    @Column(nullable = false)
    private Date birthdate;
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
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
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getRg() {
        return rg;
    }
    public void setRg(String rg) {
        this.rg = rg;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Date getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
