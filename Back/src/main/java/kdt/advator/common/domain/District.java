package kdt.advator.common.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    private String name;
    @ManyToOne
    @JoinColumn(name = "city_number", nullable = false)
    private City city;
}
