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
    @Column(name = "street")
    private String street;
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

    public Inform(Inform other) {
        this.id = other.getId();
        this.type = other.getType();
        this.target = other.getTarget();
        this.hostName = other.getHostName();
        this.fullname = other.getFullname();
        this.code = other.getCode();
        this.cmnd = other.getCmnd();
        this.birthday = other.getBirthday();
        this.gender = other.getGender();
        this.nation = other.getNation();
        this.ethnicity = other.getEthnicity();
        this.geocode = other.getGeocode();
        this.phoneNumber = other.getPhoneNumber();
        this.street = other.getStreet();
        this.district = other.getDistrict();
        this.city = other.getCity();
        this.dkcb = other.getDkcb();
        this.guardians = other.getGuardians();
        this.salary = other.getSalary();
        this.household = other.getHousehold();
        this.familyCode = other.getFamilyCode();
        this.phoneContact = other.getPhoneContact();
        this.addressHousehold = other.getAddressHousehold();
        this.lastPayment = other.getLastPayment();
    }

    public Inform(Integer type, Integer target, String hostName, String fullname, String code, String cmnd, Date birthday, Integer gender, String nation, String ethnicity, String geocode, String phoneNumber, String street, String district, String city, String dkcb, String guardians, BigDecimal salary, String household, String familyCode, String phoneContact, String addressHousehold, String lastPayment) {
        this.type = type;
        this.target = target;
        this.hostName = hostName;
        this.fullname = fullname;
        this.code = code;
        this.cmnd = cmnd;
        this.birthday = birthday;
        this.gender = gender;
        this.nation = nation;
        this.ethnicity = ethnicity;
        this.geocode = geocode;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.district = district;
        this.city = city;
        this.dkcb = dkcb;
        this.guardians = guardians;
        this.salary = salary;
        this.household = household;
        this.familyCode = familyCode;
        this.phoneContact = phoneContact;
        this.addressHousehold = addressHousehold;
        this.lastPayment = lastPayment;
    }

    public String formatToStringReqeust() {

        return "{\n" +
            "    \"type\":\""+ this.type +"\",\n" +
            "    \"target\":\""+ this.target +"\",\n" +
            "    \"hostName\":\""+ this.hostName +"\",\n" +
            "    \"fullname\":\"" + this.fullname + "\",\n" +
            "    \"code\":\"" + this.code + "\",\n" +
            "    \"cmnd\":\"" + this.cmnd + "\",\n" +
            "    \"birthday\":\"" + "2022-04-30T17:00:00.000Z" + "\",\n" +
            "    \"gender\":\"" + this.gender + "\",\n" +
            "    \"nation\":\"" + this.nation + "\",\n" +
            "    \"ethnicity\":\"" + this.ethnicity + "\",\n" +
            "    \"geocode\":\"" + this.geocode + "\",\n" +
            "    \"phoneNumber\":\"" + this.phoneNumber + "\",\n" +
            "    \"district\":\"" + this.district + "\",\n" +
            "    \"city\":\"" + this.city + "\",\n" +
            "    \"dkcb\":\"" + this.dkcb + "\",\n" +
            "    \"guardians\":\"" + this.guardians + "\",\n" +
            "    \"salary\":\"" + this.salary + "\",\n" +
            "    \"household\":\"" + this.household + "\",\n" +
            "    \"familyCode\":\"" + this.familyCode + "\",\n" +
            "    \"phoneContact\":\"" + this.phoneContact + "\",\n" +
            "    \"addressHousehold\":\"" + this.addressHousehold + "\",\n" +
            "    \"lastPayment\":\"" + "2022-04-30T17:00:00.000Z" + "\",\n" +
            "\n" +
            "}";
    };
}
