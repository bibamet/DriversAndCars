package com.example.driversandcars.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
    private Long serialLicense;

    @Column(unique = true)
    private Long numberLicense;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//, mappedBy = "owner")
    @JoinColumn(name = "owner_id")
    private List<CarEntity> cars;

    private LocalDate license;

}
