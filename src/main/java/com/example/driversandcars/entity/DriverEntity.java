package com.example.driversandcars.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity(name = "drivers")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class DriverEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driver_gen")
//    @SequenceGenerator(name = "driver_gen", allocationSize = 1, sequenceName = "driver_gen")
    @GeneratedValue(generator = "uuid_gen")
    @GenericGenerator(name = "uuid_gen", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;

    private String category;

    @Column(unique = true)
    private Long serial_license;

    @Column(unique = true)
    private Long number_license;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//, mappedBy = "owner")
    @JoinColumn(name = "owner_id")
    private List<CarEntity> cars;

    LocalDate license;

}
