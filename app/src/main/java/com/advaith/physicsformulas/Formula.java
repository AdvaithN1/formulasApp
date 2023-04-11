package com.advaith.physicsformulas;


public class Formula {
    private String formulaText;
    private int layout;
    private int zoom;

    public Formula(String formText, int zoom, int layout) {
        this.formulaText = formText;
        this.layout = layout;
        this.zoom = zoom;
    }


    public String getFormulaText() {
        return formulaText;
    }

    public void setFormulaText(String formulaText) {
        this.formulaText = formulaText;
    }


    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public int getZoom(){return zoom;}

    public void setZoom(int zoom){this.zoom=zoom;}
}
