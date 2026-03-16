package Lab_7;

import java.util.ArrayList;

interface AcademicComponent {
    public String getTitle();

    public void setTitle(String title);

    public void display();

    public void add(AcademicComponent c);

    public void remove(AcademicComponent c);
}

class Course implements AcademicComponent {
    private String title;

    public Course(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void display() {
        System.out.println("Course:" + title);
    }

    @Override
    public void add(AcademicComponent c) {
        // do nothing
    }

    @Override
    public void remove(AcademicComponent c) {
        // do nothing
    }
}

class CourseModule implements AcademicComponent {

    private String title;

    private ArrayList<AcademicComponent> Courses;

    public CourseModule(String title) {
        this.title = title;
        Courses = new ArrayList<>();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void display() {
        System.out.println("Module:" + title);
        for (AcademicComponent c : Courses) {
            c.display();
        }
    }

    public void add(AcademicComponent c) {
        Courses.add(c);
    }

    public void remove(AcademicComponent c) {
        Courses.remove(c);
    }
}

public class Q1 {
    public static void main(String[] args) {
        // courses
        AcademicComponent introProg = new Course("Introduction to Programming");
        AcademicComponent dataStructures = new Course("Data Structures");
        AcademicComponent machineLearning = new Course("Machine Learning");
        AcademicComponent nlp = new Course("Natural Language Processing");
        AcademicComponent finalProject = new Course("Final Year Project");
        // Modules
        AcademicComponent programmingModule = new CourseModule("Programming Module");
        programmingModule.add(introProg);
        programmingModule.add(dataStructures);
        AcademicComponent aiModule = new CourseModule("AI Module");
        aiModule.add(machineLearning);
        aiModule.add(nlp);
        // Program root
        AcademicComponent csProgram = new CourseModule("Computer Science Program");
        csProgram.add(programmingModule);
        csProgram.add(aiModule);
        csProgram.add(finalProject);
        // display program structure
        csProgram.display();
    }
}
