package com.example.TechnicianCompanion.authentication.models;

public enum UserRole {
    ADMIN("admin"),
    SUPERVISOR("supervisor"),
    TECHNICIAN("technician");

    private String role;

    UserRole(String role){
        this.role = role;
    }

   String getRole(){
        return role;
   }
}
