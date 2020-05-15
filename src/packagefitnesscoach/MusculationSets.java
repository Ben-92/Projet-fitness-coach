package packagefitnesscoach;
/* à voir */
/*si le fichier n'existe pas au départ, il me fait une erreur IndexOutOfBoundException et je ne sais pas pkoi*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MusculationSets {

    List<Set> listOfSets = new ArrayList<Set>();
    static final String path = "./Ressources/Exemple1";

    /**
     * constructor of the object containing a list of set
     * read the csv file containing the sets and load each in an arrayList of sets
     */
    /*on peut ausi déporter la lecture csv et le load, dans une méthode plus bas dans la classe, en private*/
    public MusculationSets ()  {

        File file = new File(path);
        try (BufferedReader bufferR = new BufferedReader(new FileReader(file))) {
            String lineRead ; // = bufferR.readLine();
        bufferR.readLine();

            while ((lineRead = bufferR.readLine()) != null) { //SI ON VEUT SAUTER LA LIGNE 1, METTRE LA LECTURE DANS LA CONDITION WHILE
                String[] tabSet = lineRead.split(";");
                Set newSet = new Set(tabSet[0], Integer.parseInt(tabSet[1]), Float.parseFloat(tabSet[2]));
                listOfSets.add(newSet);

            }
        }
        catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier");
            System.out.println("libellé erreur : " + e.getMessage());
            System.out.println("Veuillez corriger l\'origine du pb avant de relancer l'application");
        }
    }

    /**
     * method  which add a set to an object of the musculationSets class
     * @param type type of the set added
     * @param reps number of repetitions of the set added
     * @param weight weight of the set added
     */
    //pas besoin de mettre static car on a un créé un objet sur lequel va s'appliquer la méthode
    public  void addSet(String type, int reps, float weight)  {

       Set setToAdd = new Set(type, reps, weight);

       listOfSets.add(setToAdd);
       File file = new File(path);

        try(
        BufferedWriter bufferW = new BufferedWriter(new FileWriter(file, true)))
        {
        String setToAddToFile = type + ";" + reps + ";" + weight;

        bufferW.newLine();
        bufferW.write(setToAddToFile); }
        catch (IOException e) {
            System.out.println("Erreur lors de l'écriture dans le fichier csv");
        }

    }

    /**
     * retrieve an array of the number of repetitions for a given type of set
     * @param setToRetrieve type of set from which we want to retrieve data
     * @return an array of the number of repetitions for a given type of set
     */
    //pas besoin de mettre static car on a un créé un objet sur lequel va s'appliquer la méthode
    public  int [] getDataRepFromTypeOfSet(String setToRetrieve) {

        int tabOfReps[] = new int[listOfSets.size()];
        String typeOfSet;

        for (int i = 0; i < listOfSets.size(); i++) {
            typeOfSet = listOfSets.get(i).getTypeOfSet();
            if (typeOfSet.equals(setToRetrieve)) {
                tabOfReps[i] = listOfSets.get(i).getNbOfReps();
            }
        }
        return tabOfReps;
    }

    /**
     * retrieve an array of the weights for a given type of set
     * @param setToRetrieve type of set from which we want to retrieve data
     * @return an array of the weights for a given type of set
     */
    public  float [] getDataWeightFromTypeOfSet(String setToRetrieve) {

        float tabOfWeight[] = new float[listOfSets.size()];
        String typeOfSet;

        for (int i = 0; i < listOfSets.size(); i++) {
            typeOfSet = listOfSets.get(i).getTypeOfSet();
            if (typeOfSet.equals(setToRetrieve)) {
                tabOfWeight[i] = listOfSets.get(i).getWeightOfReps();
            }
        }
        return tabOfWeight;
    }

}
