package com.example.manaspande.bloodbanksindia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.example.manaspande.bloodbanksindia.Classes.BloodBankBriefInfo;
import com.example.manaspande.bloodbanksindia.Classes.BloodBankInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manaspande on 2017-01-10.
 */

public class Adapter extends ExpandableRecyclerAdapter<BloodBankBriefInfo, BloodBankInfo, Adapter.bloodBankBriefInfoViewHolder, Adapter.bloodBankInfoViewHolder> {
    LayoutInflater mInflater;
    List<BloodBankBriefInfo> mParentList;

    public Adapter(Context context, @NonNull List<BloodBankBriefInfo> parentList) {
        super(parentList);
        mInflater = LayoutInflater.from(context);
        mParentList = parentList;
    }

    @NonNull
    @Override
    public bloodBankBriefInfoViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.parent_viewholder, parentViewGroup, false);
        return new bloodBankBriefInfoViewHolder(view);
    }

    @NonNull
    @Override
    public bloodBankInfoViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.child_viewholder, childViewGroup, false);
        return new bloodBankInfoViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(@NonNull bloodBankBriefInfoViewHolder parentViewHolder, int parentPosition, @NonNull BloodBankBriefInfo parent) {
        parentViewHolder.bind(parent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull bloodBankInfoViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull BloodBankInfo child) {
        childViewHolder.bind(child);
    }

    public class bloodBankBriefInfoViewHolder extends ParentViewHolder {
        TextView mBloodBankBriefInfoNameTV, mBloodBankBriefInfoAddressTV, mBloodBankBriefInfoContactTV;
        ImageView mBloodBankBriefInfoImageView;

        public bloodBankBriefInfoViewHolder(@NonNull View itemView) {
            super(itemView);

            mBloodBankBriefInfoNameTV = (TextView) itemView.findViewById(R.id.bloodBanksNameTextView);
            mBloodBankBriefInfoAddressTV = (TextView) itemView.findViewById(R.id.bloodBanksAddressTextView);
            mBloodBankBriefInfoContactTV = (TextView) itemView.findViewById(R.id.bloodBanksContactTextView);
            mBloodBankBriefInfoImageView = (ImageView) itemView.findViewById(R.id.bloodBanksImage);
        }

        public void bind(BloodBankBriefInfo parent) {
            BloodBankInfo desiredChild = parent.getChildList().get(0);
            ArrayList<String> desiredContent = desiredChild.getContent();

            mBloodBankBriefInfoNameTV.setText(desiredContent.get(0));
            if(!desiredContent.get(1).equals("NA")) {
                mBloodBankBriefInfoAddressTV.setText(desiredContent.get(1));
            }
            if(!desiredContent.get(2).equals("NA")){
                mBloodBankBriefInfoContactTV.setText(desiredContent.get(2));
            }
            mBloodBankBriefInfoImageView.setImageResource(R.drawable.ic_blood_donate);
        }
    }

    public class bloodBankInfoViewHolder extends ChildViewHolder {
        TextView mBloodBankInfoAddressTV, mBloodBankInfoContactTV, mBloodBankInfoHelplineTV, mBloodBankInfoFaxTV, mBloodBankInfoCategoryTV, mBloodBankInfoWebsiteTV, mBloodBankInfoEmailTV, mBloodBankInfoServiceTimeTV;

        public bloodBankInfoViewHolder(@NonNull View itemView) {
            super(itemView);

            mBloodBankInfoAddressTV = (TextView) itemView.findViewById(R.id.bloodBankAddress);
            mBloodBankInfoContactTV = (TextView) itemView.findViewById(R.id.bloodBankContact);
            mBloodBankInfoHelplineTV = (TextView) itemView.findViewById(R.id.bloodBankHelpline);
            mBloodBankInfoFaxTV = (TextView) itemView.findViewById(R.id.bloodBankFax);
            mBloodBankInfoCategoryTV = (TextView) itemView.findViewById(R.id.bloodBankCategory);
            mBloodBankInfoWebsiteTV = (TextView) itemView.findViewById(R.id.bloodBankWebsite);
            mBloodBankInfoEmailTV = (TextView) itemView.findViewById(R.id.bloodBankEmail);
            mBloodBankInfoServiceTimeTV = (TextView) itemView.findViewById(R.id.bloodBankServiceTime);
        }

        public void bind(BloodBankInfo child) {
            ArrayList<String> desiredContent = child.getContent();
            if(!desiredContent.get(1).equals("NA")) {
                String address = "<font color='#423F3E'>" + "<b>" + "Address: " + "</b>" + "</font>" + desiredContent.get(1);
                mBloodBankInfoAddressTV.setText(Html.fromHtml(address));
                mBloodBankInfoAddressTV.setVisibility(View.VISIBLE);
            }
            if(!desiredContent.get(2).equals("NA")) {
                String contact = "<font color='#423F3E'>" + "<b>" + "Contact: " + "</b>" + "</font>" + desiredContent.get(2);
                mBloodBankInfoContactTV.setText(Html.fromHtml(contact));
                mBloodBankInfoContactTV.setVisibility(View.VISIBLE);
            }
            if(!desiredContent.get(3).equals("NA")) {
                String helpline = "<font color='#423F3E'>" + "<b>" + "Helpline: " + "</b>"+ "</font>" + desiredContent.get(3);
                mBloodBankInfoHelplineTV.setText(Html.fromHtml(helpline));
                mBloodBankInfoHelplineTV.setVisibility(View.VISIBLE);
            }
            if(!desiredContent.get(4).equals("NA")) {
                String fax = "<font color='#423F3E'>" + "<b>" + "Fax: " + "</b>" + "</font>" + desiredContent.get(4);
                mBloodBankInfoFaxTV.setText(Html.fromHtml(fax));
                mBloodBankInfoFaxTV.setVisibility(View.VISIBLE);
            }
            if(!desiredContent.get(5).equals("NA")) {
                String category = "<font color='#423F3E'>" + "<b>" + "Category: " + "</b>" + "</font>" + desiredContent.get(5);
                mBloodBankInfoCategoryTV.setText(Html.fromHtml(category));
                mBloodBankInfoCategoryTV.setVisibility(View.VISIBLE);
            }
            if(!desiredContent.get(6).equals("NA")) {
                String website = "<font color='#423F3E'>" + "<b>" + "Website: " + "</b>" + "</font>" + desiredContent.get(6);
                mBloodBankInfoWebsiteTV.setText(Html.fromHtml(website));
                mBloodBankInfoWebsiteTV.setVisibility(View.VISIBLE);
            }
            if(!desiredContent.get(7).equals("NA")) {
                String email = "<font color='#423F3E'>" + "<b>" + "E-mail: " + "</b>" + "</font>" + desiredContent.get(7);
                mBloodBankInfoEmailTV.setText(Html.fromHtml(email));
                mBloodBankInfoEmailTV.setVisibility(View.VISIBLE);
            }
            if(!desiredContent.get(8).equals("NA")) {
                String serviceTime = "<font color='#423F3E'>" + "<b>" + "Service-time: " + "</b>" + "</font>" + desiredContent.get(8);
                mBloodBankInfoServiceTimeTV.setText(Html.fromHtml(serviceTime));
                mBloodBankInfoServiceTimeTV.setVisibility(View.VISIBLE);
            }
        }
    }
}