import org.omg.CORBA.CODESET_INCOMPATIBLE;

import java.util.ArrayList;
import java.util.Arrays;

/*
* L'idée d'un Graphe ici est créer un tableau de 2 dimension où chaque indice est correspondant avec un sommet
* Chaque élément dans le tableau est un valeur qui nous indique que si il existe l'arête entre deux sommet ou pas (1 si oui, -1 sinon)
* Exemple : G[2,3] = 1 Cele nous permet de dire qu'il y a un arête entre sommet 2 et sommet 3
* */
public class Graphe {

    private int[][] aretes_tab;

    public Graphe() {
        this.aretes_tab = new int[100][100];
        for(int i=0; i<100; i++){
            for(int j=0; j<100; j++){
                this.aretes_tab[i][j] = -1;
            }
        }
    }

    public Graphe(int height,int width) {
        this.aretes_tab = new int[height][width];
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                this.aretes_tab[i][j] = -1;
            }
        }
    }

    public Graphe(int[][] aretes_tab) {
        this.aretes_tab = aretes_tab;
    }

    /**
     * Vérifier si il y a de solution pour le probleme IC
     * @param k Nombre maximum d'arête
     * @param composantes des sous-ensemble qui contient des sommets
     * @return Nothing
     */
    public boolean checkICProblem(int k, ArrayList<Composante> composantes){
        int n = 0;
        for(Composante c: composantes){
            n+=c.getSommets().size()-1;
        }
        return n<=k;
    }

    public boolean checkICProblem(int k){
        ArrayList check_tab = new ArrayList();
        boolean[][] connected = new boolean[this.aretes_tab.length][this.aretes_tab[0].length];
        for(boolean[] arr1 : connected)
            Arrays.fill(arr1, false);
        for(int i=0; i<this.aretes_tab.length; i++) {
            for (int j = 0; j < this.aretes_tab[i].length; j++) {
                if(this.aretes_tab[i][j]==1 && !connected[j][i]){
                    check_tab.add(this.aretes_tab[i][j]);
                    connected[i][j] = true;
                }
            }
        }
        return check_tab.size() <= k;
    }


    /**
     * Vérifier et ajouter une arête entre deux sommets.
     * -1 il n'y pas d'arête entre deux sommets
     * 1 il existe une arête entre deux sommets
     * @param s1 Premier sommet
     * @param s2 Deuxième sommet
     * @return Nothing
     */
    public void ajouter_arete(Sommet s1, Sommet s2){
        if(this.aretes_tab[s1.getValue()][ s2.getValue()]==-1){     //On vérifie si une arête avec ces sommets existe déjà
            this.aretes_tab[s1.getValue()][ s2.getValue()] = 1;     //On ajoute l'arête entre les deux sommets
            this.aretes_tab[s2.getValue()][ s1.getValue()] = 1;     //On ajoute également l'arête symétrique
        }
    }

    /**
     * Monter un graphe en utilisant l'ensemble des composantes
     * @param composantes L'ensemble des composantes afin de créer le graphe
     * @return Nothing
     * @version: 1.0
     */
    public void buildGraphe(ArrayList<Composante> composantes){
        Sommet precedent = new Sommet(-1);  //precedent permet de garder en mémoire le sommet précédent
        for(Composante c: composantes){          //On itère sur chacune des composantes
            for(Sommet s: c.getSommets()){      //On itère sur chacun des sommets d'une composante
                if(precedent.getValue()!=-1)  //Dans le cas où le sommet actuel est le 1er, on ne fait rien
                    this.ajouter_arete(precedent, s);   //On ajoute l'arête entre le sommet actuel et le précédent
                precedent.setValue(s.getValue());   //On garde le sommet actuel en mémoire pour le prochain sommet
            }
            precedent.setValue(-1); //On réinitialise precedent avant de changer de composante
        }
    }

    /**
     * Monter un graphe en utilisant l'ensemble des composantes
     * @param composantes L'ensemble des composantes afin de créer le graphe
     * @return Nothing
     * @version: 2.0
     */
    public void buildGraphe2(ArrayList<Composante> composantes){
        for(Composante c: composantes){         //On itère sur chacune des composantes
            int length = c.getSommets().size();
            if(length > 1){                     //Dans le cas où une composante ne possède qu'un sommet, on ne fait rien
                for(int i=1; i<length; i++){    //On itère à partir de l'indice 1 pour ne pas prendre le 1er sommet
                    boolean au_moins_une = false;   //Boolean de vérification si une arête avec ce sommet existe déjà
                    for(int j=0; j<i; j++){
                        //On vérifie si une arête avec ces sommets existe déjà
                        if(aretes_tab[c.getSommets().get(i).getValue()][c.getSommets().get(j).getValue()] == 1)
                            au_moins_une = true;
                    }
                    //S'il n'y a pas d'arête existance avec ces sommets, on l'ajoute
                    if(!au_moins_une)
                        this.ajouter_arete(c.getSommets().get(i), c.getSommets().get(i-1));
                }
            }
        }
    }



    public int[][] getAretes_tab() {
        return aretes_tab;
    }

    public void setAretes_tab(int[][] aretes_tab) {
        this.aretes_tab = aretes_tab;
    }

    /**
     * Afficher tous les arêtes existant de ce graphe
     * @return String
     */
    public String toString(){
        //Trick to not duplicate edge
        boolean[][] connected = new boolean[this.aretes_tab.length][this.aretes_tab[0].length];
        for(boolean[] arr1 : connected)
            Arrays.fill(arr1, false);

        String res = "Vouz venez de créer un graphe à partir des composantes. L'ensemble des arêtes de ce graphe est comme ci-dessous: \n";
        for(int i=0; i<this.aretes_tab.length; i++) {
            for (int j = 0; j < this.aretes_tab[i].length; j++) {
                if(this.aretes_tab[i][j]==1 && connected[j][i]==false){
                    res+=i+"---"+j+"\n";
                    connected[i][j] = true;
                }
            }
        }
        return res;
    }

}
