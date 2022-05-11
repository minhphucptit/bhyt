package com.nminh.bhyt.dto;

import java.util.List;

public class CalculatorFamilyDto {

    private String household;
    private String familyCode;
    private String baseSalary;
    private String count;
    private String cost;
    private String option;
    private String lastPayment;
    private List<String> ids;

    public String getHousehold() {
        return household;
    }

    public void setHousehold(String household) {
        this.household = household;
    }

    public String getFamilyCode() {
        return familyCode;
    }

    public void setFamilyCode(String familyCode) {
        this.familyCode = familyCode;
    }

    public String getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(String baseSalary) {
        this.baseSalary = baseSalary;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCost() {
                return cost;
        }

        public void setCost(String cost) {
                this.cost = cost;
        }

        public String getOption() {
                return option;
        }

        public void setOption(String option) {
                this.option = option;
        }

    public String getLastPayment() {
        return lastPayment;
    }

    public void setLastPayment(String lastPayment) {
        this.lastPayment = lastPayment;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}