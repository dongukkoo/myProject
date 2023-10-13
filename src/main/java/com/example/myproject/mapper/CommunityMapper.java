package com.example.myproject.mapper;

import com.example.myproject.domain.Community;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommunityMapper {

    @Select("""
            SELECT
                	id,
                    title,
                    body,
                    inserted,
                    writer,
                    (SELECT COUNT(*)
                    FROM CommunityComment
                    WHERE communityId = id) commentCount
                    FROM Community
                    WHERE id = #{id}
            """)
//    @ResultMap("communityResultMap")
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
            				id,
            				title,
            				writer,
            				inserted,
            				category,
            			    (SELECT COUNT(*)
            			     FROM CommunityComment
            			     WHERE communityId = id) commentCount
                        
            			FROM Community
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
                        
            			GROUP BY id
            			ORDER BY id DESC
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
                id,
                category,
                title,
                writer,
                inserted
            FROM Community
            ORDER BY id DESC
            LIMIT 7
            """)
    List<Community> selectAll();
}
