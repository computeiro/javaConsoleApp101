package agenda;

public class ArvoreAVL {
    private ContatoNoh raiz;

    public void inserir(ContatoNoh contato) {
	inserirAVL(this.raiz, contato);
    }

    public void inserirAVL(ContatoNoh aComparar, ContatoNoh aInserir) {

	if (aComparar == null) {
	    this.raiz = aInserir;

	} else {

	    if (aInserir.getNome().compareTo(aComparar.getNome()) < 0) {

		if (aComparar.getEsq() == null) {
		    aComparar.setEsq(aInserir);
		    aInserir.setPai(aComparar);
		    verificarBalanceamento(aComparar);

		} else {
		    inserirAVL(aComparar.getEsq(), aInserir);
		}

	    } else if (aInserir.getNome().compareTo(aComparar.getNome()) > 1) {

		if (aComparar.getDir() == null) {
		    aComparar.setDir(aInserir);
		    aInserir.setPai(aComparar);
		    verificarBalanceamento(aComparar);

		} else {
		    inserirAVL(aComparar.getDir(), aInserir);
		}

	    } else {
		// O nó já existe
	    }
	}
    }

    public void verificarBalanceamento(ContatoNoh atual) {
	setBalanceamento(atual);
	int balanceamento = atual.getBalanceamento();

	if (balanceamento == -2) {

	    if (altura(atual.getEsq().getEsq()) >= altura(atual.getEsq().getDir())) {
		atual = rotacaoDireita(atual);

	    } else {
		atual = duplaRotacaoEsquerdaDireita(atual);
	    }

	} else if (balanceamento == 2) {

	    if (altura(atual.getDir().getDir()) >= altura(atual.getDir().getEsq())) {
		atual = rotacaoEsquerda(atual);

	    } else {
		atual = duplaRotacaoDireitaEsquerda(atual);
	    }
	}

	if (atual.getPai() != null) {
	    verificarBalanceamento(atual.getPai());
	} else {
	    this.raiz = atual;
	}
    }

    public void remover(String nome) {
	removerAVL(this.raiz, nome);
    }

    public void removerAVL(ContatoNoh atual, String nome) {
	if (atual == null) {
	    return;

	} else {

	    // if (atual.getNome() > k) {
	    if (atual.getNome().compareTo(nome) > 0) {
		removerAVL(atual.getEsq(), nome);
	    } else if (atual.getNome().compareTo(nome) < 0) {
		removerAVL(atual.getDir(), nome);
	    } else {
		removerNoEncontrado(atual);
	    }
	}
    }

    public void removerNoEncontrado(ContatoNoh aRemover) {
	ContatoNoh r = null;

	if (aRemover.getEsq() == null || aRemover.getDir() == null) {

	    if (aRemover.getPai() == null) {
		this.raiz = null;
		aRemover = null;
		return;
	    }
	    r = aRemover;

	} else {
	    r = buscaSucessor(aRemover);
	    
	    aRemover.setNome(r.getNome());
	    aRemover.setCelular(r.getCelular());
	    aRemover.setEmail(r.getEmail());
	}

	ContatoNoh p = null;
	
	if (r.getEsq() != null) {
	    p = r.getEsq();
	} else {
	    p = r.getDir();
	}

	if (p != null) {
	    p.setPai(r.getPai());
	}

	if (r.getPai() == null) {
	    this.raiz = p;
	} else {
	    if (r == r.getPai().getEsq()) {
		r.getPai().setEsq(p);
	    } else {
		r.getPai().setDir(p);
	    }
	    verificarBalanceamento(r.getPai());
	}
	
	r = null;
    }

    public ContatoNoh rotacaoEsquerda(ContatoNoh inicial) {

	ContatoNoh direita = inicial.getDir();
	direita.setPai(inicial.getPai());

	inicial.setDir(direita.getEsq());

	if (inicial.getDir() != null) {
	    inicial.getDir().setPai(inicial);
	}

	direita.setEsq(inicial);
	inicial.setPai(direita);

	if (direita.getPai() != null) {

	    if (direita.getPai().getDir() == inicial) {
		direita.getPai().setDir(direita);

	    } else if (direita.getPai().getEsq() == inicial) {
		direita.getPai().setEsq(direita);
	    }
	}

	setBalanceamento(inicial);
	setBalanceamento(direita);

	return direita;
    }

    public ContatoNoh rotacaoDireita(ContatoNoh inicial) {

	ContatoNoh esquerda = inicial.getEsq();
	esquerda.setPai(inicial.getPai());

	inicial.setEsq(esquerda.getDir());

	if (inicial.getEsq() != null) {
	    inicial.getEsq().setPai(inicial);
	}

	esquerda.setDir(inicial);
	inicial.setPai(esquerda);

	if (esquerda.getPai() != null) {

	    if (esquerda.getPai().getDir() == inicial) {
		esquerda.getPai().setDir(esquerda);

	    } else if (esquerda.getPai().getEsq() == inicial) {
		esquerda.getPai().setEsq(esquerda);
	    }
	}

	setBalanceamento(inicial);
	setBalanceamento(esquerda);

	return esquerda;
    }

    public ContatoNoh duplaRotacaoEsquerdaDireita(ContatoNoh inicial) {
	inicial.setEsq(rotacaoEsquerda(inicial.getEsq()));
	return rotacaoDireita(inicial);
    }

    public ContatoNoh duplaRotacaoDireitaEsquerda(ContatoNoh inicial) {
	inicial.setDir(rotacaoDireita(inicial.getDir()));
	return rotacaoEsquerda(inicial);
    }

    public ContatoNoh buscaSucessor(ContatoNoh q) {
	if (q.getDir() != null) {
	    
	    ContatoNoh r = q.getDir();
	    
	    while (r.getEsq() != null) {
		r = r.getEsq();
	    }
	    
	    return r;
	} else {
	    ContatoNoh p = q.getPai();
	    
	    while (p != null && q == p.getDir()) {
		q = p;
		p = q.getPai();
	    }
	    return p;
	}
    }

    private int altura(ContatoNoh atual) {
	if (atual == null) {
	    return -1;
	}

	if (atual.getEsq() == null && atual.getDir() == null) {
	    return 0;

	} else if (atual.getEsq() == null) {
	    return 1 + altura(atual.getDir());

	} else if (atual.getDir() == null) {
	    return 1 + altura(atual.getEsq());

	} else {
	    return 1 + Math.max(altura(atual.getEsq()), altura(atual.getDir()));
	}
    }

    private void setBalanceamento(ContatoNoh no) {
	no.setBalanceamento(altura(no.getDir()) - altura(no.getEsq()));
    }
}
