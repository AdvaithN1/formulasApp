package com.advaith.physicsformulas;

public class Moi {
    private String formula; //I=cmr^2
    private int visual; //Image of axis
    private String description;

    public Moi(String formula, int visual, String description) {
        this.formula = formula;
        this.visual = visual;
        this.description = description;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public int getVisual() {
        return visual;
    }

    public void setVisual(int visual) {
        this.visual = visual;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
