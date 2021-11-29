package Controller;

public enum StudentCondition {
    ZAPISANY("Zapisany"), WYPISANY("Wypisany"), PRZEPISANY("przepisany"), OCZEKUJACY("Oczekujacy na zapisanie"), NIEZAPISANY("Niezapisany");

    public final String cond;

    StudentCondition(String condition){
        this.cond = condition;
    }

}
