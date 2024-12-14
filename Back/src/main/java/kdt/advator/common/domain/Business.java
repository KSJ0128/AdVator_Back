package kdt.advator.common.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "business")
@Getter
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    private String name;
}
