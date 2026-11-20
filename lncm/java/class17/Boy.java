package class17;

abstract class GirlFriend{
    abstract void speak();
    abstract void cooking();
}
class ChinaGirlFriend extends GirlFriend{
    void speak() {
        System.out.println("Hello");
    }
    void cooking(){
        System.out.println("Food");
    }
}

class AmericanGirlFriend extends GirlFriend{
    void speak(){
        System.out.println("Hi");
    }
    void cooking(){
        System.out.println("Good Food");
    }
}

class Man{
    GirlFriend friend;
    void setGirlfriend(GirlFriend f){
        friend = f;

    }
    void showGirlFriend(){
        friend.speak();
        friend.cooking();
    }
}
public class Boy {
    public static void main(String[] args) {
        GirlFriend girl = new ChinaGirlFriend();
        Man boy = new Man();
        boy.setGirlfriend(girl);
        boy.showGirlFriend();
        girl = new AmericanGirlFriend();
        boy.setGirlfriend(girl);
        boy.showGirlFriend();

    }
}
