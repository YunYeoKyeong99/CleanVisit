package com.dabateam.cleanvisit.mapper;

import com.dabateam.cleanvisit.domain.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DuplicateKeyException;

public interface UserMapper {

    void create(User user) throws DuplicateKeyException;

    int update(User user);

    int delete(User user);

    User findById(String id);

    int updateUserImageUrl(
            @Param("id") String userId,
            @Param("imgUrl") String imgUrl
    );
}
