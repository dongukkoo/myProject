package com.example.myproject.mapper;

import com.example.myproject.domain.Attendance;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AttendanceMapper {

    @Insert("""
            INSERT INTO Attendance (userId, checkIn)
            VALUES ( #{userId}, NOW() )
            """)
    void insertCheckIn(String userId);

    @Update("""
            UPDATE Attendance 
            SET checkOut = NOW()
            WHERE userId = #{userId} AND checkOut IS NULL
            """)
    void updateCheckOut(String userId);

    @Select("""
            SELECT * FROM Attendance
            WHERE userId = #{userId} ORDER BY checkIn DESC LIMIT 1
            """)
    Attendance getLatestAttendance(String userId);


    @Select("""
            SELECT 
                a.id, 
                a.userId, 
                a.checkIn, 
                a.checkOut, 
                m.name
            FROM Attendance a
            INNER JOIN Member m ON a.userId = m.id
            ORDER BY a.id DESC;
            """)
    List<Attendance> getAllUserAttendances();
}
