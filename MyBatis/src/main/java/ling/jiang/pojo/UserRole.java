package ling.jiang.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    11/1/2018
 * version: v1.0
 */
public class UserRole implements Serializable {
    private Integer roleId;
    private String roleName;
    private List<User> users;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", users=" + users +
                '}';
    }

    public String toStringWithoutUsers() {
        return "UserRole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
