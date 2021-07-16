package com.example.app2.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.app2.R;
import com.example.app2.activities.CadastrarClienteActivity;
import com.example.app2.utils.MaskEditUtil;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

public class ClienteDialogFragment extends DialogFragment implements View.OnClickListener {

    private RadioButton rbPessoaFisica, rbPessoaJuridica;
    private TextInputLayout inputCPFouCNPJ;
    private TextWatcher textWatcher;
    private AlertDialog alertDialog;
    private View mView;

    public ClienteDialogFragment() {
    }

    @NonNull
    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        mView = inflater.inflate(R.layout.fragment_cliente_dialog, null, false);

        rbPessoaFisica = mView.findViewById(R.id.rbFisica);
        rbPessoaJuridica = mView.findViewById(R.id.rbJuridica);
        inputCPFouCNPJ = mView.findViewById(R.id.inputCPFouCNPJ);
        inputCPFouCNPJ.setEnabled(false);
        rbPessoaJuridica.setOnClickListener(this);
        rbPessoaFisica.setOnClickListener(this);

        verificaInstanceState(savedInstanceState);

        if (textWatcher == null){
            if (inputCPFouCNPJ.getEditText() != null)
            inputCPFouCNPJ.getEditText().addTextChangedListener(textWatcher);
            inputCPFouCNPJ.getEditText().removeTextChangedListener(textWatcher);
            if (rbPessoaJuridica.isChecked()){
                textWatcher = MaskEditUtil.mask(inputCPFouCNPJ.getEditText(), MaskEditUtil.FORMAT_CNPJ);
            } else if (rbPessoaFisica.isChecked()){
                textWatcher = MaskEditUtil.mask(inputCPFouCNPJ.getEditText(), MaskEditUtil.FORMAT_CPF);
            }
            inputCPFouCNPJ.getEditText().addTextChangedListener(textWatcher);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Novo Cliente");
        builder.setView(mView);
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), CadastrarClienteActivity.class);

                if (rbPessoaJuridica.isChecked()){
                    intent.putExtra("tipoCliente", "Jurídico");
                    intent.putExtra("cpfoucnpj", inputCPFouCNPJ.getEditText().getText().toString());
                } else {
                    intent.putExtra("tipoCliente", "Física");
                    intent.putExtra("cpfoucnpj", inputCPFouCNPJ.getEditText().getText().toString());
                }

                startActivity(intent);
            }
        });

        alertDialog = builder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                if (rbPessoaFisica.isChecked() &&
                        inputCPFouCNPJ.getEditText().getText().toString().length() >= 14){
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                } else if (rbPessoaJuridica.isChecked() &&
                        inputCPFouCNPJ.getEditText().getText().toString().length() >= 18){
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                } else {
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }

                inputCPFouCNPJ.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                        if (rbPessoaFisica.isChecked() && s.toString().length() >= 14){
                            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                        } else if (rbPessoaJuridica.isChecked() && s.toString().length() >= 18){
                            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                        } else {
                            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                        }

                    }
                });
            }
        });



        return alertDialog;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.rbJuridica:

                rbPessoaJuridica.setChecked(true);
                rbPessoaFisica.setChecked(false);
                inputCPFouCNPJ.setEnabled(true);

                if(!TextUtils.isEmpty(inputCPFouCNPJ.getEditText().getText().toString())){
                    inputCPFouCNPJ.getEditText().getText().clear();
                }

                inputCPFouCNPJ.setHint("CNPJ");
                inputCPFouCNPJ.getEditText().removeTextChangedListener(textWatcher);
                textWatcher = MaskEditUtil.mask(inputCPFouCNPJ.getEditText(), MaskEditUtil.FORMAT_CNPJ);
                inputCPFouCNPJ.getEditText().addTextChangedListener(textWatcher);

                break;

            case R.id.rbFisica:

                rbPessoaFisica.setChecked(true);
                rbPessoaJuridica.setChecked(false);
                inputCPFouCNPJ.setEnabled(true);

                if (!TextUtils.isEmpty(inputCPFouCNPJ.getEditText().getText().toString())){
                    inputCPFouCNPJ.getEditText().getText().clear();
                }

                inputCPFouCNPJ.setHint("CPF");
                inputCPFouCNPJ.getEditText().removeTextChangedListener(textWatcher);
                textWatcher = MaskEditUtil.mask(inputCPFouCNPJ.getEditText(), MaskEditUtil.FORMAT_CPF);
                inputCPFouCNPJ.getEditText().addTextChangedListener(textWatcher);

                break;

        }

    }

    @Override
    public void onSaveInstanceState(@NonNull @NotNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (!TextUtils.isEmpty(inputCPFouCNPJ.getEditText().getText().toString())){
            outState.putString("cpfoucnpj", inputCPFouCNPJ.getEditText().getText().toString());
        }

        if (rbPessoaFisica.isChecked()){
            outState.putBoolean("rbFisica", true);
            outState.putBoolean("rbJuridico", false);
        }

        if (rbPessoaJuridica.isChecked()){
            outState.putBoolean("rbJuridico", true);
            outState.putBoolean("rbFisica", false);
        }

    }

    public void verificaInstanceState(Bundle savedInstanceState){

        if (savedInstanceState != null){
            if (savedInstanceState.getBoolean("rbFisica") == true){

                rbPessoaFisica.setChecked(true);
                inputCPFouCNPJ.setEnabled(true);
                inputCPFouCNPJ.setHint("CPF");
                inputCPFouCNPJ.getEditText().setText(savedInstanceState.getString("cpfoucnpj"));

            } else if (savedInstanceState.getBoolean("rbJuridico") == true){
                rbPessoaJuridica.setChecked(true);
                inputCPFouCNPJ.setEnabled(true);
                inputCPFouCNPJ.setHint("CNPJ");
                inputCPFouCNPJ.getEditText().setText(savedInstanceState.getString("cpfoucnpj"));
            }

        }

    }

}