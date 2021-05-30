package com.dabateam.cleanvisit.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class User {

    @NotBlank
    private String id;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    private String imgUrl;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;

    @NotBlank
    private String address;

//    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
//    private LocalDateTime createdAt;
}
