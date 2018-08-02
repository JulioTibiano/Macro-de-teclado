package main;

import parte1.Item;

public class FilaPrioridade<T> {

    public static class Celula {

        Object item;
        int prioridade;
        Celula prox;
        
    }
    private Celula frente;
    private Celula tras;

    public FilaPrioridade() {
        this.frente = new Celula();
        this.tras = this.frente;
        this.frente.prox = null;
    }

    public void insere(Object x, int prioridade) {
        this.tras.prox = new Celula();
        this.tras = this.tras.prox;
        this.tras.item = x;
        this.tras.prioridade = prioridade;
        this.tras.prox = null;
    }

    public Celula remove() throws Exception {
        if (this.isVazia()) {
            throw new Exception("Erro : A fila esta vazia");
        }

        Celula aux = this.frente.prox;
        int prioritario = aux.prioridade;
        while (aux != null) {
            if (aux.prioridade < prioritario) {
                prioritario = aux.prioridade;
            }
            aux = aux.prox;
        }

        aux = this.frente;

        while (aux.prox.prioridade != prioritario) {
            aux = aux.prox;
        }
        Celula tmp = aux.prox;
        Object item = tmp.item;
        aux.prox = aux.prox.prox;
        if (aux.prox == null) {
            this.tras = aux;
        }
        return tmp;
    }

    public boolean isVazia() {
        return this.tamanho() == 0;
    }

    public void imprime() {
        Celula aux = this.frente.prox;
        while (aux != null) {
            System.out.println(" " + ((Item) aux.item).toString());
            aux = aux.prox;
        }
        System.out.println();
    }

    public void insereOrdenado(Object x, int prioridade) {
        Celula aux = this.frente;
        while (aux.prox != null && prioridade > aux.prox.prioridade) {
            aux = aux.prox;
        }

        Celula tmp = aux.prox;
        aux.prox = new Celula();
        aux.prox.item = x;
        aux.prox.prioridade = prioridade;
        aux.prox.prox = tmp;
    }

    public int tamanho() {
        int count = 0;
        Celula aux = this.frente;
        while (aux.prox != null) {
            count++;
            aux = aux.prox;
        }

        return count;
    }

    public void reseta() {
        Celula aux = this.frente;
        aux.prox = null;
        tras = frente;
    }
}
