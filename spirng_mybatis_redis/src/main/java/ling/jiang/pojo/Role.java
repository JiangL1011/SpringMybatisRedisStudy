package ling.jiang.pojo;

import java.io.Serializable;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018年11月08日
 * version: v1.0
 */
public class Role implements Serializable {
    private static final long serialVersionUID = -4874367156763987113L;

    private Long id;
    private String roleName;
    private String note;

    public Role() {
    }

    public Role(String roleName, String note) {
        this.roleName = roleName;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
