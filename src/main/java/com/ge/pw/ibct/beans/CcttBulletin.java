/*
 * Java bean class for entity table cctt_bulletin 
 * Created on 30 Nov 2016 ( Date ISO 2016-11-30 - Time 20:48:19 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.ge.pw.ibct.beans;

import java.io.Serializable;

import java.util.Date;

/**
 * Entity bean for table "cctt_bulletin"
 * 
 * @author Telosys Tools Generator
 *
 */
public class CcttBulletin implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String     bulletinNum  ; // Primary Key

    private String     productLine  ;
    private Integer    bulletinTypeCode ;
    private Date       issueDate    ;
    private String     bulletinStatus ;
    private String     supercededBulletinNum ;
    private Integer    latestRevId  ;
    private String     createdBy    ;
    private Date       createdDate  ;
    private String     lastUpdatedBy ;
    private Date       lastUpdatedDate ;

    /**
     * Default constructor
     */
    public CcttBulletin()
    {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR THE PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "bulletinNum" field value
     * This field is mapped on the database column "bulletin_num" ( type "varchar", NotNull : true ) 
     * @param bulletinNum
     */
	public void setBulletinNum( String bulletinNum )
    {
        this.bulletinNum = bulletinNum ;
    }
    /**
     * Get the "bulletinNum" field value
     * This field is mapped on the database column "bulletin_num" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
	public String getBulletinNum()
    {
        return this.bulletinNum;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR DATA FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : product_line ( varchar ) 
    /**
     * Set the "productLine" field value
     * This field is mapped on the database column "product_line" ( type "varchar", NotNull : true ) 
     * @param productLine
     */
    public void setProductLine( String productLine )
    {
        this.productLine = productLine;
    }
    /**
     * Get the "productLine" field value
     * This field is mapped on the database column "product_line" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getProductLine()
    {
        return this.productLine;
    }

    //--- DATABASE MAPPING : bulletin_type_code ( int4 ) 
    /**
     * Set the "bulletinTypeCode" field value
     * This field is mapped on the database column "bulletin_type_code" ( type "int4", NotNull : true ) 
     * @param bulletinTypeCode
     */
    public void setBulletinTypeCode( Integer bulletinTypeCode )
    {
        this.bulletinTypeCode = bulletinTypeCode;
    }
    /**
     * Get the "bulletinTypeCode" field value
     * This field is mapped on the database column "bulletin_type_code" ( type "int4", NotNull : true ) 
     * @return the field value
     */
    public Integer getBulletinTypeCode()
    {
        return this.bulletinTypeCode;
    }

    //--- DATABASE MAPPING : issue_date ( timestamp ) 
    /**
     * Set the "issueDate" field value
     * This field is mapped on the database column "issue_date" ( type "timestamp", NotNull : true ) 
     * @param issueDate
     */
    public void setIssueDate( Date issueDate )
    {
        this.issueDate = issueDate;
    }
    /**
     * Get the "issueDate" field value
     * This field is mapped on the database column "issue_date" ( type "timestamp", NotNull : true ) 
     * @return the field value
     */
    public Date getIssueDate()
    {
        return this.issueDate;
    }

    //--- DATABASE MAPPING : bulletin_status ( varchar ) 
    /**
     * Set the "bulletinStatus" field value
     * This field is mapped on the database column "bulletin_status" ( type "varchar", NotNull : true ) 
     * @param bulletinStatus
     */
    public void setBulletinStatus( String bulletinStatus )
    {
        this.bulletinStatus = bulletinStatus;
    }
    /**
     * Get the "bulletinStatus" field value
     * This field is mapped on the database column "bulletin_status" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getBulletinStatus()
    {
        return this.bulletinStatus;
    }

    //--- DATABASE MAPPING : superceded_bulletin_num ( varchar ) 
    /**
     * Set the "supercededBulletinNum" field value
     * This field is mapped on the database column "superceded_bulletin_num" ( type "varchar", NotNull : false ) 
     * @param supercededBulletinNum
     */
    public void setSupercededBulletinNum( String supercededBulletinNum )
    {
        this.supercededBulletinNum = supercededBulletinNum;
    }
    /**
     * Get the "supercededBulletinNum" field value
     * This field is mapped on the database column "superceded_bulletin_num" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getSupercededBulletinNum()
    {
        return this.supercededBulletinNum;
    }

    //--- DATABASE MAPPING : latest_rev_id ( int4 ) 
    /**
     * Set the "latestRevId" field value
     * This field is mapped on the database column "latest_rev_id" ( type "int4", NotNull : false ) 
     * @param latestRevId
     */
    public void setLatestRevId( Integer latestRevId )
    {
        this.latestRevId = latestRevId;
    }
    /**
     * Get the "latestRevId" field value
     * This field is mapped on the database column "latest_rev_id" ( type "int4", NotNull : false ) 
     * @return the field value
     */
    public Integer getLatestRevId()
    {
        return this.latestRevId;
    }

    //--- DATABASE MAPPING : created_by ( varchar ) 
    /**
     * Set the "createdBy" field value
     * This field is mapped on the database column "created_by" ( type "varchar", NotNull : true ) 
     * @param createdBy
     */
    public void setCreatedBy( String createdBy )
    {
        this.createdBy = createdBy;
    }
    /**
     * Get the "createdBy" field value
     * This field is mapped on the database column "created_by" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getCreatedBy()
    {
        return this.createdBy;
    }

    //--- DATABASE MAPPING : created_date ( timestamp ) 
    /**
     * Set the "createdDate" field value
     * This field is mapped on the database column "created_date" ( type "timestamp", NotNull : true ) 
     * @param createdDate
     */
    public void setCreatedDate( Date createdDate )
    {
        this.createdDate = createdDate;
    }
    /**
     * Get the "createdDate" field value
     * This field is mapped on the database column "created_date" ( type "timestamp", NotNull : true ) 
     * @return the field value
     */
    public Date getCreatedDate()
    {
        return this.createdDate;
    }

    //--- DATABASE MAPPING : last_updated_by ( varchar ) 
    /**
     * Set the "lastUpdatedBy" field value
     * This field is mapped on the database column "last_updated_by" ( type "varchar", NotNull : true ) 
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy( String lastUpdatedBy )
    {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /**
     * Get the "lastUpdatedBy" field value
     * This field is mapped on the database column "last_updated_by" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getLastUpdatedBy()
    {
        return this.lastUpdatedBy;
    }

    //--- DATABASE MAPPING : last_updated_date ( timestamp ) 
    /**
     * Set the "lastUpdatedDate" field value
     * This field is mapped on the database column "last_updated_date" ( type "timestamp", NotNull : true ) 
     * @param lastUpdatedDate
     */
    public void setLastUpdatedDate( Date lastUpdatedDate )
    {
        this.lastUpdatedDate = lastUpdatedDate;
    }
    /**
     * Get the "lastUpdatedDate" field value
     * This field is mapped on the database column "last_updated_date" ( type "timestamp", NotNull : true ) 
     * @return the field value
     */
    public Date getLastUpdatedDate()
    {
        return this.lastUpdatedDate;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(bulletinNum);
        sb.append("|");
        sb.append(productLine);
        sb.append("|");
        sb.append(bulletinTypeCode);
        sb.append("|");
        sb.append(issueDate);
        sb.append("|");
        sb.append(bulletinStatus);
        sb.append("|");
        sb.append(supercededBulletinNum);
        sb.append("|");
        sb.append(latestRevId);
        sb.append("|");
        sb.append(createdBy);
        sb.append("|");
        sb.append(createdDate);
        sb.append("|");
        sb.append(lastUpdatedBy);
        sb.append("|");
        sb.append(lastUpdatedDate);
        return sb.toString(); 
    } 


}
