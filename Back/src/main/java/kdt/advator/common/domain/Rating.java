package kdt.advator.common.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "rating")
@Getter
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    private String name;
}
