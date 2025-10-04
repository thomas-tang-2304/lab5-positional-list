public class App {
    public static void main(String[] args) throws Exception {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        
        list.addFirst("A");
        list.addLast("B");
        list.addLast("C");
        System.out.println("Last element in list: " + list.last());
        list.addLast("D");
        list.addLast("E");
        list.addFirst("0");
        System.out.println("First element in list: " + list.first());

        System.out.println("Before A: " + list.before("A"));
        System.out.println("After D: " + list.after("D"));
        list.set("0", "1");
        list.remove("1");

        list.addAfter("B", "B1");
        list.addBefore("D", "D1");

        list.display();

    }
}
