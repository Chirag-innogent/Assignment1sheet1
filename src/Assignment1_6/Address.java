package Assignment1_6;

public class Address {
    private int id;
    private int pinCode;
    private String city;
    private int studentId;

    public Address() {
    }

    public Address(int id, int pinCode, String city, int studentId) {
        this.id = id;
        this.pinCode = pinCode;
        this.city = city;
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", pinCode=" + pinCode +
                ", city='" + city + '\'' +
                ", studentId=" + studentId +
                '}';
    }
}
