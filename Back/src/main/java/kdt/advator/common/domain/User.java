package kdt.advator.common.domain;

import jakarta.persistence.*;
import kdt.advator.estimate.dto.AdvertiseReq;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
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

    @Builder
    public User(AdvertiseReq advertiseReq, Business business, Industry industry) {
        this.storeName = advertiseReq.getStore();
        this.description = advertiseReq.getDescription();
        this.author = advertiseReq.getAuthor();
        this.phone = advertiseReq.getPhone();
        this.email = advertiseReq.getEmail();
        this.personalAgree = advertiseReq.getPersonalAgree();
        this.marketingAgree = advertiseReq.getMarketingAgree();
        this.business = business;
        this.industry = industry;
    }
}
