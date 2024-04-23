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

    @TableId(type = IdType.AUTO)
    private Long id;

    private Integer gender;

    private String nickName;

    private String mobile;

    private String password;

    private LocalDate birthday;

    private String avatarUrl;

    private String openid;

    private String sessionKey;

    private String city;

    private String country;

    private String language;

    private String province;

    private Boolean status;

    private Long balance;

    @TableLogic(delval = "1", value = "0")
    private Integer deleted;

    private Integer point;

}
