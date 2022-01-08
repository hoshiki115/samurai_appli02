package model;

import java.util.List;

import dao.ResultsDAO;

public class GetSaveResLogic {
    public List<SaveResult> execute() {
        ResultsDAO dao = new ResultsDAO();
        List<SaveResult> saveList = dao.findAll();
        return saveList;
    }
}
