import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        HashMap<String, Student> students = new HashMap<>();

        // ====================== TASK 1 ======================
        // TODO: Добавь минимум 5 студентов (ключ = ID)
        // Сделай минимум два студента с одинаковым GPA (для Task 3)
        students.put("101", new Student("Aisuluu", 3.5, 18));
        students.put("102", new Student("Bek", 3.8, 19));
        students.put("103", new Student("Chyngyz", 3.5, 20)); // Same GPA as Aisuluu
        students.put("104", new Student("Dastan", 4.0, 19));
        students.put("105", new Student("Eliza", 3.2, 18));
        students.put("106", new Student("Farida", 3.8, 21)); // Same GPA as Bek

        // TODO: Напечатай всех студентов (ID + объект)
        System.out.println("=== All Students ===");
        for (String id : students.keySet()) {
            System.out.println("ID: " + id + " -> " + students.get(id));
        }

        // TODO: Найди студента по ID и выведи его
        System.out.println("\n=== Find Student by ID (102) ===");
        System.out.println(students.get("102"));

        // TODO: Удали одного студента по ID
        students.remove("105"); // Remove Eliza

        // TODO: Обнови GPA у одного студента
        students.get("101").setGpa(3.6); // Update Aisuluu's GPA

        // ====================== SORTING (IMPORTANT) ======================
        // TODO: Создай ArrayList из всех студентов (students.values())
        ArrayList<Student> studentList = new ArrayList<>(students.values());

        // TODO 6a: Отсортируй по GPA (natural ordering) и выведи
        Collections.sort(studentList);
        System.out.println("\n=== Sorted by GPA (ascending) ===");
        for (Student s : studentList) {
            System.out.println(s);
        }

        // TODO 6b: Отсортируй по имени (Comparator) и выведи
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getName().compareTo(s2.getName());
            }
        });
        System.out.println("\n=== Sorted by Name ===");
        for (Student s : studentList) {
            System.out.println(s);
        }

        // ====================== TASK 2 ======================
        System.out.println("\n=== Task 2: Top 3 by GPA ===");
        // TODO: Создай новый список, отсортируй по GPA по убыванию, выведи первые 3
        ArrayList<Student> topStudents = new ArrayList<>(students.values());
        Collections.sort(topStudents, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                if (s1.getGpa() < s2.getGpa()) return 1;
                else if (s1.getGpa() > s2.getGpa()) return -1;
                return 0;
            }
        });
        for (int i = 0; i < 3 && i < topStudents.size(); i++) {
            System.out.println(topStudents.get(i));
        }

        // ====================== TASK 3 ======================
        System.out.println("\n=== Task 3: Students with same GPA ===");
        // TODO: Сгруппируй студентов по GPA и выведи только те, где больше 1 студента
        HashMap<Double, List<String>> gpaMap = new HashMap<>();
        for (Student s : students.values()) {
            if (!gpaMap.containsKey(s.getGpa())) {
                gpaMap.put(s.getGpa(), new ArrayList<>());
            }
            gpaMap.get(s.getGpa()).add(s.getName());
        }
        for (Double gpa : gpaMap.keySet()) {
            if (gpaMap.get(gpa).size() > 1) {
                System.out.print("GPA " + gpa + " -> ");
                List<String> names = gpaMap.get(gpa);
                for (int i = 0; i < names.size(); i++) {
                    System.out.print(names.get(i));
                    if (i < names.size() - 1) System.out.print(", ");
                }
                System.out.println();
            }
        }

        // ====================== TASK 4 ======================
        System.out.println("\n=== Task 4: Courses ===");
        HashMap<Course, List<Student>> courseMap = new HashMap<>();
        // TODO: Создай 2–3 курса, добавь студентов, выведи всё
        Course javaCourse = new Course("Java Programming");
        Course mathCourse = new Course("Mathematics");

        List<Student> javaStudents = new ArrayList<>();
        if (students.containsKey("101")) javaStudents.add(students.get("101"));
        if (students.containsKey("102")) javaStudents.add(students.get("102"));
        
        List<Student> mathStudents = new ArrayList<>();
        if (students.containsKey("103")) mathStudents.add(students.get("103"));
        if (students.containsKey("104")) mathStudents.add(students.get("104"));

        courseMap.put(javaCourse, javaStudents);
        courseMap.put(mathCourse, mathStudents);

        for (Course c : courseMap.keySet()) {
            System.out.println(c + " -> " + courseMap.get(c));
        }

        // ====================== TASK 5 ======================
        System.out.println("\n=== Task 5: GPA desc + Name ===");
        // TODO: Создай Comparator (GPA убывание → если равно, то имя возрастание) и отсортируй
        ArrayList<Student> task5List = new ArrayList<>(students.values());
        Collections.sort(task5List, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                if (s1.getGpa() < s2.getGpa()) return 1;
                else if (s1.getGpa() > s2.getGpa()) return -1;
                else return s1.getName().compareTo(s2.getName());
            }
        });
        for (Student s : task5List) {
            System.out.println(s);
        }
    }
}
