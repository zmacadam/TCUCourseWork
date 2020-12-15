package problem2;

public class SelectStatement {
    private String select;
    private String from;
    private String where;
    private String groupBy;
    private String having;
    private String orderBy;

    public SelectStatement(SelectStatementBuilder builder) {
        this.select = builder.select;
        this.from = builder.from;
        this.where = builder.where;
        this.groupBy = builder.groupBy;
        this.having = builder.having;
        this.orderBy = builder.orderBy;
    }

    public void print() {
        System.out.println(select + from + where + groupBy + having + orderBy);
    }

    public static class SelectStatementBuilder {
        private String select = "";
        private String from = "";
        private String where = "";
        private String groupBy = "";
        private String having = "";
        private String orderBy = "";

        public SelectStatementBuilder(String select, String from) {
            this.select = "SELECT\t\t" + select + "\n";
            this.from = "FROM\t\t" + from  + "\n";
        }

        public SelectStatementBuilder setWhere(String where) {
            this.where = "WHERE\t\t" + where  + "\n";
            return this;
        }
        public SelectStatementBuilder setGroupBy(String groupBy) {
            this.groupBy = "GROUP BY\t" + groupBy  + "\n";
            return this;
        }
        public SelectStatementBuilder setHaving(String having) {
            this.having = "HAVING\t\t" + having  + "\n";
            return this;
        }
        public SelectStatementBuilder setOrderBy(String orderBy) {
            this.orderBy = "ORDER BY\t" + orderBy  + "\n";
            return this;
        }

        public SelectStatement build() {
            return new SelectStatement(this);
        }
    }
}
