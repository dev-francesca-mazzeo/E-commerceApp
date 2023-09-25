package com.devfm.offcialwebapp.controller;

import lombok.Data;

@Data
public class RoleToUserForm {
    // The username to which a role will be assigned
    private String username;

    // The name of the role to assign to the user
    private String role_name;
}
