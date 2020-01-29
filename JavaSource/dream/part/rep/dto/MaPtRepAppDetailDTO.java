package dream.part.rep.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 수리기안 - 상세 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPtRepAppDetailDTO extends BaseDTO
{
	/** 수리-기안품의Id */
	private String ptRprAppListId  = "";
	/** 부품수리Id */
	private String ptRepairListId  = "";
	/** 문서번호 */
	private String ptAppId       = "";
	/** 참조 */
	private String referrer      = "";
	/** 접수일자 */
	private String recDate       = "";
	/** 제목 */
	private String title         = "";
	/** 설비명 */
	private String eqDesc        = "";
	/** 설비번호 */
	private String itemNo        = "";
	/** 설비장소 */
	private String eqPlace       = "";
	/** 의뢰일자 */
	private String reqDate       = "";
	/** 고장발생일 */
	private String occurDate     = "";
    /** 완료희망일 */
    private String tbcDate       = "";
	/** 소요금액 */
	private String totAmt        = "";
	/** 내용 */
	private String contents      = "";
	/** 고장원인 */
	private String failDesc      = "";
	/** 의견 */
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
