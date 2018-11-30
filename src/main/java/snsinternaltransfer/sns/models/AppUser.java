package snsinternaltransfer.sns.models;

public class AppUser {

    private Long userId;
    private String userName;
    private String encrytedPassword;
    private int department;

    public AppUser() {

    }

    public AppUser(Long userId, String userName, String encrytedPassword, int department) {
        this.userId = userId;
        this.userName = userName;
        this.encrytedPassword = encrytedPassword;
        this.department = department;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncrytedPassword() {
        return encrytedPassword;
    }

    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }

    @Override
    public String toString() {
        return this.userName;
    }

}