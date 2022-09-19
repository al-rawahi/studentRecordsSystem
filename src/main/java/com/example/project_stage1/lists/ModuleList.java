//Name: Al-Warith Al-Rawahi
//Student No.: R00196016
//Group: SDH2A

package com.example.project_stage1.lists;

import com.example.project_stage1.objects.Module;

import java.util.ArrayList;

public class ModuleList {

    //create an arraylist of modules
    private ArrayList<Module> modules;

    //create constructor
    public ModuleList (){
        modules = new ArrayList <Module> ();
    }

    //create getters
    public ArrayList<Module> getList (){
        return modules;
    }

    public Module getModule(int i)
    {
        if ((i>-1) && (i < modules.size()))
            return modules.get (i);
        return null;
    }

    public int getSize (){
        return modules.size();
    }


}
