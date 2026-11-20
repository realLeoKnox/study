package class17;

class 动物{
    void cry(){

    }
}
class 狗 extends 动物{
    void cry(){
        System.out.println("这是狗的叫声旺旺~");
    }
}
class 猫 extends 动物{
    void cry() {
        System.out.println("这是猫叫喵喵喵~");;
    }
}
public class dongwu {
    public static void main(String args[]){
        动物 animal = new 狗();
        animal.cry();
        animal = new 猫();
        animal.cry();
    }
}
