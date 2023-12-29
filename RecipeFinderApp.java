import java.util.Scanner;

public class RecipeFinderApp extends Recipes {
    private Recipes recipes;

    public RecipeFinderApp() {
        this.recipes = new Recipes();
        Recipes.loadRecipesFromFile("recipes.dat");
    }

    public void menuDisplay() {
    	System.out.println("        *** MAIN MENU ***        ");
        System.out.println("Make a selection from the following options");
        System.out.println();
        System.out.println("1 - Search recipes by name");
        System.out.println("2 - Search recipes by ingredient");
        System.out.println("3 - Add a recipe to the list");
        System.out.println("4 - Show all available recipes");
        System.out.println("5 - Exit Program");
        System.out.println();
    }

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
                    recipes.printRecipeArray();
                    System.out.println();
                    break;             	
                case 5:
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

    private void searchRecipesByNameOption() {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter recipe name to search: ");
        String searchName = scnr.nextLine();
        recipes.searchRecipesByName(searchName);
        
    }     
    private void searchRecipesByIngredientOption() {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter recipe ingredient to search: ");
        String searchIngredient = scnr.nextLine();
        recipes.searchRecipesByIngredient(searchIngredient);
    }
    
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


    public static void main(String[] args) {
        RecipeFinderApp app = new RecipeFinderApp();

        app.menuChoices();
        Recipes.saveRecipesToFile("recipes.dat");
    }
}

