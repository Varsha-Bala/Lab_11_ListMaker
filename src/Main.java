import java.util.Scanner;
import java.util.ArrayList; // required for using

public class Main
{
    private static ArrayList<String> myArrList = new ArrayList<>();  // note the diamond notation on the type parameter <>
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args)
    {
        String option;
        boolean quitYN = false;

        do
        {
            display();
            option = SafeInput.getRegExString(in, "•   Choose an option (A, D, P, Q or a, d, p, q)", "[ADPQadpq]"); //in.nextLine();
            if (option.equalsIgnoreCase("A"))
                addItem();
            else if (option.equalsIgnoreCase("D"))
                deleteItem();
            else if (option.equalsIgnoreCase("P"))
                printList();
            else if (option.equalsIgnoreCase("Q"))
            {
                quitYN = SafeInput.getYNConfirm(in, "Are you sure?");
                if (quitYN)
                    break;
            }
        } while (true);

    }

    private static void display() // shows the options
    {
        System.out.println("    A – Add an item to the list");
        System.out.println("    D – Delete an item from the list");
        System.out.println("    P – Print (i.e. display) the list");
        System.out.println("    Q – Quit the program");
    }

    private static void addItem()
    {
        String item;
        boolean done = false;
        item = SafeInput.getNonZeroLengthString(in, "Add item to list : ");
        for (int row = 0; row < myArrList.size(); row++) // look for deleted item and replace
        {
            if (myArrList.get(row) == "")
            {
                myArrList.set(row, item);
                done = true;
                break;
            }
        }
        if (!done) // add item to the end of list if not replaced above
            myArrList.add(item);
    }

    private static void deleteItem()
    {
        int row;
        printList(); // before delete
        row = SafeInput.getRangedInt(in, "Delete item : ", 1, myArrList.size()) - 1;
        myArrList.set(row, "");
        printList(); // after delete
    }

    private static void printList()
    {
        for (int row = 0; row < myArrList.size(); row++)
            System.out.println(row + 1 + " : " + myArrList.get(row));
    }
}
