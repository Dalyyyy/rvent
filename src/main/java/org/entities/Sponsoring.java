package org.entities;

public class Sponsoring {
    private int id;
    private String nom;

    private String nom_etab;
    private String prenom;
    private String domaine;
    private String adresse;
    private String email;
    private Tetab tetab;
    private int numero;
    private String description;
    //private int etat;
    public enum Tetab {
        entreprise,startup,organisme,institution_financiére;

        private String stringValue;

        public static Tetab fromString(String tetabString) {
            for (Tetab tetab : Tetab.values()) {
                if (tetab.stringValue.equalsIgnoreCase(tetabString)) {
                    return tetab;
                }
            }
            throw new IllegalArgumentException("No enum constant for string: " + tetabString);
        }
    }

    public Sponsoring(int id, String nom, String nom_etab, String prenom, String domaine, String adresse, String email, Tetab tetab, int numero, String description) {
        this.id = id;
        this.nom = nom;
        this.nom_etab = nom_etab;
        this.prenom = prenom;
        this.domaine = domaine;
        this.adresse = adresse;
        this.email = email;
        this.tetab = tetab;
        this.numero = numero;
        this.description = description;
    }

//    public Sponsoring(int id, String nom, String nom_etab, String prenom, String domaine, String adresse, String email, Tetab tetab, int numero, String description, int etat) {
//        this.id = id;
//        this.nom = nom;
//        this.nom_etab = nom_etab;
//        this.prenom = prenom;
//        this.domaine = domaine;
//        this.adresse = adresse;
//        this.email = email;
//        this.tetab = tetab;
//        this.numero = numero;
//        this.description = description;
//        this.etat = etat;
//    }

    public Sponsoring() {
    }

//    public int getEtat() {
//        return etat;
//    }
//
//    public void setEtat(int etat) {
//        this.etat = etat;
//    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domane) {
        this.domaine = domane;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Tetab getTetab() {
        return tetab;
    }

    public void setTetab(Tetab tetab) {
        this.tetab = tetab;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom_etab() {
        return nom_etab;
    }

    public void setNom_etab(String nom_etab) {
        this.nom_etab = nom_etab;
    }

    @Override
    public String toString() {
        return "Sponsoring{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", nom_etab='" + nom_etab + '\'' +
                ", prenom='" + prenom + '\'' +
                ", domane='" + domaine + '\'' +
                ", adresse='" + adresse + '\'' +
                ", email='" + email + '\'' +
                ", tetab=" + tetab +
                ", numero=" + numero +
                ", description='" + description + '\'' +
                '}';
    }
}
