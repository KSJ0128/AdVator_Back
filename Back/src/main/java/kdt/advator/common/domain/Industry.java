package kdt.advator.common.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "industry")
public class Industry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    private String name;
}
