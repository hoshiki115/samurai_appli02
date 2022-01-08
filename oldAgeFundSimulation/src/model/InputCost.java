package model;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public class InputCost implements Serializable {
    
    private String monthlyCost; // 毎月必ず発生する費用
    private String everyYearCost; // 毎年必ず発生する費用
    private String timeLimitCost1; // 期限付きで毎年発生する費用１
    private String timeLimitAge1; // 期限付きで毎年発生する費用１の年齢
    private String timeLimitCost2; // 期限付きで毎年発生する費用２
    private String timeLimitAge2; // 期限付きで毎年発生する費用２の年齢
    private String expectedCost; // 期限付きで予定する費用
    private String expectedAge; // 期限付きで予定する費用の年齢
    private String planCostF; // 毎年予定する費用
    
    public InputCost() {}
    public InputCost(HttpServletRequest request) {
        this.monthlyCost = request.getParameter("monthlyCost");
        this.everyYearCost = request.getParameter("everyYearCost");
        this.timeLimitCost1 = request.getParameter("timeLimitCost1");
        this.timeLimitAge1 = request.getParameter("timeLimitAge1");
        this.timeLimitCost2 = request.getParameter("timeLimitCost2");
        this.timeLimitAge2 = request.getParameter("timeLimitAge2");
        this.expectedCost = request.getParameter("expectedCost");
        this.expectedAge = request.getParameter("expectedAge");
        this.planCostF = request.getParameter("planCostF");
    }
    
    public String getMonthlyCost() { return monthlyCost; }
    public void setMonthlyCost(String monthlyCost) { this.monthlyCost = monthlyCost; }
    public String getEveryYearCost() { return everyYearCost; }
    public void setEveryYearCost(String everyYearCost) { this.everyYearCost = everyYearCost; }
    public String getTimeLimitCost1() { return timeLimitCost1; }
    public void setTimeLimitCost1(String timeLimitCost1) { this.timeLimitCost1 = timeLimitCost1; }
    public String getTimeLimitAge1() { return timeLimitAge1; }
    public void setTimeLimitAge1(String timeLimitAge1) { this.timeLimitAge1 = timeLimitAge1; }
    public String getTimeLimitCost2() { return timeLimitCost2; }
    public void setTimeLimitCost2(String timeLimitCost2) { this.timeLimitCost2 = timeLimitCost2; }
    public String getTimeLimitAge2() { return timeLimitAge2; }
    public void setTimeLimitAge2(String timeLimitAge2) { this.timeLimitAge2 = timeLimitAge2; }
    public String getExpectedCost() { return expectedCost; }
    public void setExpectedCost(String expectedCost) { this.expectedCost = expectedCost; }
    public String getExpectedAge() { return expectedAge; }
    public void setExpectedAge(String expectedAge) { this.expectedAge = expectedAge; }
    public String getPlanCostF() { return planCostF; }
    public void setPlanCostF(String planCostF) { this.planCostF = planCostF; }
}
