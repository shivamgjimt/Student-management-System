
import java.util.ArrayList;
import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();
    static int nextId = 1001;

    static class Student {
        int id;
        String roll, name, dept, semester, email, phone, address;
        double cgpa;

        Student(int id, String roll, String name, String dept,
                String semester, String email, String phone,
                String address, double cgpa) {
            this.id = id;
            this.roll = roll;
            this.name = name;
            this.dept = dept;
            this.semester = semester;
            this.email = email;
            this.phone = phone;
            this.address = address;
            this.cgpa = cgpa;
        }
    }

    public static void main(String[] args) {
        System.out.println("==================================");
        System.out.println("  STUDENT MANAGEMENT SYSTEM");
        System.out.println("==================================");

        boolean loggedIn = false;

        for (int i = 0; i < 3; i++) {
            System.out.print("Username: ");
            String user = sc.nextLine().trim();
            System.out.print("Password: ");
            String pass = sc.nextLine().trim();

            if (user.equals("admin") && pass.equals("admin123")) {
                loggedIn = true;
                break;
            }
            System.out.println("Invalid login. Try again.");
        }

        if (!loggedIn) {
            System.out.println("Login failed.");
            return;
        }

        System.out.println("Login successful!");

        while (true) {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Complete Student Report");
            System.out.println("7. Department-wise Report");
            System.out.println("8. Semester-wise Report");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid menu number.");
                continue;
            }

            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewStudents(students); break;
                case 3: searchStudent(); break;
                case 4: updateStudent(); break;
                case 5: deleteStudent(); break;
                case 6:
                    System.out.println("Total Students: " + students.size());
                    viewStudents(students);
                    break;
                case 7: departmentReport(); break;
                case 8: semesterReport(); break;
                case 9:
                    System.out.println("Thank you! Goodbye.");
                    return;
                default:
                    System.out.println("Choose a number from 1 to 9.");
            }
        }
    }

    static String required(String label) {
        while (true) {
            System.out.print(label);
            String value = sc.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("This field is required.");
        }
    }

    static Student find(String key) {
        for (Student s : students) {
            if (String.valueOf(s.id).equalsIgnoreCase(key)
                    || s.roll.equalsIgnoreCase(key))
                return s;
        }
        return null;
    }

    static String getRoll(Student current) {
        while (true) {
            String roll = required("Roll Number: ");
            boolean duplicate = false;
            for (Student s : students) {
                if (s != current && s.roll.equalsIgnoreCase(roll)) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) return roll;
            System.out.println("Duplicate roll number. Try another.");
        }
    }

    static String getEmail(Student current) {
        while (true) {
            String email = required("Email: ");
            if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                System.out.println("Enter a valid email address.");
                continue;
            }
            boolean duplicate = false;
            for (Student s : students) {
                if (s != current && s.email.equalsIgnoreCase(email)) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) return email;
            System.out.println("Email already exists.");
        }
    }

    static String getPhone() {
        while (true) {
            String phone = required("Phone (10 digits): ");
            if (phone.matches("[0-9]{10}")) return phone;
            System.out.println("Phone must contain exactly 10 digits.");
        }
    }

    static String getSemester() {
        while (true) {
            String sem = required("Semester (1-8): ");
            try {
                int n = Integer.parseInt(sem);
                if (n >= 1 && n <= 8) return sem;
            } catch (NumberFormatException ignored) {}
            System.out.println("Semester must be between 1 and 8.");
        }
    }

    static double getCgpa() {
        while (true) {
            System.out.print("CGPA (0-10): ");
            try {
                double value = Double.parseDouble(sc.nextLine().trim());
                if (Double.isFinite(value) && value >= 0 && value <= 10)
                    return value;
            } catch (NumberFormatException ignored) {}
            System.out.println("CGPA must be a number between 0 and 10.");
        }
    }

    static void addStudent() {
        System.out.println("\n--- ADD STUDENT ---");
        String roll = getRoll(null);
        String name = required("Name: ");
        String dept = required("Department: ");
        String sem = getSemester();
        String email = getEmail(null);
        String phone = getPhone();
        String address = required("Address: ");
        double cgpa = getCgpa();

        Student s = new Student(nextId++, roll, name, dept,
                sem, email, phone, address, cgpa);
        students.add(s);
        System.out.println("Student added successfully!");
        printStudent(s);
    }

    static void printStudent(Student s) {
        System.out.println("------------------------------");
        System.out.println("Student ID : " + s.id);
        System.out.println("Roll Number: " + s.roll);
        System.out.println("Name       : " + s.name);
        System.out.println("Department : " + s.dept);
        System.out.println("Semester   : " + s.semester);
        System.out.println("Email      : " + s.email);
        System.out.println("Phone      : " + s.phone);
        System.out.println("Address    : " + s.address);
        System.out.printf("CGPA       : %.2f%n", s.cgpa);
        System.out.println("------------------------------");
    }

    static void viewStudents(ArrayList<Student> list) {
        if (list.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.printf("%-6s %-12s %-18s %-12s %-5s %-25s %-12s %-5s%n",
                "ID", "Roll", "Name", "Department", "Sem",
                "Email", "Phone", "CGPA");

        for (Student s : list) {
            System.out.printf("%-6d %-12s %-18s %-12s %-5s %-25s %-12s %.2f%n",
                    s.id, s.roll, s.name, s.dept, s.semester,
                    s.email, s.phone, s.cgpa);
            System.out.println("Address: " + s.address);
        }
    }

    static void searchStudent() {
        String key = required("Enter Student ID or Roll Number: ");
        Student s = find(key);
        if (s == null) {
            boolean found = false;
            for (Student st : students) {
                if (st.name.toLowerCase().contains(key.toLowerCase())) {
                    printStudent(st);
                    found = true;
                }
            }
            if (!found) System.out.println("Student not found.");
        } else {
            printStudent(s);
        }
    }

    static void updateStudent() {
        String key = required("Enter ID or Roll Number to update: ");
        Student s = find(key);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Press Enter to keep an existing value.");
        String value;

        System.out.print("Roll [" + s.roll + "]: ");
        value = sc.nextLine().trim();
        if (!value.isEmpty()) {
            String old = s.roll;
            try {
                s.roll = value;
                for (Student other : students) {
                    if (other != s && other.roll.equalsIgnoreCase(value))
                        throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                s.roll = old;
                System.out.println("Duplicate roll; keeping old value.");
            }
        }

        System.out.print("Name [" + s.name + "]: ");
        value = sc.nextLine().trim();
        if (!value.isEmpty()) s.name = value;

        System.out.print("Department [" + s.dept + "]: ");
        value = sc.nextLine().trim();
        if (!value.isEmpty()) s.dept = value;

        System.out.print("Semester [" + s.semester + "] (1-8): ");
        value = sc.nextLine().trim();
        if (!value.isEmpty()) {
            try {
                int n = Integer.parseInt(value);
                if (n >= 1 && n <= 8) s.semester = value;
                else System.out.println("Invalid semester; keeping old value.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid semester; keeping old value.");
            }
        }

        System.out.print("Email [" + s.email + "]: ");
        value = sc.nextLine().trim();
        if (!value.isEmpty()) {
            if (value.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
                s.email = value;
            else System.out.println("Invalid email; keeping old value.");
        }

        System.out.print("Phone [" + s.phone + "]: ");
        value = sc.nextLine().trim();
        if (!value.isEmpty()) {
            if (value.matches("[0-9]{10}")) s.phone = value;
            else System.out.println("Invalid phone; keeping old value.");
        }

        System.out.print("Address [" + s.address + "]: ");
        value = sc.nextLine().trim();
        if (!value.isEmpty()) s.address = value;

        System.out.print("CGPA [" + s.cgpa + "] (0-10): ");
        value = sc.nextLine().trim();
        if (!value.isEmpty()) {
            try {
                double n = Double.parseDouble(value);
                if (Double.isFinite(n) && n >= 0 && n <= 10) s.cgpa = n;
                else System.out.println("Invalid CGPA; keeping old value.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid CGPA; keeping old value.");
            }
        }

        System.out.println("Student updated successfully!");
        printStudent(s);
    }

    static void deleteStudent() {
        String key = required("Enter ID or Roll Number to delete: ");
        Student s = find(key);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        printStudent(s);
        System.out.print("Confirm deletion? (yes/no): ");
        if (sc.nextLine().trim().equalsIgnoreCase("yes")) {
            students.remove(s);
            System.out.println("Student deleted.");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    static void departmentReport() {
        Map<String, ArrayList<Student>> groups = new LinkedHashMap<>();
        for (Student s : students)
            groups.computeIfAbsent(s.dept, k -> new ArrayList<>()).add(s);

        if (groups.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        for (String dept : groups.keySet()) {
            System.out.println("\nDepartment: " + dept
                    + " | Total: " + groups.get(dept).size());
            viewStudents(groups.get(dept));
        }
    }

    static void semesterReport() {
        Map<String, ArrayList<Student>> groups = new LinkedHashMap<>();
        for (Student s : students)
            groups.computeIfAbsent(s.semester, k -> new ArrayList<>()).add(s);

        if (groups.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        for (String sem : groups.keySet()) {
            System.out.println("\nSemester: " + sem
                    + " | Total: " + groups.get(sem).size());
            viewStudents(groups.get(sem));
        }
    }
}
