package com.demo.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 12-12-20
 * Time: 上午12:22
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class House {
    private int id;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;

    @javax.persistence.Column(name = "name", nullable = false, insertable = true, updatable = true, length = 200, precision = 0)
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String desc;

    @javax.persistence.Column(name = "desc", nullable = true, insertable = true, updatable = true, length = 2000, precision = 0)
    @Basic
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String status;

    @javax.persistence.Column(name = "status", nullable = true, insertable = true, updatable = true, length = 50, precision = 0)
    @Basic
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String seller;

    @javax.persistence.Column(name = "seller", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    private String buyer;

    @javax.persistence.Column(name = "buyer", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    private String middler;

    @javax.persistence.Column(name = "middler", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getMiddler() {
        return middler;
    }

    public void setMiddler(String middler) {
        this.middler = middler;
    }

    private String img;

    @javax.persistence.Column(name = "img", nullable = true, insertable = true, updatable = true, length = 200, precision = 0)
    @Basic
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private String size;

    @javax.persistence.Column(name = "size", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    private String price;

    @javax.persistence.Column(name = "price", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        House house = (House) o;

        if (id != house.id) return false;
        if (buyer != null ? !buyer.equals(house.buyer) : house.buyer != null) return false;
        if (desc != null ? !desc.equals(house.desc) : house.desc != null) return false;
        if (img != null ? !img.equals(house.img) : house.img != null) return false;
        if (middler != null ? !middler.equals(house.middler) : house.middler != null) return false;
        if (name != null ? !name.equals(house.name) : house.name != null) return false;
        if (price != null ? !price.equals(house.price) : house.price != null) return false;
        if (seller != null ? !seller.equals(house.seller) : house.seller != null) return false;
        if (size != null ? !size.equals(house.size) : house.size != null) return false;
        if (status != null ? !status.equals(house.status) : house.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (seller != null ? seller.hashCode() : 0);
        result = 31 * result + (buyer != null ? buyer.hashCode() : 0);
        result = 31 * result + (middler != null ? middler.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
