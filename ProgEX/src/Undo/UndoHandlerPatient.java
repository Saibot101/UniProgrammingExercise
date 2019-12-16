package Undo;



import model.Patients;

import java.util.ArrayList;
import java.util.List;

public class UndoHandlerPatient {
    private List<Integer> saveAddList = new ArrayList<>();
    private List<PairPatient> saveChangeList = new ArrayList<>();
    private List<PairPatient> saveDeleteList = new ArrayList<>();
    private List<String> undoHandler = new ArrayList<>() ;

    public void setAdd(int position){
        saveAddList.add(position);
        undoHandler.add("Add");
    }

    public void setChange(Patients old, int position){
        PairPatient pairPatient = new PairPatient(position, old, "Change");
        this.saveChangeList.add(pairPatient);
        undoHandler.add("Change");
    }

    public void setDelete(Patients deleted,int position){
        PairPatient pairPatient = new PairPatient(position,deleted,"Delete");
        this.saveDeleteList.add(pairPatient);
        undoHandler.add("Delete");
    }

    public PairPatient undo(){
        if (undoHandler.isEmpty()){
            return null;
        }
        String undo = undoHandler.get(undoHandler.size()-1);
        undoHandler.remove(undoHandler.size()-1);
        if (undo.equals("Add")){
            Patients patients = null;
            PairPatient pairPatient = new PairPatient(saveAddList.get(saveAddList.size() - 1),patients,"Add");
            saveAddList.remove(saveAddList.size()-1);
            return pairPatient;
        }else if (undo.equals("Change")){
            PairPatient pairPatient = saveChangeList.get(saveChangeList.size()-1);
            saveAddList.remove(saveAddList.size()-1);
            return pairPatient;
        }else {
            PairPatient pairPatient = saveDeleteList.get(saveDeleteList.size()-1);
            saveDeleteList.remove(saveDeleteList.size()-1);
            return pairPatient;
        }
    }

}
