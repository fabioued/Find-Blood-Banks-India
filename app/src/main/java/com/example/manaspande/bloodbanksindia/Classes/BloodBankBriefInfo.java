package com.example.manaspande.bloodbanksindia.Classes;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

/**
 * Created by manaspande on 2017-02-24.
 */

public class BloodBankBriefInfo implements Parent<BloodBankInfo> {
    private List<BloodBankInfo> mBloodBankInfos;

    public BloodBankBriefInfo(List<BloodBankInfo> bloodBankInfos) {
        mBloodBankInfos = bloodBankInfos;
    }

    @Override
    public List<BloodBankInfo> getChildList() {
        return mBloodBankInfos;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
