import Algo.*;

import Function.LoadData;
import Type.Info;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        long debut;
        long fin;

        //Pour chosir son fichier parmis les fichiers contenu dans graph
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le nom du fichier avec l'extention:");
        String NomFichier = sc.nextLine();
        sc.close();

        //Récupération des données
        Info info;
        debut = System.currentTimeMillis();
        LoadData data = new LoadData("binpack1d_00.txt");
        info = data.getInfo();
        fin = System.currentTimeMillis() - debut;
        System.out.println("Les données on été récupéré en " + fin +"ms\n");

        //Résumé
        System.out.println("Nombre d'item : " + info.getData().size());
        System.out.println("Taille des boites : " + info.getSize());
        int total = 0;
        for(Integer i:info.getData()) {
            //System.out.println(i);
            total += i;
        }

        //Calcule théorique
        System.out.println("En théorie la ne nombre doit etre de : " + total/info.getSize() + " boites\n");

        //Algo FirstFitDecreasing
        debut = System.currentTimeMillis();
        FirstFitDecreasing firstFitDecreasing = new FirstFitDecreasing(info);
        fin = System.currentTimeMillis() - debut;
        System.out.println("Le cacul algo FirstFitDecreasing à pris " + fin + "ms avec " + info.getBins().size() + " boite et une fitness = " + firstFitDecreasing.getObjectif() + "\n");

        //Algo ProgramationLineaire
        debut = System.currentTimeMillis();
        ProgramationLinéaire programationLinéaire = new ProgramationLinéaire(info);
        fin = System.currentTimeMillis() - debut;
        System.out.println("Le cacul algo de programation à pris " + fin + "ms avec une solution de " + programationLinéaire.getSolution() + "\n");

        //Algo 1 bin = 1 item
        debut = System.currentTimeMillis();
        UnBinParItem unBinParItem = new UnBinParItem(info);
        fin = System.currentTimeMillis() - debut;
        System.out.println("Le cacul algo de 1 bien par item à pris " + fin + "ms avec une solution de " + info.getBins().size() + " boite et une fitness = " + unBinParItem.getObjectif() + "\n");

        //Algo Random
        debut = System.currentTimeMillis();
        RandomBin randomBin = new RandomBin(info);
        fin = System.currentTimeMillis() - debut;
        System.out.println("Le cacul algo de randomBin à pris " + fin + "ms avec une solution de " + info.getBins().size() + " boite et une fitness = " + randomBin.getObjectif() + "\n");

        //Algo FirstFitDecreasing random
        debut = System.currentTimeMillis();
        FirstFistDecreasingRand firstFistDecreasingRand = new FirstFistDecreasingRand(info);
        fin = System.currentTimeMillis() - debut;
        System.out.println("Le cacul algo de FirstFistDecreasingRand à pris " + fin + "ms avec une solution de " + info.getBins().size() + " boite et une fitness = " + firstFistDecreasingRand.getObjectif() + "\n");

        //Algo Recuit Simulé
        debut = System.currentTimeMillis();
        RecuitSimule recuitSimule = new RecuitSimule(info);
        fin = System.currentTimeMillis() - debut;
        System.out.println("Le cacul algo de Recuit Simulé à pris " + fin + "ms avec une solution de " + info.getBins().size() + " boite et une fitness = " + recuitSimule.getScore() + "\n");

        //Algo Tabu Search
        debut = System.currentTimeMillis();
        TabuSearch tabuSearch = new TabuSearch(info);
        fin = System.currentTimeMillis() - debut;
        System.out.println("Le cacul algo de recherche tabu à pris " + fin + "ms avec une solution de " + info.getBins().size() + " boite et une fitness = " + tabuSearch.getScore() + "\n");
    }
}
