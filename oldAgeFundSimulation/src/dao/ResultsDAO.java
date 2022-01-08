package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.InputCost;
import model.InputIncome;
import model.SaveResult;

public class ResultsDAO {
    // データベース接続に使用する情報
    private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/oldAgeFundSimulation";
    private final String DB_USER = "sa";
    private final String DB_PASS = "";
    
    public int count() { //レコード数を取得
        int saveNum = 0;
        // データベース接続
        try (Connection conn = DriverManager.getConnection(
                JDBC_URL, DB_USER, DB_PASS)) {
            
            // SELECT文の準備
            String sql = "SELECT COUNT(*) AS SIMID FROM SIMRESULTS";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            
            // SELECTを実行
            ResultSet rs = pStmt.executeQuery();
            
            // SELECT文の結果を格納
            if(rs.next()) {
                saveNum = rs.getInt("SIMID");
            }
            pStmt.close();
            return saveNum;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public List<SaveResult> findSimName() { //全レコードのシミュレーション名称を取得
        List<SaveResult> simNameList = new ArrayList<>();
        
        // データベース接続
        try (Connection conn = DriverManager.getConnection(
                JDBC_URL, DB_USER, DB_PASS)) {
            
            // SELECT文の準備
            String sql = "SELECT SIMNAME FROM SIMRESULTS";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            
            // SELECTを実行
            ResultSet rs = pStmt.executeQuery();
            
            // SELECT文の結果をArrayListに格納
            while (rs.next()) {
                String simName = rs.getString("SIMNAME");
                SaveResult saveResult = new SaveResult(simName);
                simNameList.add(saveResult);
            }
            pStmt.close();
            return simNameList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String [] findByName(String selectName) { //選択した１レコードを取得
        String [] inputValue = new String [21];
        
        // データベース接続
        try (Connection conn = DriverManager.getConnection(
                JDBC_URL, DB_USER, DB_PASS)) {
            
            // SELECT文の準備
            String sql = "SELECT * FROM SIMRESULTS WHERE SIMNAME = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, selectName);
            
            // SELECTを実行
            ResultSet rs = pStmt.executeQuery();
            
            // SELECT文の結果をinputValueに格納
            if (rs.next()) {
                inputValue[0] = rs.getString("CURRENTAGE");
                inputValue[1] = rs.getString("CURRENTINCOME");
                inputValue[2] = rs.getString("RETIREAGE");
                inputValue[3] = rs.getString("SEVERANCEINCOME");
                inputValue[4] = rs.getString("PENSIONAGE");
                inputValue[5] = rs.getString("PENSIONINCOME");
                inputValue[6] = rs.getString("CURRENTSAVING");
                inputValue[7] = rs.getString("REGINCOME");
                inputValue[8] = rs.getString("REGINCOMEAGE");
                inputValue[9] = rs.getString("OTHERINCOME");
                inputValue[10] = rs.getString("OTHERINCOMEAGE");
                inputValue[11] = rs.getString("ENDAGE");
                inputValue[12] = rs.getString("MONTHLYCOST");
                inputValue[13] = rs.getString("EVERYYEARCOST");
                inputValue[14] = rs.getString("TIMELIMITCOST1");
                inputValue[15] = rs.getString("TIMELIMITAGE1");
                inputValue[16] = rs.getString("TIMELIMITCOST2");
                inputValue[17] = rs.getString("TIMELIMITAGE2");
                inputValue[18] = rs.getString("EXPECTEDCOST");
                inputValue[19] = rs.getString("EXPECTEDAGE");
                inputValue[20] = rs.getString("PLANCOSTF");
            }
            pStmt.close();
            return inputValue;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String findByNameCom(String selectName) { //選択したシミュレーション名称のコメントを取得
        String comment = null;
        
        // データベース接続
        try (Connection conn = DriverManager.getConnection(
                JDBC_URL, DB_USER, DB_PASS)) {
            
            // SELECT文の準備
            String sql = "SELECT SIMCOM FROM SIMRESULTS WHERE SIMNAME = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, selectName);
            
            // SELECTを実行
            ResultSet rs = pStmt.executeQuery();
            
            // SELECT文の結果をinputValueに格納
            if (rs.next()) {
                comment = rs.getString("SIMCOM");
            }
            pStmt.close();
            return comment;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<SaveResult> findAll() { //全レコードを取得
        List<SaveResult> saveList = new ArrayList<>();
        
        // データベース接続
        try (Connection conn = DriverManager.getConnection(
                JDBC_URL, DB_USER, DB_PASS)) {
            
            // SELECT文の準備
            String sql = "SELECT SIMID, SIMNAME, SAVEDATE, SIMCOM FROM SIMRESULTS ORDER BY SIMID DESC";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            
            // SELECTを実行
            ResultSet rs = pStmt.executeQuery();
            
            // SELECT文の結果をArrayListに格納
            while (rs.next()) {
                int simId = rs.getInt("SIMID");
                String simName = rs.getString("SIMNAME");
                String saveDate = rs.getString("SAVEDATE");
                String simCom = rs.getString("SIMCOM");
                SaveResult saveResult = new SaveResult(simId, simName, saveDate, simCom);
                saveList.add(saveResult);
            }
            pStmt.close();
            return saveList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean create(SaveResult saveResult, InputIncome inputIncome, InputCost inputCost) { // レコードの追加
     // データベース接続
        try (Connection conn = DriverManager.getConnection(
                JDBC_URL, DB_USER, DB_PASS)) {
            
            // INSERT文の準備
            String sql = "INSERT INTO SIMRESULTS(SIMNAME, SAVEDATE, SIMCOM, CURRENTAGE, CURRENTINCOME, "
                    + "RETIREAGE, SEVERANCEINCOME, PENSIONAGE, PENSIONINCOME, CURRENTSAVING, REGINCOME, REGINCOMEAGE, "
                    + "OTHERINCOME, OTHERINCOMEAGE, ENDAGE, MONTHLYCOST, EVERYYEARCOST, TIMELIMITCOST1, TIMELIMITAGE1, "
                    + "TIMELIMITCOST2, TIMELIMITAGE2, EXPECTEDCOST, EXPECTEDAGE, PLANCOSTF) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, saveResult.getSimName());
            pStmt.setString(2, saveResult.getSaveDate());
            pStmt.setString(3, saveResult.getSimCom());
            pStmt.setString(4, inputIncome.getCurrentAge());
            pStmt.setString(5, inputIncome.getCurrentIncome());
            pStmt.setString(6, inputIncome.getRetireAge());
            pStmt.setString(7, inputIncome.getSeveranceIncome());
            pStmt.setString(8, inputIncome.getPensionAge());
            pStmt.setString(9, inputIncome.getPensionIncome());
            pStmt.setString(10, inputIncome.getCurrentSaving());
            pStmt.setString(11, inputIncome.getRegIncome());
            pStmt.setString(12, inputIncome.getRegIncomeAge());
            pStmt.setString(13, inputIncome.getOtherIncome());
            pStmt.setString(14, inputIncome.getOtherIncomeAge());
            pStmt.setString(15, inputIncome.getEndAge());
            pStmt.setString(16, inputCost.getMonthlyCost());
            pStmt.setString(17, inputCost.getEveryYearCost());
            pStmt.setString(18, inputCost.getTimeLimitCost1());
            pStmt.setString(19, inputCost.getTimeLimitAge1());
            pStmt.setString(20, inputCost.getTimeLimitCost2());
            pStmt.setString(21, inputCost.getTimeLimitAge2());
            pStmt.setString(22, inputCost.getExpectedCost());
            pStmt.setString(23, inputCost.getExpectedAge());
            pStmt.setString(24, inputCost.getPlanCostF());
            
            // INSERTを実行
            int result = pStmt.executeUpdate();
            if(result != 1) {
                return false;
            }
            pStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean delete(List<String> checkNames) { // レコードの削除
        // データベース接続
        try (Connection conn = DriverManager.getConnection(
               JDBC_URL, DB_USER, DB_PASS)) {
               
            // DELETE文の準備とDELETEの実行
            String sql = "DELETE FROM SIMRESULTS WHERE SIMNAME = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            for(int i = 0; i < checkNames.size(); i++) {
                pStmt.setString(1, checkNames.get(i));
                pStmt.executeUpdate();
            }
            pStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateCom(String selectName, String comment) { //選択したシミュレーション名称のコメントを編集して更新
        // データベース接続
        try (Connection conn = DriverManager.getConnection(
                JDBC_URL, DB_USER, DB_PASS)) {
            
            // SELECT文の準備
            String sql = "UPDATE SIMRESULTS SET SIMCOM = ? WHERE SIMNAME = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, comment);
            pStmt.setString(2, selectName);
            
            // UPDATEを実行
            int result = pStmt.executeUpdate();
            if(result != 1) {
                return false;
            }
            pStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}