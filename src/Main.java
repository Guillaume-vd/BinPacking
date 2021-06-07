import Algo.*;

import Function.LoadData;
import Type.Info;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        long debut;
        long fin;

        //Pour chosir son fichier parmis les fichiers contenus dans graph
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le nom du fichier avec l'extention:");
        String nomFichier = sc.nextLine();

        //Récupération des données
        Info info;
        debut = System.currentTimeMillis();
        LoadData data = new LoadData(nomFichier);
        info = data.getInfo();
        fin = System.currentTimeMillis() - debut;
        System.out.println("Les données ont été récupérées en " + fin +" ms\n");

        //Résumé
        System.out.println("Nombre d'items : " + info.getData().size());
        System.out.println("Taille des boites : " + info.getSize());
        int total = 0;
        for(Integer i:info.getData()) {
            //System.out.println(i);
            total += i;
        }

        //Calcul théorique
        System.out.println("En théorie le nombre doit être de : " + total/info.getSize() + " boites\n");

        //Algo FirstFitDecreasing
        debut = System.currentTimeMillis();
        FirstFitDecreasing firstFitDecreasing = new FirstFitDecreasing(info);
        fin = System.currentTimeMillis() - debut;
        System.out.println("Le cacul avec FirstFitDecreasing a pris " + fin + " ms avec " + info.getBins().size() + " boites et une fitness = " + firstFitDecreasing.getObjectif() + "\n");

        //Algo ProgramationLineaire
        /*debut = System.currentTimeMillis();
        LinearSolver linearSolver = new LinearSolver(info);
        fin = System.currentTimeMillis() - debut;
        System.out.println("Le cacul avec Linear Solver a pris " + fin + " ms avec une solution égale à " + linearSolver.getSolution() + "\n");
*/
        //Algo 1 bin = 1 item
        debut = System.currentTimeMillis();
        UnBinParItem unBinParItem = new UnBinParItem(info);
        fin = System.currentTimeMillis() - debut;
        System.out.println("Le cacul avec 1 bin par item a pris " + fin + " ms avec " + info.getBins().size() + " boites et une fitness = " + unBinParItem.getObjectif() + "\n");

        //Algo FirstFitDecreasing random
        debut = System.currentTimeMillis();
        FirstFistDecreasingRand firstFistDecreasingRand = new FirstFistDecreasingRand(info);
        fin = System.currentTimeMillis() - debut;
        System.out.println("Le cacul avec FirstFistDecreasingRand a pris " + fin + " ms avec " + info.getBins().size() + " boites et une fitness = " + firstFistDecreasingRand.getObjectif() + "\n");

        //Algo Recuit Simulé
        System.out.println("Veuillez saisir le nombre d'itérations (int)");
        info.setRecuitIeration(sc.nextInt());
        System.out.println("Veuillez saisir la température initiale (double)");
        info.setTempRecuit(sc.nextDouble());
        debut = System.currentTimeMillis();
        RecuitSimule recuitSimule = new RecuitSimule(info);
        fin = System.currentTimeMillis() - debut;
        System.out.println("Le cacul avec Recuit Simule a pris " + fin + " ms avec " + info.getBins().size() + " boites et une fitness = " + recuitSimule.getScore() + "\n");

        //Algo TabuSearch
        System.out.println("Veuillez saisir la taille maximum de la liste tabou (int)");
        info.setTabuMax(sc.nextInt());
        System.out.println("Veuillez saisir le nombre d'itérations (int)");
        info.setTabouIteration(sc.nextInt());
        debut = System.currentTimeMillis();
        TabuSearch tabuSearch = new TabuSearch(info);
        fin = System.currentTimeMillis() - debut;
        System.out.println("Le cacul avec TabuSearch a pris " + fin + "ms avec une solution de " + info.getBins().size() + " boites et une fitness = " + tabuSearch.getScore() + "\n");
        sc.close();
    }
}
