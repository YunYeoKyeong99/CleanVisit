package com.dabateam.cleanvisit.service;

import com.dabateam.cleanvisit.common.UserManager;
import com.dabateam.cleanvisit.domain.entity.Place;
import com.dabateam.cleanvisit.domain.entity.User;
import com.dabateam.cleanvisit.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;

    public void register(User user) {
        try {
            userMapper.create(user);
        } catch (DuplicateKeyException e) {
           e.printStackTrace(); // FIXME
        }
    }

    public void modify(User user){
        int updateCount = userMapper.update(user);

        if(updateCount != 1) {
            throw new RuntimeException(); // TODO FIX
        }

    }

    public void delete(User user){
        userMapper.delete(user);
    }

    public User getUser(String id) {
        return userMapper.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        User user = userMapper.findById(userId);

        if(user==null){
            throw new UsernameNotFoundException("Check ID");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getId(),
                user.getPassword(),
                UserManager.USER_AUTHORITIES
        );
    }

    public void updateImage(String userId, String imageUrl) {

        int updateCount = userMapper.updateUserImageUrl(userId, imageUrl);

        if(updateCount != 1) {
            throw new RuntimeException(); // TODO FIX
        }
    }

}
