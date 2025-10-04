
public class App {
    public static void main(String[] args) throws Exception {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        LinkedPositionalList.ElementIterator it = (LinkedPositionalList.ElementIterator) list.iterator();

        list.addFirst("A");
        list.addLast("B");
        list.addLast("C");
        System.out.println("Last element in list: " + list.last().getElement());
        list.addLast("D");
        list.addLast("E");
        list.addFirst("0");
        System.out.println("First element in list: " + list.first().getElement());

        System.out.println("Before A: " + list.before("A").getElement());
        System.out.println("After D: " + list.after("D").getElement());
        list.set("0", "1");
        list.remove("0");

        list.addAfter("B", "B1");
        list.addBefore("D", "D1");


        list.display();

        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }

    }
}
