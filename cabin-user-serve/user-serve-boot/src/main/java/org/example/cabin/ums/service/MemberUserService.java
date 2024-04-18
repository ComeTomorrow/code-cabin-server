package org.example.cabin.ums.service;

import org.example.cabin.ums.dto.MemberAuthDTO;

public interface MemberUserService {

    /**
     * 根据手机号获取会员认证信息
     *
     * @param mobile
     * @return
     */
    MemberAuthDTO getMemberUserByMobile(String mobile);
}
