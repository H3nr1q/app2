package com.example.app2.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.app2.R;
import com.example.app2.database.DbHelper;
import com.example.app2.helper.DBSelector;

import org.jetbrains.annotations.NotNull;

public class SelectBancoDialogFragment extends DialogFragment implements View.OnClickListener {

    private View mView;
    private Button btnBanco1, btnBanco2, btnBanco3;
    private OnDialogButtonClick onDialogButtonClick;

    public SelectBancoDialogFragment() {
    }

    @NonNull
    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        mView = inflater.inflate(R.layout.fragment_select_banco_dialog, null, false);

        btnBanco1 = mView.findViewById(R.id.btnBD1);
        btnBanco2 = mView.findViewById(R.id.btnBD2);
        btnBanco3 = mView.findViewById(R.id.btnBD3);

        btnBanco1.setOnClickListener(this);
        btnBanco2.setOnClickListener(this);
        btnBanco3.setOnClickListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Escolha o Banco de Dados:");
        builder.setView(mView);

        return builder.create();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnBD1:
                DbHelper.getInstance(DBSelector.getInstance().getDbName()).setNull();
                DBSelector.getInstance().setDbName(DbHelper.DB_1);
                onDialogButtonClick.onButtonClick();
                dismiss();
                break;

            case R.id.btnBD2:
                DbHelper.getInstance(DBSelector.getInstance().getDbName()).setNull();
                DBSelector.getInstance().setDbName(DbHelper.DB_2);
                onDialogButtonClick.onButtonClick();
                dismiss();
                break;

            case R.id.btnBD3:
                DbHelper.getInstance(DBSelector.getInstance().getDbName()).setNull();
                DBSelector.getInstance().setDbName(DbHelper.DB_3);
                onDialogButtonClick.onButtonClick();
                dismiss();
                break;

        }
    }

    public void setOnDialogButtonClick(OnDialogButtonClick onDialogButtonClick){

        this.onDialogButtonClick = onDialogButtonClick;
    }

    public interface OnDialogButtonClick{

        void onButtonClick();

    }



}
