package com.devteam.marketing.domain.sample.entity;

import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity @Setter
public class Sample {

    @Id
    @Column(name = "sample_id")
    private Long id;

}
