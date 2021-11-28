package com.example.driversandcars.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Persistent;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "cars")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CarEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_seq")
//    @SequenceGenerator(name = "car_seq", allocationSize = 1)
    @GeneratedValue(generator = "uuid_gen")
    @GenericGenerator(name = "uuid_gen", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID Id;

    private String model;
    @Column(unique = true)
    private String numberOfCar;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    DriverEntity owner;

}
