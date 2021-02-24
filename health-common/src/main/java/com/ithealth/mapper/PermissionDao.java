package com.ithealth.mapper;

import com.ithealth.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;
@Mapper
public interface PermissionDao {
    /**
     * 根据角色信息查询权限信息
     * @param id
     * @return
     */
    Set<Permission> queryPremissionByRoleID(Integer id);
}
