package com.example.manaspande.bloodbanksindia;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.example.manaspande.bloodbanksindia.Classes.BloodBankBriefInfo;
import com.example.manaspande.bloodbanksindia.Classes.BloodBankInfo;
import com.example.manaspande.bloodbanksindia.data.DatabaseContract;
import com.example.manaspande.bloodbanksindia.data.dbAssetsHelper;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    Button submitButton;
    Toolbar mainActivityToolbar;
    Cursor resultCursor;
    RecyclerView mRecyclerView;
    SQLiteDatabase mDb;
    Adapter mAdapter;
    Spinner state, district;
    ArrayAdapter<CharSequence> arrayAdapterState, arrayAdapterDistrict;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityToolbar = (Toolbar) findViewById(R.id.mainActivityToolbar);
        setSupportActionBar(mainActivityToolbar);
        submitButton = (Button) findViewById(R.id.submitButton);
        setSpinner1();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                switch (id) {
                    case R.id.submitButton: {

                        mRecyclerView = (RecyclerView) findViewById(R.id.bloodBanksRecyclerView);
                        dbAssetsHelper dbHelper = new dbAssetsHelper(v.getContext());
                        mDb = dbHelper.getReadableDatabase();
                        Cursor mCursor = getDesiredResultsCursor(mDb);
                        final List<BloodBankBriefInfo> mList = getDesiredResultsList(mCursor);
                        mAdapter = new Adapter(v.getContext(), mList);
                        mRecyclerView.setAdapter(mAdapter);
                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(v.getContext());
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                }
            }
        });
    }

    public Cursor getDesiredResultsCursor(SQLiteDatabase db) {
        String selection = DatabaseContract.dbContract.district + "= ?";
        String[] selectionArgs = {getUserQuery()};

        resultCursor = db.query(DatabaseContract.dbContract.tableName,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null,
                null);

        return resultCursor;
    }

    public List<BloodBankBriefInfo> getDesiredResultsList(Cursor cursor) {
        List<BloodBankBriefInfo> outer = new ArrayList<>();

        try {
            while(cursor.moveToNext()) {
                String bloodBankName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.dbContract.bloodBankName));
                String bloodBankAddress = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.dbContract.address));
                String bloodBankContact = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.dbContract.contact));
                String bloodBankHelpline = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.dbContract.helpline));
                String bloodBankFax = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.dbContract.fax));
                String bloodBankCategory = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.dbContract.category));
                String bloodBankWebsite = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.dbContract.website));
                String bloodBankEmail = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.dbContract.email));
                String bloodBankServiceTime = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.dbContract.serviceTime));

                ArrayList<String> inner = new ArrayList<>();
                inner.add(bloodBankName);
                inner.add(bloodBankAddress);
                inner.add(bloodBankContact);
                inner.add(bloodBankHelpline);
                inner.add(bloodBankFax);
                inner.add(bloodBankCategory);
                inner.add(bloodBankWebsite);
                inner.add(bloodBankEmail);
                inner.add(bloodBankServiceTime);
                BloodBankInfo bloodBankInfoObject = new BloodBankInfo(inner);
                BloodBankBriefInfo bloodBankBriefInfoObject = new BloodBankBriefInfo(Arrays.asList(bloodBankInfoObject));
                outer.add(bloodBankBriefInfoObject);
            }
        }
        finally {
            cursor.close();
        }
        return outer;
    }

    public String getUserQuery() {
        String input = district.getSelectedItem().toString().toLowerCase();
        String query = WordUtils.capitalize(input);
        return query;
    }

    public void setSpinner1() {
        state = (Spinner) findViewById(R.id.state);
        final String[] statesArray = getResources().getStringArray(R.array.state);
        arrayAdapterState = ArrayAdapter.createFromResource(this, R.array.state, R.layout.custom_spinner_item);
        arrayAdapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(arrayAdapterState);

        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setSpinner2(statesArray[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setSpinner2(String state) {
        district = (Spinner) findViewById(R.id.district);

        if (state.equals("Andhra Pradesh")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.AndhraPradesh, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Andaman and Nicobar Islands")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.AndamanandNicobarIslands, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Arunachal Pradesh")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.ArunachalPradesh, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Assam")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Assam, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Bihar")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Bihar, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Chandigarh")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Chandigarh, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Chhattisgarh")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Chhattisgarh, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Dadra and Nagar Haveli")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.DadraandNagarHaveli, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Daman and Diu")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.DamanandDiu, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Delhi")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Delhi, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Goa")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Goa, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Gujarat")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Gujarat, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Haryana")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Haryana, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Himachal Pradesh")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.HimachalPradesh, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Jammu and Kashmir")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.JammuandKashmir, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Jharkhand")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Jharkhand, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Karnataka")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Karnataka, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Kerala")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Kerala, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Lakshdweep")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Lakshdweep, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Madhya Pradesh")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.MadhyaPradesh, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Maharashtra")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Maharashtra, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Manipur")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Manipur, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Meghalaya")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Meghalaya, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Mizoram")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Mizoram, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Nagaland")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Nagaland, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Odisha")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Odisha, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Puducherry")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Puducherry, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Punjab")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Punjab, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Rajasthan")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Rajasthan, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Sikkim")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Sikkim, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Tamil Nadu")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.TamilNadu, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Telangana")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Telangana, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Tripura")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Tripura, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Uttar Pradesh")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.UttarPradesh, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("Uttarakhand")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.Uttarakhand, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

        if (state.equals("West Bengal")) {
            arrayAdapterDistrict = ArrayAdapter.createFromResource(this, R.array.WestBengal, R.layout.custom_spinner_item);
            arrayAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(arrayAdapterDistrict);
        }

    }
}