package model;

import java.util.ArrayList;
import java.util.List;

public class InputArray {
    private String [] paramName = new String [21];
    private String [] paramValue = new String [21];
    private int [] intParamValue = new int [21];
    
    public InputArray(InputIncome inputIncome, InputCost inputCost) {
        this.paramName[0] = "現在の年齢";
        this.paramName[1] = "手取り年収";
        this.paramName[2] = "定年年齢";
        this.paramName[3] = "退職金";
        this.paramName[4] = "年金受給年齢";
        this.paramName[5] = "年金受給額";
        this.paramName[6] = "現在の預貯金";
        this.paramName[10] = "定期的な手取り年収がある年齢";
        this.paramName[15] = "定期的な手取り年収";
        this.paramName[11] = "その他収入を受け取る年齢";
        this.paramName[16] = "その他収入";
        this.paramName[7] = "シミュレーション終了年齢";
        this.paramName[8] = "毎月必ず発生する費用";
        this.paramName[9] = "毎年必ず発生する費用";
        this.paramName[12] = "期限付きで毎年発生する費用１の年齢";
        this.paramName[17] = "期限付きで毎年発生する費用１";
        this.paramName[13] = "期限付きで毎年発生する費用２の年齢";
        this.paramName[18] = "期限付きで毎年発生する費用２";
        this.paramName[14] = "期限付きで予定する費用の年齢";
        this.paramName[19] = "期限付きで予定する費用";
        this.paramName[20] = "毎年予定する費用";
        this.paramValue[0] = inputIncome.getCurrentAge();
        this.paramValue[1] = inputIncome.getCurrentIncome();
        this.paramValue[2] = inputIncome.getRetireAge();
        this.paramValue[3] = inputIncome.getSeveranceIncome();
        this.paramValue[4] = inputIncome.getPensionAge();
        this.paramValue[5] = inputIncome.getPensionIncome();
        this.paramValue[6] = inputIncome.getCurrentSaving();
        this.paramValue[10] = inputIncome.getRegIncomeAge();
        this.paramValue[15] = inputIncome.getRegIncome();
        this.paramValue[11] = inputIncome.getOtherIncomeAge();
        this.paramValue[16] = inputIncome.getOtherIncome();
        this.paramValue[7] = inputIncome.getEndAge();
        this.paramValue[8] = inputCost.getMonthlyCost();
        this.paramValue[9] = inputCost.getEveryYearCost();
        this.paramValue[12] = inputCost.getTimeLimitAge1();
        this.paramValue[17] = inputCost.getTimeLimitCost1();
        this.paramValue[13] = inputCost.getTimeLimitAge2();
        this.paramValue[18] = inputCost.getTimeLimitCost2();
        this.paramValue[14] = inputCost.getExpectedAge();
        this.paramValue[19] = inputCost.getExpectedCost();
        this.paramValue[20] = inputCost.getPlanCostF();
        
        // 入力値を文字型から整数型配列に変換
        for(int i=0; i<21; i++) {
            if(this.paramValue[i] == "") {
                this.intParamValue[i] = 0;
            } else {
                this.intParamValue[i] = Integer.parseInt(this.paramValue[i]);
            }
        }
    }
    
    public List<String> inputCheck() {
        List<String> errorList = new ArrayList<String>();
        // 入力値が空欄の場合のエラー
        for(int i=0; i<10; i++) {
            if(this.paramValue[i] == "") {
                errorList.add(this.paramName[i] + "が入力されていません");
            }
        }
        // 必要とする入力値が空欄の場合のエラー
        for(int i=10; i<15; i++) {
            if(this.paramValue[i+5] != "") {
                if(this.intParamValue[i+5] > 0 && this.paramValue[i] == "") {
                    errorList.add(this. paramName[i] + "が入力されていません");
                }
            }
        }
        // 入力値がマイナスの場合のエラー
        for(int i=0; i<21; i++) {
            if(this.intParamValue[i] < 0) {
                errorList.add(this.paramName[i] + "は0あるいは正の値を入力してください");
            }
        }
        // 入力値がその他の場合のエラー
        if(this.intParamValue[0] < 40 || this.intParamValue[0] >= 100) { //現在の年齢の制約
                errorList.add(this.paramName[0] + "は40以上かつ100より小さい値を入力してください");
        }
        if(this.intParamValue[7] > 100 || this.intParamValue[7] <= this.intParamValue[0]) { //シミュレーション終了年齢の制約
            errorList.add(this.paramName[7] + "は100以下かつ" + this.paramName[0] + "より大きい値を入力してください");
        }
        if(this.intParamValue[2] < this.intParamValue[0] || this.intParamValue[2] > this.intParamValue[7]) { //定年年齢の制約
            errorList.add(this.paramName[2] + "は現在の年齢以上かつシミュレーション終了年齢以下の値を入力してください");
        }
        if(this.intParamValue[4] < this.intParamValue[0] || this.intParamValue[4] > this.intParamValue[7]) { //年金受給年齢の制約
            errorList.add(this.paramName[4] + "は現在の年齢以上かつシミュレーション終了年齢以下の値を入力してください");
        }
        if(this.intParamValue[15] > 0 && (this.intParamValue[10] <= this.intParamValue[2] || this.intParamValue[10] > this.intParamValue[7])) { //定期的な手取り年収がある年齢の制約
            errorList.add(this.paramName[10] + "は定年年齢より大きくかつシミュレーション終了年齢以下の値を入力してください");
        }
        for(int i=11; i<15; i++) {
            if(this.intParamValue[i+5] > 0 && (this.intParamValue[i] < this.intParamValue[0] || this.intParamValue[i] > this.intParamValue[7])) { // その他の制約
            errorList.add(this.paramName[i] + "は現在の年齢以上かつシミュレーション終了年齢以下の値を入力してください");
            }
        }
        return errorList;
    }

    public String [] getParamName() { return paramName; }
    public String [] getParamValue() { return paramValue; }
    public int [] getIntParamValue() { return intParamValue; }
}
