package com.dabateam.cleanvisit.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Quarantine {
    private Long seq;

    private Long placeSeq;

    @JsonFormat(pattern="yyyy-MM-dd ")
    private LocalDateTime datetime;
}
