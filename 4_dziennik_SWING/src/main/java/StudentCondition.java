public enum StudentCondition {
    MAKING_UP("making up"), SICK("sick"), ABSENT("absent"), PRESENT("present");

    public final String cond;

    StudentCondition(String condition){
        this.cond = condition;
    }

}
