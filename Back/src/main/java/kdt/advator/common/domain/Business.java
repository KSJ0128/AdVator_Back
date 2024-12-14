package kdt.advator.common.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "business")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    private String name;
}
