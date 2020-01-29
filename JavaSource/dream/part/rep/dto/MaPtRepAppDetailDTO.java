package dream.part.rep.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ������� - �� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPtRepAppDetailDTO extends BaseDTO
{
	/** ����-���ǰ��Id */
	private String ptRprAppListId  = "";
	/** ��ǰ����Id */
	private String ptRepairListId  = "";
	/** ������ȣ */
	private String ptAppId       = "";
	/** ���� */
	private String referrer      = "";
	/** �������� */
	private String recDate       = "";
	/** ���� */
	private String title         = "";
	/** ����� */
	private String eqDesc        = "";
	/** �����ȣ */
	private String itemNo        = "";
	/** ������� */
	private String eqPlace       = "";
	/** �Ƿ����� */
	private String reqDate       = "";
	/** ����߻��� */
	private String occurDate     = "";
    /** �Ϸ������ */
    private String tbcDate       = "";
	/** �ҿ�ݾ� */
	private String totAmt        = "";
	/** ���� */
	private String contents      = "";
	/** ������� */
	private String failDesc      = "";
	/** �ǰ� */
	private String comments      = "";
	
    public String getPtRprAppListId()
    {
        return ptRprAppListId;
    }
    public void setPtRprAppListId(String ptRprAppListId)
    {
        this.ptRprAppListId = ptRprAppListId;
    }
    public String getPtRepairListId()
    {
        return ptRepairListId;
    }
    public void setPtRepairListId(String ptRepairListId)
    {
        this.ptRepairListId = ptRepairListId;
    }
    public String getPtAppId()
    {
        return ptAppId;
    }
    public void setPtAppId(String ptAppId)
    {
        this.ptAppId = ptAppId;
    }
    public String getReferrer()
    {
        return referrer;
    }
    public void setReferrer(String referrer)
    {
        this.referrer = referrer;
    }
    public String getRecDate()
    {
        return recDate;
    }
    public void setRecDate(String recDate)
    {
        this.recDate = CommonUtil.convertDate(recDate);
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getEqDesc()
    {
        return eqDesc;
    }
    public void setEqDesc(String eqDesc)
    {
        this.eqDesc = eqDesc;
    }
    public String getItemNo()
    {
        return itemNo;
    }
    public void setItemNo(String itemNo)
    {
        this.itemNo = itemNo;
    }
    public String getEqPlace()
    {
        return eqPlace;
    }
    public void setEqPlace(String eqPlace)
    {
        this.eqPlace = eqPlace;
    }
    public String getReqDate()
    {
        return reqDate;
    }
    public void setReqDate(String reqDate)
    {
        this.reqDate = CommonUtil.convertDate(reqDate);
    }
    public String getOccurDate()
    {
        return occurDate;
    }
    public void setOccurDate(String occurDate)
    {
        this.occurDate = occurDate;
    }
    public String getTbcDate()
    {
        return tbcDate;
    }
    public void setTbcDate(String tbcDate)
    {
        this.tbcDate = tbcDate;
    }
    public String getTotAmt()
    {
        return totAmt;
    }
    public void setTotAmt(String totAmt)
    {
        this.totAmt = totAmt;
    }
    public String getContents()
    {
        return contents;
    }
    public void setContents(String contents)
    {
        this.contents = contents;
    }
    public String getFailDesc()
    {
        return failDesc;
    }
    public void setFailDesc(String failDesc)
    {
        this.failDesc = failDesc;
    }
    public String getComments()
    {
        return comments;
    }
    public void setComments(String comments)
    {
        this.comments = comments;
    }
}
