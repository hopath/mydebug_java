package algorithms_java.class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code01_Comparator_ {
    static class Student{
        int age;
        String name;

        public Student(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        Student jj = new Student(100, "jj");
        Student dd = new Student(20, "dd");
        students.add(jj);
        students.add(dd);
        students.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                  return o1.age - o2.age;
            }
        });

        System.out.println(students.get(0) + " " +students.get(1));

        TreeMap<Student, String> sTM = new TreeMap<>((a, b) -> b.age - a.age);
        sTM.put(jj, "k");
        sTM.put(dd, "s");

        System.out.println("==============================");
        for(Student s:sTM.keySet()){
            System.out.print(s.age + "   ");
        }

    }
}
