package model;

import dao.ResultsDAO;

public class PostSaveResLogic {
    public void execute(SaveResult saveResult, InputIncome inputIncome, InputCost inputCost) {
        ResultsDAO dao = new ResultsDAO();
        dao.create(saveResult, inputIncome, inputCost);
    }
}
