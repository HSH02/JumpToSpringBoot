package com.mysite.sbb.service;

import com.mysite.sbb.model.user.entity.SiteUser;

public interface UserService {
    SiteUser create(String username, String email, String password);

    SiteUser getUser(String username);
}
