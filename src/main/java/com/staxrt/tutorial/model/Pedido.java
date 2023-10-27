package com.staxrt.tutorial.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "pedidos")
@EntityListeners(AuditingEntityListener.class)
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nome_produto", nullable = false)
    private String nomeProduto;

    @Column(name = "qtd", nullable = false)
    private int qtd;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

  public long getId() {
        return id;
    }

  public void setId(long id) {
        this.id = id;
    }

  public String getNomeProduto() {
        return nomeProduto;
    }

  public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

  public int getQtd() {
        return qtd;
    }

  public void setQtd(int qtd) {
        this.qtd = qtd;
    }

  public Date getCreatedAt() {
        return createdAt;
    }

  public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", qtd='" + qtd + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }


}
