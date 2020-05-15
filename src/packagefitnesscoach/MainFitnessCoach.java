package packagefitnesscoach;

import java.io.IOException;
import java.util.*;
import java.util.Set;

public class MainFitnessCoach {

    private static int choice = 0;
    private static int choiceActivity;
    private static int choiceNbRep;
    private static int choiceStats;
    private static float choiceWeight;
    private static String choiceActivityType;

    private static Map<Integer, String>  menuMain = new HashMap<>();
    private static Map<Integer, String>  menuActivities = new HashMap<>();
    private static Map<Integer, String>  menuStats = new HashMap<>();

    private static Scanner inputScanner = new Scanner(System.in);

    /**
     * @param args no arguments needed to run the app
     */
    public static void main(String[] args)  {

        loadMenusToMaps();

        /*creation of an object MusculationSets who will contain the Sets */
        MusculationSets muscuSets = new MusculationSets();

/*loop on the main menu till the user decide to leave (choice n°3) */
        while(choice != 3) {
            System.out.println(showHeadingMenu());
            choice = showMenu(menuMain);

            switch (choice) {
                /*choice n°1 : we get the characteristics of the Set, by asking the user and receiving the data*/
                case 1:
                    System.out.println(showHeadingMenu());

                    choiceActivity = showMenu(menuActivities);
                    /*get the label of the activity (value of the key retrieved in the choice)*/
                    choiceActivityType = menuActivities.get(choiceActivity);

                    choiceNbRep = askGetNbRep();

                    choiceWeight = askGetWeight();

                    /*call the method addSet from the muscusets object */
                    muscuSets.addSet(choiceActivityType, choiceNbRep, choiceWeight);

                    break;
                /* choice n°2 : we retrieve all the repetitions and weight for a given type of rep*/
                case 2:
                    System.out.println(showHeadingMenu());
                    choiceActivity = showMenu(menuActivities);
                    choiceActivityType = menuActivities.get(choiceActivity);

                    /*get all numbers of repetitions ans weight, by calling 2 methods from the object muscuSets */
                    int[] tabRep = muscuSets.getDataRepFromTypeOfSet(choiceActivityType);
                    float[] tabWeight = muscuSets.getDataWeightFromTypeOfSet(choiceActivityType);

                    /* print the menu statistics, retrieve the user choice and calculate statistics with the data */
                    /* retrieved just above */
                    System.out.println(showHeadingMenu());
                    choiceStats = showMenu(menuStats);
                    switch (choiceStats) {
                        case 1:
                            float maxWeight = 0.0F;
                            for (int i = 0; i < tabWeight.length; i++) {
                                if (tabWeight[i] > maxWeight) {
                                    maxWeight = tabWeight[i];
                                }
                            }
                            System.out.println("poids max soulevé: " + maxWeight + " kg");
                            break;

                        case 2:
                            int maxRep = 0;
                            for (int i = 0; i < tabRep.length; i++) {
                                if (tabRep[i] > maxRep) {
                                    maxRep = tabRep[i];
                                }
                            }
                            System.out.println("nombre max de répétitions: " + maxRep + " reps");
                            break;
                        case 3:
                            float maxWeightPerSet = 0.0F;
                            for (int i = 0; i < tabRep.length; i++) {

                                if (tabRep[i] * tabWeight[i] > maxWeightPerSet) {
                                    maxWeightPerSet = tabRep[i] * tabWeight[i];
                                }
                            }
                            System.out.println("poids max soulevé par set: " + maxWeightPerSet + "kg");
                            break;
                    }
                    break;
            }
        }
    }

    /**
     * print the menus header, which cannot be included in the hashmap
     * @return print the menus header
     */
    public static String showHeadingMenu(){
        String heading = "-------------------- Fitness Coach --------------------";
        return heading;
    }

    /**
     * load all menus structures into hashmaps
     */
    public static void loadMenusToMaps(){

        menuMain.put(1, "Ajouter un set");
        menuMain.put(2, "Afficher les performances sur un exercice");
        menuMain.put(3, "Quitter le programme");

        menuActivities.put(1, "SQUAT");
        menuActivities.put(2, "LEG_EXTENSION");
        menuActivities.put(3, "LEG_CURL");
        menuActivities.put(4, "LEG_PRESS");
        menuActivities.put(5, "CRUNCH");
        menuActivities.put(6, "PLANK");
        menuActivities.put(7, "BENCH_PRESS");
        menuActivities.put(8, "TRICEPS_EXTENSION");
        menuActivities.put(9, "BICEPS_CURL");

        menuStats.put(1, "Stats de poids (/ répétitions)");
        menuStats.put(2, "Stats de nombre répétitions");
        menuStats.put(3, "Stats de poids (/ set)");

    }

    /**
     * @param menuType  hashmap describing the menu structure
     * @return
     */
    public static int showMenu(Map<Integer, String> menuType){

        int choiceM = 1;
        boolean boucleOk ;

        Set menuSet = menuType.keySet();

//        for (String choice : menuSet) /*pourquoi ça ne marche pas ? */

        for (Integer choice : menuType.keySet()){
            System.out.println(choice + ". --> " + menuType.get(choice) );
        }

        do {
            System.out.println("-------------------- ############# --------------------");
            System.out.println("Entrez votre choix: ");
            try {
                choiceM = inputScanner.nextInt();
//                System.out.println(choiceM);
                boucleOk= true;

            } catch (InputMismatchException e){
                System.out.println("Saisie incorrecte");
                boucleOk = false;
                inputScanner.nextLine(); /* IL FAUT INITIALISER LE SCANNER SINON IL GARDE EN MEMOIRE LA SAISIE PRECEDENTE*/
            }

        } while ((choiceM < 0) || (choiceM > menuSet.size()) || (boucleOk == false));

        return choiceM;

    }

    /**
     *
     * @return the number of repetitions the user wants to add to the set
     */
    public static int askGetNbRep(){

        int choiceNbRepEntered = 1;
        boolean boucleOk = true;

        do {
            System.out.println("-------------------- ############# --------------------");
            System.out.println("Entrez le nb de répétitions: ");
            try {
                choiceNbRepEntered = inputScanner.nextInt();
                boucleOk= true;

            } catch (InputMismatchException e){
                System.out.println("Saisie incorrecte");
                boucleOk = false;
                inputScanner.nextLine(); /* IL FAUT INITIALISER LE SCANNER SINON IL GARDE EN MEMOIRE LA SAISIE PRECEDENTE*/
            }

        } while ((choiceNbRepEntered < 0) || (boucleOk == false));

        return choiceNbRepEntered;

    }

    /**
     * @return the weight the user wants to add to the set
     */
    public static float askGetWeight(){

        float choiceWeightEntered = 0.0F;
        boolean boucleOk = true;

        do {
            System.out.println("-------------------- ############# --------------------");
            System.out.println("Entrez le poids (décimales séparées par une \',\') : ");
            try {
                choiceWeightEntered = inputScanner.nextFloat();
                boucleOk= true;

            } catch (InputMismatchException e){
                System.out.println("Saisie incorrecte");
                System.out.println(e.getMessage());
                System.out.println(choiceWeightEntered);
                boucleOk = false;
                inputScanner.nextLine(); /* IL FAUT INITIALISER LE SCANNER SINON IL GARDE EN MEMOIRE LA SAISIE PRECEDENTE*/
            }

        } while ((choiceWeightEntered < 0) || (boucleOk == false));

        return choiceWeightEntered;

    }

}
