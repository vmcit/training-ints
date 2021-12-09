package com.vmc.manageemployee.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserSystem {
    private int user_id;
    private String full_name;
    private String username;
    private String password;
    private boolean enabled;
    private boolean account_non_expired;
    private boolean credentials_non_expired;
    private boolean account_non_locked;

    public UserSystem(int user_id, String full_name, String username, String password, boolean enabled, boolean account_non_expired, boolean credentials_non_expired, boolean account_non_locked) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.account_non_expired = account_non_expired;
        this.credentials_non_expired = credentials_non_expired;
        this.account_non_locked = account_non_locked;
    }
}
