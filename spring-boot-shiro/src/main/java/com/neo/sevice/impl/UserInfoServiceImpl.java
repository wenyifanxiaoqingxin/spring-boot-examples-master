package com.neo.sevice.impl;

import com.neo.dao.UserInfoDao;
import com.neo.entity.UserInfo;
import com.neo.sevice.UserInfoService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo findByUsername(String username,String password) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        Specification specification = new Specification<UserInfo>() {
            @Override
            public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                predicates.add(criteriaBuilder.equal(root.get("username"),username));
//                predicates.add(criteriaBuilder.equal(root.get("username"),username));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        List<UserInfo> list = new ArrayList<>();
        list = userInfoDao.findAll(specification);
        UserInfo userInfo = new UserInfo();
        if(list.size()>0){
            userInfo = list.get(0);
        }

        return userInfo;
    }
}