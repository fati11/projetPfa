package com.pfa.projetpfa.web;

import java.io.IOException;
import com.pfa.projetpfa.DAO.ProductRepository;
import com.pfa.projetpfa.constants.ResponseCode;
import com.pfa.projetpfa.constants.WebConstants;
import com.pfa.projetpfa.entities.Product;
import com.pfa.projetpfa.response.prodResp;
import com.pfa.projetpfa.response.serverResp;
import com.pfa.projetpfa.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private ProductRepository prodRepo;

	@PostMapping("/addProduct")
	public ResponseEntity<prodResp> addProduct(
											   @RequestParam(name = WebConstants.PROD_FILE) MultipartFile prodImage,
											   @RequestParam(name = WebConstants.PROD_ID) String productid,
											   @RequestParam(name = WebConstants.PROD_PRICE) String price,
											   @RequestParam(name = WebConstants.PROD_NAME) String productname,
											   @RequestParam(name = WebConstants.PROD_QUANITY) String quantity) throws Exception {
		prodResp resp = new prodResp();
		if (prodRepo.findByDesignation(productname) != null || prodRepo.findByProductid(productid) !=null ) {
			throw new Exception("ce produit existe déjà");
		} else {
				Product prod = new Product();
				prod.setProductid(productid);
				prod.setPrice(Double.parseDouble(price));
				prod.setDesignation(productname);
				prod.setQuantity(Integer.parseInt(quantity));
				prod.setProductimage(prodImage.getBytes());
				prodRepo.save(prod);

				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.ADD_SUCCESS_MESSAGE);
				resp.setOblist(prodRepo.findAll());
			return new ResponseEntity<prodResp>(resp, HttpStatus.ACCEPTED);
		}
	}
	@GetMapping("/getProducts")
	public ResponseEntity<prodResp> getProducts()
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
	@PostMapping("/updateProducts")
	public ResponseEntity<serverResp> updateProducts(
			@RequestParam(name = WebConstants.PROD_FILE, required = false) MultipartFile prodImage,
			@RequestParam(name = WebConstants.PROD_PRICE) String price,
			@RequestParam(name = WebConstants.PROD_NAME) String productname,
			@RequestParam(name = WebConstants.PROD_QUANITY) String quantity,
			@RequestParam(name = WebConstants.PROD_ID) String productid) throws Exception {
		Product loadedProduct = prodRepo.findByDesignation(productname);
		if (loadedProduct != null && !loadedProduct.getProductid().equals(productid) ) {
			throw new Exception("ce produit existe déjà");
		} else {
			serverResp resp = new serverResp();
				try {
					Product prodOrg;
					Product prod;
					if (prodImage != null) {
						prod = new Product(productid, productname, Double.parseDouble(price),
								Integer.parseInt(quantity), prodImage.getBytes());
					} else {
						prodOrg = prodRepo.findByProductid(productid);
						prod = new Product(productid, productname, Double.parseDouble(price),
								Integer.parseInt(quantity), prodOrg.getProductimage());
					}
					prodRepo.save(prod);
					resp.setStatus(ResponseCode.SUCCESS_CODE);
					resp.setMessage(ResponseCode.UPD_SUCCESS_MESSAGE);
				} catch (Exception e) {
					resp.setStatus(ResponseCode.FAILURE_CODE);
					resp.setMessage(e.getMessage());
				}
			return new ResponseEntity<serverResp>(resp, HttpStatus.ACCEPTED);
		}
	}

	@GetMapping("/delProduct")
	public ResponseEntity<prodResp> delProduct(
			@RequestParam(name = WebConstants.PROD_ID) String productid) throws IOException {
		prodResp resp = new prodResp();
			try {
				prodRepo.deleteByProductid(productid);
				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.DEL_SUCCESS_MESSAGE);
				resp.setOblist(prodRepo.findAll());
			} catch (Exception e) {
				resp.setStatus(ResponseCode.FAILURE_CODE);
				resp.setMessage(e.toString());
			}
		return new ResponseEntity<prodResp>(resp, HttpStatus.ACCEPTED);
	}
}
