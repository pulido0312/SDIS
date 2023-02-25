package protocol.common;

public class MapQueue {
    public static void main(String [] args) {
        Multimap<String,String> chm = new Multimap<String,String>();
        chm.push("hola", "me llamo juan");
        chm.push("hola", "me llamo pedro");
        chm.push("adios", "me llamo alicia");

        System.out.println("hola -> "+chm.pop("hola"));
        System.out.println("hola -> "+chm.pop("hola"));
        System.out.println("adios -> "+chm.pop("adios"));
        System.out.println("adios -> "+chm.pop("adios"));
        System.out.println("otro -> "+chm.pop("otro"));

    }
}
