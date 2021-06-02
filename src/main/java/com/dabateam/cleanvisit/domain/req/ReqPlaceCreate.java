package com.dabateam.cleanvisit.domain.req;

import com.dabateam.cleanvisit.domain.entity.HygieneManagement;
import com.dabateam.cleanvisit.domain.entity.Place;
import com.dabateam.cleanvisit.domain.entity.Quarantine;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class ReqPlaceCreate {

    private Place place;

    private HygieneManagement hygieneManagement;

    private Quarantine quarantine;

    private MultipartFile picture;
}
