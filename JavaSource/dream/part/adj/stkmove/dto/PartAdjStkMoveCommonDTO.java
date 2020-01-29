package dream.part.adj.stkmove.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 재고이동 공통 DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * 
 */
public class PartAdjStkMoveCommonDTO extends BaseDTO
{
	/** 재고이동 id */
	private String ptStkMoveId 				   = "";
	/** 필터 처리일자 Start  */
	private String filterStartMoveDate	       = "";
	/** 필터 처리일자 End  */
	private String filterEndMoveDate	       = "";
	/** 필터 재고이동상태  */
	private String filterPtStkMoveStatus	   = "";
	/** 필터 재고이동상태 Desc  */
	private String filterPtStkMoveStatusDesc   = "";
	/** 필터 From 창고 id  */
	private String filterFromWcodeId	       = "";
	/** 필터 From 창고 Desc  */
	private String filterFromWname	       = "";
	/** 필터 To 창고 id  */
	private String filterToWcodeId	           = "";
	/** 필터 To 창고 Desc  */
	private String filterToWname	       = "";
	/** 필터 부품 id  */
	private String filterPartId	               = "";
	/** 필터 부품 Desc  */
	private String filterPartDesc	           = "";
	/** 필터 등록자 id  */
	private String filterRegId	               = "";
	/** 필터 등록자 Desc  */
	private String filterRegDesc	           = "";
	
    public String getPtStkMoveId()
    {
        return ptStkMoveId;
    }
    public void setPtStkMoveId(String ptStkMoveId)
    {
        this.ptStkMoveId = ptStkMoveId;
    }
    public String getFilterStartMoveDate()
    {
        return filterStartMoveDate;
    }
    public void setFilterStartMoveDate(String filterStartMoveDate)
    {
        this.filterStartMoveDate = CommonUtil.convertDate(filterStartMoveDate);
    }
    public String getFilterEndMoveDate()
    {
        return filterEndMoveDate;
    }
    public void setFilterEndMoveDate(String filterEndMoveDate)
    {
        this.filterEndMoveDate = CommonUtil.convertDate(filterEndMoveDate);
    }
    public String getFilterPtStkMoveStatus()
    {
        return filterPtStkMoveStatus;
    }
    public void setFilterPtStkMoveStatus(String filterPtStkMoveStatus)
    {
        this.filterPtStkMoveStatus = filterPtStkMoveStatus;
    }
    public String getFilterPtStkMoveStatusDesc()
    {
        return filterPtStkMoveStatusDesc;
    }
    public void setFilterPtStkMoveStatusDesc(String filterPtStkMoveStatusDesc)
    {
        this.filterPtStkMoveStatusDesc = filterPtStkMoveStatusDesc;
    }
    public String getFilterFromWcodeId()
    {
        return filterFromWcodeId;
    }
    public void setFilterFromWcodeId(String filterFromWcodeId)
    {
        this.filterFromWcodeId = filterFromWcodeId;
    }
    public String getFilterFromWname()
    {
        return filterFromWname;
    }
    public void setFilterFromWname(String filterFromWname)
    {
        this.filterFromWname = filterFromWname;
    }
    public String getFilterToWcodeId()
    {
        return filterToWcodeId;
    }
    public void setFilterToWcodeId(String filterToWcodeId)
    {
        this.filterToWcodeId = filterToWcodeId;
    }
    public String getFilterToWname()
    {
        return filterToWname;
    }
    public void setFilterToWname(String filterToWname)
    {
        this.filterToWname = filterToWname;
    }
    public String getFilterPartId()
    {
        return filterPartId;
    }
    public void setFilterPartId(String filterPartId)
    {
        this.filterPartId = filterPartId;
    }
    public String getFilterPartDesc()
    {
        return filterPartDesc;
    }
    public void setFilterPartDesc(String filterPartDesc)
    {
        this.filterPartDesc = filterPartDesc;
    }
    public String getFilterRegId()
    {
        return filterRegId;
    }
    public void setFilterRegId(String filterRegId)
    {
        this.filterRegId = filterRegId;
    }
    public String getFilterRegDesc()
    {
        return filterRegDesc;
    }
    public void setFilterRegDesc(String filterRegDesc)
    {
        this.filterRegDesc = filterRegDesc;
    }
	
}
