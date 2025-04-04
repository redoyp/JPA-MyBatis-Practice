package com.example.demo.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Team {
    @Id
    @Column(name = "team_id", updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column // 컬럼명 그대로 할 거면 Column 뒤에 안 붙여도 됨
    private String name;
}
