package com.nminh.bhyt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "household")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class HouseHold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "household")
    private Set<Inform> informs = new HashSet<>();
}
