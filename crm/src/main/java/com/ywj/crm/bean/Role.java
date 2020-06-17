package com.ywj.crm.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private Integer flag;


/*    	//targetEntity：目标类
        @OneToMany(targetEntity = User.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
        //name：外键字段名称  referencedColumnName:参考的主表主键字段名称
        @JoinColumn(name = "role_id",referencedColumnName = "id")*/
    //role :User中的属性，属性映射数据库字段 放弃外键维护
    //fetch 默认懒加载，eager:表示立马加载,fetch = FetchType.EAGER
    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<User> users;


    public Role() {
    }

    public Role(Integer id) {
        super();
        this.id = id;
    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", name=" + name + ", description=" + description + ", flag=" + flag + "}";
    }

}
