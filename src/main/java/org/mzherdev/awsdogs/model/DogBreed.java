package org.mzherdev.awsdogs.model;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "breeds")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DogBreed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "date")
    private LocalDateTime dateCreated;
    @Column(name = "image")
    private String imageLocation;
}
