package com.csys.workflow.security;

import com.csys.workflow.domain.Employe;
import com.csys.workflow.repository.EmployeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MyUserDetails implements UserDetailsService {

  private final EmployeRepository employeRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final Employe employe = employeRepository.findByUsername(username).orElse(null);

    if (employe == null) {
      throw new UsernameNotFoundException("User '" + username + "' not found");
    }

    return org.springframework.security.core.userdetails.User//
        .withUsername(username)//
        .password(employe.getPassword())//
        .accountExpired(false)//
        .accountLocked(false)//
        .credentialsExpired(false)//
        .disabled(false)//
        .build();
  }

}
