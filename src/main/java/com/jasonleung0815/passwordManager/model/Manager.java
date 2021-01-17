package com.jasonleung0815.passwordManager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "manager")
@Table
@Data
public class Manager implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String password;

    private String domain;

    @UpdateTimestamp
    @Column(name = "updated_date")
    @JsonFormat(timezone = "Hongkong")
    private Date updatedDate;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    @JsonFormat(timezone = "Hongkong")
    private Date createdDate;
}
