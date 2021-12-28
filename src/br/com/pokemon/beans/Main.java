package br.com.pokemon.beans;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Cria um ataque Ember, e coloca em uma lista de ataques inicias pro pokemon
        Ataque ember = new Ataque(01,"Ember", 10);
        ArrayList<Ataque> ataquesEmber = new ArrayList<>();
        ataquesEmber.add(ember);

        // Como Pokemons podem ter até 2 tipos, ele recebe um array de tipos, pro pokemon
        ArrayList<Tipo> tipoFogo = new ArrayList<>();
        Tipo fogo = new Tipo();


        // Cria um ataque e coloca em uma lista de ataques inicias pro pokemon
        Ataque bubble = new Ataque(02,"Bubble", 10);
        ArrayList<Ataque> ataquesBubble = new ArrayList<>();
        ataquesBubble.add(bubble);

        // Como Pokemons podem ter até 2 tipos, ele recebe um array de tipos, pro pokemon
        ArrayList<Tipo> tipoAgua = new ArrayList<>();
        Tipo agua = new Tipo();

        // Cria dois pokemons com seus respectivos parametros
        Pokemon charmanderInicial = new Pokemon(04,"Charmander",25,25,
                tipoFogo, ataquesEmber,05,"Masculino",false);
        Pokemon squirtleInicial = new Pokemon(07,"Squirtle",25,25,
                tipoAgua, ataquesBubble,05,"Feminino",false);

        // Adiciona os parametros pro treinador assim como o pokemon inicial
        Treinador arthur = new Treinador("arthur",25, "Masculino", charmanderInicial);

        // Criar pokemons selvagens;
        Ataque tackle = new Ataque(01,"Tackle", 5);
        ArrayList<Ataque> ataquesRattataNormal = new ArrayList<>();
        ataquesRattataNormal.add(tackle);
        ArrayList<Tipo> listaDeTiposRattata = new ArrayList<>();
        Tipo normal = new Tipo();
        Pokemon rattata = new Pokemon(19,"Ratatta",12,12, listaDeTiposRattata,
                ataquesRattataNormal,03,"Masculino",false);

        // Adiciona um pokemon adicional para o treinador
        arthur.addPokemon(rattata);

        // Comando para escolher qual pokemon vai batalhar pelo treinador...
        // arthur.pickPokemon();

        // Criar uma batalha entre o trainer e um pokemon
        Batalha batalhaInicial = new Batalha(arthur.pickPokemon(),squirtleInicial);
        batalhaInicial.startBattle();
    }
}
