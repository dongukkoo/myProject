package com.example.myproject.service;

import com.example.myproject.domain.Community;
import com.example.myproject.mapper.CommunityCommentMapper;
import com.example.myproject.mapper.CommunityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommunityService {

    @Autowired
    private CommunityMapper mapper;

    @Autowired
    private CommunityCommentMapper commentMapper;

    public Map<String, Object> selectAll(Integer page, String search, String type, String category){
        // 페이지당 행의 수
        Integer rowPerPage = 10;

        // 쿼리 LIMIT 절에 사용할 시작 인덱스
        Integer startIndex = (page - 1) * rowPerPage;

        // 페이지네이션이 필요한 정보
        // 전체 레코드 수
        Integer numOfRecords = mapper.countAll(search, type);
        // 마지막 페이지 번호
        Integer lastPageNumber = (numOfRecords - 1) / rowPerPage + 1;
        // 페이지네이션 왼쪽번호
        Integer leftPageNum = page - 5;
        // 1보다 작을 수 없음
        leftPageNum = Math.max(leftPageNum, 1);

        // 페이지네이션 오른쪽번호
        Integer rightPageNum = leftPageNum + 9;
        // 마지막페이지보다 클 수 없음
        rightPageNum = Math.min(rightPageNum, lastPageNumber);

        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("rightPageNum", rightPageNum);
        pageInfo.put("leftPageNum", leftPageNum);
        pageInfo.put("currentPageNum", page);
        pageInfo.put("lastPageNum", lastPageNumber);

        // 게시물 목록
        List<Community> list = mapper.selectAllPaging(startIndex, rowPerPage, search, type, category);

        return Map.of("pageInfo", pageInfo,
                "communityList", list);
    }


    public Community getCommunity(Integer id, Authentication authentication) {
        Community community = mapper.selectById(id);

        return community;
    }

    public boolean addCommunity(Community community) {
        int cnt = mapper.insert(community);

        return cnt == 1;
    }

    public boolean modify(Community community) {
        int cnt = mapper.update(community);

        return cnt == 1;
    }

    public boolean remove(Integer id) {
        commentMapper.deleteById(id);

        int cnt = mapper.deleteById(id);
        return cnt == 1;
    }


    public Object getCom(Integer id) {
        return getCommunity(id, null);
    }
}
