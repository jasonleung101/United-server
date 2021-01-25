package com.jasonleung0815.passwordManager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "spending")
@Table
@Data
public class Spending implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private BigDecimal amount;

    private String type;

    private String details;

    @UpdateTimestamp
    @Column(name = "updated_date")
    @JsonFormat(timezone = "Hongkong")
    private Date updatedDate;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    @JsonFormat(timezone = "Hongkong")
    private Date createdDate;
}
