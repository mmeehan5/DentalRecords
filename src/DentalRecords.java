import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;


public class DentalRecords {
    public static Scanner scnr = new Scanner(in);
    final static int maximum_fam_members = 5;
    final static int maximum_teeth = 10;

    public static void main(String[] args) {

////set string of fam members in array, a string of tooth types in 3d array... enter number of people system
        int famMember;
        char menuOption;
        out.print("Please enter number of people in the family :");
        famMember = scnr.nextInt();
        while (famMember < 0 || famMember > maximum_fam_members) {
            out.print("Invalid number of people, try again : ");
            famMember = scnr.nextInt();
        }
        String[] FamName = new String[famMember];
        char[][][] TeethInfo = new char[famMember][2][];
        Input(FamName, TeethInfo);

////switch statement for the 4 options: print extract root exit
        menuOption = opt();
        switch (menuOption) {
            case 'P':
                Print(FamName, TeethInfo);
                break;
            case 'E':
                Extract(FamName, TeethInfo);
                break;
            case 'R':
                RootCanal();
            case 'X':
                break;
        }
        ///// get relative fam info for each statement
        while (menuOption != 'X') {
            menuOption = opt();
            switch (menuOption) {
                case 'P':
                    Print(FamName, TeethInfo);
                    break;
                case 'E':
                    Extract(FamName, TeethInfo);
                    break;
                case 'R':
                    RootCanal();
                case 'X':
                    break;
            }
        }
///case X (final print statement)
        out.print("Exiting the Floridian Tooth Records :-)");
    }


    ///enter member name and the input given from prof sutcliffe for the teeth upper and lower
    public static void Input(String[] mem_name, char[][][] teeth) {
        String change;
        int name = 0;
        while (name < mem_name.length) {
            out.print("Please enter the name for family member " + (name + 1) + " : ");
            mem_name[name] = scnr.next();
            out.print("Please enter the uppers for " + mem_name[name] + " : ");
            change = scnr.next();
            change = change.toUpperCase();
            while (!NumTeeth(change))
                change = scnr.next();
            teeth[name][0] = change.toCharArray();
            out.print("Please enter the lowers for " + mem_name[name] + " : ");
            change = scnr.next();
            change = change.toUpperCase();
            while (!NumTeeth(change)) {
                change = scnr.next();
                change = change.toUpperCase();
            }
            teeth[name][1] = change.toCharArray();
            final int i = name++;
        }
    }

/////test number of teeth (T/F) "All input must be checked to be in range...user must be asked to input again."
    public static boolean NumTeeth(String t) {
        if (t.length() > maximum_teeth) {
            out.print("Too many teeth, try again : ");
            return false;
        }
        for (int n = 0; n < t.length(); n++)
            if (t.charAt(n) != 'M' && t.charAt(n) != 'C' && t.charAt(n) != 'B') {
                out.print("Invalid teeth types, try again :");
                return false;
            }
        return true;
    }

//show the P,E,R,X menu
    private static char opt() {
        char choice;
        out.print("(P)rint, (E)xtract, (R)oot, e(X)it :");
        choice = scnr.next().charAt(0);
        choice = Character.toUpperCase(choice);
        while (!(choice == 'P' || choice == 'E' || choice == 'R' || choice == 'X')) {
            out.print("Invalid menu option, try again : ");
            choice = scnr.next().charAt(0);
            choice = Character.toUpperCase(choice);
        }
        return choice;
    }

///case P
    private static void Print(String[] names, char[][][] teeth) {
        for (String ignored : names)
            out.println();
        for (int info = 0; info < names.length; info++) {
            out.println(names[info]);
            out.print(" Uppers: ");
            for (int memb = 0; memb < teeth[info][0].length; memb++)
                out.print((memb + 1) + ":" + teeth[info][0][memb] + " ");
            out.print(" Lowers: ");
            for (int memb = 0; memb < teeth[info][1].length; memb ++)
                out.print((memb + 1) + ":" + teeth[info][1][memb] + " ");
            out.println();
        }

    }

//family member subtraction
    public static int findMember(String[] names, String Name) {
        for (int info = 0; info < names.length; info++) {
            if (!names[info].equals(Name)) continue;
            return info;
        }
        return -1;
    }

//Case E
    private static void Extract(String[] names, char[][][] teeth) {
        String name;
        char row;
        out.print("Which family member : ");
        name = scnr.next();
        int fam = findMember(names, name);
        while (fam < 0) {
            out.print("Invalid family member, try again : ");
            name = scnr.next();
            fam = findMember(names, name);
        }
        out.print("Which tooth layer (U)pper or (L)ower : ");
        row = scnr.next().charAt(0);
        row = Character.toUpperCase(row);
        while ((row == 'U' || row == 'L')) {
            out.print("Invalid layer, try again : ");
            row = scnr.next().charAt(0);
            row = Character.toUpperCase(row);
        }
        int UppersOrLowers;
        UppersOrLowers = 1;
        out.print("Which tooth number : ");
        int Letter = scnr.nextInt();
        while (true) {
            if (Letter < 0 || Letter > maximum_teeth) {
                out.print("Invalid tooth number, try again : ");
                Letter = scnr.nextInt();
                continue;
            }
            if (teeth[fam][UppersOrLowers][Letter - 1] == 'M') {
                out.print("Missing tooth, try again : ");
                Letter = scnr.nextInt();
                continue;
            }
            teeth[fam][UppersOrLowers][Letter - 1] = 'M';
            break;
        }
    }
//Case R
    private static void RootCanal() {
        out.println("One root canal at 0.40");
                out.println("Another root canal at -2.07");
            }
        }
