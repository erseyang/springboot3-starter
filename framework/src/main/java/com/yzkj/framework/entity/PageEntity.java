package com.yzkj.framework.entity;

import com.yzkj.framework.exception.YzkjException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 * 分页对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageEntity<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 7336164510319813697L;

    /**
     * 每页记录数
     */
    private Long pageSize;

    /**
     * 当前页数
     */
    private Long pageNo;

    /**
     * 总记录数
     */
    private Long totalCount;

    /**
     * 总页数
     */
    private Long totalPage;

    /**
     * 排序规则
     */
    private List<PageSortEntity.Order> sortOrders;

    /**
     * 数据
     */
    private List<T> data;

    public PageEntity(Long pageNo, Long pageSize) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
    }

    public PageEntity(Long pageNo, Long pageSize,Long totalCount,Long totalPage, List<T> data) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.data = data;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
    }

    public boolean hasNextPage() {
        return true;
    }

    public boolean hasPreviousPage() {
        return pageNo > 1;
    }

    /**
     * 获取总页数
     * @return Long
     */
    public Long getTotalPage() {
        return ((totalCount % pageSize == 0) ? totalCount / pageSize : totalCount / pageSize + 1);
    }

    public static <T> Comparator<T> createComparator(List<PageSortEntity.Order> orders) {
        Comparator<T> comparator = null;
        for (PageSortEntity.Order order : orders) {
            Comparator sortComparator = Comparator.comparing(o -> (Comparable) getFieldValue(o, order.getColumn()));
            if (order.getOrderBy() == PageSortEntity.OrderBy.DESC) {
                sortComparator = sortComparator.reversed();
            }
            comparator = comparator == null ? sortComparator : comparator.thenComparing(sortComparator);
        }

        return comparator;
    }

    private static Object getFieldValue(Object obj, String fieldName) {
        try{
            var field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            throw new YzkjException(e);
        }
    }
}
