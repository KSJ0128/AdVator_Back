package kdt.advator.common.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "company")
@Getter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    private String name;
}
