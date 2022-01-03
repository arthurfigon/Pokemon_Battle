package br.com.pokemon.beans;

import util.Utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Treinador {

    String nome;
    int idade;
    int insignias;
    int pokebolas;
    ArrayList<Item> mochila = new ArrayList<Item>();
    ArrayList<Pokemon> time = new ArrayList<Pokemon>();
    String genero;

    public ArrayList<Pokemon> getTime() {
        return time;
    }

    public Treinador(String nome, int idade, String genero, Pokemon pokemon) {
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.insignias = 0;
        this.pokebolas = 5;
        this.time.add(pokemon);
    }

    public void addPokemon(Pokemon pokemon){
        this.time.add(pokemon);
    }

    private Pokemon escolhePokemon(int idEscolhido){
        for(Pokemon pokemon: time){
            if(idEscolhido == pokemon.getId()){
                return pokemon;
            }
        }
        return null;
    }


    public Pokemon pickPokemon(){
        util.Utils utils = new Utils();
        int[] ids = this.getIdsPokemons();
        System.out.println("\n\n\n\n\n\n");
        utils.balaoDebatalha("Pokémons:");
        System.out.println("\n");
        printaPokemons();
        int opcao = utils.LerInt("Escolha seu pokémon pelo id: ");
        while(!checkPokemonIsIn(ids, opcao)){
            printaPokemons();
            opcao = utils.LerInt("Escolha seu pokémon pelo id: ");
        }
        System.out.println("Você escolheu: "+getPokemonById(opcao).getNome()+"!");
        return this.getPokemonById(opcao);
    }

    private Pokemon getPokemonById(int id){
        for (Pokemon pokemon : this.time){
            if(pokemon.getId() == id){
                return pokemon;
            }
        }
        return null;
    }

    private ArrayList<Pokemon> sortPokemonListById(ArrayList<Pokemon> time){
        Pokemon slotParaTroca = new Pokemon();
        //ArrayList<Pokemon> timeOrdenado = this.time;
        for(int j = 0; j < time.size()-1; j++) {
            for (int i = 0; i < time.size()-1; i++) {
                if (time.get(i).getId() > time.get(i + 1).getId()) {
                    slotParaTroca = time.get(i);
                    time.set(i, time.get(i + 1));
                    time.set(i + 1, slotParaTroca);
                }
            }
        }
        return time;
    }

    private int[] getIdsPokemons(){
        int[] ids = new int[this.time.size()];
        int i = 0;
        for (Pokemon pokemon : this.time){
            ids[i] = pokemon.getId();
            i++;
        }
        return ids;
    }

    public Pokemon getFirstPokemon(){
        return time.get(0);
    }

    private void printaPokemons(){
        Utils ajuda = new Utils();
        ArrayList<Pokemon> listaOrdenadaPokemons = this.time;
        listaOrdenadaPokemons = this.sortPokemonListById(listaOrdenadaPokemons);
        for (Pokemon pokemon : listaOrdenadaPokemons){
            ajuda.balaoDebatalha(pokemon.getId()+" - "+pokemon.getNome()+ ": "+pokemon.getVidaAtual()+"/"+pokemon.getVidaTotal());
        }
    }

    public static void printaPokemon(Pokemon pokemon){
        Utils ajuda = new Utils();
        System.out.println(ajuda.balaoPequeno(pokemon.getId()+" - "+pokemon.getNome()+ ": "
                +pokemon.getVidaAtual()+"/"+pokemon.getVidaTotal()));
    }

    private boolean checkPokemonIsIn(int[] vetorDeIds, int id){
        return Arrays.stream(vetorDeIds).anyMatch(i -> i == id);
    }
}
