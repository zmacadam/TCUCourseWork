package ewallet.datainitializer;

import ewallet.dao.WalletDao;
import ewallet.domain.Wallet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class DBDataInitializer {

    private WalletDao walletDao;

    public DBDataInitializer(WalletDao walletDao) {
        this.walletDao = walletDao;
    }

//    @Override
//    public void run(String... args) throws Exception {
//        Wallet w1 = new Wallet();
//        w1.setId(UUID.randomUUID().toString());
//        w1.setCreation(new Date());
//        walletDao.save(w1);
//    }
}
