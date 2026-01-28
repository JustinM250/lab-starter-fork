import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


/*

Types used in this code:

(Add your answers to lab instruction #4 here)

    // FAMILIAR TYPES
    Main - The main program.
    String - Self expl;

    Math - Used for math operations.
        Belongs to java.lang
    Color - Self expl; usually called with rgba params.
        Belongs to java.awt.*

    // ERRORS

    IOException
        Belongs to java.io.IOException
    InterruptedException
    Exception

    ^ Those are all exceptions for catching errors.

    // STUFF I'M UNFAMILIAR WITH
    InputStream - The input stream used for the showAvatar method.
    URI - "Represents a Uniform Resource Identifier (URI) reference." Here we use it to create an HTTP request, to connect to dicebear.com.
        Belongs to java.net.URI
    HttpClient - The HTTP Client (You)
        Belongs to java.net.http.HttpClient
    HttpRequest - The HTTP Request (Your call to the API)
        Belongs to java.net.http.HttpRequest
    HttpResponse - The HTPP Response (The avatar)
        Belongs to java.net.http.HttpResponse
    ImageIO - Here we use the InputStream to feed into ImageIO to return an Image object.
        Belongs to javax.imageio.ImageIO
    BodyHandlers
        Belongs to java.net.http.HttpResponse
    JFrame
        Belongs to javax.imageio.ImageIO
    JOptionPane
        Belongs to import javax.swing.*
    BorderLayout
        Belongs to java.awt.*
    Image
        Belongs to java.awt.*


 */

//// FOR CLARITY'S SAKE, MY COMMENTS WILL HAVE 4 slashes: ////
class Main{
    public static void main(String[] args) {

        try {
            var avatarStream = getRandomAvatarStream();
            //// Here var assumes it to be an InputStream.

            showAvatar(avatarStream);
        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(null, "Failed to load avatar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            //// After JOptionPane (1): class method, void, shows a message of all parameters.
            //// After e: instance method, String, shows description of the exception.
            //// After JOptionPane (2): class variable, constant, int. This int corresponds to an error message.
            //// Yes ik constants are called final in Java.
        }

    }

    static InputStream getRandomAvatarStream() throws IOException, InterruptedException {
        String[] styles = { "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral" };
        var style = styles[(int)(Math.random() * styles.length)];
        //// After Math: class method, double (which is converted to int here), returns random double.
        //// After styles: instance variable, int, returns the amount of items in the array.

        var seed = (int)(Math.random() * 10000);
        //// After Math: class method, double (which is converted to int here), returns random double.

        // Create an HTTP request for a random avatar
        var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed));
        //// After URI: class method, URI, returns a new URI.
        //// After the String: instance method, returns the String but formatted. %d is for decimal, %s is for string.
        var request = HttpRequest.newBuilder(uri).build();
        //// After HttpRequest: class method, HttpRequest.Builder, returns a new HttpRequest.Builder. Builder is an interface in HttpRequest.
        //// Q3: Looking into HttpRequest.java > newBuilder() method: This method makes a constructor call: new HttpRequestBuilderImpl(uri). The purpose? To obtain a new HttpRequestBuilderImpl object.

        // Send the request
        try{
            HttpClient client = HttpClient.newHttpClient();
            //// After HttpClient: class method, HttpClient, returns a new HttpClient.
            //// Q3: Looking into HttpClient.java > newHttpClient() method: This method retrieves a new Builder object via newBuilder(), which makes a constructor call: HttpClientBuilderImpl(). The purpose? To obtain a new HttpClientBuilderImpl object.
            var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            //// After client: abstract method (which are instance methods according to a quick search), <T> HttpResponse<T> is the data type, sends the http request.
            //// After HttpResponse: class identifier, BodyHandlers, used to access methods within the BodyHandlers class.
            //// After BodyHandlers: class method BodyHandler<InputStream>, "Returns a BodyHandler<Stream<String>> that returns a BodySubscriber<Stream<String>> obtained from BodySubscribers.ofLines(charset). The charset used to decode the response body bytes is obtained from the HTTP response headers as specified by ofString(), and lines are delimited in the manner of BufferedReader.readLine()."

            return response.body();
            //// After body: instance method, T, "Returns an Optional containing the SSLSession in effect for this response. Returns an empty Optional if this is not a HTTPS response."
        }
        catch(Exception e)
        {
            return null;
        }
    }

    static void showAvatar(InputStream imageStream) {
        JFrame frame = new JFrame("PNG Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //// After frame: instance method, void, sets the default close operation. In this case we're using the int constant for exit on close.
        //// After JFrame: class variable, constant, int. Refer to the previous line.
        frame.setResizable(false);
        //// After frame: instance method, void, sets whether or not the frame is resizable.
        frame.setSize(200, 200);
        //// After frame: instance method, void, sets the frame's size.
        frame.getContentPane().setBackground(Color.BLACK);
        //// After frame: instance method, Container, returns the contentPane object for the frame.
        //// After getContentPane(): instance method, void, sets the background.
        //// After Color: class variable, constant, Color. Being black, this corresponds to 0,0,0 for the rgb values.

        try {
            // Load the PNG image
            Image image = ImageIO.read(imageStream);
            //// After ImageIO, class method, BufferedImage, decodes the URL and returns a BufferedImage.

            // Create a JLabel to display the image
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            //// Q3: Here we're making a new JLabel using the new JLabel() constructor, and in that constructor we're inputting a new ImageIcon created from the new ImageIcon() constructor. The parameter for that takes an image. The purpose is to create an icon.
            frame.add(imageLabel, BorderLayout.CENTER);
            //// After frame: instance method, void, adds a component to the frame.
            //// After BorderLayout: class method, constant, String.

        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Failed to load image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            //// After JOptionPane (1): class method, void, shows a message of all parameters.
            //// After e: instance method, String, shows description of the exception.
            //// After JOptionPane (2): class variable, constant, int. This int corresponds to an error message.
            //// Same as in main().
        }

        frame.setVisible(true);
        //// After frame: instance method, void, sets the visiblity.
    }

}

