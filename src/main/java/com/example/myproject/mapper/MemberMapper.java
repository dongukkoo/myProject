package com.example.myproject.mapper;

import com.example.myproject.domain.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {
    @Insert("""
            INSERT INTO Member(id, password, name, position, gender, address, phoneNum, email, birthDate, authority)
            VALUES (#{id}, #{password}, #{name}, #{position}, #{gender}, #{address}, #{phoneNum}, #{email}, #{birthDate}, #{authority} )
            """)
    int insert(Member member);

    @Select("""
            SELECT
                id,
                name,
                position,
                gender,
                address,
                phoneNum,
                email,
                birthDate,
                authority
            FROM Member
            ORDER BY name ASC
            """)
    List<Member> selectAll();

    @Select("""
            SELECT
                id,
                password,
                name,
                position,
                gender,
                address,
                phoneNum,
                email,
                birthDate,
                authority
            FROM Member
            WHERE id = #{id}
            """)
    Member selectById(String id);

    @Delete("""
            DELETE FROM Member
            WHERE id = #{id}
            """)
    Integer deleteById(String id);

    @Update("""
            <script>
                        
            UPDATE Member
            SET 
                <if test="password neq null and password neq ''">
                password = #{password},
                </if>
                name = #{name},                
                position = #{position},
                email = #{email},
                phoneNum = #{phoneNum},
                birthDate = #{birthDate},
                address = #{address},
                gender = #{gender}
            WHERE 
                id = #{id}
                
            </script>
            """)
    Integer update(Member member);


    @Select("""
            SELECT * FROM Member
            WHERE id = #{id}
            """)
    Member findById(String id);


}
