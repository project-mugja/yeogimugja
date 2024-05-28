package com.mugja.host.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tag")
public class Tag {
    @Id
    @Column(name = "tag_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tagId;

    @Column(name = "host_id", nullable = false)
    private Integer hostId;

    @Column(name = "tag_1", nullable = false)
    private String category;
    private String convFacility;
    private String roomFacility;
}
