package org.example.cabin.ums.model.vo;

import lombok.Data;

/**
 * 会员视图层对象
 *
 * @author ComeTomorrow
 * @since 2024/4/10
 */
//@Schema(description = "会员视图层对象")
@Data
public class MemberVO {

//    @Schema(description="会员ID")
    private Long id;

//    @Schema(description="会员昵称")
    private String nickName;

//    @Schema(description="会员头像地址")
    private String avatarUrl;

//    @Schema(description="会员手机号")
    private String mobile;

}
