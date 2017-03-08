package com.example.manaspande.bloodbanksindia.Classes;

import java.util.ArrayList;

/**
 * Created by manaspande on 2017-02-24.
 */

public class BloodBankInfo {
    ArrayList<String> mArrayList;

    public BloodBankInfo(ArrayList<String> arrayList) {
        mArrayList = arrayList;
    }

    public ArrayList<String> getContent() {
        return mArrayList;
    }
}
