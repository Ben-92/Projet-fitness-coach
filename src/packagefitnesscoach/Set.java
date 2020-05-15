package packagefitnesscoach;

public class Set {

    private String typeOfSet;
    private int nbOfReps;
    private float weightOfReps;

    /**
     * constructor of the set object
     * @param typeOfSet type of the set added
     * @param nbOfReps number of repetitions for the set added
     * @param weightOfRep weight of the set added
     */
    public Set (String typeOfSet, int nbOfReps, float weightOfRep){
        this.typeOfSet = typeOfSet;
        this.nbOfReps = nbOfReps;
        this.weightOfReps = weightOfRep;
    }


    public String toString (){
        return "Type: " + typeOfSet + " Nb rep: " + nbOfReps + " Weight: " + weightOfReps  ;
    }


    public String getTypeOfSet() {
        return typeOfSet;
    }

    public void setTypeOfSet(String typeOfSet) {
        this.typeOfSet = typeOfSet;
    }

    public int getNbOfReps() {
        return nbOfReps;
    }

    public void setNbOfReps(int nbOfReps) {
        this.nbOfReps = nbOfReps;
    }

    public float getWeightOfReps() {
        return weightOfReps;
    }

    public void setWeightOfReps(float weightOfReps) {
        this.weightOfReps = weightOfReps;
    }
}
