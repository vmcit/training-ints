package com.vmc.manageemployee.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="mst_Tanto_Info")
public class TantoInfo {

    @Id
    @Column(name = "TANTOCD")
    @Size(min = 6, max = 6)
    @NotEmpty
    private String tantocd;

    @Column(name = "tantnm")
    private String TANTNM;

    @Column(name = "TANTOKANA")
    private String tantokana;

    @Column(name = "BUKACD")
    @Size(min = 6, max = 6)
    private String bukacd;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "MOBILEEMAIL")
    private String mobileemail;

    @Column(name = "EGYKBNFLGD1EGYBU")
    @Size(min = 1, max = 1)
    private String egykbnflgD1egybu;

    @Column(name = "EGYKBNFLGD2EGYBU")
    @Size(min = 1, max = 1)
    private String egykbnflgD2egybu;

    @Column(name = "EGYKBNFLGD1EGY2BU")
    @Size(min = 1, max = 1)
    private String egykbnflgD1egy2bu;

    @Column(name = "ZAISHOKUKBN")
    @Size(min = 1, max = 1)
    private String zaishokukbn;

    @Column(name = "ROLECD")
    @Size(min = 1, max = 1)
    private String rolecd;

    @Column(name = "JOBCD")
    @Size(min = 4, max = 4)
    private String jobcd;

    @Column(name = "SPECIALTANTOCDFLG")
    @Size(min = 4, max = 4)
    private String specialtantocdflg;

    @Column(name = "DELFLG")
    @Size(min = 1, max = 1)
    private String delflg;

    @Column(name = "CREUSERID")
    @Size(min = 6, max = 6)
    private String creuserid;

    @Column(name = "CREDATETIME")
    private String credatetime;

    @Column(name = "CREPROGID")
    @Size(min = 13, max = 13)
    private String creprogid;

    @Column(name = "UPDUSERID")
    @Size(min = 6, max = 6)
    private String upduserid;

    @Column(name = "upddatetime")
    private String upddatetime;

    @Column(name = "UPDPROGID")
    @Size(min = 13, max = 13)
    private String updProgId;

    @Column(name = "DELUSERID")
    @Size(min = 6, max = 6)
    private String deluserid;

    @Column(name = "DELDATETIME")
    private String deldatetime;

    @Column(name = "DELPROGID")
    @Size(min = 13, max = 13)
    private String delprogid;

    @Column(name = "VERSION")
    @Min(1)
    private Integer version;

}
