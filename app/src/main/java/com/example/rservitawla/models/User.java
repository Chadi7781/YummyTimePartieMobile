package com.example.rservitawla.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    @SerializedName("reservationtable")
    @Expose
    private List<Object> reservationtable = null;
    @SerializedName("reclamations")
    @Expose
    private List<Object> reclamations = null;
    @SerializedName("restaurants")
    @Expose
    private List<Object> restaurants = null;
    @SerializedName("offres")
    @Expose
    private List<Object> offres = null;
    @SerializedName("__t")
    @Expose
    private String t;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("nom")
    @Expose
    private String nom;
    @SerializedName("numero_telephone")
    @Expose
    private String numeroTelephone;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("adresse")
    @Expose
    private String adresse;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("sexe")
    @Expose
    private String sexe;
    @SerializedName("confirmPassword")
    @Expose
    private String confirmPassword;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public List<Object> getReservationtable() {
        return reservationtable;
    }

    public void setReservationtable(List<Object> reservationtable) {
        this.reservationtable = reservationtable;
    }

    public List<Object> getReclamations() {
        return reclamations;
    }

    public void setReclamations(List<Object> reclamations) {
        this.reclamations = reclamations;
    }

    public List<Object> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Object> restaurants) {
        this.restaurants = restaurants;
    }

    public List<Object> getOffres() {
        return offres;
    }

    public void setOffres(List<Object> offres) {
        this.offres = offres;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}