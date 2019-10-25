package com.example.donamariabuscas;

public class Produtos {
    private String itemNome;
    private String itemDescricao;
    private String itemPreco;
    private int itemImage;

    public Produtos(String itemNome, String itemDescricao, String itemPreco, int itemImage) {
        this.itemNome = itemNome;
        this.itemDescricao = itemDescricao;
        this.itemPreco = itemPreco;
        this.itemImage = itemImage;
    }

    public String getItemNome() {
        return itemNome;
    }

    public String getItemDescricao() {
        return itemDescricao;
    }

    public String getItemPreco() {
        return itemPreco;
    }

    public int getItemImage() {
        return itemImage;
    }
}
