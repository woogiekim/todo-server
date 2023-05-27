package com.woogie.todo.core.todo.domain;

import com.woogie.todo.core.user.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TodoRepository {

    @Insert("""
            insert into todo (user_id, title, description, startTime, endTime, status, created_at)
            value (#{user.id}, #{title}, #{description}, #{startTime}, #{endTime}, #{status}, #{createdAt})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Todo todo);

    @Update("""
            update todo set
                title = #{title},
                description = #{description},
                startTime = #{startTime},
                endTime = #{endTime},
                status = #{status}
            where id = #{id}
            """)
    void update(Todo todo);

    @Select("select * from todo where id = #{id}")
    @Results({
            @Result(property = "user", column = "user_id", one = @One(select = "getUser"))
    })
    Optional<Todo> findById(long id);

    @Select("select * from user where id = #{id}")
    Optional<User> getUser(long id);

    @Select("select * from todo")
    List<Todo> findAll();
}
