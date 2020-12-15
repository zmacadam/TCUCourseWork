package problem2;

import org.junit.Test;

public class SelectStatementTest {

    @Test
    public void testSelectStatement() {
        SelectStatement s1 = new SelectStatement.SelectStatementBuilder("pnumber, pname, count(*)", "Project join Works_on on pnumber=pno")
                .setGroupBy("pnumber, pname").setHaving("count(*) > 2;").build();
        s1.print();
        SelectStatement s2 = new SelectStatement.SelectStatementBuilder("fname, lname, address", "Employee joins Department on dnumber=dno").setWhere("dname=\'research\';").build();
        s2.print();
        SelectStatement s3 = new SelectStatement.SelectStatementBuilder("fname, lname, address", "Employee").build();
        s3.print();
        SelectStatement s4 = new SelectStatement.SelectStatementBuilder("pnumber, pname, budget, count(*)", "Project join Works_on on pnumber=pno")
                .setWhere("budget > 200000").setGroupBy("pnumber, pname, budget").setHaving("count(*) > 2").setOrderBy("pnumber").build();
        s4.print();
    }
}
