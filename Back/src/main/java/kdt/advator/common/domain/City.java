package kdt.advator.common.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    private String name;
}
