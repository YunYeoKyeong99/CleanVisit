package com.dabateam.cleanvisit.domain.req;

import com.dabateam.cleanvisit.domain.entity.HygieneManagement;
import com.dabateam.cleanvisit.domain.entity.Place;
import com.dabateam.cleanvisit.domain.entity.Quarantine;
import lombok.Getter;

@Getter
public class ReqPlaceCreate {

    private Place place;

    private HygieneManagement hygieneManagement;

    private Quarantine quarantine;
}
