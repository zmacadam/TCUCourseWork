package ewallet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ewallet.domain.Wallet;

public interface WalletDao extends JpaRepository<Wallet, String> {

}
