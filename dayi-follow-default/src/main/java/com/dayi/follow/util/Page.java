package com.dayi.follow.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

/**
 * 
* @ClassName: Page
* @Description: 与具体ORM实现无关的分页参数及查询结果封装. 
* @date 2013-8-12 上午9:21:26
* @param <T> Page中记录的类型.
 */
public class Page<T> {
	// -- 公共变量 --//
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	public static final int DEFAULT_PAGE_SIZE = 10;
	// -- 分页参数 --//
	protected int pageNo = 1;
	protected int pageSize = DEFAULT_PAGE_SIZE;
	protected String orderByField = "";      //排序字段，使用自定义属性过滤器的查询方式时使用
	protected String orderByDirection = "";  //排序方向，使用自定义属性过滤器的查询方式时使用
	protected boolean autoCount = true;
    @JsonIgnore
    protected OrderBy orderBy;//排序条件,配合Criterion查询方法使用
	protected String[] groupBy;//分组条件


	// -- 返回结果 --//
	protected List<T> items = new ArrayList<T>();
	protected long totalCount = 0;

	public Page() {
	}

	public Page(int pageSize) {
		this.pageSize = pageSize;
	}

	// -- 访问查询参数函数 --//
	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	public Page<T> pageNo(final int thePageNo) {
		setPageNo(thePageNo);
		return this;
	}

	/**
	 * 获得每页的记录数量,默认为1.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的记录数量,低于1时自动调整为1.
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}

	public Page<T> pageSize(final int thePageSize) {
		setPageSize(thePageSize);
		return this;
	}

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从0开始.
	 */
	public int getFirst() {
		return (pageNo - 1) * pageSize;
	}

	public int getShowSize(){
		if(null == items){
			return getFirst();
		}
		return getFirst() + items.size();
	}

	/**
	 * 获得排序字段,无默认值.多个排序字段时用','分隔.
	 */
	public String getOrderByField() {
		return orderByField;
	}

	/**
	 * 设置排序字段,多个排序字段时用','分隔.
	 */
	public void setOrderByField(final String orderByField) {
		this.orderByField = orderByField;
	}

	public Page<T> orderByField(final String theOrderBy) {
		setOrderByField(theOrderBy);
		return this;
	}

	/**
	 * 获得排序方向.
	 */
	public String getOrderByDirection() {
		return orderByDirection;
	}

	/**
	 * 设置排序方式向.
	 * 
	 * @param sc 可选值为desc或asc,多个排序字段时用','分隔.
	 */
    public void setOrderByDirection(final String orderByDirection) {
        // 检查order字符串的合法值
        String[] orders = StringUtils.split(StringUtils.lowerCase(orderByDirection), ',');
        for (String orderStr : orders) {
            if (!StringUtils.equals(DESC, orderStr)
                    && !StringUtils.equals(ASC, orderStr)) {
                throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
            }
        }
        this.orderByDirection = StringUtils.lowerCase(orderByDirection);
    }

	public Page<T> direction(final String theOrder) {
		setOrderByDirection(theOrder);
		return this;
	}

	/**
	 * 是否已设置排序字段,无默认值.
	 */
	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(orderByField) && StringUtils
				.isNotBlank(orderByDirection));
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数, 默认为true.
	 */
	public boolean isAutoCount() {
		return autoCount;
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数.
	 */
	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	public Page<T> autoCount(final boolean theAutoCount) {
		setAutoCount(theAutoCount);
		return this;
	}

	// -- 访问查询结果函数 --//

	/**
	 * 取得页内的记录列表.
	 */
	public List<T> getItems() {
		return items;
	}

	/**
	 * 设置页内的记录列表.
	 */
	public void setItems(final List<T> items) {
		this.items = items;
	}

	/**
	 * 取得总记录数, 默认值为0.
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置总记录数.
	 */
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 根据pageSize与totalCount计算总页数, 默认值为0.
	 */
	public long getTotalPages() {
		if (totalCount < 0) {
			return 0;
		}

		long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * 取得下页的页号, 序号从1开始. 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (isHasNext()) {
			return pageNo + 1;
		} else {
			return pageNo;
		}
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * 取得上页的页号, 序号从1开始. 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {
		if (isHasPre()) {
			return pageNo - 1;
		} else {
			return pageNo;
		}
	}

    public OrderBy getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
    }

	public String[] getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String[] groupBy) {
		this.groupBy = groupBy;
	}
}
