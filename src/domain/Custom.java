package domain;

/**
 * @Author: Tod
 * @Description:
 * @Date: Created in 2019/11/27 20:13
 * @Version: 1.0
 */
public class Custom {
    private int id;
    private String name;
    private String gender;
    private String address;

    public Custom() {
    }

    public Custom(int id, String name, String gender, String address, String i_Level) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.address = address;
        I_Level = i_Level;
    }

    private String I_Level;

    @Override
    public String toString() {
        return "custom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", I_Level='" + I_Level + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getI_Level() {
        return I_Level;
    }

    public void setI_Level(String i_Level) {
        I_Level = i_Level;
    }
}
