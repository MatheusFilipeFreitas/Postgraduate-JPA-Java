package com.mathffreitas.jpaunipds.model.entity.user;

import com.mathffreitas.jpaunipds.model.entity.common.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_user")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email")
    private String email;
}
