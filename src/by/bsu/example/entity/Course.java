package by.bsu.example.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

public class Course extends Entity {
    private String name;
    private Integer teacherId;
    private Date dateAdd;

    private Integer studentsCount;
    private String teacherFirstName;
    private String teacherLastName;
    private boolean subscribe;
    private Integer mark;
    private String note;

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Course(){
        super();
    }

    public Course(String name, int teacherId){
        this.name=name;
        this.teacherId=teacherId;
        this.dateAdd=new Date();
        this.studentsCount=0;
        this.subscribe=false;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public boolean isSubscribe() {
        return subscribe;
    }

    public boolean getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(boolean subscribe) {
        this.subscribe = subscribe;
    }

    public String getName(){
        return this.name;
    };

    public void setName(String name){
        this.name=name;
    }

    public Integer getStudentsCount(){
        return this.studentsCount;
    };

    public void setStudentsCount(Integer count){
        if(count==null){
            this.studentsCount=0;
        }
        this.studentsCount=count;
    }

    public void setTeacherId(int id){
        this.teacherId=id;
    }

    public int getTeacherId(){
        return this.teacherId;
    }

    public void setTeacherFirstName(String firstName){
        this.teacherFirstName=firstName;
    }

    public String getTeacherFirstName(){
        return this.teacherFirstName;
    }

    public void setTeacherLastName(String lastName){
        this.teacherLastName=lastName;
    }

    public String getTeacherLastName(){
        return this.teacherLastName;
    }

    public void setDateAdd(String dateStr) throws ParseException{

        DateFormat format=new SimpleDateFormat("yyyy-mm-dd");
        this.dateAdd = format.parse(dateStr);

    }

    public void setDateAdd(Date date){
        this.dateAdd=date;
    }

    public Date getDateAdd(){
        return this.dateAdd;
    }

    public String getDateAddStr(){
        Formatter formatter=new Formatter();
        formatter.format("%tF",this.dateAdd);
        return formatter.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o==null) return false;
        if (this.getClass()!=o.getClass()) return false;
        if (!super.equals(o)) return false;

        Course course = (Course) o;

        if (getTeacherId() != course.getTeacherId()) return false;
        if (getName() != null ? !getName().equals(course.getName()) : course.getName() != null) return false;
        return getDateAdd() != null ? getDateAdd().equals(course.getDateAdd()) : course.getDateAdd() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getTeacherId();
        result = 31 * result + (getDateAdd() != null ? getDateAdd().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", teacherId=" + teacherId +
                ", dateAdd=" + dateAdd +
                '}';
    }
}
