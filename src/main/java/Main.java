/* ********************************

Add your Lab 1 code to this file

1.1. Uses at least two different types of variables / yup

1.2. Uses at least one conditional statement / yup

1.3. Uses at least one loop / yup

1.4. Obtains keyboard input from the user in some way / yup

1.5. Prints information to the console / yup

1.6. Writes information to a file / yup

1.7. Gracefully handles errors using try/catch / yup

1.8. Uses a List or array in a meaningful way (perhaps collect multiple user inputs into a List and then iterate over the list to display/store the information) / yup

1.9. A function that accepts at least one argument and returns a value is defined and used NOTE: The ‘main’ function does not count / yup

1.10. JavaDoc to document the function(s) you create / yup

1.11. Other code comments as appropriate / yup
 */

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner; // Needed for input.
import java.util.Random; // Needed for rand.

class MainProgram {
    static Random random_gen = new Random(); // Didn't end up using this, good to know tho.

    /**
     * @param the length of the rectangle
     * @param the width of the rectangle
     * @return returns a string
     */
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
                    s += x == 0 || x == w-1 ? "X " : "  " ; // i don't like java ternaries :/
                }

            }
            s += "\n";
        }
        return s;
    }

    /**
     * @param The message to display. Ex: "Enter an integer: "
     * @return Returns an integer inputted from user
     */
    public static int get_int(String m)
    {
        Scanner input_scanner = new Scanner(System.in);
        while(true)
        {
            System.out.println(m);
            String input = input_scanner.nextLine();
            try {
                // System.out.println(input.length());
                if (input.length() < 3){ // This is to avoid the for loop taking too long.
                    int return_value = Integer.parseInt(input);
                    return return_value;
                }
                else
                {
                    System.out.println("Please enter a # less than 100");
                    // throw new RuntimeException("Please enter a number less than 100."); // Testing error throwing.
                }

            } catch (NumberFormatException fail) {
                System.out.println("ERROR, TRY AGAIN");
                continue;
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<String> shapes_list = new ArrayList<String>();

        System.out.println("Welcome to rectangle list maker, the semi-useless program where you can make a list of rectangles!");
        boolean mainloop_active = true;
        while(mainloop_active)
        {
            System.out.println(String.format("You're on rectangle #%d", shapes_list.size()+1 ));
            int rectangle_length = get_int("Enter rectangle length: ");
            int rectangle_width = get_int("Enter rectangle width: ");

            String new_shape = get_rect_string(rectangle_length, rectangle_width);
            System.out.println(new_shape);
            shapes_list.add(new_shape);

            while(true)
            {
                Scanner choice_scanner = new Scanner(System.in);
                System.out.println("\nYou added that rectangle to your list. Do you want to end the program? Type Y/N: ");
                String choice = choice_scanner.nextLine().toLowerCase();
                if (choice.equals("y") )
                {
                    mainloop_active = false;
                    break;
                }
                else if(choice.equals("n"))
                {
                    break;
                }
            }


        }
        String final_output = "";
        for (String shape: shapes_list) {
            final_output += shape;
            final_output += "\n";
        }


        String filepath;
        // So this works, though technically it can override pre-existing files; bad practice.
        // ChatGPT helped me figure out the importing/checking if file exists. FileWrite can't do that.
        // sfilepath = String.format("C:\\Users\\25014394\\Desktop\\shape%d.txt", random_gen.nextInt(9999999));
        filepath = "C:\\Users\\25014394\\Desktop\\shape.txt";

        // ChatGPT told me what to import to check if filepaths exist. I then did the classic "add +1 until the path doesn't exist".
        // Path path = Path.of("data/output.txt");
        String og_filepath = filepath;
        int path_exist_count = 2;
        while(true){
            if (Files.exists(Path.of(filepath))){
                String new_filepath = og_filepath.substring(0,og_filepath.length()-4);
                new_filepath += Integer.toString(path_exist_count);
                new_filepath += ".txt";
                filepath = new_filepath;
                path_exist_count += 1;
            }
            else
            {
                break;
            }
        }

        // https://www.youtube.com/watch?v=Pg0aoSbrqOE
        // Got this from BroCode. I'm surprised it's this easy with Java to get a file written out.
        try (FileWriter writer = new FileWriter(filepath)) {
            writer.write(final_output + "\n");
        } catch (IOException fail) {
            System.out.println("Failed to write.");
        }
        System.out.println(String.format("You have received a .txt file copy of all your shapes in your downloads! Filepath is %s", filepath) );
    }

}