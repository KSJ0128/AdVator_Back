package kdt.advator.estimate.domain;

import jakarta.persistence.*;
import kdt.advator.common.domain.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "estimate")
@Getter
@NoArgsConstructor
@Setter
public class Estimate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    private Long request;
    @ManyToOne
    @JoinColumn(name = "apart_number", nullable = false)
    private Apart apart;

    @Builder
    public Estimate(Apart apart) {
        this.apart = apart;
        this.request = 1L;
    }
}