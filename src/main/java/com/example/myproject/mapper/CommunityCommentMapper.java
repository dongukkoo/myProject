package com.example.myproject.mapper;

import com.example.myproject.domain.CommunityComment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommunityCommentMapper {

    @Select("""
            SELECT * 
            FROM CommunityComment
            WHERE id = #{id}
            """)
    CommunityComment selectById(Integer id);

    @Delete("""
            DELETE FROM CommunityComment
            WHERE communityId = #{communityId}
            """)
    Integer deleteById(Integer communityId);

    @Update("""
            UPDATE CommunityComment
            SET
                content = #{content}
            WHERE id = #{id}
            """)
    int update(CommunityComment comment);

    @Insert("""
            INSERT INTO CommunityComment (communityId, content, userId)
            VALUES ( #{boardId}, #{content}, #{memberId} )
            """)
    int insert(CommunityComment comment);

    @Select("""
            SELECT *
            FROM CommunityComment
            WHERE communityId = #{communityId}
            ORDER BY id
            """)
    List<CommunityComment> selectAllByCommunityId(Integer communityId);
}
