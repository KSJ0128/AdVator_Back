package kdt.advator.common.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "period")
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    private String period;
}
