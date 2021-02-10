package com.springboot.foodorderservice.entity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "USER_PROFILE")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "USER_NAME",nullable = false, length = 100)
    private String userName;

    @Column(name = "MOBILE_NUMBER",nullable = false, length = 15)
    private String mobileNumber;

}