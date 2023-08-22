/*
 * package com.example.cars.and.users.api.controller;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.security.authentication.AuthenticationManager; import
 * org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken; import
 * org.springframework.security.core.Authentication; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.example.cars.and.users.api.dto.JwtResponseDTO; import
 * com.example.cars.and.users.api.dto.UserLoginDTO;
 * 
 * @RestController
 * 
 * @RequestMapping("/api") public class LoginController {
 * 
 * @Autowired private AuthenticationManager authenticationManager;
 * 
 * @Autowired private UserDetailsService userDetailsService;
 * 
 * 
 * 
 * @PostMapping("/signin") public ResponseEntity<?> signin(@RequestBody
 * UserLoginDTO userLoginDTO) { try { Authentication authentication =
 * authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
 * userLoginDTO.getLogin(), userLoginDTO.getPassword() ) );
 * 
 * UserDetails userDetails =
 * userDetailsService.loadUserByUsername(userLoginDTO.getLogin()); String token
 * = jwtUtil.generateToken(userDetails);
 * 
 * return ResponseEntity.ok(new JwtResponseDTO(token)); } catch (Exception e) {
 * return ResponseEntity.badRequest().body("Invalid credentials"); } } }
 */