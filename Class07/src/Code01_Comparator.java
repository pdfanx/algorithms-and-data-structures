package class07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class Code01_Comparator {
    public static void main(String[] args) {
        Student student01 = new Student("A",3,24);
        Student student02 = new Student("B",2,21);
        Student student03 = new Student("C",10,22);

        Student[] students = new Student[] {student01, student02, student03};

        // 比较器

        // 1.实现Comparator<Student>接口
        // Arrays.sort(students,new IDjiangOrder());

        // 2.匿名内部类
        Arrays.sort(students,new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.id-o2.id;
            }
        });

        // 3.Lambda表达式
        Arrays.sort(students, (o1, o2) -> o1.age-o2.age);
        for (Student student : students) {
            System.out.println(student.name);
        }


        // 有序表
        TreeMap<Student,String> treeMap = new TreeMap<>((o1, o2) -> o1.age-o2.age);
        treeMap.put(student01,"A");
        treeMap.put(student02,"B");
        treeMap.put(student03,"C");
    }

    public static class Student
    {
        public String name;
        public int id;
        public int age;

        public Student(String name, int id, int age)
        {
            this.name = name;
            this.id = id;
            this.age = age;
        }
    }

    // 如何比较器
    // compare方法中，遵循一个统一的规范：
    // 返回负数时候，认为第一个参数应该排在前面
    // 返回正数时，认为第二个数应该排在前面
    // 返回0时，认为无所谓谁放在前面
    public static class AgeShengOrder implements Comparator<Student>
    {
        @Override
        public int compare(Student s1, Student s2)
        {
            return s1.age - s2.age;
        }
    }

    public static class IDjiangOrder implements Comparator<Student>
    {
        @Override
        public int compare(Student s1, Student s2)
        {
            return s1.id - s2.id;
        }
    }
}


