package com.dabateam.cleanvisit.mapper;

import com.dabateam.cleanvisit.domain.entity.User;
import org.springframework.dao.DuplicateKeyException;

public interface UserMapper {

    void create(User user) throws DuplicateKeyException;

    User findById(String id);
}
