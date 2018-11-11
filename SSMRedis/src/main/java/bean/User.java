package bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    10/20/2018
 * version: v1.0
 */
public class User implements Serializable {
    private String name;
    @Pattern(regexp = "\\d+", message = "密码必须是数字")
    @Length(min = 6, message = "密码长度不得小于6位")
    private String pwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
