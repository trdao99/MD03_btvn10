package com.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Employee {
   private Integer id;
    private String name;
    private Boolean sex;
    private Date birthday;
    private String Avatar;
    public String formatDate(){
        return new SimpleDateFormat("dd/MM/yyyy").format(birthday);
    }
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + formatDate() +
                ", sex=" + sex +
                ", avatar='" + Avatar + '\'' +
                '}';
    }

}
