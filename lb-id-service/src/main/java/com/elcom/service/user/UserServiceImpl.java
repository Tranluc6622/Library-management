package com.elcom.service.user;

import com.elcom.model.User;

import com.elcom.repository.UserCustomizeRepository;
import com.elcom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Service
public abstract class UserServiceImpl implements UserService{
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private UserCustomizeRepository userCustomizeRepository;

    @Override
    public List<User> findAll(Integer currentPage, Integer rowsPerPage, String sort) {

        if( currentPage > 0 ) currentPage--;

        Pageable paging = PageRequest.of(currentPage, rowsPerPage, Sort.by(sort).descending());

        Page<User> pagedResult = userRepository.findAll(paging);

        if(pagedResult.hasContent())
            return pagedResult.getContent();
        else
            return new ArrayList<>();
        //return (List<User>) userRepository.findAll(paging);
    }
    @Override
    public User findById(Integer id)
    {
        return userCustomizeRepository.findById(id);
    }
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }



}
