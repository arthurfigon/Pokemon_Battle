package br.com.pokemon.beans;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {


        // Tipo Fogo para Pokemons
        Tipo fogo = new Tipo();
        // Tipo Agua para Pokemons
        Tipo agua = new Tipo();
        // Tipo Normal para Pokemons
        Tipo normal = new Tipo();


        // Cria um ataque Bubble
        Ataque bubble = new Ataque(02,"Bubble", 10, agua);
        // Cria um ataque Ember
        Ataque ember = new Ataque(01,"Ember", 10, fogo);
        // Cria um ataque Tackle
        Ataque tackle = new Ataque(03,"Tackle", 5, normal);


        // Cria dois pokemons com seus respectivos parametros
        Pokemon charmanderInicial = new Pokemon(04,"Charmander",25,25,
                fogo, ember,05,"Masculino",false);
        Pokemon squirtleInicial = new Pokemon(07,"Squirtle",25,25,
                agua, bubble,05,"Feminino",false);
        Pokemon rattata = new Pokemon(19,"Ratatta",12,12, normal,
                tackle,03,"Masculino",false);

        // Adiciona os parametros pro treinador assim como o pokemon inicial
        Treinador arthur = new Treinador("arthur",25, "Masculino", charmanderInicial);
        
        // Adiciona um pokemon adicional para o treinador
        arthur.addPokemon(rattata);

        // Criar uma batalha entre o trainer e um pokemon
        Batalha batalhaInicial = new Batalha(arthur.pickPokemon(),squirtleInicial);
        batalhaInicial.startBattle();
    }
}
