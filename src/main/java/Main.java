/* ********************************

Add your Lab 1 code to this file

1.1. Uses at least two different types of variables / yup

1.2. Uses at least one conditional statement / yup

1.3. Uses at least one loop / yup

1.4. Obtains keyboard input from the user in some way / yup

1.5. Prints information to the console / yup

1.6. Writes information to a file

1.7. Gracefully handles errors using try/catch

1.8. Uses a List or array in a meaningful way (perhaps collect multiple user inputs into a List and then iterate over the list to display/store the information)

1.9. A function that accepts at least one argument and returns a value is defined and used NOTE: The ‘main’ function does not count / yup

1.10. JavaDoc to document the function(s) you create / yup

1.11. Other code comments as appropriate / yup
 */

import java.util.Scanner;

class MainProgram {
    public static String get_rect_string(int l, int w)
    {
        String s = new String("");
        for (int y = 0; y < l; y++)
        {
            for (int x = 0; x < w; x++){
                if (y == 0 || y == l-1 )
                {
                    s += "X ";
                }
                else
                {
                    s += x == 0 || x == w-1 ? "X " : "  " ; // i don't like java ternaries
                }

            }
            s += "\n";
        }
        return s;
    }
    public static void main(String[] args)
    {
        // add multiple shapes, use array
        // failsafe this w try/catch
        Scanner input_scanner = new Scanner(System.in);
        System.out.println("Enter rectangle length: ");
        int rectangle_length = Integer.parseInt(input_scanner.nextLine());
        System.out.println("Enter rectangle width: ");
        int rectangle_width = Integer.parseInt(input_scanner.nextLine());
        System.out.println(get_rect_string(rectangle_length, rectangle_width));
        System.out.println("\nYou have also recieved a .txt file copy of your shape in your downloads!");
        // write this to file
    }

     /**
     * @param the length of the rectangle
     * @param the width of the rectangle
     * @return returns a string
     */

}