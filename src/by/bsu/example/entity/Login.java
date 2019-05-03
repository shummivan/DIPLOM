package by.bsu.example.entity;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Login extends Entity{

    private String login;
    private String password;
    private String email;
    private boolean isActive;
    private int roleId;
    private String roleName;
    private String firstName;
    private String lastName;
    private String subunit;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    private Date dateAdd;
    private Date lastLogin;

    public String getSubunit() {
        return subunit;
    }

    public void setSubunit(String subunit) {
        this.subunit = subunit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o==null) return false;
        if (this.getClass()!=o.getClass()) return false;
        if (!super.equals(o)) return false;

        Login login1 = (Login) o;

        if (isActive != login1.isActive) return false;
        if (getRoleId() != login1.getRoleId()) return false;
        if (getLogin() != null ? !getLogin().equals(login1.getLogin()) : login1.getLogin() != null) return false;
        if (getPassword() != null ? !getPassword().equals(login1.getPassword()) : login1.getPassword() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(login1.getEmail()) : login1.getEmail() != null) return false;
        if (getFirstName() != null ? !getFirstName().equals(login1.getFirstName()) : login1.getFirstName() != null)
            return false;
        return getLastName() != null ? getLastName().equals(login1.getLastName()) : login1.getLastName() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + getRoleId();
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        return result;
    }

    private String md5(String password){
        String result=null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes(), 0, password.length());
            result = new BigInteger(1, md5.digest()).toString(16);
        }catch(NoSuchAlgorithmException e2){

        }
        return result;
    }

    public void setLogin(String login){
        this.login=login;
    }
    public String getLogin(){
        return login;
    }
    public void setPassword(String password){

        this.password=password;
    }
    public void setMd5Password(String password){
        this.password=md5(password);
    }
    public String getPassword(){
        return password;
    }
    public void setEmail(String password){
        this.email=email;
    }
    public String getEmail(){
        return email;
    }
    public boolean getIsActive(){
        return isActive;
    }
    public void setIsActive(boolean isActive){
        this.isActive=isActive;
    }
    public void setRoleId(int roleId){
        this.roleId=roleId;
    }
    public int getRoleId(){
        return roleId;
    }
    public void setRoleName(String roleName){
        this.roleName=roleName;
    }
    public String getRoleName(){
        return roleName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setDateAdd(String dateStr) throws ParseException {

        DateFormat format=new SimpleDateFormat("yyyy-MM-dd H:m:s");
        this.dateAdd = format.parse(dateStr);

    }

    public void setDateAdd(Date date){
        this.dateAdd=date;
    }

    public Date getDateAdd(){
        return this.dateAdd;
    }

    public void setLastLogin(Date date){
        this.lastLogin=date;
    }
    public Date getLastLogin(){
        return this.lastLogin;
    }
    @Override
    public String toString() {
        return "Login{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                ", roleId=" + roleId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
