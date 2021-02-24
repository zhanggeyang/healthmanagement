package com.ithealth.mapper;

import com.ithealth.pojo.Menu;
import com.ithealth.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;
@Mapper
public interface RoleDao {
    /**
     * 根据用户id查询角色信息
     * @param id
     * @return
     */
    Set<Role> queryRolesByUID(Integer id);

    List<Menu> getMenusByUsername(String username);

    List<Menu> getChildById(Integer id);
}
