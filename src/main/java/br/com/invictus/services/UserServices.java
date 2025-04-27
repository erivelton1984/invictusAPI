package br.com.invictus.services;

import br.com.invictus.data.vo.UserVO;
import br.com.invictus.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserServices implements UserDetailsService {

    private Logger logger = Logger.getLogger(UserVO.class.getName());

    @Autowired
    UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        logger.info("Find one user by name " + userName + "!");
        var user = userRepository.findByUserName(userName);
        if(user != null){
            return user;
        }else{
            throw new UsernameNotFoundException("UserName " + userName + "not found!");
        }
    }
}