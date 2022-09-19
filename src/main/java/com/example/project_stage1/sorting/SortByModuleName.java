package com.example.project_stage1.sorting;

import com.example.project_stage1.objects.Module;

import java.util.Comparator;
import java.util.HashMap;

public class SortByModuleName implements Comparator<Module> {
    HashMap<Module, Integer> base;

    public SortByModuleName(HashMap<Module, Integer> base) {
        this.base = new HashMap<Module, Integer>(base);
    }

    public int compare(Module a, Module b) {
        if ((a.getModuleName().charAt(0)) >= (b.getModuleName().charAt(0))) {
            return 1;
        } else {
            return -1;
        }
    }
}
