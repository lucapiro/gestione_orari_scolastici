package com.confalonieri.inserimentocsv;

//classe per creare il vettore di oggetti da caricare poi nel database!

public class OrarioProfDatabase {
    String nomeProfessore;
    int ora;
    int giorno;
    String Aula;
    String Classe;

    public OrarioProfDatabase(){}

    public void SetNewValue(String nome, int h, int g, String a,String c)
    {
        this.nomeProfessore=nome;
        this.ora=h;
        this.giorno=g;
        this.Classe=c;
        this.Aula=a;
    }
}
