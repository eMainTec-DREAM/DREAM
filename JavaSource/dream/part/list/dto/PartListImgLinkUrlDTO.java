package dream.part.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 부품Image Link DTO
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public class PartListImgLinkUrlDTO extends BaseDTO
{
	/** 부품이미지 ID */
	private String ptImgUrlId			    = "";
	/** 부품 ID */
	private String partId			        = "";
	/** Image Link 주소정보 */
	private String imgUrl			        = "";
	/** Image 수신여부 */
	private String isReceived			    = "";
	/** Image 수신여부 Desc */
	private String isReceivedDesc			= "";
	/** Image 수신시간 */
	private String receivedTime			    = "";
	/** Image 수신진행상태 */
	private String imageReceiveStatus		= "";
	/** Image 수신진행상태 Desc */
	private String imageReceiveStatusDesc	= "";
	/** 생성일자 */
	private String creDate			        = "";
	/** 생성자 */
	private String creBy			        = "";
	/** 생성자 Desc */
	private String creByDesc			    = "";
	/** 수정일자 */
	private String updDate			        = "";
	/** 수정자 */
	private String updBy			        = "";
	/** 수정자 Desc */
	private String updByDesc			    = "";
	
    public String getPtImgUrlId()
    {
        return ptImgUrlId;
    }
    public void setPtImgUrlId(String ptImgUrlId)
    {
        this.ptImgUrlId = ptImgUrlId;
    }
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
    }
    public String getImgUrl()
    {
        return imgUrl;
    }
    public void setImgUrl(String imgUrl)
    {
        this.imgUrl = imgUrl;
    }
    public String getIsReceived()
    {
        return isReceived;
    }
    public void setIsReceived(String isReceived)
    {
        this.isReceived = isReceived;
    }
    public String getIsReceivedDesc()
    {
        return isReceivedDesc;
    }
    public void setIsReceivedDesc(String isReceivedDesc)
    {
        this.isReceivedDesc = isReceivedDesc;
    }
    public String getReceivedTime()
    {
        return receivedTime;
    }
    public void setReceivedTime(String receivedTime)
    {
        this.receivedTime = CommonUtil.convertDateTime(receivedTime);
    }
    public String getImageReceiveStatus()
    {
        return imageReceiveStatus;
    }
    public void setImageReceiveStatus(String imageReceiveStatus)
    {
        this.imageReceiveStatus = imageReceiveStatus;
    }
    public String getImageReceiveStatusDesc()
    {
        return imageReceiveStatusDesc;
    }
    public void setImageReceiveStatusDesc(String imageReceiveStatusDesc)
    {
        this.imageReceiveStatusDesc = imageReceiveStatusDesc;
    }
    public String getCreDate()
    {
        return creDate;
    }
    public void setCreDate(String creDate)
    {
        this.creDate = CommonUtil.convertDateTime(creDate);
    }
    public String getCreBy()
    {
        return creBy;
    }
    public void setCreBy(String creBy)
    {
        this.creBy = creBy;
    }
    public String getCreByDesc()
    {
        return creByDesc;
    }
    public void setCreByDesc(String creByDesc)
    {
        this.creByDesc = creByDesc;
    }
    public String getUpdDate()
    {
        return updDate;
    }
    public void setUpdDate(String updDate)
    {
        this.updDate = CommonUtil.convertDateTime(updDate);
    }
    public String getUpdBy()
    {
        return updBy;
    }
    public void setUpdBy(String updBy)
    {
        this.updBy = updBy;
    }
    public String getUpdByDesc()
    {
        return updByDesc;
    }
    public void setUpdByDesc(String updByDesc)
    {
        this.updByDesc = updByDesc;
    }
}
