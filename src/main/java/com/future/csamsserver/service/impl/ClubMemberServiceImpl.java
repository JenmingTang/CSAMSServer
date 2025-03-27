package com.future.csamsserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.csamsserver.domain.ClubMember;
import com.future.csamsserver.service.ClubMemberService;
import com.future.csamsserver.mapper.ClubMemberMapper;
import org.springframework.stereotype.Service;

/**
* @author Jenming
* @description 针对表【club_member(社团成员表)】的数据库操作Service实现
* @createDate 2025-03-08 22:03:14
*/
@Service
public class ClubMemberServiceImpl extends ServiceImpl<ClubMemberMapper, ClubMember>
    implements ClubMemberService{

}




