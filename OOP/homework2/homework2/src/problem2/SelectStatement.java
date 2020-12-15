package problem2;

public class SelectStatement {
    private String select;
    private String from;
    private String where;
    private String groupBy;
    private String having;
    private String orderBy;

    public SelectStatement(String select, String from) {
        this.select = select;
        this.from = from;
    }

    public SelectStatement(String select, String from, String where) {
        this.select = select;
        this.from = from;
        this.where = where;
    }

    public SelectStatement(String select, String from, String groupBy, String where) {
        this.select = select;
        this.from = from;
        this.groupBy = groupBy;
        this.where = where;
    }

    public SelectStatement(String select, String from, String where, String groupBy, String having, String orderBy) {
        this.select = select;
        this.from = from;
        this.where = where;
        this.groupBy = groupBy;
        this.having = having;
        this.orderBy = orderBy;
    }
}
