package com.workhuman.interview.finance.user.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.workhuman.interview.common.entity.BaseEntity;
import com.workhuman.interview.finance.account.model.Account;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "user_id")),
        @AttributeOverride(name = "createdDate", column = @Column(name = "user_created_date")),
        @AttributeOverride(name = "updatedDate", column = @Column(name = "user_updated_date"))
})
public class User extends BaseEntity implements Serializable {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<Account> accounts = new HashSet<>();

    @Column(name = "user_user_name", nullable = false)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "user_first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "user_last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NaturalId
    @Column(name = "user_email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "user_password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
