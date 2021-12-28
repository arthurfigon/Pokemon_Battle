package br.com.pokemon.beans;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Cria um ataque Flamethower, e coloca em uma lista de ataques inicias pro pokemon
        Ataque flameThrower = new Ataque(01,"Flamethrower", 80);
        ArrayList<Ataque> listaDeAtaques = new ArrayList<>();
        listaDeAtaques.add(flameThrower);

        // Como Pokemons podem ter até 2 tipos, ele recebe um array de tipos, pro pokemon
        ArrayList<Tipo> listaDeTipos = new ArrayList<>();
        Tipo fogo = new Tipo();

        // Cria dois pokemons com seus respectivos parametros
        Pokemon charmanderInicial = new Pokemon(04,"Charmander",25,25, listaDeTipos, listaDeAtaques,05,"Masculino",false);
        Pokemon squirtleInicial = new Pokemon(01,"Squirtle",30,30, listaDeTipos, listaDeAtaques,05,"Feminino",false);

        // Adiciona os parametros pro treinador assim como o pokemon inicial
        Treinador arthur = new Treinador("arthur",25, "Masculino", charmanderInicial);

        // Adiciona um pokemon adicional para o treinador
        arthur.addPokemon(squirtleInicial);

        // Comando para escolher qual pokemon vai batalhar pelo treinador...
        arthur.pickPokemon();

    }
}
