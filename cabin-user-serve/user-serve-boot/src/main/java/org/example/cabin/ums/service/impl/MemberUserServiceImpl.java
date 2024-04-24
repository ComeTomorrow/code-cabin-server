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
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
public class MemberUserServiceImpl implements MemberUserService {

    @Autowired
    private MemberUserMapper memberUserMapper;

    /**
     * 根据手机号获取会员认证信息
     *
     * @param mobile
     * @return
     */
    @Override
    public MemberAuthDTO getMemberUserByMobile(String mobile) {
        MemberUser user = memberUserMapper.selectOne(
                Wrappers.lambdaQuery(MemberUser.class)
                        .eq(MemberUser::getMobile, mobile)
                        .select(MemberUser::getId, MemberUser::getMobile, MemberUser::getEnabled, MemberUser::getPassword)
        );

        if (user == null) {
            throw new BizException(ResultCode.USER_NOT_EXIST);
        }

        MemberAuthDTO authDTO = new MemberAuthDTO();
        authDTO.setId(user.getId());
        authDTO.setUsername(user.getMobile());
        authDTO.setEnabled(user.getEnabled());
        authDTO.setPassword(user.getPassword());
        return authDTO;
    }

    @Transactional
    @Override
    public int addMemberUser(MemberAuthDTO member) {
        MemberUser user = memberUserMapper.selectOne(
                Wrappers.lambdaQuery(MemberUser.class)
                        .eq(MemberUser::getMobile, member.getUsername())
        );

        if(Objects.nonNull(user)){
            return 0;
        }
        MemberUser entity = new MemberUser();
        entity.setMobile(member.getUsername());
        entity.setPassword(member.getPassword());
        entity.setMuCode(UUID.randomUUID().toString());
        entity.setNickName("coder"+entity.getMuCode());
        entity.setEnabled(true);
        entity.setAccountNonExpired(true);
        entity.setAccountNonLocked(true);
        entity.setCredentialsNonExpired(true);
        return memberUserMapper.insert(entity);
    }
}
