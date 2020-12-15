package ewallet.controller;

import ewallet.domain.Wallet;
import ewallet.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

//Controller for UI Calls
@Controller
public class UIController {

    @Autowired
    private WalletService walletService;

    //POST request to create a new wallet
    @PostMapping("/welcome")
    public String save(Model model, HttpSession session){
        String walletID = UUID.randomUUID().toString();
        Wallet newWallet = new Wallet(walletID, new Date());
        walletService.save(newWallet);
        model.addAttribute("page", "welcome");
        model.addAttribute("illegal", false);
        session.setAttribute("walletID", walletID);
        session.setAttribute("frozen", false);
        return "redirect:/home.html";
    }

    //GET request to deposit funds into a wallet
    @GetMapping("/deposit")
    public String deposit(Model model, HttpSession session, @RequestParam("deposit") double value) {
        String id = (String) session.getAttribute("walletID");
        if (value > 0) {
            model.addAttribute("illegal", false);
            if (!(boolean) session.getAttribute("frozen")) {
                walletService.lastModified(id, new Date());
                String calcBalance = walletService.deposit(id, value) + "";
                model.addAttribute("balance", calcBalance);
                model.addAttribute("frozen", false);
            } else {
                model.addAttribute("balance", walletService.getBalance(id));
                model.addAttribute("frozen", true);
            }
        } else {
            model.addAttribute("balance", walletService.getBalance(id));
            model.addAttribute("illegal", true);
        }
        return "home";
    }

    //GET request to withdraw funds from a wallet
    @GetMapping("/withdraw")
    public String withdraw(Model model, HttpSession session, @RequestParam("withdraw") double value) {
        String id = (String) session.getAttribute("walletID");
        if (value > 0) {
            if ((Double.parseDouble(walletService.getBalance(id)) - value) >= 0) {
                model.addAttribute("illegal", false);
                if (!(boolean) session.getAttribute("frozen")) {
                    walletService.lastModified(id, new Date());
                    String calcBalance = walletService.withdraw(id, value) + "";
                    model.addAttribute("balance", calcBalance);
                    model.addAttribute("frozen", false);
                } else {
                    model.addAttribute("balance", walletService.getBalance(id));
                    model.addAttribute("frozen", true);
                }
            } else {
                model.addAttribute("balance", walletService.getBalance(id));
                model.addAttribute("illegal", true);
            }
        } else {
            model.addAttribute("balance", walletService.getBalance(id));
            model.addAttribute("illegal", true);
        }
        return "home";
    }

    //GET request to transfer funds from one wallet to another
    @GetMapping("/transfer")
    public String transfer(Model model, HttpSession session, @RequestParam("id2") String id2, @RequestParam("transfer") double value) {
        String id1 = (String) session.getAttribute("walletID");
        if (value > 0) {
            if ((Double.parseDouble(walletService.getBalance(id1)) - value) > 0) {
                model.addAttribute("illegal", false);
                if (!(boolean) session.getAttribute("frozen")) {
                    walletService.lastModified(id1, new Date());
                    walletService.lastModified(id2, new Date());
                    String calcBalance = walletService.transfer(id1, id2, value) + "";
                    model.addAttribute("balance", calcBalance);
                    model.addAttribute("frozen", false);
                } else {
                    model.addAttribute("balance", walletService.getBalance(id1));
                    model.addAttribute("frozen", true);
                }
            } else {
                model.addAttribute("balance", walletService.getBalance(id1));
                model.addAttribute("illegal", true);
            }
        } else {
            model.addAttribute("balance", walletService.getBalance(id1));
            model.addAttribute("illegal", true);
        }
        return "home";
    }

    //GET request to freeze or unfreeze the wallet
    @GetMapping("/freeze")
    public String toggleFreeze(Model model, HttpSession session) {
        String id = (String) session.getAttribute("walletID");
        boolean frozen = walletService.toggleFreeze(id);
        session.setAttribute("frozen",frozen);
        model.addAttribute("frozen", frozen);
        model.addAttribute("balance", walletService.getBalance(id));
        return "home";
    }

}
