package kdt.advator.common.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "apart")
@Getter
@NoArgsConstructor
public class Apart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    @Column(name = "apart_name")
    private String apartName;
    private String code;
    @Column(name = "lot_address")
    private String lotAddress;
    @Column(name = "road_address")
    private String roadAddress;
    @Column(name = "tv_count")
    private Long tvCount;
    @Column(name = "unit_price")
    private Long unitPrice;
    @Column(name = "total_price")
    private Long totalPrice;
    @ManyToOne
    @JoinColumn(name = "company_number", nullable = false)
    private Company company;
    @ManyToOne
    @JoinColumn(name = "rating_number", nullable = false)
    private Rating rating;
}
