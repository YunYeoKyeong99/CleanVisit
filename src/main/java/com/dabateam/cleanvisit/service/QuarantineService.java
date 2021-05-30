package com.dabateam.cleanvisit.service;

import com.dabateam.cleanvisit.domain.entity.HygieneManagement;
import com.dabateam.cleanvisit.domain.entity.Quarantine;
import com.dabateam.cleanvisit.mapper.QuarantineMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuarantineService {
    private final QuarantineMapper quarantineMapper;

    public void createQuarantine(Quarantine quarantine) {
        quarantineMapper.create(quarantine);
    }

}
