package Controller;

public class CourseInfo {
    private Course course;
    private StudentCondition condition;
    private Float average;

    public CourseInfo(Course course, StudentCondition condition, Float average){
        this.course = course;
        this.condition = condition;
        this.average = average;
    }

    public void setAverage(Float average) {
        this.average = average;
    }

    public String getCourse() {
        return course.getName();
    }

    public StudentCondition getCondition() {
        return condition;
    }

    public Float getAverage() {
        return average;
    }

    public Course getCourseReal() {
        return course;
    }

    public void setCondition(StudentCondition condition) {
        this.condition = condition;
    }
}
