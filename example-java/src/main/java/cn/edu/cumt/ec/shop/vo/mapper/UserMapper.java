package cn.edu.cumt.ec.shop.vo.mapper;

import cn.edu.cumt.ec.shop.entity.User;
import cn.edu.cumt.ec.shop.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class);
    @Mappings({@Mapping(source = "phoneNumber", target = "phoneNumber")})
    UserVo userToUserVo(User user);
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    List<UserVo> userToUserVo(List<User> users);
}
