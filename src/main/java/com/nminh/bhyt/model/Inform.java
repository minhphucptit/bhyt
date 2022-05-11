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
import java.util.Date;

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
    @Column(name = "hostName")
    private String hostName;
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "code")
    private String code;
    @Column(name = "cmnd")
    private String cmnd;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "gender")
    private Integer gender;
    @Column(name = "nation")
    private String nation;
    @Column(name = "ethnicity")
    private String ethnicity;
    @Column(name = "geocode")
    private String geocode;
    @Column(name = "phoneNumber")
    private String phoneNumber;
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
    @Column(name = "familyCode")
    private String familyCode;
    @Column(name = "phoneContact")
    private String phoneContact;
    @Column(name = "addressHousehold")
    private String addressHousehold;
    @Column(name = "lastPayment")
    private String lastPayment;
}
