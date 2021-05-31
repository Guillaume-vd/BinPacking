package Function;

import Type.Info;

import java.util.Random;

public class Voisinage {

    public Voisinage (){

    }

    public void deplaceRecuit(Info info) {
        int nbIteration = 0;
        boolean deplace = false;

        //Tant que le déplacement et un nombre d'itération max pour ne pas rester infiniment bloquer dans la boucle
        while (nbIteration < 200 && !deplace) {
            Random random = new Random();
            int source = random.nextInt(info.getBins().size());
            int destination = random.nextInt(info.getBins().size());
            int itemNumber = random.nextInt(info.getBins().get(source).getData().size());

            //On met plusieurs condition pour pas en avoir une seul trop longue
            if (info.getBins().get(source).getNbBin() <= itemNumber) {//Si l'item dans le bin
                //System.out.println("1");
            } else if(source == destination){//Si la destination est différentes de la source
                //System.out.println("3");
            } else if (info.getBins().get(destination).getSizeAvailable() < info.getBins().get(source).getData().get(itemNumber)) { //Si la place n'est pas suffisante
                //System.out.println("2");
            } else  { //Si c'est bon
                info.getBins().get(destination).addData(info.getBins().get(source).getData().get(itemNumber));
                info.getBins().get(source).removeData(itemNumber);
                if (info.getBins().get(source).getData().isEmpty()) //si le bin utiliser est vide
                    info.getBins().remove(source);
                deplace = true;
            }

            //Pour finir on incrément le nombre de tentative
            nbIteration++;
        }
    }

    public void swapRecuit(Info info) {
        int nbIteration = 0;
        boolean swapOK = false;

        //tant que l'échange n'a pas été fait et un nombre d'itération max pour ne pas rester infiniment bloquer dans la boucle
        while (!swapOK && nbIteration < 300) {
            Random random = new Random();
            int source = random.nextInt(info.getBins().size());
            int destination = random.nextInt(info.getBins().size());
            int sourceItemNumber = random.nextInt(info.getBins().get(source).getData().size());
            int destinationItemNumber = random.nextInt(info.getBins().get(destination).getData().size());

            //On met plusieurs condition pour pas en avoir une seul trop longue
            if (source == destination) {//Si échange dans un même bin
                //System.out.println("1");
            } else if (info.getBins().get(source).getData().size() <= sourceItemNumber) { //Si l'item n'est pas présente dans le bin source
                //System.out.println("2");
            } else if (info.getBins().get(source).getData().size() <= destinationItemNumber) { //Si l'item n'est pas présente dans le bin destination
                //System.out.println("3");
            } else if (info.getBins().get(destination).getSizeAvailable() + info.getBins().get(destination).getData().get(destinationItemNumber) < info.getBins().get(source).getData().get(sourceItemNumber)) { //Il n'y a pas assez de place dans le bin source
                //System.out.println("4");
            } else if (info.getBins().get(source).getSizeAvailable() + info.getBins().get(source).getData().get(sourceItemNumber) < info.getBins().get(destination).getData().get(destinationItemNumber)) { //Il n'y a pas assez de place dans le bin destination
                //System.out.println("5");
            } else { //Si tout va bien
                int size = info.getBins().get(destination).getData().get(destinationItemNumber);
                info.getBins().get(destination).removeData(destinationItemNumber);
                info.getBins().get(destination).addData(info.getBins().get(source).getData().get(sourceItemNumber));
                info.getBins().get(source).removeData(sourceItemNumber);
                info.getBins().get(source).addData(size);
                swapOK = true;
            }

            //Pour finir on incrément le nombre de tentative
            nbIteration++;
        }
    }

    public boolean swapTabu(Info info, int source, int sourceItemNumber, int destination, int destinationItemNumber) {
        //On met plusieurs condition pour pas en avoir une seul trop longue
        if (source == destination) {//Si échange dans un même bin
            //System.out.println("1");
        } else if (info.getBins().get(source).getData().size() <= sourceItemNumber) { //Si l'item n'est pas présente dans le bin source
            //System.out.println("2");
        } else if (info.getBins().get(source).getData().size() <= destinationItemNumber) { //Si l'item n'est pas présente dans le bin destination
            //System.out.println("3");
        } else if (info.getBins().get(destination).getSizeAvailable() + info.getBins().get(destination).getData().get(destinationItemNumber) < info.getBins().get(source).getData().get(sourceItemNumber)) { //Il n'y a pas assez de place dans le bin source
            //System.out.println("4");
        } else if (info.getBins().get(source).getSizeAvailable() + info.getBins().get(source).getData().get(sourceItemNumber) < info.getBins().get(destination).getData().get(destinationItemNumber)) { //Il n'y a pas assez de place dans le bin destination
            //System.out.println("5");
        } else { //Si tout va bien
            int size = info.getBins().get(destination).getData().get(destinationItemNumber);
            info.getBins().get(destination).removeData(destinationItemNumber);
            info.getBins().get(destination).addData(info.getBins().get(source).getData().get(sourceItemNumber));
            info.getBins().get(source).removeData(sourceItemNumber);
            info.getBins().get(source).addData(size);
            return true;
        }
        return false;
    }

    public boolean deplaceTabu(Info info, int source, int itemNumber, int destination) {
        //On met plusieurs condition pour pas en avoir une seul trop longue
        if (info.getBins().get(source).getNbBin() <= itemNumber) {//Si l'item dans le bin
            //System.out.println("1");
            return false;
        } else if (info.getBins().get(destination).getSizeAvailable() < info.getBins().get(source).getData().get(itemNumber)) { //Si la place n'est pas suffisante
            //System.out.println("2");
            return false;
        } else  { //Si c'est bon
            info.getBins().get(destination).addData(info.getBins().get(source).getData().get(itemNumber));
            info.getBins().get(source).removeData(itemNumber);
            if (info.getBins().get(source).getData().isEmpty()) //si le bin utiliser est vide
                info.getBins().remove(source);
            return true;
        }
    }
}
