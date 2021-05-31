package Algo;

import Type.Info;

import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;


public class ProgramationLinéaire {
    private double solution;

    public ProgramationLinéaire(Info info){
        int nbItems = info.getData().size();
        String lstr;

        Loader.loadNativeLibraries();
        MPSolver lSolver = new MPSolver("BinPacking1d", MPSolver.OptimizationProblemType.SAT_INTEGER_PROGRAMMING);
        //lSolver.setTimeLimit(50000);

        //Création de toute les variables
        MPVariable[][] x = new MPVariable[nbItems][nbItems];
        for (int i = 0; i < nbItems; i++){
            for (int j = 0; j < nbItems; j++){
                lstr = "X[" + i + "," + j + "]";
                x[i][j] = lSolver.makeBoolVar(lstr);
            }
        }
        MPVariable[] y = new MPVariable[nbItems];
        for (int j = 0; j < nbItems; j++){
            lstr = "Y" + j;
            y[j] = lSolver.makeBoolVar(lstr);
        }

        //Création des contraintes
        MPConstraint[] lConstraint1 = new MPConstraint[nbItems];
        for (int i = 0; i < nbItems; i++){
            lConstraint1[i] = lSolver.makeConstraint(1,1);
            for (int j = 0; j < nbItems; j++){
                lConstraint1[i].setCoefficient(x[i][j],1);
            }
        }
        MPConstraint[] lConstraint2 = new MPConstraint[nbItems];
        for (int j = 0; j < nbItems; j++){
            lConstraint2[j] = lSolver.makeConstraint(-info.getSize(), 0);
            for (int i = 0; i < nbItems; i++){
                lConstraint2[j].setCoefficient(x[i][j], info.getData().get(i));
                lConstraint2[j].setCoefficient(y[j], -info.getSize());
            }
        }

        //function
        MPObjective lObjective = lSolver.objective();
        for (int j = 0; j < nbItems; j++){
            lObjective.setCoefficient(y[j], 1);
        }
        lObjective.setMinimization();

        MPSolver.ResultStatus resultStatus = lSolver.solve();
        if ((resultStatus == MPSolver.ResultStatus.FEASIBLE)){
            if (resultStatus == MPSolver.ResultStatus.OPTIMAL){
                System.out.println("An optimal solution has been found in " +lSolver.wallTime() + "ms" );
            } else {
                System.out.println("A feasible solution has been found in " + lSolver.wallTime() + "ms");
            }
            this.solution = lObjective.value();
        } else {
            System.out.println("No feasible solution has been found");
            this.solution = 0.0;
        }
    }

    public double getSolution() {
        return solution;
    }
}
