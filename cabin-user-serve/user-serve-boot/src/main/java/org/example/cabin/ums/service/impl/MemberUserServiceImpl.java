package org.example.cabin.ums.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.example.cabin.ums.dto.MemberAuthDTO;
import org.example.cabin.ums.mapper.MemberUserMapper;
import org.example.cabin.ums.model.entity.MemberUser;
import org.example.cabin.ums.service.MemberUserService;
import org.example.common.core.enums.ResultCode;
import org.example.common.web.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberUserServiceImpl implements MemberUserService {

    @Autowired
    private MemberUserMapper memberMapper;

    /**
     * 根据手机号获取会员认证信息
     *
     * @param mobile
     * @return
     */
    @Override
    public MemberAuthDTO getMemberUserByMobile(String mobile) {
        MemberUser user = memberMapper.selectOne(
                Wrappers.lambdaQuery(MemberUser.class)
                        .eq(MemberUser::getMobile, mobile)
                        .select(MemberUser::getId, MemberUser::getMobile, MemberUser::getStatus)
        );

        if (user == null) {
            throw new BizException(ResultCode.USER_NOT_EXIST);
        }

        MemberAuthDTO authDTO = new MemberAuthDTO();
        authDTO.setId(user.getId());
        authDTO.setStatus(user.getStatus());
        return authDTO;
    }
}
