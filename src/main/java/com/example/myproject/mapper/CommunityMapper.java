package com.example.myproject.mapper;

import com.example.myproject.domain.Community;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommunityMapper {

    @Select("""
            SELECT
                c.id,
                c.title,
                c.body,
                c.inserted,
                m.name,
                (SELECT COUNT(*)
                    FROM CommunityComment
                    WHERE communityId = id) commentCount
            FROM Community c 
            INNER JOIN Member m ON c.writer = m.id
            WHERE c.id = #{c.id}
            """)
    Community selectById(Integer id);

    @Insert("""
            INSERT INTO Community (category, title, body, writer)
            VALUES ( #{category}, #{title}, #{body}, #{writer} )
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Community community);

    @Select("""
            <script>
            			<bind name="pattern" value="'%' + search + '%'" />
            			SELECT COUNT(*)
            			FROM Community
                        
            			<where>
            				<if test="(type eq 'all') or (type eq 'title')">
            				   title  LIKE #{pattern}
            				</if>
            				<if test="(type eq 'all') or (type eq 'body')">
            				OR body   LIKE #{pattern}
            				</if>
            				<if test="(type eq 'all') or (type eq 'writer')">
            				OR writer LIKE #{pattern}
            				</if>
            			</where>
                        
            </script>
            """)
    Integer countAll(String search, String type);

    @Select("""
            <script>
            			<bind name="pattern" value="'%' + search + '%'" />
            			SELECT
            			    c.id,
            			    c.title,
            			    c.inserted,
            			    c.category,
            			    m.name,
            			    (SELECT COUNT(*)
            			     FROM CommunityComment
            			     WHERE communityId = id) commentCount            			            			                        
            			FROM Community c
            			INNER JOIN Member m ON c.writer = m.id
            			WHERE (category = #{category} OR #{category} IS NULL)
                        
            			
            				 <if test="type eq 'title'">
            			         AND title LIKE #{pattern}
            			     </if>
            			     <if test="type eq 'body'">
            			         AND body LIKE #{pattern}
            			     </if>
            			     <if test="type eq 'writer'">
            			         AND writer LIKE #{pattern}
            			     </if>
                        
            			GROUP BY c.id
            			ORDER BY c.id DESC
            			LIMIT #{startIndex}, #{rowPerPage}
            </script>
            """)
//    @ResultMap("pagingMap")
    List<Community> selectAllPaging(Integer startIndex, Integer rowPerPage, String search, String type, String category);


    @Update("""
            UPDATE Community
            SET
                title = #{title},
                body = #{body},
                category = #{category}
            WHERE 
                id = #{id}
            """)
    int update(Community community);


    @Delete("""
            DELETE FROM Community
            WHERE id = #{id}
            """)
    int deleteById(Integer id);

    @Select("""
            SELECT
                c.id,
                c.category,
                c.title,
                c.inserted,
                m.name
            FROM Community c
            INNER JOIN Member m ON c.writer = m.id
            ORDER BY c.id DESC
            LIMIT 7
            """)
    List<Community> selectAll();
}
