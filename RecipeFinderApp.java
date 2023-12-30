import java.util.Scanner;

public class RecipeFinderApp extends Recipes {
    private Recipes recipes;
    
// Opens the program and loads the recipes from the data file
    public RecipeFinderApp() {
        this.recipes = new Recipes();
        Recipes.loadRecipesFromFile("recipes.dat");
    }
// Displays the main menu options
    public void menuDisplay() {
    	System.out.println("        *** MAIN MENU ***        ");
        System.out.println("Make a selection from the following options");
        System.out.println();
        System.out.println("1 - Search recipes by name");
        System.out.println("2 - Search recipes by ingredient");
        System.out.println("3 - Add a recipe to the list");
        System.out.println("4 - Delete a recipe from the list");
        System.out.println("5 - Show all available recipes");
        System.out.println("6 - Exit Program");
        System.out.println();
    }
// Allows user to make one of six menu choices while input is valid. 
    public void menuChoices() {
        Scanner scnr = new Scanner(System.in);
        boolean input = true;

        do {
        	menuDisplay();
        	System.out.println("Enter your selection: ");
        	
        	while (!scnr.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer (1-5): ");
                scnr.next(); // 
        	}    
        	int choice = scnr.nextInt();
            scnr.nextLine(); // Consume the newline character left by nextInt()
            switch (choice) {
                case 1:
                    searchRecipesByNameOption(); 
                    System.out.println();
                    break;  
                case 2: 
                	searchRecipesByIngredientOption();
                	System.out.println();
                	break;               	
                case 3:
                	addRecipeOption();
                	System.out.println();
                	break;
                case 4: 
                	deleteRecipeOption();
                	System.out.println();
                	break;
                	
                case 5:
                    recipes.printRecipeArray();
                    System.out.println();
                    break;             	
                case 6:
                    System.out.println("Exiting the application.");
                    input = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println();
                    break;
            }

        } while (input);
    }
// User can enter the name of a recipe to search for the recipe in the data file.
    private void searchRecipesByNameOption() {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter recipe name to search: ");
        String searchName = scnr.nextLine();
        recipes.searchRecipesByName(searchName);
        
    }     
// User can enter the name of an ingredient and search all recipes that contain this ingredient.   
    private void searchRecipesByIngredientOption() {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter recipe ingredient to search: ");
        String searchIngredient = scnr.nextLine();
        recipes.searchRecipesByIngredient(searchIngredient);
    }
// User can add a recipe to the file    
    public void addRecipeOption() {
        Scanner scnr = new Scanner(System.in);

        System.out.print("Enter recipe name: ");
        String name = scnr.nextLine();

        System.out.print("Enter ingredients (comma-separated): ");
        String ingredients = scnr.nextLine();

        System.out.print("Enter image URL: ");
        String imageUrl = scnr.nextLine();
        System.out.println();

        Recipes.addRecipe(name, ingredients, imageUrl);
        
        displayImageFromUrl(imageUrl);

    }
// User can delete a recipe from the file based on name
    public void deleteRecipeOption() {
        Scanner scnr = new Scanner(System.in);

        System.out.print("Enter recipe name to delete: ");
        String nameToDelete = scnr.nextLine().trim();
        System.out.println();
        
        while (nameToDelete.isEmpty() || !Recipes.recipeExists(nameToDelete)) {
            if (nameToDelete.isEmpty()) {
                System.out.println("Recipe name cannot be empty. Please enter a valid recipe name: ");
            } else {
                System.out.println("Recipe with the name '" + nameToDelete + "' not found. Please enter a valid recipe name: ");
            }

            nameToDelete = scnr.nextLine().trim();
            System.out.println();

        Recipes.deleteRecipe(nameToDelete); }
    }	

    public static void main(String[] args) {
        RecipeFinderApp app = new RecipeFinderApp();

        app.menuChoices();
        Recipes.saveRecipesToFile("recipes.dat");
    }
}

