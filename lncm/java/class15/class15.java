
package class15;

public class class15 {
    static void main() {
        Phone phone = new Phone();
        Student student=new Student(phone);
        student.setId(2);
        System.out.println(student.age);

        Student []students=new Student[100];
        students[0]=new Student();
        for (int i=1;i<students.length;i++){
            students[i]=student;
        }
        students[0].setA("ZhangSan",20,1);
    }
}

class Student extends person{
    int age=8;
    Phone a;
    public Student(Phone a){
        this.a=a;
    }
    Student(){}
    void setA(String name,int age,int id){
        this.name=name;
        this.age=age;
        this.id=id;
    }
    @Override
    void eat(){};
}
class Phone{

}

class Teacher extends person{


}

class person{
    String name;
    private int age=1;
    int id;
    void setId(int id){
        this.id=id;
    }
    int getid(){
        return id;
    }
    void eat(){};
}

