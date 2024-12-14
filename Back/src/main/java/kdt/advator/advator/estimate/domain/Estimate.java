package kdt.advator.advator.estimate.domain;

import jakarta.persistence.*;
import kdt.advator.common.domain.*;

@Entity
@Table(name = "estimate")
public class Estimate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    private Long request;
    @ManyToOne
    @JoinColumn(name = "apart_number", nullable = false)
    private Apart apart;
}
