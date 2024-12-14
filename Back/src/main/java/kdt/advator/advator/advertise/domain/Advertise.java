package kdt.advator.advator.advertise.domain;

import jakarta.persistence.*;
import kdt.advator.common.domain.*;

@Entity
@Table(name = "advertise")
public class Advertise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    private Boolean check;
    private String video;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_number")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apart_number")
    private Apart apart;
}
