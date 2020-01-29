package dream.asset.std.asset.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ȸ���ڻ� - �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class AssetStdAssetDetailDTO extends BaseDTO
{
	/** ȸ���ڻ�ID */
	private String assetId 			= "";
	/** ȸ���ڻ��ڵ� */
	private String assetNo 			= "";
	/** ȸ���ڻ�� */
	private String description 		= "";
	/** ��뿩�� */
	private String isUse 			= "";
	/** ������� */
	private String acqDate         = "";
	/** ��氡�� */
	private String buyerAmt       = "";
	/** �����󰢴���� */
	private String depAmt          = "";
	/** �������� */
	private String resAmt           = "";
	/** ��� */
	private String remark           = "";
	
    public String getAssetId()
    {
        return assetId;
    }
    public void setAssetId(String assetId)
    {
        this.assetId = assetId;
        super.setAuditKey(assetId);
    }
    public String getAssetNo()
    {
        return assetNo;
    }
    public void setAssetNo(String assetNo)
    {
        this.assetNo = assetNo;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getIsUse()
    {
        return isUse;
    }
    public void setIsUse(String isUse)
    {
        this.isUse = isUse;
    }
    public String getAcqDate()
    {
        return acqDate;
    }
    public void setAcqDate(String acqDate)
    {
        this.acqDate = CommonUtil.convertDate(acqDate);
    }
    public String getBuyerAmt()
    {
        return buyerAmt;
    }
    public void setBuyerAmt(String buyerAmt)
    {
        this.buyerAmt = CommonUtil.convertMoney(buyerAmt);
    }
    public String getDepAmt()
    {
        return depAmt;
    }
    public void setDepAmt(String depAmt)
    {
        this.depAmt = CommonUtil.convertMoney(depAmt);
    }
    public String getResAmt()
    {
        return resAmt;
    }
    public void setResAmt(String resAmt)
    {
        this.resAmt = CommonUtil.convertMoney(resAmt);
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
	
}
