package ewallet.controller;

import ewallet.domain.Wallet;
import ewallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;


//Controller for API Calls
@RestController
public class APIController {

    @Autowired
    private WalletService walletService;

    //POST to create a new Wallet
    //Returns the users walletID
    @PostMapping("/api/create")
    public String save(HttpSession session){
        String walletID = UUID.randomUUID().toString();
        Wallet newWallet = new Wallet(walletID, new Date());
        walletService.save(newWallet);
        session.setAttribute("frozen", false);
        return "WalletID: "+ walletID;
    }

    //PUT request to deposit to a wallet
    //Returns the new balance of the wallet
    @PutMapping("/api/deposit/{walletID}")
    public String deposit(HttpSession session, @PathVariable("walletID") String id, @RequestParam("value") double value) {
        String calcBalance;
        if (value > 0) {
            if (!(boolean) session.getAttribute("frozen")) {
                walletService.lastModified(id, new Date());
                calcBalance = walletService.deposit(id, value) + "";
            } else {
                return "Wallet is frozen, no transactions allowed!";
            }
        } else {
            return "Illegal transaction!";
        }
        return "New Balance: " + calcBalance;
    }

    //PUT request to withdraw from a wallet
    //Returns the new balance of the wallet
    @PutMapping("/api/withdraw/{walletID}")
    public String withdraw(HttpSession session, @PathVariable("walletID") String id, @RequestParam("value") double value) {
        String calcBalance;
        if (value > 0) {
            if ((Double.parseDouble(walletService.getBalance(id)) - value) >= 0) {
                if (!(boolean) session.getAttribute("frozen")) {
                    walletService.lastModified(id, new Date());
                    calcBalance = walletService.withdraw(id, value) + "";
                } else {
                    return "Wallet is frozen, no transactions allowed!";
                }
            } else {
                return "Illegal transaction!";
            }
        } else {
            return "Illegal transaction!";
        }
        return "New Balance: " + calcBalance;
    }

    //PUT request to transfer funds from one wallet to another
    //Returns the new balance of the wallet
    @PutMapping("/api/transfer/{walletID}")
    public String transfer(HttpSession session, @PathVariable("walletID") String id1, @RequestParam("id2") String id2, @RequestParam("value") double value) {
        String calcBalance;
        if (value > 0) {
            if ((Double.parseDouble(walletService.getBalance(id1)) - value) > 0) {
                if (!(boolean) session.getAttribute("frozen")) {
                    walletService.lastModified(id1, new Date());
                    walletService.lastModified(id2, new Date());
                    calcBalance = walletService.transfer(id1, id2, value) + "";
                } else {
                    return "Wallet is frozen, no transactions allowed!";
                }
            } else {
                return "Illegal transaction!";
            }
        } else {
            return "Illegal transaction!";
        }
        return "New Balance: " + calcBalance;
    }

    //POST request to freeze the wallet
    @PostMapping("/api/freeze/{walletID}")
    public String freeze(@PathVariable("walletID") String id, HttpSession session) {
        boolean frozen = walletService.freeze(id);
        session.setAttribute("frozen",frozen);
        return "Wallet is Frozen!";
    }

    //POST request to unfreeze the wallet
    @PostMapping("/api/unfreeze/{walletID}")
    public String unfreeze(@PathVariable("walletID") String id, HttpSession session) {
        boolean frozen = walletService.unfreeze(id);
        session.setAttribute("frozen",frozen);
        return "Wallet is Unfrozen!";
    }

    //GET request to get the current balance of the wallet
    @GetMapping("/api/balance/{walletID}")
    public String getBalance(@PathVariable("walletID") String id) {
        return "Balance: " + walletService.getBalance(id);
    }
}
