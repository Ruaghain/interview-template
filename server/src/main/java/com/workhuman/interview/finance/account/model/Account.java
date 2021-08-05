package com.workhuman.interview.finance.account.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.workhuman.interview.common.entity.BaseEntity;
import com.workhuman.interview.finance.user.model.User;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "account_id")),
        @AttributeOverride(name = "createdDate", column = @Column(name = "account_created_date")),
        @AttributeOverride(name = "updatedDate", column = @Column(name = "account_updated_date"))
})
public class Account extends BaseEntity implements Serializable {
    private String accountNumber;
    private String accountName;
    private Double accountBalance;
    private User user;

    @NaturalId
    @Column(name = "account_number", nullable = false)
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Column(name = "account_name", nullable = false)
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Column(name = "account_balance", nullable = false)
    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
