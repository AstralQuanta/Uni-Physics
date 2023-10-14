package Milligan;

public class Milligan {

    protected double votage, votage1;
    protected double Vg, P, K;
    protected double η, η1, d, L, g, ρ, ρ1, pi, b, r;
    protected double down_time, up_time;
    protected String answer;
    protected static final double temp1 = 1.5;
    protected static final double temp2 = 0.5;
    protected double StandardElectron = 1.6E-19;
    protected double q = 0.0;
    protected long n = 0;
    public double finalAnswer = 0.0;

    public Milligan(int a1, double a2, int a3, double a4) {
        this.votage = a1;
        this.down_time = a2;

        this.votage1 = a3;
        this.up_time = a4;
    }

    public void calculateData() {

        d = 5E-3; // Unit is m
        b = 6.17E-6;
        g = 9.794; // unit is m* s ^ -2
        ρ = 981; // unit is Kg*m ^ -3
        ρ1 = 1.2928; // unit is Kg*m ^ -3
        L = 1.6E-3; // Unit is m
        η = 1.83E-5; // unit Kg / m / s
        P = 76.0; // unit is cmHg
        pi = Math.PI; // Pi = 3.1415926535...

        Vg = L / down_time;
        r = Math.sqrt((9 * η * Vg) / (2 * (ρ - ρ1) * g));
        η1 = η / (1 + (b / (r * P)));
        K = (18 * pi * Math.pow(η1, temp1)) * d * Math.pow(L, temp1) / (Math.sqrt((2 * g * (ρ - ρ1))));
        q = K * (1 / votage1) * ((1 / down_time) + (1 / up_time)) * Math.pow((1 / down_time), temp2);
        n = Math.round((q / StandardElectron));
        finalAnswer = q / n;
    }

    public String MessageFromCal() {

        answer = "油滴下降速度(v) =" + Vg + "m/s" + "\n" + "油滴的半径 = " + r + "\n"
                + "修正后黏滞系数η' = " + η1 + "\n" + "K = " + K + "\n" + "油滴的电荷量（q） = " + q + " C";
        return answer;
    }
}
