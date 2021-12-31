package br.com.pokemon.beans;

public class Mapa {
    // Matriz 15 linhas x 15 colunas
    String[][] mapaOriginal = {
            {".",".",".",".",".",".",".",".",".",".",".",".",".",".","."},
            {".",".",".",".",".",".",".",".",".","#","#","#",".",".","."},
            {".",".",".",".",".",".",".",".",".","#",".",".",".",".","."},
            {".","C",".",".",".",".","#","#","#","#","C","#","#","#","."},
            {".","#",".","C","#","#","#",".",".",".","#",".",".","#","."},
            {".","#",".","#",".",".",".",".",".",".","#",".",".","#","."},
            {".","#",".","#",".","#","#","#","C","#","C","#","#","C","."},
            {".","#",".","#",".","#",".",".",".",".","#",".",".","#","."},
            {".","#","#","C",".","#",".",".",".",".","#",".",".","#","."},
            {".",".",".","#",".","#",".",".",".",".","C","#","#","#","."},
            {".",".",".","#",".","#",".",".",".",".",".",".",".","#","."},
            {".",".",".","C",".","#","#","#","#","C","#","#","#","#","."},
            {".",".",".","|",".",".",".",".",".","|",".",".",".",".","."},
            {".",".",".","C","|","|","|","|","|","|",".",".",".",".","."},
            {".",".",".",".",".",".",".",".",".",".",".",".",".",".","."},};
    // Matriz 15 linhas x 15 colunas (3 Linha,11 Coluna)
    String[][] mapaPlayer = {
            {".",".",".",".",".",".",".",".",".",".",".",".",".",".","."},
            {".",".",".",".",".",".",".",".",".","#","#","#",".",".","."},
            {".",".",".",".",".",".",".",".",".","#",".",".",".",".","."},
            {".","C",".",".",".",".","#","#","#","#","C","#","#","#","."},
            {".","#",".","C","#","#","#",".",".",".","#",".",".","#","."},
            {".","#",".","#",".",".",".",".",".",".","#",".",".","#","."},
            {".","#",".","#",".","#","#","#","C","#","C","#","#","C","."},
            {".","#",".","#",".","#",".",".",".",".","#",".",".","#","."},
            {".","#","#","C",".","#",".",".",".",".","#",".",".","#","."},
            {".",".",".","#",".","#",".",".",".",".","C","#","#","#","."},
            {".",".",".","#",".","#",".",".",".",".",".",".",".","#","."},
            {".",".",".","O",".","#","#","#","#","C","#","#","#","#","."},
            {".",".",".","|",".",".",".",".",".","|",".",".",".",".","."},
            {".",".",".","C","|","|","|","|","|","|",".",".",".",".","."},
            {".",".",".",".",".",".",".",".",".",".",".",".",".",".","."},};
    int localizacaoLinha = 11;
    int localizacaoColuna = 3;


    public void showMapa(){
        for (int i = 0 ; i < 15; i++){
            for (int j = 0; j < 15; j++){
                System.out.print(mapaPlayer[i][j]);
                System.out.print("  ");
            }
            System.out.print("\n");
        }
    }

    public void andaMapa(String keyPressed){
        if(isPossible(keyPressed)){
            if(keyPressed.equals("w")){
                mapaPlayer[localizacaoLinha][localizacaoColuna] = mapaOriginal[localizacaoLinha][localizacaoColuna];
                this.localizacaoLinha--;
                mapaPlayer[localizacaoLinha][localizacaoColuna] = "O";

            }
            if(keyPressed.equals("s")){
                mapaPlayer[localizacaoLinha][localizacaoColuna] = mapaOriginal[localizacaoLinha][localizacaoColuna];
                localizacaoLinha++;
                mapaPlayer[localizacaoLinha][localizacaoColuna] = "O";
            }
            if(keyPressed.equals("a")){
                mapaPlayer[localizacaoLinha][localizacaoColuna] = mapaOriginal[localizacaoLinha][localizacaoColuna];
                localizacaoColuna--;
                mapaPlayer[localizacaoLinha][localizacaoColuna] = "O";
            }
            if(keyPressed.equals("d")){
                mapaPlayer[localizacaoLinha][localizacaoColuna] = mapaOriginal[localizacaoLinha][localizacaoColuna];
                localizacaoColuna++;
                mapaPlayer[localizacaoLinha][localizacaoColuna] = "O";
            }
        }

    }
    private boolean isPossible(String andar){
        if(andar.equals("w")){
            if(mapaOriginal[localizacaoLinha-1][localizacaoColuna].equals(".")){
                return false;
            }
            return true;
        }
        if(andar.equals("s")){
            if(mapaOriginal[localizacaoLinha+1][localizacaoColuna].equals(".")){
                return false;
            }
            return true;
        }
        if(andar.equals("a")){
            if(mapaOriginal[localizacaoLinha][localizacaoColuna-1].equals(".")){
                return false;
            }
            return true;
        }
        if(andar.equals("d")){
            if(mapaOriginal[localizacaoLinha][localizacaoColuna+1].equals(".")){
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean isCidade(){
        if(mapaOriginal[localizacaoLinha][localizacaoColuna].equals("C")){
            return true;
        } else{
            return false;
        }
    }

    public int getLocalizacaoLinha() {
        return localizacaoLinha;
    }

    public int getLocalizacaoColuna() {
        return localizacaoColuna;
    }
}