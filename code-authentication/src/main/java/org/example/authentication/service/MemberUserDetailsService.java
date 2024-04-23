package org.example.authentication.service;

import org.example.authentication.model.MemberUserDetails;
import org.example.cabin.ums.api.MemberUserFeignClient;
import org.example.cabin.ums.dto.MemberAuthDTO;
import org.example.common.core.enums.ResultCode;
import org.example.common.core.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

/**
 * 会员用户认证服务
 *
 * @author ComeTomorrow
 * @since 2024/4/2
 */
@Service
public class MemberUserDetailsService implements UserDetailsService, UserDetailsPasswordService {

    @Autowired
    private MemberUserFeignClient memberFeignClient;

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    public void createUser(MemberAuthDTO user) {
        memberFeignClient.createUserByMobile(user);
    }

    public void updateUser(UserDetails user) {

    }

    public void deleteUser(String username) {

    }

    public void changePassword(String oldPassword, String newPassword) {

    }

    public boolean userExists(String username) {
        return false;
    }

    /**
     * 从数据库中获取用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Result<MemberAuthDTO> result = memberFeignClient.loadUserByMobile(username);

        MemberAuthDTO memberAuthInfo;
        if (!(Result.isSuccess(result) && (memberAuthInfo = result.getData()) != null)) {
            throw new UsernameNotFoundException(ResultCode.USER_NOT_EXIST.getMsg());
        }
        MemberUserDetails userDetails = new MemberUserDetails(memberAuthInfo);
        if (!userDetails.isEnabled()) {
            throw new DisabledException("该账户已被禁用!");
        } else if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定!");
        } else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期!");
        }
        return userDetails;
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param openid  微信公众平台唯一身份标识
     * @return 用户信息 {@link MemberUserDetails}
     */
//    public UserDetails loadUserByOpenid(String openid) {
//        // 根据 openid 获取微信用户认证信息
//        Result<MemberAuthDTO> getMemberAuthInfoResult = memberFeignClient.loadUserByOpenId(openid);
//
//        MemberAuthDTO memberAuthInfo = null;
//
//        // 会员不存在，注册成为新会员
//        if (ResultCode.USER_NOT_EXIST.getCode().equals(getMemberAuthInfoResult.getCode())) {
//            MemberRegisterDto memberRegisterInfo = new MemberRegisterDto();
//            memberRegisterInfo.setOpenid(openid);
//            memberRegisterInfo.setNickName("微信用户");
//            // 注册会员
//            Result<Long> registerMemberResult = memberFeignClient.registerMember(memberRegisterInfo);
//            // 注册成功将会员信息赋值给会员认证信息
//            Long memberId;
//            if (Result.isSuccess(registerMemberResult) && (memberId = registerMemberResult.getData()) != null) {
//                memberAuthInfo = new MemberAuthDTO(memberId, openid, StatusEnum.ENABLE.getValue());
//            }
//        } else {
//            Assert.isTrue((memberAuthInfo = getMemberAuthInfoResult.getData()) != null, "会员认证信息失败");
//        }
//
//        // 用户不存在
//        if (memberAuthInfo == null) {
//            throw new UsernameNotFoundException(ResultCode.USER_NOT_EXIST.getMsg());
//        }
//
//        UserDetails userDetails = new MemberDetails(memberAuthInfo);
//        if (!userDetails.isEnabled()) {
//            throw new DisabledException("该账户已被禁用!");
//        } else if (!userDetails.isAccountNonLocked()) {
//            throw new LockedException("该账号已被锁定!");
//        } else if (!userDetails.isAccountNonExpired()) {
//            throw new AccountExpiredException("该账号已过期!");
//        }
//        return userDetails;
//    }

}