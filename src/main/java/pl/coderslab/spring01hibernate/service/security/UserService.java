package pl.coderslab.spring01hibernate.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.spring01hibernate.entity.security.User;
import pl.coderslab.spring01hibernate.model.security.UserDto;
import pl.coderslab.spring01hibernate.repository.security.UserRepository;
import pl.coderslab.spring01hibernate.validation.security.EmailExistsException;


import javax.transaction.Transactional;
import java.util.Arrays;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserRepository repository;

	@Transactional
    public User registerNewUserAccount(UserDto accountDto)
      throws EmailExistsException {
         
        if (emailExist(accountDto.getEmail())) {   
            throw new EmailExistsException(
              "There is an account with that email address: " + accountDto.getEmail());
        }
        User user = new User();    
        user.setUsername(accountDto.getUsername());
        user.setPassword(accountDto.getPassword());
        user.setEmail(accountDto.getEmail());
        user.setRoles(Arrays.asList("ROLE_ADMIN"));
        return repository.save(user);       
    }

	private boolean emailExist(String email) {
		User user = repository.findByEmail(email);
		if (user != null) {
			return true;
		}
		return false;
	}
}