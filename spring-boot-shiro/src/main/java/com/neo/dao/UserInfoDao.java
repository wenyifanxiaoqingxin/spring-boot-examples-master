package com.neo.dao;

import com.neo.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoDao extends JpaRepository<UserInfo,String>,JpaSpecificationExecutor {
    /**通过username查找用户信息;*/
}