package br.com.pokemon.beans;

import java.util.ArrayList;

public class Pokemon {
    int id;
    String nome;
    int vidaAtual;
    int vidaTotal;
    ArrayList<Tipo> tipos = new ArrayList<Tipo>();
    ArrayList<Ataque> ataques = new ArrayList<Ataque>();
    int nivel;
    String genero;
    Boolean Shiny;

    public Pokemon() {
    }

    public Pokemon(int id, String nome, int vidaAtual, int vidaTotal, ArrayList<Tipo> tipos, ArrayList<Ataque> ataques, int nivel, String genero, Boolean shiny) {
        this.id = id;
        this.nome = nome;
        this.vidaAtual = vidaAtual;
        this.vidaTotal = vidaTotal;
        this.tipos = tipos;
        this.ataques = ataques;
        this.nivel = nivel;
        this.genero = genero;
        Shiny = shiny;
    }

    public ArrayList<Ataque> getAtaques() {
        return ataques;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public void setVidaAtual(int vida) {
        this.vidaAtual = vida;
    }

    public int getVidaTotal() {
        return vidaTotal;
    }

    public void setVidaTotal(int vida) {
        this.vidaTotal = vida;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public void perdeHP(int dano){
        this.vidaAtual -= dano;
    }
}
