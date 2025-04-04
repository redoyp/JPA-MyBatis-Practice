package com.example.demo.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 id에 들어가는 값을 1씩 증가시켜서 넣어줌(JPA)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false) // name 설정을 밑의 선언과 같게 할거면 굳이 설정 안해도 됨
    private String name;

    // team_id
    @ManyToOne // member 쪽이 다, team 쪽이 일. 다 쪽에 ManyToOne 선언
    @JoinColumn(name = "team_id")
    private Team team;

//    @Temporal(TemporalType.TIMESTAMP)
//    private Timestamp createAt;
}
