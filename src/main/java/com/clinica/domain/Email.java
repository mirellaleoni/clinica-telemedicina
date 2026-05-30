package com.clinica.domain;

public class Email {
    private final String endereco;

    public Email(String endereco){
        if(endereco == null || endereco.trim().isEmpty()){
            throw new IllegalArgumentException("O email não pode ser nulo ou vazio.");
        }

        if(!endereco.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            throw new IllegalArgumentException("Formato de email inválido.");
        }

        this.endereco = endereco;
    }

    public String getEndereco(){
        return endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return endereco.equals(email.endereco);
    }

    @Override
    public int hashCode() {
        return endereco.hashCode();
    }
}