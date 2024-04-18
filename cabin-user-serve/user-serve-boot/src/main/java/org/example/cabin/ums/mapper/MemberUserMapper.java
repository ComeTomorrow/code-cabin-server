package org.example.cabin.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;
import org.example.cabin.ums.model.entity.MemberUser;

import java.util.List;

@Mapper
public interface MemberUserMapper extends BaseMapper<MemberUser> {

    @Select("<script>" +
            " SELECT * from ums_member " +
            " <if test ='nickname !=null and nickname.trim() neq \"\" ' >" +
            "       WHERE nick_name like concat('%',#{nickname},'%')" +
            " </if>" +
            " ORDER BY update_time DESC, create_time DESC" +
            "</script>")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "addressList", column = "id", many = @Many(select = "com.youlai.mall.ums.mapper.UmsAddressMapper.listByUserId"))
    })
    List<MemberUser> list(Page<MemberUser> page, String nickname);


}
