package kdt.advator.ad_company.domain;

import jakarta.persistence.*;
import kdt.advator.common.domain.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "kt_town_board")
@NoArgsConstructor
@Getter
public class KTTownBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    @ManyToOne
    @JoinColumn(name = "apart_number", nullable = false)
    private Apart apart;
    private String start;
    @ManyToOne
    @JoinColumn(name = "period_number", nullable = false)
    private Period period;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_number")
    private User user;

    @Builder
    public KTTownBoard(Apart apart, Period period, User user, String start) {
        this.apart = apart;
        this.period = period;
        this.user = user;
        this.start = start;
    }
}
