import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.ImageIcon;
import java.util.Iterator;

public class Recipes implements Serializable  {
	private static final long serialVersionUID = 1L;
    private String name;
    private String ingredients;
    private String imageUrl;


    // Create an arraylist to collect recipe information.	
    private static ArrayList<Recipes> recipesList = new ArrayList<>();

    // Constructor	
    public Recipes() {
    	
    }
    // Parameterized constructor
    public Recipes(String name, String ingredients, String imageUrl) {
    	this.name = name;
        this.ingredients = ingredients;
        this.imageUrl = imageUrl;
    }
    // Returns the Arraylist of recipes when called
    public static ArrayList<Recipes> getRecipesList() {
        return recipesList;
    }
    // Loads the recipes from the file "recipes.dat" 
    public static void loadRecipesFromFile(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("New file created: " + filename);
            }

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                recipesList = (ArrayList<Recipes>) ois.readObject();
                System.out.println("Recipes loaded from file: " + filename);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // Saves recipes to the file "recipes.dat"
    public static void saveRecipesToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(recipesList);
            System.out.println("Recipes saved to file: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Accessors
    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    // Searches for a recipe in the data file based on the name of the recipe
    public void searchRecipesByName(String name) {
        System.out.println("Searching for recipes with the name: " + name);
        System.out.println();
        boolean found = false;
        
        loadRecipesFromFile("recipes.dat");  // load the current recipe list

        for (Recipes recipe : recipesList) {
        	
            if (recipe.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println("Recipe: " + recipe.getName());
                System.out.println("Ingredients: " + recipe.getIngredients());
                System.out.println("Image URL: " + recipe.getImageUrl());
                System.out.println();
                displayImageFromUrl(recipe.getImageUrl());
                found = true;
            }
        
        }
        
        if (!found) {
            System.out.println("No recipes found with this name: " + name);
        }  
    }
 
     // Searches for a recipe from the data file based on an ingredient 
     public void searchRecipesByIngredient(String ingredients) {
            System.out.println("Searching for recipes with the ingredient: " + ingredients);
            System.out.println();
            boolean found = false;

            for (Recipes recipe : recipesList) {
                if (recipe.getIngredients().toLowerCase().contains(ingredients.toLowerCase())) {
                    System.out.println("Recipe: " + recipe.getName());
                    System.out.println("Ingredients: " + recipe.getIngredients());
                    System.out.println("Image URL: " + recipe.getImageUrl());
                    System.out.println();
                    displayImageFromUrl(recipe.getImageUrl());
                    found = true;
                }
            }

        if (!found) {
            System.out.println("No recipes found with this ingredient: " + ingredients);
        }
    } 
     // Adds a recipe to the data file
    public static void addRecipe(String name, String ingredients, String imageUrl) {
        Recipes newRecipe = new Recipes(name, ingredients, imageUrl);
        System.out.println("Recipe added successfully:");
        System.out.println("Name: " + name);
        System.out.println("Ingredients: " + ingredients);
        System.out.println("Image URL: " + imageUrl);
        System.out.println();
        
        recipesList.add(newRecipe);  // Add the new recipe to the list
        saveRecipesToFile("recipes.dat");  // Save the updated list to a file

    }
    // Deletes a recipe from the data file based on name.	
    public static void deleteRecipe(String name) {
        Iterator <Recipes> iterator = recipesList.iterator();

        while (iterator.hasNext()) {
            Recipes recipe = iterator.next();

            if (recipe.getName().equalsIgnoreCase(name)) {
                System.out.println("Deleting recipe: " + recipe.getName());
                iterator.remove();
            }
        }
        saveRecipesToFile("recipes.dat");
    }
    // Checks that a recipe exists in the data file
    public static boolean recipeExists(String name) {
        loadRecipesFromFile("recipes.dat");  // Load recipes from file
        return recipesList.stream().anyMatch(recipe -> recipe.getName().equalsIgnoreCase(name));
    }
    // Displays image from the imageUrl in a separate window
    public static void displayImageFromUrl(String imageUrl) {
  
        JFrame frame = new JFrame("Image Display Example");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
        ImageIcon icon = createImageIcon(imageUrl);

        JLabel label = new JLabel(icon);

        frame.getContentPane().add(label);
        frame.setVisible(true);
    }
    // Creates an image icon from the URL
    private static ImageIcon createImageIcon(String imageUrl) {
        try {
            URL url = new URL(imageUrl);

            return new ImageIcon(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //Prints all of the recipes in the recipe arraylist
    public void printRecipeArray() {

        // Access and print information about each recipe
        for (Recipes recipe : recipesList) {
            System.out.println("Recipe: " + recipe.getName());
            System.out.println("Ingredients: " + recipe.getIngredients());
            System.out.println("Image URL: " + recipe.getImageUrl());
            System.out.println();
        }
    }
    
} 
