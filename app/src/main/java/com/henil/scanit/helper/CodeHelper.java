package com.henil.scanit.helper;

import android.content.Context;

import com.henil.scanit.db.QRCodeItem;
import com.henil.scanit.fragment.FragmentList;
import com.henil.scanit.constant.Constants;
import com.henil.scanit.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import static com.henil.scanit.MainActivity.codeListFragmentAdapter;

public class CodeHelper implements Constants {
    private static List<QRCodeItem> callRecordList = new ArrayList<>();

    public static void updateAllLists(Context context) {
        SharedPreferencesUtils.initSharedReferences(context);
        try {
            callRecordList.clear();

            if (codeListFragmentAdapter != null) {
                callRecordList = FactoryHelper.getHelper().getQRCodeDAO().getAllItems();
                codeListFragmentAdapter.setLists(callRecordList);
                codeListFragmentAdapter.notifyDataSetChanged();

                if (callRecordList.size() != 0)
                    FragmentList.visibleRecycler();
                else
                    FragmentList.visibleTextView();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
