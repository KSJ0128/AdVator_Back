package kdt.advator.ad_server.ad_company.domain;

import jakarta.persistence.*;
import kdt.advator.common.domain.*;

@Entity
@Table(name = "media_mid")
public class MediaMid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @ManyToOne
    @JoinColumn(name = "apart_number", nullable = false)
    private Apart apart;
    @ManyToOne
    @JoinColumn(name = "period_number", nullable = false)
    private Period period;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_number")
    private User user;
}
