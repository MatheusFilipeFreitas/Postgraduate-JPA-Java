package com.mathffreitas.jpaunipds.model.entity.user;

import com.mathffreitas.jpaunipds.model.entity.common.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "tbl_user")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@Getter
@NoArgsConstructor
public class User extends BaseEntity {

    private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Setter
    @Column(name = "user_name")
    private String name;

    @Setter
    @Column(name = "user_email")
    private String email;

    @Column(name = "password")
    private String password;

    public User(String name, String email, String rawPassword) {
        this.name = name;
        this.email = email;
        setPassword(rawPassword);
    }

    public void setPassword(String rawPassword) {
        this.password = encryptPassword(rawPassword);
    }

    private String encryptPassword(String rawPassword) {
        if (rawPassword == null) {
            return null;
        }
        if (isEncrypted(rawPassword)) {
            return rawPassword;
        }
        return PASSWORD_ENCODER.encode(rawPassword);
    }

    private boolean isEncrypted(String value) {
        return value.startsWith("$2a$") || value.startsWith("$2b$") || value.startsWith("$2y$");
    }
}
