package com.nminh.bhyt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity(name = "inform")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Inform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    private Integer type;
    @Column(name = "target")
    private Integer target;
    @Column(name = "hostname")
    private String hostname;
    @Column(name = "fullname")
    @NotNull(message = "Not empty value!")
    private String fullname;
    @Column(name = "code")
//    @NonNull
    private String code;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "gender")
    private Integer gender;
    @Column(name = "nation")
    private String nation;
    @Column(name = "ethnicity")
    private String ethnicity;
    @Column(name = "geocode")
    private String geocode;
    @Column(name = "phonenumber")
    private String phonenumber;
    @Column(name = "district")
    private String district;
    @Column(name = "city")
    private String city;
    @Column(name = "dkcb")
    private String dkcb;
    @Column(name = "guardians")
    private String guardians;
    @Column(name = "salary")
    private BigDecimal salary;
    @Column(name = "household")
    private String household;
    @Column(name = "familycode")
    private String familycode;
    @Column(name = "phonecontact")

    private String phonecontact;
    @Column(name = "addresshousehold")
    private String addresshousehold;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private HouseHold houseHold;
}
