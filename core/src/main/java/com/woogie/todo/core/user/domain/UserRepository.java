package com.woogie.todo.core.user.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserRepository {

    @Insert("""
            insert into user (email, name, password, status, created_at)
            value (#{email}, #{name}, #{password}, #{status}, #{createdAt})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(User user);

    @Select("select * from user where id = #{id}")
    Optional<User> findById(long id);

    @Select("select * from user")
    List<User> findAll();
}
