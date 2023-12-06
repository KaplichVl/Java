import static java.lang.Math.*;

public class Formula {

    private int formulaId = 1;

    public void setFormulaId(int formulaId) {
        this.formulaId = formulaId;
    }

    public int getFormulaId() {
        return formulaId;
    }

    public Double calculate1(Double x, Double y, Double z) {
        return pow((Math.cos(exp(x)) + log(1 + y) * log(1 + y) + sqrt(exp(cos(x)) + sin(PI * z) * sin(PI * z)) + sqrt(1 / x) + cos(y * y)), sin(z));
    }

    public Double calculate2(Double x, Double y, Double z) {
        return pow((1 + x * x), 1 / y) / exp(sin(z) + x);
    }
}
