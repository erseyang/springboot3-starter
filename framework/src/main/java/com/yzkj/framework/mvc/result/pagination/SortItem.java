package com.yzkj.framework.mvc.result.pagination;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "排序的属性")
public class SortItem implements Serializable {

    private String column;

    private boolean ascending = true;

    public static SortItem asc(String column) {
        return build(column, true);
    }

    public static SortItem desc(String column) {
        return build(column, false);
    }

    public static List<SortItem> ascs(String... columns) {
        return (List<SortItem>) Arrays.stream(columns).map(SortItem::asc).collect(Collectors.toList());
    }

    public static List<SortItem> descs(String... columns) {
        return (List<SortItem>) Arrays.stream(columns).map(SortItem::desc).collect(Collectors.toList());
    }

    private static SortItem build(String column, boolean ascending) {
        SortItem orderColumn = new SortItem();
        orderColumn.column = column;
        orderColumn.ascending = ascending;
        return orderColumn;
    }

}
