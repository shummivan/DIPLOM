package by.bsu.example.entity;


import java.util.Objects;

public class Journal extends Entity {
    private Integer id;
    private Integer studentId;
    private Integer mark;
    private String note;
    private Integer miss;
    private String mdata;
    private Integer courseId;
    private String subunit;
    private String studentcource;

    public Journal(){
        super();
    }

    public Journal(String mdata, int courseId)
    {
        this.mdata = mdata;
        this.courseId = courseId;
    }

    public String getStudentcource() {
        return studentcource;
    }

    public void setStudentcource(String studentcource) {
        this.studentcource = studentcource;
    }

    public String getSubunit() {
        return subunit;
    }

    public void setSubunit(String subunit) {
        this.subunit = subunit;
    }

    private String studentLastName;
    private String studentFirstName;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getMdata() {
        return mdata;
    }

    public void setMdata(String mdata) {
        this.mdata = mdata;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {

        return studentLastName;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public Integer getMiss() {
        return miss;
    }

    public void setMiss(Integer miss) {
        this.miss = miss;
    }

    public void setStudentLastName(String studentLastName) {

        this.studentLastName = studentLastName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "studentId=" + studentId +
                ", mark=" + mark +
                ", note='" + note + '\'' +
                ", miss=" + miss +
                ", mdata='" + mdata + '\'' +
                ", courseId=" + courseId +
                ", studentLastName='" + studentLastName + '\'' +
                ", studentFirstName='" + studentFirstName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Journal journal = (Journal) o;
        return Objects.equals(studentId, journal.studentId) &&
                Objects.equals(mark, journal.mark) &&
                Objects.equals(note, journal.note) &&
                Objects.equals(miss, journal.miss) &&
                Objects.equals(mdata, journal.mdata) &&
                Objects.equals(courseId, journal.courseId) &&
                Objects.equals(studentLastName, journal.studentLastName) &&
                Objects.equals(studentFirstName, journal.studentFirstName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), studentId, mark, note, miss, mdata, courseId, studentLastName, studentFirstName);
    }
}
