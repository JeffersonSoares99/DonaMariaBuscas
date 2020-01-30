package com.example.donamariabuscas;

public class Produtos {
    private String itemName;
    private String itemDescription;
    private String itemPrice;
    private String itemMercado;
    private String itemEndereco;
    private String itemImage;


    private String key;


    public Produtos() {
    }

    public Produtos(String itemName, String itemDescription, String itemPrice, String itemMercado, String itemEndereco, String itemImage) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemMercado = itemMercado;
        this.itemEndereco = itemEndereco;
        this.itemImage = itemImage;
    }


    public String getItemName() { return itemName; }

    public String getItemDescription() { return itemDescription; }

    public String getItemPrice() { return itemPrice; }

    public String getItemMercado() { return itemMercado; }

    public String getItemEndereco() { return itemEndereco; }

    public String getItemImage() { return itemImage; }


    public String getKey() { return key; }

    public void setKey(String key) { this.key = key; }



}
