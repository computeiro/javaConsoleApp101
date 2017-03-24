package agenda;

public class ContatoNoh extends Contato{
    private ContatoNoh esq;
    private ContatoNoh dir;
    
    public ContatoNoh getEsq() {
        return esq;
    }
    public void setEsq(ContatoNoh esq) {
        this.esq = esq;
    }
    public ContatoNoh getDir() {
        return dir;
    }
    public void setDir(ContatoNoh dir) {
        this.dir = dir;
    }
}
