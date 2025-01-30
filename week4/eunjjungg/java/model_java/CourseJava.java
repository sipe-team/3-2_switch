package model_java;

public enum CourseJava {
    BACKEND("백엔드"),
    ANDROID("안드로이드");

    private final String inputCode;
    private final String printingName;

    CourseJava(String inputCode) {
        this.inputCode = inputCode;
        this.printingName = this.inputCode;
    }

    public String getInputCode() {
        return this.inputCode;
    }

    public String getPrintingName() {
        return this.printingName;
    }

    public static CourseJava parseFrom(String input) {
        for (CourseJava course : CourseJava.values()) {
            if (course.inputCode.equals(input)) return course;
        }
        return null;
    }
}
