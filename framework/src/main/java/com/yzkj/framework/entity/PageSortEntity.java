package com.yzkj.framework.entity;

public class PageSortEntity implements java.io.Serializable {

    public enum OrderBy {ASC, DESC}

    public static class Order {
        private String column;
        private OrderBy orderBy;
        public Order(String column, OrderBy orderBy) {
            this.column = column;
            this.orderBy = orderBy;
        }

        public String getColumn() {return this.column;}
        public OrderBy getOrderBy() {return this.orderBy;}
    }
}
