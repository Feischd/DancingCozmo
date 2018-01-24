import java.lang.*;

public class Java_Cozmo {
    public static void main(String[] args) throws Exception {
        String program = "01_hello_world.py";
        Runtime.getRuntime().exec("cmd /c start " + program);

    }

    // Starting external programs from java
    // http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_11_005.htm
}
