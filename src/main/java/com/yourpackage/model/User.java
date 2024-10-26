package com.yourpackage.model;
import jakarta.persistence.*;
import com.task.signingentilspring.model.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class User {
    // existing fields...

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled = true;
  
}
