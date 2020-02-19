package com.pfa.projetpfa.web;

import com.pfa.projetpfa.DAO.*;
import com.pfa.projetpfa.config.JwtTokenUtil;
import com.pfa.projetpfa.constants.ResponseCode;
import com.pfa.projetpfa.constants.WebConstants;
import com.pfa.projetpfa.entities.Bufcart;
import com.pfa.projetpfa.entities.Product;
import com.pfa.projetpfa.entities.User;
import com.pfa.projetpfa.response.cartResp;
import com.pfa.projetpfa.response.detailProdResp;
import com.pfa.projetpfa.response.prodResp;
import com.pfa.projetpfa.response.serverResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
@Transactional
public class UserRestService {
    private static Logger logger = Logger.getLogger(UserRestService.class.getName());
@Autowired
    EmailRepository emailRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository prodRepo;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private CartRepository cartRepo;
    @RequestMapping(value="/chercherUserName",method= RequestMethod.GET)
    public User chercher(@RequestParam(name = "username",defaultValue = "") String nom
    )
    {
        return userRepository.chercherUser(nom);

    }
    @GetMapping("/user/getProducts")
    public ResponseEntity<prodResp> getProductsContainingMotCle()
            throws IOException {

        prodResp resp = new prodResp();
        try {
            resp.setStatus(ResponseCode.SUCCESS_CODE);
            resp.setMessage(ResponseCode.LIST_SUCCESS_MESSAGE);
            resp.setOblist(prodRepo.findAll());
        } catch (Exception e) {
            resp.setStatus(ResponseCode.FAILURE_CODE);
            resp.setMessage(e.getMessage());
        }

        return new ResponseEntity<prodResp>(resp, HttpStatus.ACCEPTED);
    }
    @GetMapping("/user/getProducts/{search}")
    public ResponseEntity<prodResp> getProducts(@PathVariable String search)
            throws IOException {

        prodResp resp = new prodResp();
        try {
            resp.setStatus(ResponseCode.SUCCESS_CODE);
            resp.setMessage(ResponseCode.LIST_SUCCESS_MESSAGE);
            resp.setOblist(prodRepo.findByDesignationContaining(search));
        } catch (Exception e) {
            resp.setStatus(ResponseCode.FAILURE_CODE);
            resp.setMessage(e.getMessage());
        }
        return new ResponseEntity<prodResp>(resp, HttpStatus.ACCEPTED);
    }
    @GetMapping("/user/getProduct")
    public ResponseEntity<detailProdResp> getProduct(
                                                     @RequestParam(WebConstants.PROD_ID) String productId)
            throws IOException {

        detailProdResp resp = new detailProdResp();
        try {
            resp.setStatus(ResponseCode.SUCCESS_CODE);
            resp.setMessage(ResponseCode.LIST_SUCCESS_MESSAGE);
            resp.setProd(prodRepo.findByProductid(productId));
        } catch (Exception e) {
            resp.setStatus(ResponseCode.FAILURE_CODE);
            resp.setMessage(e.getMessage());
        }
        return new ResponseEntity<detailProdResp>(resp, HttpStatus.ACCEPTED);
    }
    @GetMapping("/user/addToCart")
    public ResponseEntity<serverResp> addToCart(@RequestParam(name = "email",defaultValue = "") String email,
                                                @RequestParam(WebConstants.PROD_ID) String productId, @RequestParam(WebConstants.PROD_QUANITY) String quantity) throws Exception {

        serverResp resp = new serverResp();
        Product cartItem = prodRepo.findByProductid(productId);
        if (cartItem.getQuantity() - Integer.parseInt(quantity)<0) {
            throw new Exception("Stock indisponible");
        } else {
            try {
                User loggedUser = userRepository.findByUsername(email);
                Bufcart buf = new Bufcart();
                buf.setEmail(loggedUser.getUsername());
                buf.setQuantity(Integer.parseInt(quantity));
                buf.setPrice(cartItem.getPrice());
                buf.setProductId(productId);
                buf.setProductname(cartItem.getDesignation());
                Date date = new Date();
                buf.setDateAdded(date);
                cartRepo.save(buf);
                //cartItem.setQuantity(cartItem.getQuantity() - Integer.parseInt(quantity));
                prodRepo.save(cartItem);
                resp.setStatus(ResponseCode.SUCCESS_CODE);
                resp.setMessage(ResponseCode.CART_UPD_MESSAGE_CODE);
            } catch (Exception e) {
                resp.setStatus(ResponseCode.FAILURE_CODE);
                resp.setMessage(e.getMessage());
            }
            return new ResponseEntity<serverResp>(resp, HttpStatus.ACCEPTED);
        }
    }
    @GetMapping("/user/viewCart")
    public ResponseEntity<cartResp> viewCart(@RequestParam(name = "email",defaultValue = "") String email)
            throws IOException {
        logger.info("Inside View cart request method");
        cartResp resp = new cartResp();
        try {
            logger.info("Inside View cart request method 2");
            User loggedUser = userRepository.findByUsername(email);
            resp.setStatus(ResponseCode.SUCCESS_CODE);
            resp.setMessage(ResponseCode.VW_CART_MESSAGE);
            resp.setOblist(cartRepo.findByEmail(loggedUser.getUsername()));
        } catch (Exception e) {
            resp.setStatus(ResponseCode.FAILURE_CODE);
            resp.setMessage(e.getMessage());
        }
        return new ResponseEntity<cartResp>(resp, HttpStatus.ACCEPTED);
    }
    @GetMapping("/user/updateCart")
    public ResponseEntity<cartResp> updateCart(@RequestParam(name = "email",defaultValue = "") String email,
                                               @RequestParam(name = WebConstants.BUF_ID) String bufcartid,
                                               @RequestParam(name = WebConstants.BUF_QUANTITY) String quantity) throws Exception {

        cartResp resp = new cartResp();
        User loggedUser = userRepository.findByUsername(email);
        Bufcart selCart = cartRepo.findByBufcartIdAndEmail(Integer.parseInt(bufcartid), loggedUser.getUsername());
        Product p = prodRepo.findByProductid(selCart.getProductId());
        if (p.getQuantity() - Integer.parseInt(quantity) < 0) {
            throw new Exception("Stock indisponible");

        } else {
            selCart.setQuantity(Integer.parseInt(quantity));
            p.setQuantity(p.getQuantity() - Integer.parseInt(quantity));
            cartRepo.save(selCart);
            prodRepo.save(p);
            List<Bufcart> bufcartlist = cartRepo.findByEmail(loggedUser.getUsername());
            resp.setStatus(ResponseCode.SUCCESS_CODE);
            resp.setMessage(ResponseCode.UPD_CART_MESSAGE);
            resp.setOblist(bufcartlist);
            return new ResponseEntity<cartResp>(resp, HttpStatus.ACCEPTED);
        }
    }
    @GetMapping("/user/delCart")
    public ResponseEntity<cartResp> delCart(@RequestParam(name = "email",defaultValue = "") String email,
                                            @RequestParam(name = WebConstants.BUF_ID) String bufcartid) throws IOException {

        cartResp resp = new cartResp();
        try {
            User loggedUser = userRepository.findByUsername(email);
            Bufcart bufcart = cartRepo.findByBufcartIdAndEmail(Integer.parseInt(bufcartid), email);
            Product product = prodRepo.findByProductid(bufcart.getProductId());
            product.setQuantity(product.getQuantity() + bufcart.getQuantity());
            cartRepo.deleteByBufcartIdAndEmail(Integer.parseInt(bufcartid), email);

            List<Bufcart> bufcartlist = cartRepo.findByEmail(email);
            resp.setStatus(ResponseCode.SUCCESS_CODE);
            resp.setMessage(ResponseCode.DEL_CART_SUCCESS_MESSAGE);
            resp.setOblist(bufcartlist);
        } catch (Exception e) {
            resp.setStatus(ResponseCode.FAILURE_CODE);
            resp.setMessage(e.getMessage());
        }
        return new ResponseEntity<cartResp>(resp, HttpStatus.ACCEPTED);
    }
}
