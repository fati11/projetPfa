package com.pfa.projetpfa.web;

import com.pfa.projetpfa.DAO.JwtRequest;
import com.pfa.projetpfa.DAO.JwtResponse;
import com.pfa.projetpfa.DAO.ProspectRepository;
import com.pfa.projetpfa.DAO.UserRepository;
import com.pfa.projetpfa.config.JwtTokenUtil;
import com.pfa.projetpfa.entities.Role;
import com.pfa.projetpfa.entities.User;
import com.pfa.projetpfa.entities.Utilisateur;
import com.pfa.projetpfa.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    @GetMapping(value="/rest/user/role")
    public Role getRoleOfAuthenticatedUser(HttpServletRequest request){
        final String requestTokenHeader=request.getHeader("Authorization");
        String jwtToken = requestTokenHeader.substring(7);
        String usernme = jwtTokenUtil.getUsernameFromToken(jwtToken);
        return userRepository.findByUsername(usernme).getRole();
    }
}
