package org.example.cabin.ums.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import org.example.common.core.base.BaseEntity;

import java.time.LocalDate;

/**
 * @author ComeTomorrow
 * @since 2024/4/10
 */
@Data
public class MemberUser extends BaseEntity {

    private String mobile;

    private String muCode;

    private String password;

    private String nickName;

    private Boolean enabled;

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private Boolean credentialsNonExpired;

}
