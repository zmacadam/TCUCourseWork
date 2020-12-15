package ewallet.service;

import ewallet.controller.UIController;
import ewallet.domain.Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ewallet.dao.WalletDao;
import javax.transaction.Transactional;
import java.util.Date;


@Service
@Transactional
public class WalletService {

    @Autowired
    private WalletDao walletDao;

    public void save(Wallet newWallet) {
        walletDao.save(newWallet);
    }

    public double deposit(String id, double value) {
        Wallet cur = walletDao.findById(id).get();
        cur.deposit(value);
        walletDao.save(cur);
        return cur.getBalance();
    }

    public double withdraw(String id, double value) {
        Wallet cur = walletDao.findById(id).get();
        cur.withdraw(value);
        walletDao.save(cur);
        return cur.getBalance();
    }

    public double transfer(String id1, String id2, double value) {
        Wallet buyer = walletDao.findById(id1).get();
        Wallet seller = walletDao.findById(id2).get();
        buyer.withdraw(value);
        seller.deposit(value);
        walletDao.save(buyer);
        walletDao.save(seller);
        return buyer.getBalance();
    }

    public boolean toggleFreeze(String id) {
        Wallet cur = walletDao.findById(id).get();
        if (cur.isFrozen()) {
            cur.Unfreeze();
            return false;
        } else {
            cur.Freeze();
            return true;
        }
    }

    public String getBalance(String id) {
        return walletDao.findById(id).get().getBalance() + "";
    }

    public void lastModified(String id, Date lastModified) {
        walletDao.findById(id).get().setLastModified(lastModified);
        return;
    }

    public boolean freeze(String id) {
        walletDao.findById(id).get().Freeze();
        return true;
    }
    public boolean unfreeze(String id) {
        walletDao.findById(id).get().Unfreeze();
        return false;
    }
}
