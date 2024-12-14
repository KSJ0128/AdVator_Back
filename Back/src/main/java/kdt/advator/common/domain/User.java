package kdt.advator.common.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    @Column(name = "store_name")
    private String storeName;
    private String description;
    private String author;
    private String phone;
    private String email;
    @Column(name = "personal_agree")
    private Boolean personalAgree;
    @Column(name = "marketing_agree")
    private Boolean marketingAgree;
    @ManyToOne
    @JoinColumn(name = "business_number", nullable = false)
    private Business business;
    @ManyToOne
    @JoinColumn(name = "industry_number", nullable = false)
    private Industry industry;
}
