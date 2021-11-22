import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ClassGroupTableModel extends AbstractTableModel {

    private List<ClassGroup> classGroupList;
    private final String[] headers = {"Name", "Student limit"};

    public ClassGroupTableModel(List<ClassGroup> classGroupList){
        this.classGroupList = classGroupList;
    }

    @Override
    public int getRowCount() {
        return classGroupList.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int col){
        return headers[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "??";
        ClassGroup classGroup = classGroupList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = classGroup.getName();
                break;
            case 1:
                value = classGroup.getStudentLimit();
                break;
        }
        return value;
    }
}
