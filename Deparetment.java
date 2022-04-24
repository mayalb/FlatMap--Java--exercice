import com.sun.javafx.collections.ArrayListenerHelper;

import java.util.ArrayList;
import java.util.List;

public class Deparetment {
    private String name;
    private String field;
    private List<Etudiant> etudiantList;

    public Deparetment(String name, String field, List<Etudiant> etudiantList) {
        this.name = name;
        this.field=field;
        this.etudiantList= etudiantList;
    }

    public String getName() {
        return name;
    }

    public String getField() {
        return field;
    }

    public List<Etudiant> getEtudiantList() {
        return etudiantList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setEtudiantList(List<Etudiant> etudiantList) {
        this.etudiantList = etudiantList;
    }
}
