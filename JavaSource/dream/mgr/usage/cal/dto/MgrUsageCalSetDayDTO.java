package dream.mgr.usage.cal.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 사용달력일별횟수설정 공통 DTO
 * @author youngjoo38
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MgrUsageCalSetDayDTO extends BaseDTO
{
    
    /** 일별라인가동Id */
    private String lnWrkTimeId				= "";
    /** filter - 시작일자 */
    private String filterStartWrkDate		= "";
    /** filter - 끝일자 */
    private String filterEndWrkDate			= "";
    /** filter - 공장Id */
    private String filterPlantId			= "";
    /** filter - 공장명 */
    private String filterPlantDesc			= "";
    /** filter - 라인Id */
    private String filterLineId				= "";
    /** filter - 라인명 */
    private String filterLineDesc			= "";
    /** filter - 가동달력ID */
    private String filterLnWrkListId		= "";
    /** filter - 가동달력명 */
    private String filterLnWrkListDesc		= "";
    
	/** 공장Id */
	private String plantId					= "";
	/** 공장명 */
	private String plantDesc				= "";
	/** 일자 */
	private String wrkDate					= "";
	/** 라인Id */
	private String lineId					= "";
	/** 라인명 */
	private String lineDesc					= "";
	/** 낮 가동시간 */
	private String dayRunTime				= "";
	/** 밤 가동시간 */
	private String nightRunTime				= "";
	/** 밤 가동시간 */
	private String evenRunTime				= "";
	/** 비고 */
	private String remark					= "";
	
	private String lnStr					= "";
	/** 가동달력ID */
	private String lnWrkListId				= "";
	/** 가동달력DESC */
	private String lnWrkListDesc			= "";
	/** 사용횟수 */
	private String ucnt						= "";
	
	public String getUcnt() {
		return ucnt;
	}
	public void setUcnt(String ucnt) {
		this.ucnt = CommonUtil.convertMoney(ucnt);
	}
	public String getLnWrkListId()
    {
        return lnWrkListId;
    }
    public void setLnWrkListId(String lnWrkListId)
    {
        this.lnWrkListId = lnWrkListId;
    }
    public String getLnWrkListDesc()
    {
        return lnWrkListDesc;
    }
    public void setLnWrkListDesc(String lnWrkListDesc)
    {
        this.lnWrkListDesc = lnWrkListDesc;
    }
    public String getLnStr() {
		return lnStr;
	}
	public void setLnStr(String lnStr) {
		this.lnStr = lnStr;
	}
	public String getLnWrkTimeId() {
		return lnWrkTimeId;
	}
	public void setLnWrkTimeId(String lnWrkTimeId) {
		this.lnWrkTimeId = lnWrkTimeId;
		super.setAuditKey(lnWrkTimeId);
	}
	public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getWrkDate() {
		return wrkDate;
	}
	public void setWrkDate(String wrkDate) {
		this.wrkDate = CommonUtil.convertDate(wrkDate);
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public String getLineDesc() {
		return lineDesc;
	}
	public void setLineDesc(String lineDesc) {
		this.lineDesc = lineDesc;
	}
	public String getDayRunTime() {
		return dayRunTime;
	}
	public void setDayRunTime(String dayRunTime) {
		this.dayRunTime = dayRunTime;
	}
	public String getNightRunTime() {
		return nightRunTime;
	}
	public void setNightRunTime(String nightRunTime) {
		this.nightRunTime = nightRunTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEvenRunTime() {
		return evenRunTime;
	}
	public void setEvenRunTime(String evenRunTime) {
		this.evenRunTime = evenRunTime;
	}
	public String getFilterLnWrkListId()
    {
        return filterLnWrkListId;
    }
    public void setFilterLnWrkListId(String filterLnWrkListId)
    {
        this.filterLnWrkListId = filterLnWrkListId;
    }
    public String getFilterLnWrkListDesc()
    {
        return filterLnWrkListDesc;
    }
    public void setFilterLnWrkListDesc(String filterLnWrkListDesc)
    {
        this.filterLnWrkListDesc = filterLnWrkListDesc;
    }
	public String getFilterStartWrkDate() {
		return filterStartWrkDate;
	}
	public void setFilterStartWrkDate(String filterStartWrkDate) {
		this.filterStartWrkDate = CommonUtil.convertDate(filterStartWrkDate);
	}
	public String getFilterEndWrkDate() {
		return filterEndWrkDate;
	}
	public void setFilterEndWrkDate(String filterEndWrkDate) {
		this.filterEndWrkDate = CommonUtil.convertDate(filterEndWrkDate);
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
	public String getFilterLineId() {
		return filterLineId;
	}
	public void setFilterLineId(String filterLineId) {
		this.filterLineId = filterLineId;
	}
	public String getFilterLineDesc() {
		return filterLineDesc;
	}
	public void setFilterLineDesc(String filterLineDesc) {
		this.filterLineDesc = filterLineDesc;
	}
}
