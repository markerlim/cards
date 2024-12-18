package com.geekstack.cards.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "CL_cookierunbraverse")
public class CookieRunCard {

    @Id
    private ObjectId _id;

    private int id;

    @Field("elementId")
    private int elementId;

    @Field("title")
    @TextIndexed()
    private String title;

    @Field("field_artistTitle")
    private String artistTitle;

    @Field("field_productTitle")
    private String productTitle;

    @Field("field_cardDesc")
    private String cardDescription;

    @Field("field_rare_tzsrperf")
    private String rarity;

    @Field("field_hp_zbxcocvx")
    private String hp;

    @Field("field_cardNo_suyeowsc")
    private String cardNo;

    @Field("field_grade")
    private String grade;

    @Field("cardImage")
    private String cardImage;

    private String productCategory;
    private String productCategoryTitle;

    private String cardType;
    private String cardTypeTitle;

    private String energyType;
    private String energyTypeTitle;

    private String cardLevel;
    private String cardLevelTitle;

    private String boostercode;

    public CookieRunCard() {
    }

    public CookieRunCard(ObjectId _id, int id, int elementId, String title, String artistTitle, String productTitle,
                         String cardDescription, String rarity, String hp, String cardNo, String grade,
                         String cardImage, String productCategory, String productCategoryTitle, String cardType,
                         String cardTypeTitle, String energyType, String energyTypeTitle, String cardLevel,
                         String cardLevelTitle, String boostercode) {
        this._id = _id;
        this.id = id;
        this.elementId = elementId;
        this.title = title;
        this.artistTitle = artistTitle;
        this.productTitle = productTitle;
        this.cardDescription = cardDescription;
        this.rarity = rarity;
        this.hp = hp;
        this.cardNo = cardNo;
        this.grade = grade;
        this.cardImage = cardImage;
        this.productCategory = productCategory;
        this.productCategoryTitle = productCategoryTitle;
        this.cardType = cardType;
        this.cardTypeTitle = cardTypeTitle;
        this.energyType = energyType;
        this.energyTypeTitle = energyTypeTitle;
        this.cardLevel = cardLevel;
        this.cardLevelTitle = cardLevelTitle;
        this.boostercode = boostercode;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getElementId() {
        return elementId;
    }

    public void setElementId(int elementId) {
        this.elementId = elementId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtistTitle() {
        return artistTitle;
    }

    public void setArtistTitle(String artistTitle) {
        this.artistTitle = artistTitle;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductCategoryTitle() {
        return productCategoryTitle;
    }

    public void setProductCategoryTitle(String productCategoryTitle) {
        this.productCategoryTitle = productCategoryTitle;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardTypeTitle() {
        return cardTypeTitle;
    }

    public void setCardTypeTitle(String cardTypeTitle) {
        this.cardTypeTitle = cardTypeTitle;
    }

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }

    public String getEnergyTypeTitle() {
        return energyTypeTitle;
    }

    public void setEnergyTypeTitle(String energyTypeTitle) {
        this.energyTypeTitle = energyTypeTitle;
    }

    public String getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(String cardLevel) {
        this.cardLevel = cardLevel;
    }

    public String getCardLevelTitle() {
        return cardLevelTitle;
    }

    public void setCardLevelTitle(String cardLevelTitle) {
        this.cardLevelTitle = cardLevelTitle;
    }

    public String getBoostercode() {
        return boostercode;
    }

    public void setBoostercode(String boostercode) {
        this.boostercode = boostercode;
    }
}
