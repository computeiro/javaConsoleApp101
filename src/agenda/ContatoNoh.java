package agenda;

public class ContatoNoh extends Contato {
    private ContatoNoh pai;
    private ContatoNoh esq;
    private ContatoNoh dir;
    private int balanceamento;
    
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

    public ContatoNoh getPai() {
	return pai;
    }

    public void setPai(ContatoNoh pai) {
	this.pai = pai;
    }

    public int getBalanceamento() {
	return balanceamento;
    }

    public void setBalanceamento(int balanceamento) {
	this.balanceamento = balanceamento;
    }
}
