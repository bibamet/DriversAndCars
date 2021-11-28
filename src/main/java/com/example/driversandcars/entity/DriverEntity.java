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
    @GeneratedValue(generator = "uuid_gen")
    @GenericGenerator(name = "uuid_gen", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;

    private String category;

    @Column(unique = true)
    private Long serial_license; //rename to camelCase

    @Column(unique = true)
    private Long number_license;  //rename to camelCase

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//, mappedBy = "owner")
    @JoinColumn(name = "owner_id")
    private List<CarEntity> cars;

    private LocalDate license;

}
