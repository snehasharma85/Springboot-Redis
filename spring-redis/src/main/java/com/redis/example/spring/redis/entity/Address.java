package com.redis.example.spring.redis.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER_ADDRESS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "ADDRESS_TYPE")
    public String addressType;

    public String country;

    public String city;

    public String lane;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(referencedColumnName = "ID", nullable = false, insertable = true, updatable = false)
    @JsonBackReference
    public UserEntity user;
}
