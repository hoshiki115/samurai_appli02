package model;

import java.util.List;

import dao.ResultsDAO;

public class CheckSaveNameLogic {
    public boolean execute(String name) {
        ResultsDAO dao = new ResultsDAO();
        List<SaveResult> simNameList = dao.findSimName();
        
        for(SaveResult i : simNameList) {
            if(i.getSimName().equals(name)) { return true; }
        }
        return false;
    }
}
