package com.dayi.follow.model;

import com.dayi.common.util.NameItem;
import com.dayi.common.util.NameItems;

import java.io.Serializable;
import java.util.List;


/**
 * @author xiell
 * @date 2018/11/23
 */
public class Menu implements Serializable, Comparable<Menu> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    private String id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父菜单id
     */
    private String parentId;

    /**
     * 菜单url
     */
    private String url;
    /**
     * 菜单类型（0=非叶子节点，1=叶子节点）
     */

    private int type;

    /**
     * 菜单状态
     */
    private Integer status;

    /**
     * 菜单顺序
     */
    private Integer order;

    /**
     * 子菜单
     */
    private List<Menu> childMenus;
    /**
     * 菜单类型 - 非叶子节点
     */
    public final static NameItem TYPE_NONLEAF_NODE = NameItem.valueOf("非叶子节点", 0);
    /**
     * 菜单类型 - 叶子节点
     */
    public final static NameItem TYPE_LEAF_NODE = NameItem.valueOf("叶子节点", 1);
    /**
     * 显示状态 - 全部状态
     */
    public final static NameItems TYPE_ALL = NameItems.valueOf(TYPE_NONLEAF_NODE, TYPE_LEAF_NODE);

    private Menu() {
    }

    public Menu(String id, String name, String parentId, String url, Integer status, Integer order) {
        if (order == null) {
            order = 0;
        }
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.url = url;
        this.status = status;
        this.order = order;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<Menu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<Menu> childMenus) {
        this.childMenus = childMenus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public Menu setType(int type) {
        this.type = type;
        return this;
    }

    @Override
    public int compareTo(Menu o) {
        if (o == null || o.getOrder() == null) {
            return -1;
        }
        return this.getOrder() - o.getOrder();
    }
}
