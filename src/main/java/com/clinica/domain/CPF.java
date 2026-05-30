package com.clinica.domain;

public class CPF {
    private final String numero;

    public CPF(String numero) {
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF não pode ser nulo ou vazio");
        }

        String cpfLimpo = numero.replaceALL("[^0-9]", "");

        if(cpfLimpo.length() != 11) {
            throw new IllegalArgumentException("O CPF deve conter exatamente 11 digitos.");
        }

        this.numero = cpfLimpo;
    }

    public String getNumero() {
        return numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CPF cpf = (cpf) o;
        return numero.equals(cpf.numero);
    }

    @Override
    public int hashCode() {
        return numero.hashCode();
    }
}