package Undo;

import model.Employee;

import java.util.ArrayList;
import java.util.List;

public class UndoHandlerEmployee {
    private List<Integer> saveAddList = new ArrayList<>();
    private List<PairEmployee> saveChangeList = new ArrayList<>();
    private List<PairEmployee> saveDeleteList = new ArrayList<>();
    private List<String> undoHandler = new ArrayList<>();

    public void setAdd(int position){
        this.saveAddList.add(position);
        undoHandler.add("Add");
    }

    public void setChange(Employee old, int position){
        PairEmployee pairEmployee = new PairEmployee(position,"Change",old);
        this.saveChangeList.add(pairEmployee);
        undoHandler.add("Change");
    }

    public void setDelete(Employee old,int position){
        PairEmployee pairEmployee = new PairEmployee(position,"Delete",old);
        this.saveDeleteList.add(pairEmployee);
        undoHandler.add("Delete");
    }

    public PairEmployee undo(){
        if (undoHandler.isEmpty()){
            return null;
        }
        String undo = undoHandler.get(undoHandler.size()-1);
        undoHandler.remove(undoHandler.size()-1);

        if (undo.equals("Add")){
            Employee employee = null;
            PairEmployee pairEmployee = new PairEmployee(saveAddList.get(saveAddList.size()-1),"Add",employee);
            saveAddList.remove(saveAddList.size()-1);
            return pairEmployee;
        }else if (undo.equals("Change")){
            PairEmployee pairEmployee = saveChangeList.get(saveChangeList.size()-1);
            saveDeleteList.remove(saveChangeList.size()-1);
            return pairEmployee;

        }else {
            PairEmployee pairEmployee = saveDeleteList.get(saveDeleteList.size()-1);
            saveDeleteList.remove(saveDeleteList.size()-1);
            return pairEmployee;
        }
    }
}
