package kdt.advator.common.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "district")
@Getter
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    private String name;
    @ManyToOne
    @JoinColumn(name = "city_number", nullable = false)
    private City city;
}
