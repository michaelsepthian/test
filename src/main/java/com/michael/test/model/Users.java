package com.michael.test.model;

import com.fasterxml.jackson.annotation.*;
import com.michael.test.controller.dto.request.SignupRequest;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "users")
@Entity
public class Users extends AuditTrail{

    @Id
    @Column(nullable = false)
    @JsonIgnore
    private String id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "birth")
    private Date birth;
    @Column(name = "gender")
    private String gender;
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Transactions> transactions;

    public Users(SignupRequest request) {
        this.id = UUID.randomUUID().toString();
        this.firstName = request.getFirstName();;
        this.lastName = request.getLastName();
        this.email = request.getEmail();
        this.birth = request.getBirth();
        this.gender = request.getGender();
        this.address = request.getAddress();
    }
}
