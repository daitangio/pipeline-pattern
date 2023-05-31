package com.gioorgi.pattern.pipeline.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name="t_surf")
@Data
public class SurfTable {

    @Id
    @Column(nullable = false, length = 19)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="s_surf")
    private Long id;

    @Column(nullable = false)
    private String tableName;

    @Column(nullable = false)
    private int  lengthCm;

}
