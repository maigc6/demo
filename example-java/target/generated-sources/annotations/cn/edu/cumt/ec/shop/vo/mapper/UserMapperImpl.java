package cn.edu.cumt.ec.shop.vo.mapper;

import cn.edu.cumt.ec.shop.entity.User;
import cn.edu.cumt.ec.shop.vo.UserVo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-10T21:45:35+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_333 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserVo userToUserVo(User user) {
        if ( user == null ) {
            return null;
        }

        UserVo userVo = new UserVo();

        userVo.setPhoneNumber( user.getPhoneNumber() );
        userVo.setUserId( user.getUserId() );
        userVo.setEmail( user.getEmail() );
        userVo.setUserName( user.getUserName() );
        userVo.setPwd( user.getPwd() );
        userVo.setCreatorId( user.getCreatorId() );
        userVo.setCreatorName( user.getCreatorName() );
        userVo.setCreatedDatetime( user.getCreatedDatetime() );
        userVo.setModifierId( user.getModifierId() );
        userVo.setModifierName( user.getModifierName() );
        userVo.setUpdatedDatetime( user.getUpdatedDatetime() );
        userVo.setDeleted( user.getDeleted() );

        return userVo;
    }

    @Override
    public List<UserVo> userToUserVo(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserVo> list = new ArrayList<UserVo>( users.size() );
        for ( User user : users ) {
            list.add( userToUserVo( user ) );
        }

        return list;
    }
}
