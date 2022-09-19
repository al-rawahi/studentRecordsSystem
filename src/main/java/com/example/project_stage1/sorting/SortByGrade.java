package com.example.project_stage1.sorting;

import com.example.project_stage1.objects.Module;

import java.util.Comparator;
import java.util.HashMap;

public class SortByGrade implements Comparator<Module> {

    HashMap<Module, Integer> base;

    public SortByGrade(HashMap<Module, Integer> base) {
        this.base = new HashMap<Module, Integer>(base);
    }

    public int compare(Module a, Module b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        }
    }
}


