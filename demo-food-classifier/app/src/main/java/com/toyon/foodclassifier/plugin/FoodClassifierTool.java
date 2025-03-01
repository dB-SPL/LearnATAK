/**
 * Copyright (c) 2023 Toyon Research Corporation. No Rights Reserved.
 * Report problems or provide feedback at: https://github.com/Toyon/LearnATAK/issues
 */
package com.toyon.foodclassifier.plugin;

import com.atakmap.android.ipc.AtakBroadcast;
import com.toyon.foodclassifier.MainDropDown;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.core.content.res.ResourcesCompat;

import transapps.mapi.MapView;
import transapps.maps.plugin.tool.Group;
import transapps.maps.plugin.tool.Tool;
import transapps.maps.plugin.tool.ToolDescriptor;

/** Food Classifier's entry in the ATAK toolbar */
public class FoodClassifierTool extends Tool implements ToolDescriptor {

    private final Context context;

    public FoodClassifierTool(Context context) {
        this.context = context;
    }

    @Override
    public String getDescription() {
        return context.getString(R.string.app_name);
    }

    @Override
    public Drawable getIcon() {
        return (context == null) ? null:
                ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_meat, null);
    }


    @Override
    public Group[] getGroups() {
        return new Group[] {
                Group.GENERAL
        };
    }

    @Override
    public String getShortDescription() {
        return context.getString(R.string.app_name);
    }

    @Override
    public Tool getTool() {
        return this;
    }

    @Override
    public void onActivate(Activity arg0, MapView arg1, ViewGroup arg2, Bundle arg3,
                           ToolCallback arg4) {
        // Hack to close the dropdown that automatically opens when a tool plugin is activated.
        if (arg4 != null) {
            arg4.onToolDeactivated(this);
        }
        // Intent to launch the dropdown or tool
        Intent i = new Intent(MainDropDown.SHOW_PLUGIN);
        AtakBroadcast.getInstance().sendBroadcast(i);
    }

    @Override
    public void onDeactivate(ToolCallback arg0) { }
}
