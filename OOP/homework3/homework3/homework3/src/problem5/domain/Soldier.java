package problem5.domain;

public class Soldier {
    private String name;
    private String rank;
    private int age;

    public Soldier() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "name='" + name + '\'' +
                ", rank='" + rank + '\'' +
                ", age=" + age +
                '}';
    }
}
