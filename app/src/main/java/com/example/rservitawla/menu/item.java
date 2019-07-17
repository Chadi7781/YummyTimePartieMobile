package com.example.rservitawla.menu;

public class item {
    int background;
    String titre;

    public item(int background, String titre) {
        this.background = background;
        this.titre = titre;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
