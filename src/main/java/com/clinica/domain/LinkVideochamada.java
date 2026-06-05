package com.clinica.domain;

public class LinkVideochamada {
    private final String url;

    public LinkVideochamada(String url) {
        if (url == null || !url.startsWith("https://")) {
            throw new IllegalArgumentException("URL da videochamada inválida ou insegura.");
        }
        this.url = url;
    }

    public String getUrl() { return url; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return url.equals(((LinkVideochamada) o).url);
    }

    @Override
    public int hashCode() { return url.hashCode(); }
}