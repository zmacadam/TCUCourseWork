package ewallet.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Wallet implements Serializable {
    @Id
    private String id;
    private double balance;
    private Date creationTime;
    private Date lastModified;
    private boolean frozen;

    public Wallet () {}

    public Wallet(String id, Date creationTime) {
        this.id = id;
        this.creationTime = creationTime;
    }
    public void deposit(double value) {
        this.balance += value;
    }
    public void withdraw(double value) {
        this.balance -= value;
    }
    public void Freeze() {
        this.frozen = true;
    }
    public void Unfreeze() {
        this.frozen = false;
    }
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
    public double getBalance() {
        return balance;
    }
    public boolean isFrozen() {
        return frozen;
    }
}
