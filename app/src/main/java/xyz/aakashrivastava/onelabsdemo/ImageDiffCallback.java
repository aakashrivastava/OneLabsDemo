package xyz.aakashrivastava.onelabsdemo;


import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.ArrayList;

public class ImageDiffCallback extends DiffUtil.Callback {

    private final ArrayList<ImageModel> newList;
    private final ArrayList<ImageModel> oldList;

    public ImageDiffCallback(ArrayList<ImageModel> newList, ArrayList<ImageModel> oldList) {
        this.newList = newList;
        this.oldList = oldList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final ImageModel oldEmployee = oldList.get(oldItemPosition);
        final ImageModel newEmployee = newList.get(newItemPosition);

        return oldEmployee.getUrl().equals(newEmployee.getUrl());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
