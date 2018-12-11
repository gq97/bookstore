package cn.edu.ncu.bookstore.config;

import cn.edu.ncu.bookstore.entity.User;
import cn.edu.ncu.bookstore.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Optional;

public class MyUserDetails implements UserDetails {


    private String username;
    private String password;

    boolean exist = false;

    public boolean isExist(){
        return exist;
    }



    public MyUserDetails(String username, UserRepository userRepository){
        Optional<User> user = userRepository.findById(username);
        if(user.isPresent()){
            this.username = username;
            password = user.get().getUser_password();
            exist = true;
        }
        System.out.println("Entering MyUserDetails...");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password != null){
            this.password = password;
        }
        System.out.println("Null password!");
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return exist;
    }

    @Override
    public boolean isAccountNonLocked() {
        return exist;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return exist;
    }

    @Override
    public boolean isEnabled() {
        return exist;
    }
}
