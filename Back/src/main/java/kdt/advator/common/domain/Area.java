package kdt.advator.common.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    private String name;
    @ManyToOne
    @JoinColumn(name = "district_number", nullable = false)
    private District district;
}
