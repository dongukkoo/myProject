package com.example.myproject.mapper;

import com.example.myproject.domain.Notice;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoticeMapper {

    @Select("""
            <script>
            			<bind name="pattern" value="'%' + search + '%'" />
            			SELECT COUNT(*)
            			FROM Notice
                        
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
            				n.id,
            				n.title,
            				n.body,
            				n.inserted,
            				m.name                       
            			FROM Notice n
            			INNER JOIN Member m ON n.writer = m.id 
            			<where>
            				 <if test="type eq 'title'">
            			         AND title LIKE #{pattern}
            			     </if>
            			     <if test="type eq 'body'">
            			         AND body LIKE #{pattern}
            			     </if>
            			     <if test="type eq 'writer'">
            			         AND writer LIKE #{pattern}
            			     </if>
            			</where>                        
            			GROUP BY n.id
            			ORDER BY n.id DESC
            			LIMIT #{startIndex}, #{rowPerPage}
            </script>
            """)
    List<Notice> selectAllPaging(Integer startIndex, Integer rowPerPage, String search, String type);

    @Select("""
            SELECT
                	id,
                    title,
                    writer,
                    body,
                    inserted
            FROM Notice
            WHERE id = #{id}
            """)
    Notice selectById(Integer id);

    @Update("""
            UPDATE Notice
            SET
                title = #{title},
                body = #{body}
            WHERE
                id = #{id}
            """)
    int update(Notice notice);

    @Delete("""
            DELETE FROM Notice
            WHERE
                id = #{id}
            """)
    int deleteById(Integer id);

    @Insert("""
            INSERT INTO Notice (title, body, writer)
            VALUES ( #{title}, #{body}, #{writer} )
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Notice notice);

    @Select("""
            SELECT
                n.id,
                n.title,
                n.inserted,
                m.name
            FROM Notice n
            INNER JOIN Member m ON n.writer = m.id
            ORDER BY n.id DESC
            """)
    List<Notice> selectAll();
}
