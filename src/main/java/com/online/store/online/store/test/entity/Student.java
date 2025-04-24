package com.online.store.online.store.test.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.OffsetDateTime;

@Entity
@Table(name = "student")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "name")
    private String name;

    @Column(name = "class")
    private String studentClass; // Renamed to avoid Java keyword conflict
}
