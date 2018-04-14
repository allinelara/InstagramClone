package com.parse.starter.util;

import java.util.HashMap;

/**
 * Created by allininha on 03/01/18.
 */

public class ParseErros {
    private HashMap<Integer, String> erros;

    public ParseErros() {
        this.erros = new HashMap<>();
        this.erros.put(202, "Usuário já exite, crie outro usuário.");
        this.erros.put(201, "Preencha a senha por favor.");

    }

    public String getErro (int codErro){
        return this.erros.get(codErro);
    }
}
