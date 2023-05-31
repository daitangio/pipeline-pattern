package com.gioorgi.pattern.pipeline.entity;

import java.util.*;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class SurfGuy {
    
    @Id
    @Column(nullable = false, length = 19)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="s_surf")
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    /* Only one guy can own a surf-table at a given time */
    @JoinTable(name="t_guy_owned_table")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurfTable> ownedTables= new ArrayList<>();
}
