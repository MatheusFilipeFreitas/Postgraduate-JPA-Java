package com.mathffreitas.jpaunipds.model.entity.conference;

import com.mathffreitas.jpaunipds.model.entity.common.BaseEntity;
import com.mathffreitas.jpaunipds.model.entity.session.Session;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_conference")
@AttributeOverride(name = "id", column = @Column(name = "id_conference"))
@Getter
@Setter
@NoArgsConstructor
public class Conference extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "conference")
    private List<Session> sessions = new ArrayList<>();
}
