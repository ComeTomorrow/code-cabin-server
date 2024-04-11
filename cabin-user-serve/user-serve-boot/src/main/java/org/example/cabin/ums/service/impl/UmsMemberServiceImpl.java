package org.example.cabin.ums.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.example.cabin.ums.dto.MemberAuthDTO;
import org.example.cabin.ums.mapper.UmsMemberMapper;
import org.example.cabin.ums.model.entity.UmsMember;
import org.example.cabin.ums.service.UmsMemberService;
import org.example.common.core.enums.ResultCode;
import org.example.common.web.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    private UmsMemberMapper memberMapper;

    /**
     * 根据手机号获取会员认证信息
     *
     * @param mobile
     * @return
     */
    @Override
    public MemberAuthDTO getMemberByMobile(String mobile) {
        UmsMember member = memberMapper.selectOne(
                Wrappers.lambdaQuery(UmsMember.class)
                        .eq(UmsMember::getMobile, mobile)
                        .select(UmsMember::getId, UmsMember::getMobile, UmsMember::getStatus)
        );

        if (member == null) {
            throw new BizException(ResultCode.USER_NOT_EXIST);
        }

        MemberAuthDTO authDTO = new MemberAuthDTO();
        authDTO.setId(member.getId());
        authDTO.setStatus(member.getStatus());
        return authDTO;
    }
}
