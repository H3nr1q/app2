package com.example.app2.utils;

public class ValidaEmail {

    private static final String FORMATO_EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public ValidaEmail() {
    }

    public boolean validaEmail(String string){

        boolean valido = false;

        if (string != null){
            if (string.matches(FORMATO_EMAIL)){
                valido = true;
            }
        }
        return valido;
    }

}
