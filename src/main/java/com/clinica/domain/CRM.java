package com.clinica.domain;

public class CRM {
    private final String numero;
    private final String uf;

    public CRM(String numero, String uf) {
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException("O número do CRM é obrigatório.");
        }
        if (uf == null || uf.trim().length() != 2) {
            throw new IllegalArgumentException("A UF do CRM é obrigatória e deve conter 2 caracteres.");
        }

        this.numero = numero.trim();
        this.uf = uf.trim().toUpperCase();
    }

    public String getNumero() { return numero; }
    public String getUf() { return uf;}

    public String getValorCompleto() {
        return numero + "/" + uf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CRM crm = (CRM) o;
        return numero.equals(crm.numero) && uf.equals(crm.uf);
    }

    @Override
    public int hashCode() {
        int result = numero.hashCode();
        result = 31 * result + uf.hashCode();
        return result;
    }
}