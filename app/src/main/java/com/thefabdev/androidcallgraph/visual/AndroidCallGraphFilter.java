package com.thefabdev.androidcallgraph.visual;

import java.util.ArrayList;
import java.util.List;

import com.thefabdev.androidcallgraph.Utils;

import soot.Scene;
import soot.SootClass;
import soot.SootMethod;

public class AndroidCallGraphFilter {
    private List<SootClass> validClasses = new ArrayList<>();
    
    public List<SootClass> getValidClasses() {
        return validClasses;
    }

    public AndroidCallGraphFilter(String appPackageName) {
        for (SootClass sootClass : Scene.v().getApplicationClasses()) {
            if (!sootClass.getName().contains(appPackageName))
                continue;
            if (sootClass.getName().contains(appPackageName + ".R") || sootClass.getName().contains(appPackageName + ".BuildConfig"))
                continue;
            validClasses.add(sootClass);
        }
    }

    private boolean isValidMethod(SootMethod sootMethod){
        if(Utils.isAndroidMethod(sootMethod))
            return false;
        if(sootMethod.getDeclaringClass().getPackageName().startsWith("java"))
            return false;
        if(sootMethod.toString().contains("<init>") || sootMethod.toString().contains("<clinit>"))
            return false;
        if(sootMethod.getName().equals("dummyMainMethod"))
            return false;
        return true;
    }

    public boolean isValidEdge(soot.jimple.toolkits.callgraph.Edge sEdge) {
        if(!sEdge.src().getDeclaringClass().isApplicationClass())// || sEdge.tgt().getDeclaringClass().isApplicationClass())
            return false;
        if(!isValidMethod(sEdge.src()) || !isValidMethod(sEdge.tgt()))
            return false;
        boolean flag = validClasses.contains(sEdge.src().getDeclaringClass());
        flag |= validClasses.contains(sEdge.tgt().getDeclaringClass());
        return flag;
    }
}

