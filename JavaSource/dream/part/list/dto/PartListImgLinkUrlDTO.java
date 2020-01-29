package dream.part.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ��ǰImage Link DTO
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public class PartListImgLinkUrlDTO extends BaseDTO
{
	/** ��ǰ�̹��� ID */
	private String ptImgUrlId			    = "";
	/** ��ǰ ID */
	private String partId			        = "";
	/** Image Link �ּ����� */
	private String imgUrl			        = "";
	/** Image ���ſ��� */
	private String isReceived			    = "";
	/** Image ���ſ��� Desc */
	private String isReceivedDesc			= "";
	/** Image ���Žð� */
	private String receivedTime			    = "";
	/** Image ����������� */
	private String imageReceiveStatus		= "";
	/** Image ����������� Desc */
	private String imageReceiveStatusDesc	= "";
	/** �������� */
	private String creDate			        = "";
	/** ������ */
	private String creBy			        = "";
	/** ������ Desc */
	private String creByDesc			    = "";
	/** �������� */
	private String updDate			        = "";
	/** ������ */
	private String updBy			        = "";
	/** ������ Desc */
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
