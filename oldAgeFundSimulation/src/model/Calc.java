package model;

public class Calc {
    public static int [][] calc(InputIncome inputIncome, InputCost inputCost) {
        InputArray inputArray = new InputArray(inputIncome, inputCost);
        int [] intParamValue = inputArray.getIntParamValue();
        int term = intParamValue[7] - intParamValue[0] + 1;
        
        // 配列を初期化
        int [] age = new int [term];
        int [] currentIncomeA = new int [term];
        int [] severanceIncomeA = new int [term];
        int [] pemsionIncomeA = new int [term];
        int [] regIncomeA = new int [term];
        int [] otherIncomeA = new int [term];
        int [] totalIncome = new int [term];
        int [] annualCostA = new int [term];
        int [] everyYearCostA = new int [term];
        int [] timeLimitCost1A = new int [term];
        int [] timeLimitCost2A = new int [term];
        int [] expectedCostA = new int [term];
        int [] planCostFA = new int [term];
        int [] totalCost = new int [term];
        int [] totalBalance = new int [term];
        int [] currentSavingA = new int [term];
        
        // 現在の年齢からシミュレーション終了年齢まで入力値を配列に代入して計算
        for(int i=0; i<term; i++) {
            // 年齢
            age[i] = intParamValue[0] + i;
            // 収入の部
            // 手取り年収
            if(age[i] <= intParamValue[2]) {
                currentIncomeA[i] = intParamValue[1];
            }
            // 退職金
            if(age[i] == intParamValue[2]) {
                severanceIncomeA[i] = intParamValue[3];
            }
            // 年金
            if(age[i] >= intParamValue[4]) {
                pemsionIncomeA[i] = intParamValue[5];
            }
            // 定期的な手取り年収
            if(age[i] > intParamValue[2] && age[i] <= intParamValue[10]) {
                regIncomeA[i] = intParamValue[15];
            }
            // その他収入
            if(age[i] == intParamValue[11]) {
                otherIncomeA[i] = intParamValue[16];
            }
            // A:収入合計
            totalIncome[i] = currentIncomeA[i] + severanceIncomeA[i] + pemsionIncomeA[i]
                     + regIncomeA[i] + otherIncomeA[i];
            // 支出の部
            // 毎月必ず発生する費用（年間）
            annualCostA[i] = intParamValue[8] * 12;
            // 毎年必ず発生する費用
            everyYearCostA[i] = intParamValue[9];
            // 期限付きで毎年発生する費用１
            if(age[i] <= intParamValue[12]) {
                timeLimitCost1A[i] = intParamValue[17];
            }
            // 期限付きで毎年発生する費用２
            if(age[i] <= intParamValue[13]) {
                timeLimitCost2A[i] = intParamValue[18];
            }
            // 期限付きで予定する費用
            if(age[i] <= intParamValue[14]) {
                expectedCostA[i] = intParamValue[19] / (intParamValue[14] - intParamValue[0] + 1);
            }
            // 毎年予定する費用
            planCostFA[i] = intParamValue[20];
            // B:支出合計
            totalCost[i] = annualCostA[i] + everyYearCostA[i] + timeLimitCost1A[i]
                     + timeLimitCost2A[i] + expectedCostA[i] + planCostFA[i];
            
            // AーB：収支合計
            totalBalance[i] = totalIncome[i] - totalCost[i];
            // 預貯金
            if(i == 0) {
                currentSavingA[i] = intParamValue[6];
            } else {
                currentSavingA[i] = currentSavingA[i-1] + totalBalance[i-1];
            }
        }
        
        int [][] outputArray = {age, totalIncome, totalCost, totalBalance, currentSavingA};
        return outputArray;
    }
}
