package hu.acsaifz.blockchaindemo.controller;

import hu.acsaifz.blockchaindemo.service.BlockchainService;
import hu.acsaifz.blockchaindemo.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NodeController {
    private BlockchainService blockchainService;
    private WalletService walletService;

    @Autowired
    public void setBlockchainService(BlockchainService blockchainService) {
        this.blockchainService = blockchainService;
    }

    @Autowired
    public void setWalletService(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("blockchain", blockchainService.getBlockchain());
        model.addAttribute("wallet", walletService.getWallet());
        return "index";
    }

    @PostMapping("/wallet")
    public String createWallet(){
        walletService.generateWallet();
        return "redirect:/";
    }

    @PostMapping("/mine")
    public String mine(){
        return "redirect:/";
    }

}
