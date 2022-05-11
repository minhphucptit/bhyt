package com.nminh.bhyt.dto;


public class CalculatorDto {

    private Integer id;
    private String fullname;
    private String code;
    private String cmnd;
    private String salary;
    private String cost;
    private String option;
    private String lastPayment;

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getFullname() {
                return fullname;
        }

        public void setFullname(String fullname) {
                this.fullname = fullname;
        }

        public String getCode() {
                return code;
        }

        public void setCode(String code) {
                this.code = code;
        }

        public String getCmnd() {
                return cmnd;
        }

        public void setCmnd(String cmnd) {
                this.cmnd = cmnd;
        }

        public String getSalary() {
                return salary;
        }

        public void setSalary(String salary) {
                this.salary = salary;
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
}