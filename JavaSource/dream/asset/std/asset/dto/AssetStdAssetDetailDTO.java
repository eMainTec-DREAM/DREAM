package dream.asset.std.asset.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 회계자산 - 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class AssetStdAssetDetailDTO extends BaseDTO
{
	/** 회계자산ID */
	private String assetId 			= "";
	/** 회계자산코드 */
	private String assetNo 			= "";
	/** 회계자산명 */
	private String description 		= "";
	/** 사용여부 */
	private String isUse 			= "";
	/** 취득일자 */
	private String acqDate         = "";
	/** 취득가액 */
	private String buyerAmt       = "";
	/** 감가상각누계액 */
	private String depAmt          = "";
	/** 잔존가액 */
	private String resAmt           = "";
	/** 비고 */
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
