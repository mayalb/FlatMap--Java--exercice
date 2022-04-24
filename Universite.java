import java.util.*;
import java.util.stream.Collectors;

public class Universite {
    private String name;
    private List <Deparetment> listDepartement;

    public Universite(String name, List<Deparetment> listDep) {
        this.name = name;
        this.listDepartement= listDep;
    }

    public String getName() {
        return name;
    }

    public List<Deparetment> getListDepartement() {
        return this.listDepartement;
    }

    public void setName(String name) {
        this.name = name;
    }
     List<Etudiant> getStudents(Universite university, Integer Parameter){
        List<Deparetment> listDepartement = university.getListDepartement();
        List<Etudiant> listOfAllSudents= listDepartement.stream().flatMap(d-> {
            List<Etudiant> student= d.getEtudiantList().stream().filter(s-> s.getAge() >Parameter).collect(Collectors.toList());
            return student.stream();
        }).collect(Collectors.toList());
        return listOfAllSudents;
    }
    int getMinAge(Universite universite){
        Integer min= Integer.MAX_VALUE;
        Integer ageS1=0, res=0;
        Integer ageS2=0;
        List<Deparetment> listDepartement = universite.getListDepartement();
        List<Etudiant> listOfAllSudents= listDepartement.stream().flatMap(d-> {
            List<Etudiant> student= d.getEtudiantList().stream().collect(Collectors.toList());
            return student.stream();
        }).collect(Collectors.toList());
        boolean empty= listOfAllSudents.isEmpty();
        if(empty==false){
            // trier la liste
            listOfAllSudents.sort(
                    Comparator.comparing(Etudiant::getAge)
            );
            ageS1= listOfAllSudents.get(0).getAge();
            for(int i=1; i< listOfAllSudents.size();i++){
                ageS2=listOfAllSudents.get(i).getAge();
                if(ageS1>ageS2){
                    res=ageS1-ageS2;
                }else{
                    if(ageS1==ageS2){
                        min=0;
                    }else{
                        res=ageS2-ageS1;
                    }
                }
                if(res<min){
                    min=res;
                }
                ageS1=listOfAllSudents.get(i).getAge();
            }
        }else{
            System.out.println("liste est vide ");
            min= 0;
        }
        return min;
    }
    public static void main(String[] args) {
        // Create students
        Etudiant etudiant1= new Etudiant("Maya",23);
        Etudiant etudiant2= new Etudiant("Kaou", 1);
        List <Etudiant> listEtudiant1 = Arrays.asList(etudiant1,etudiant2);
        Etudiant etudiant3= new Etudiant("Tina",32);
        Etudiant etudiant4= new Etudiant("Hawa", 28);
        List <Etudiant> listEtudiant2 = Arrays.asList(etudiant3,etudiant4);

        // Create Departements:
        Deparetment deparetment1= new Deparetment("SIL","Computer Science",listEtudiant1);
        Deparetment deparetment2= new Deparetment("SIQ","Computer Science",listEtudiant2);
        List <Deparetment> listDepartement = Arrays.asList(deparetment1,deparetment2);

        //Create university:
        Universite universite= new Universite("ESI",listDepartement);
        // Get ecart le plus petit des ages des etudiants d'une université:
        int min=universite.getMinAge(universite);
        System.out.println("L'ecart age le plus petit est "+ min);
        // get age introduit par les étudiants
        Scanner scanner = new Scanner(System.in);
        Integer parameter;
        try{
            System.out.println(" Veuillez introduire l'age Minimum ");
             parameter= scanner.nextInt();
             if(parameter>0){
                 List<Etudiant> listOfAllSudents= universite.getStudents(universite,parameter);
                 boolean empty= listOfAllSudents.isEmpty();
                 if(empty==false){
                     System.out.println(" Liste des étudiants qui ont plus de "+ parameter+ "ans :");
                     listOfAllSudents.forEach(s->System.out.println("Etudiant: "+s.getName()+", Age: "+s.getAge()));
                 }else{
                     System.out.println("Aucun étudiant");
                 }
             }else{
                 System.out.println("Veuillez saisir un entier positif..");
             }
        }catch (InputMismatchException exception){
            System.out.println("Veuillez saisir un entier..");
            System.exit(0);
        }

    }
}
