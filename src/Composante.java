import java.util.ArrayList;

/*
 * Une Composante s'agit un sous-graphe connexe monté de un des sous-ensembles qui contient des sommets
 * */
public class Composante {

    private ArrayList<Sommet> sommets;

    public Composante() {
        sommets = new ArrayList<>();
    }

    public Composante(ArrayList<Sommet> sommets) {
        this.sommets = sommets;
    }

    public ArrayList<Sommet> getSommets() {
        return sommets;
    }

    public void setSommets(ArrayList<Sommet> sommets) {
        this.sommets = sommets;
    }

    public void ajouter_sommet(Sommet s){
        sommets.add(s);
    }

    public void ajouter_sommets(ArrayList<Sommet> _sommets){
        for (Sommet s : _sommets) {
            sommets.add(s);
        }
    }

    public int supprimer_sommet(Sommet s){
        if(sommets.remove(s)) return s.getValue();
        else return -1;
    }
}
