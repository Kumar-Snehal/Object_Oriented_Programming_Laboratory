package Lab_7;

import java.util.ArrayList;

interface Course {
    public String getTitle();

    public void setTitle(String title);

    public void Display();

    public void addCourse(Course c);

    public void removeCourse(Course c);
}

class simpleCourse implements Course {
    private String title;

    public simpleCourse(String title) {
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
    public void Display() {
        System.out.println("Course:" + title);
    }

    @Override
    public void addCourse(Course c) {
        // do nothing
    }

    @Override
    public void removeCourse(Course c) {
        // do nothing
    }
}

class CourseModule implements Course {

    private String title;

    private ArrayList<Course> Courses;

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
    public void Display() {
        System.out.println("Module:" + title);
        for (Course c : Courses) {
            c.Display();
        }
    }

    public void addCourse(Course c) {
        Courses.add(c);
    }

    public void removeCourse(Course c) {
        Courses.remove(c);
    }
}

public class Q1 {
    public static void main(String[] args) {
        // courses
        Course introProg = new simpleCourse("Introduction to Programming");
        Course dataStructures = new simpleCourse("Data Structures");
        Course machineLearning = new simpleCourse("Machine Learning");
        Course nlp = new simpleCourse("Natural Language Processing");
        Course finalProject = new simpleCourse("Final Year Project");
        // Modules
        Course programmingModule = new CourseModule("Programming Module");
        programmingModule.addCourse(introProg);
        programmingModule.addCourse(dataStructures);
        Course aiModule = new CourseModule("AI Module");
        aiModule.addCourse(machineLearning);
        aiModule.addCourse(nlp);
        // Program root
        Course csProgram = new CourseModule("Computer Science Program");
        csProgram.addCourse(programmingModule);
        csProgram.addCourse(aiModule);
        csProgram.addCourse(finalProject);
        // Display program structure
        csProgram.Display();
    }
}
