package dream.part.adj.stkmove.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ����̵� ���� DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * 
 */
public class PartAdjStkMoveCommonDTO extends BaseDTO
{
	/** ����̵� id */
	private String ptStkMoveId 				   = "";
	/** ���� ó������ Start  */
	private String filterStartMoveDate	       = "";
	/** ���� ó������ End  */
	private String filterEndMoveDate	       = "";
	/** ���� ����̵�����  */
	private String filterPtStkMoveStatus	   = "";
	/** ���� ����̵����� Desc  */
	private String filterPtStkMoveStatusDesc   = "";
	/** ���� From â�� id  */
	private String filterFromWcodeId	       = "";
	/** ���� From â�� Desc  */
	private String filterFromWname	       = "";
	/** ���� To â�� id  */
	private String filterToWcodeId	           = "";
	/** ���� To â�� Desc  */
	private String filterToWname	       = "";
	/** ���� ��ǰ id  */
	private String filterPartId	               = "";
	/** ���� ��ǰ Desc  */
	private String filterPartDesc	           = "";
	/** ���� ����� id  */
	private String filterRegId	               = "";
	/** ���� ����� Desc  */
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
