package dream.work.rpt.wopmwcmptrate.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 예방작업 계획대비 실행 비율 목록 - 공통 DTO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public class WorkRptWoPmwCmptRateCommonDTO extends BaseDTO
{
	/** 시작일자 */ 
    private String filterStartDate           = "";
    /** 종료일자 */ 
    private String filterEndDate             = "";
    /** 공장 ID */ 
    private String filterPlantId             = "";
    /** 공장명 */ 
    private String filterPlantDesc           = "";
    /** 공장 */
    private String plantId    = "";
    /** 공장 desc */
    private String plantDesc    = "";
    /** 월 */
    private String yyyymm    = "";
    
	public String getPlantId()
    {
        return plantId;
    }
    public void setPlantId(String plantId)
    {
        this.plantId = plantId;
    }
    public String getPlantDesc()
    {
        return plantDesc;
    }
    public void setPlantDesc(String plantDesc)
    {
        this.plantDesc = plantDesc;
    }
    public String getYyyymm()
    {
        return yyyymm;
    }
    public void setYyyymm(String yyyymm)
    {
        this.yyyymm = yyyymm;
    }
    public String getFilterStartDate() {
		return filterStartDate;
	}
	public void setFilterStartDate(String filterStartDate) {
		this.filterStartDate = CommonUtil.convertDate(filterStartDate);
	}
	public String getFilterEndDate() {
		return filterEndDate;
	}
	public void setFilterEndDate(String filterEndDate) {
		this.filterEndDate = CommonUtil.convertDate(filterEndDate);
	}
	public String getFilterPlantId() {
		return filterPlantId;
	}
	public void setFilterPlantId(String filterPlantId) {
		this.filterPlantId = filterPlantId;
	}
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}
    
}
