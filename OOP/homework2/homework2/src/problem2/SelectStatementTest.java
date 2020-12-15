package problem2;

public class SelectStatementTest {
    SelectStatement select1 = new SelectStatement("pnumber, pname, count(*)", "Project join Works_on on pnumber=pno", "pnumber, pname", "count(*) > 2;");
    SelectStatement select2 = new SelectStatement("fname, lname, address", "Employee join Department on dnumber=dno", "dname='research';");
    SelectStatement select3 = new SelectStatement("fname, lname, address", "Employee;");
    SelectStatement select4 = new SelectStatement("pnumber, pname, budget, count(*)", "Project join Works_on on pnumber=pno ", "budget > 200000", "pnumber, pname, budget", "count(*) > 2", "pnumber;");
}
