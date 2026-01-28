import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


//// THE CODE, THOROUGHLY DOCUMENTED

/*

Q4 - Types used in this code, and their purpose:

    // TYPES I'M FAMILIAR WITH

    Main - The main program.
        Belongs to java.lang

    String - Self expl;
        Belongs to java.lang

    Math - Used for math operations.
        Belongs to java.lang

    Color - Self expl; usually called with rgba params.
        Belongs to java.awt.*

    IOException - Exception used for error handling.
        Belongs to java.io.IOException

    InterruptedException - Exception used for error handling.
        Belongs to java.lang

    Exception - Exception used for error handling.
        Belongs to java.lang

    // TYPES I'M UNFAMILIAR WITH

    InputStream - Here we're using a method to get a random avatar stream (for converting to an image), from the dicebear.com API, and a method to show it, taking the InputStream instance as a param.
        Belongs to import java.io.InputStream

    URI - "Represents a Uniform Resource Identifier (URI) reference." Here we use it to create an HTTP request, to connect to dicebear.com, to use their API.
        Belongs to java.net.URI

    HttpClient - The HTTP Client (You)
        Belongs to java.net.http.HttpClient

    HttpRequest - The HTTP Request (Your call to the API)
        Belongs to java.net.http.HttpRequest

    HttpResponse - The HTTP Response
        Belongs to java.net.http.HttpResponse

    BodyHandlers - Class within the HttpResponse class. We use the ofInputStream() method to get the InputStream of the response, which is the avatar.
        Belongs to java.net.http.HttpResponse

    ImageIO - Here we use the InputStream to feed into ImageIO.read() to return a BufferedImage object, which is used as the value of our Image.
        Belongs to javax.imageio.ImageIO

    Image - I believe this is a .png image.
        Belongs to java.awt.*

    JFrame - To my understanding, this is used to create a window, which is then used for displaying content.
        Belongs to javax.imageio.ImageIO, also needs javax.swing.*.

    ImageIcon - From the Image we create an ImageIcon for use in a JLabel.
        Belongs to import javax.swing.*

    JLabel - We're displaying the image using a JLabel containing an ImageIcon.
        Belongs to import javax.swing.*

    JOptionPane - Used for displaying options, but in this program it's just used for displaying error msg's.
        Belongs to import javax.swing.*

    BorderLayout - Used for border around the frame.
        Belongs to java.awt.*

Q5 - Explained which types belong to which package in Q5. Brief overview of what (I believe) they do below:

    import javax.imageio.ImageIO;
    > This is used for ImageIO streams. We use the stream in the code to get an Image object, which belongs to javax.swing.*

    import javax.swing.*;
    import java.awt.*;
    > These two seem interlinked, and are used for displaying content in a heirarchical sense. Kind of like HTML tags or Nodes/GameObjects/Actors in a game engine.

    import java.io.InputStream;
    > Used for the InputStream type. As stated before, we use the read() method on ImageIO to return a BufferedImage type, which is used as the value of our Image.

    import java.net.URI;
    import java.net.http.HttpClient;
    import java.net.http.HttpRequest;
    import java.net.http.HttpResponse;
    > We use these for connecting to the dicebear.com API and getting the InputStream from it.

 */

//// FOR CLARITY'S SAKE, MY COMMENTS WILL HAVE 4 slashes: ////
class Main{
    public static void main(String[] args) {

        try {
            var avatarStream = getRandomAvatarStream();
            //// Q6: InputStream. Object/Reference type.
            showAvatar(avatarStream);
        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(null, "Failed to load avatar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            //// Q1+Q2: After JOptionPane (1): class method, void, shows a message of all parameters.
            //// Q1+Q2: After e: instance method, String, shows description of the exception.
            //// Q1: After JOptionPane (2): class variable, constant, int. This int corresponds to an error message.
            //// Yes ik constants are called final in Java. Here I'll be referring to them as constants.
        }

    }

    static InputStream getRandomAvatarStream() throws IOException, InterruptedException {
        String[] styles = { "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral" };
        //// Q6: String[]. Object/Reference type.
        var style = styles[(int)(Math.random() * styles.length)];
        //// Q1+Q2: After Math: class method, double (which is converted to int here), returns random double.
        //// Q1: After styles: instance variable, int, returns the amount of items in the array.
        //// Q6: String. Object/Reference type.

        var seed = (int)(Math.random() * 10000);
        //// Q1+Q2: After Math: class method, double (which is converted to int here), returns random double.
        //// Q6: int. Primitive type.

        // Create an HTTP request for a random avatar
        var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed));
        //// Q1+Q2: After URI: class method, URI, returns a new URI for the creation of the HTTP request.
        //// Q1+Q2: After the String: instance method, returns the String but formatted. %d is for decimal, %s is for string. The link is an API call like what we did last semester with the OMDB.
        //// Q6: URI. Object/Reference type.
        var request = HttpRequest.newBuilder(uri).build();
        //// Q1+Q2: After HttpRequest: class method, HttpRequest.Builder, returns a new HttpRequest.Builder. Builder is an interface in HttpRequest.
        //// Q1+Q2: After newBuilder(uri): instance method, HttpRequest.
        //// Q3: Looking into HttpRequest.java > newBuilder() method: This method makes a constructor call: new HttpRequestBuilderImpl(uri). The purpose? To obtain a new HttpRequestBuilderImpl object. On that we call build() to get the actual HttpRequest.
        //// Q6: HttpRequest. Object/Reference type.

        // Send the request
        try{
            HttpClient client = HttpClient.newHttpClient();
            //// Q1+Q2: After HttpClient: class method, HttpClient, returns a new HttpClient.
            //// Q3: Looking into HttpClient.java > newHttpClient() method: This method retrieves a new Builder object via newBuilder(), which returns a new HttpClientBuilderImpl. The purpose? To obtain a new HttpClientBuilderImpl object, that is used as the value of the HttpClient.
            //// Q6: HttpClient. Object/Reference type.
            var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            //// Q1+Q2: After client: abstract method (which are instance methods according to a quick search), <T> HttpResponse<T> is the data type, sends the http request.
            //// Q1: After HttpResponse: class identifier, BodyHandlers, used to access methods within the BodyHandlers class.
            //// Q1+Q2: After BodyHandlers: class method BodyHandler<InputStream>, "Returns a BodyHandler<Stream<String>> that returns a BodySubscriber<Stream<String>> obtained from BodySubscribers.ofLines(charset). The charset used to decode the response body bytes is obtained from the HTTP response headers as specified by ofString(), and lines are delimited in the manner of BufferedReader.readLine()."
            //// Q6: HttpResponse. Object/Reference type.

            return response.body();
            //// Q1: After body: instance method, T, "Returns an Optional containing the SSLSession in effect for this response. Returns an empty Optional if this is not a HTTPS response."
        }
        catch(Exception e)
        {
            return null;
        }
    }

    static void showAvatar(InputStream imageStream) {
        //// Q7: The parameter here is an object/reference type.

        JFrame frame = new JFrame("PNG Viewer");
        //// Q6: JFrame. Object/Reference type.

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //// Q1+Q2: After frame: instance method, void, sets the default close operation. In this case we're using the int constant for exit on close.
        //// Q1: After JFrame: class variable, constant, int. Refer to the previous line.
        frame.setResizable(false);
        //// Q1+Q2: After frame: instance method, void, sets whether or not the frame is resizable.
        frame.setSize(200, 200);
        //// Q1+Q2: After frame: instance method, void, sets the frame's size.
        frame.getContentPane().setBackground(Color.BLACK);
        //// Q1+Q2: After frame: instance method, Container, returns the contentPane object for the frame.
        //// Q1+Q2: After getContentPane(): instance method, void, sets the background.
        //// Q1: After Color: class variable, constant, Color. Being black, this corresponds to 0,0,0 for the rgb values.

        try {
            // Load the PNG image
            Image image = ImageIO.read(imageStream);
            //// Q6: Image. Object/Reference type.
            //// Q1+Q2: After ImageIO, class method, BufferedImage, decodes the URL and returns a BufferedImage.

            // Create a JLabel to display the image
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            //// Q6: JLabel. Object/Reference type.
            //// Q3: Here we're making a new JLabel using the new JLabel() constructor, and in that constructor we're inputting a new ImageIcon created from the new ImageIcon() constructor. The parameter for that takes an image. The purpose is to create an icon to display the image.
            frame.add(imageLabel, BorderLayout.CENTER);
            //// After frame: instance method, void, adds a component to the frame.
            //// After BorderLayout: class method, constant, String.

        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Failed to load image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            //// Q1+Q2: After JOptionPane (1): class method, void, shows a message of all parameters.
            //// Q1+Q2: After e: instance method, String, shows description of the exception.
            //// Q1: After JOptionPane (2): class variable, constant, int. This int corresponds to an error message.
            //// Same as in main().
        }

        frame.setVisible(true);
        //// Q1: After frame: instance method, void, sets the visibility. I'm assuming it's invisible by default.
    }

}

