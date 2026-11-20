package class16;

public class class16 {
    public static void main(String[] args) {
        Dog dog=new Dog("");
        dog.cry();
        dog.eat();
        System.out.println(dog.age);
        Animal animal = new Dog("");
        animal.cry();
        //animal.eat(); 不可
        /*先检查*/
        if (animal instanceof Dog) {
            Dog a = (Dog) animal; //下转型
            a.eat();
        }

    }
}

class Animal{

        String name;
        int age;
        public Animal(String name){
            this.name=name;
        }
        void cry(){
            System.out.println("动物会叫");
    }

}
class Dog extends Animal{
    int age=1;

    public Dog(String name){
        super(name);//super与this不可共存
    }

    @Override //方法的重写
    void cry(){
        System.out.println("wangwang");
    }


    void eat(){
        System.out.println("eating");
    }
}