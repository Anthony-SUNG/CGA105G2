package com.waiting.model.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Standby {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "STA_ID")
  private Integer staId;
  @Column(name = "STORE_ID")
  private Integer storeId;
  @Column(name = "STA_NAME")
  private String staName;
  @Column(name = "STA_PHONE")
  private String staPhone;
  @Column(name = "STA_NUMBER")
  private Integer staNumber;
  @Column(name = "STA_TIME")
  private java.sql.Timestamp staTime;
  @Column(name = "STA_STATUS")
  private Integer staStatus;


}
