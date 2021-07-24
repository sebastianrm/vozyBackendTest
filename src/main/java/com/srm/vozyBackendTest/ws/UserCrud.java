/**
 * 
 */
package com.srm.vozyBackendTest.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.srm.vozyBackendTest.document.User;
import com.srm.vozyBackendTest.jwtConf.JwtTokenUtil;
import com.srm.vozyBackendTest.repository.UserRepository;
import com.srm.vozyBackendTest.service.JwtUserDetailsService;
import com.srm.vozyBackendTest.vo.JwtResponse;
import com.srm.vozyBackendTest.vo.UserVo;

/**
 * @author sebastian retamal
 *
 */
@RestController
public class UserCrud {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@GetMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody UserVo userReq){
		
		try {
			authenticate(userReq.getUsername(), userReq.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(userReq.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
		
	}
	

	@PostMapping(value="/singin")
	public ResponseEntity<?> registrar(@RequestBody User user){
		
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(user.getUserName());
		
		
		if (loadUserByUsername == null) {
			
			return ResponseEntity.ok(userDetailsService.save(user));
		}else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario ya existe");
		}
		
		
	}
	
	@PutMapping(value="/edituser")
	public ResponseEntity<?> editarUsuario(@RequestBody User user){
		
		User findByUserName = userRepo.findByUserName(user.getUserName());
		
		
		if (findByUserName != null) {
			
			user.set_id(findByUserName.get_id());
			
			return ResponseEntity.ok(userRepo.save(user));
			
		}else {
			
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario no existe");
			
		}
		
	}
	
	@DeleteMapping(value="/deleteuser")
	public ResponseEntity<?> eliminarUsuario(User user){
		
		User findByUserName = userRepo.findByUserName(user.getUserName());
		
		
		if (findByUserName != null) {
			
			user.set_id(findByUserName.get_id());
			userRepo.delete(user);
			
			return ResponseEntity.ok("Registro eliminado");
			
		}else {
			
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario no existe");
			
		}
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("usuario desabilitado", e);
		} catch (BadCredentialsException e) {
			throw new Exception("Login incorrecto", e);
		}
	}
}
