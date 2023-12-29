import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageDisplayExample extends Recipes {
    
	
	public static void displayImageFromUrl () {
        // Replace this URL with the actual image URL
        String imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Pumpkin_Walnut_Bread.jpg/640px-Pumpkin_Walnut_Bread.jpg";

        //Create a JFrame
        JFrame frame = new JFrame("Image Display Example");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create an ImageIcon from the URL
        ImageIcon icon = createImageIcon(imageUrl);

        // Create a JLabel and set the ImageIcon
        JLabel label = new JLabel(icon);

        // Add the JLabel to the JFrame
        frame.getContentPane().add(label);

        // Set the JFrame to be visible
        frame.setVisible(true);
    }

   private static ImageIcon createImageIcon(String imageUrl) {
        try {
            // Create an URL object from the string
            URL url = new URL(imageUrl);

            //Create an ImageIcon from the URL
            return new ImageIcon(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
