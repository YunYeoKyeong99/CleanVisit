package com.dabateam.cleanvisit.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class Place {

    private Long seq;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;

    @NotBlank
    private String imgUrl;

    private Integer category;

    private String adminId;

    private Double score;

    HygieneManagement hygieneManagement;

    Quarantine quarantine;
}
