package com.dabateam.cleanvisit.service;

import com.dabateam.cleanvisit.domain.entity.HygieneManagement;
import com.dabateam.cleanvisit.mapper.HygieneManagementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HygieneManagementService {
    private final HygieneManagementMapper hygieneManagementMapper;

    public void createHygieneManagement(HygieneManagement hygieneManagement) {
        hygieneManagementMapper.create(hygieneManagement);
    }
}