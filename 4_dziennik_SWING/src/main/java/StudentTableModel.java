import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;

public class StudentTableModel extends AbstractTableModel {

    private LinkedList<Student> students;
    private final static String[] COLUMNS = {"Name", "Surname", "Index", "Score", "Group"};;
    public StudentTableModel(LinkedList<Student> students) {
        this.students = students;
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "??";
        Student student = students.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = student.getName();
                break;
            case 1:
                value = student.getSurname();
                break;
            case 2:
                value = student.getIndex();
                break;
            case 3:
                value = student.getScore();
                break;
            case 4:
                value = student.getGroup();
                break;
        }
        return value;
    }

    @Override
    public String getColumnName(int col) {
        return COLUMNS[col];
    }

}
