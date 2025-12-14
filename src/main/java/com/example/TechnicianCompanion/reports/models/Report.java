package com.example.TechnicianCompanion.reports.models;

import com.example.TechnicianCompanion.authentication.models.User;
import com.example.TechnicianCompanion.cities.models.Cities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reports")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Report {

    @Id
    @Column(nullable = false, unique = true)
    private Long protocolNumber;
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    @JsonBackReference
    private Cities city;
    @Column(name = "created_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dayToday;
    @Column(nullable = false)
    private ReportTypes type;
    @Column(nullable = false)
    private String broadbandPlan;
    @Column(nullable = false)
    private String client;
    @Column(nullable = false)
    private String costumerReport;
    @Column(nullable = false)
    private String problem;
    @Column(nullable = false)
    private String solution;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="onuModel", column=@Column(name="installed_onu_model")),
            @AttributeOverride(name="onuSerialNumber", column=@Column(name="installed_onu_serial")),
            @AttributeOverride(name="wifiEquipmentModel", column=@Column(name="installed_router_model")),
            @AttributeOverride(name="wifiEquipmentSN", column=@Column(name="installed_router_serial")),
            @AttributeOverride(name = "equipmentType", column = @Column(name="installed_equipment_type"))
    })
    private Equipment installedEquipment;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="onuModel", column=@Column(name="removed_onu_model")),
            @AttributeOverride(name="onuSerialNumber", column=@Column(name="removed_onu_serial")),
            @AttributeOverride(name="wifiEquipmentModel", column=@Column(name="removed_router_model")),
            @AttributeOverride(name="wifiEquipmentSN", column=@Column(name="removed_router_serial")),
            @AttributeOverride(name = "equipmentType", column = @Column(name="removed_equipment_type"))
    })
    private Equipment removedEquipment;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="oltId", column=@Column(name="network_olt_id")),
            @AttributeOverride(name="ctoId", column=@Column(name="network_cto_id")),
            @AttributeOverride(name="network", column=@Column(name="network_name")),
            @AttributeOverride(name="ctoPort", column=@Column(name="network_cto_port"))})
    private NetworkInfo networkInfo;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "drop", column = @Column(name = "drop_quantity")),
            @AttributeOverride(name = "conector", column = @Column(name = "conector_quantity")),
            @AttributeOverride(name = "alcas", column = @Column(name = "alcas_quantity")),
            @AttributeOverride(name = "barraRoscada", column = @Column(name = "barra_roscada_quantity")),
            @AttributeOverride(name = "olhal", column = @Column(name = "olhal_quantity"))
    })
    private Materials usedMaterials;


    @ManyToMany
    @JoinTable(
            name = "report_technicians",
            joinColumns = @JoinColumn(name = "report_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonBackReference
    private Set<User> technicians = new HashSet<>();


}
