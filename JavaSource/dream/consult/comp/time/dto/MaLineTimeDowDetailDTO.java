package dream.consult.comp.time.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 요일별 설정 상세 dto
 * @author  kim21017
 * @version $Id: MaLineTimeDowDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaLineTimeDowDetailDTO extends BaseDTO
{
	/** 요일별 설정 id */
	private String eqLocDowRunId	= "";
	/** 회사 No */
    private String compNo    = "";
	/** 요일 */
	private String dow				= "";
	/** 요일 명*/
	private String dowDesc			= "";
	/** 1교대(주간조) */
	private String dayRunTime		= "";
	/** 2교대(야간조) */
	private String nightRunTime		= "";
	/** 3교대(새벽조) */
	private String extraRunTime		= "";
	/** 조회순서 */
	private String ordNo			= "";
	/** 사용여부 */
	private String isUse			= "";
	/** 가동달력 id */
	private String lnWrkListId			= "";
	/** 사용횟수 */
	private String ucnt			= "";
	
	
	
	
	public String getUcnt() {
		return ucnt;
	}
	public void setUcnt(String ucnt) {
		this.ucnt = CommonUtil.convertMoney(ucnt);
	}
	public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
    public String getLnWrkListId()
    {
        return lnWrkListId;
    }
    public void setLnWrkListId(String lnWrkListId)
    {
        this.lnWrkListId = lnWrkListId;
    }
    public String getEqLocDowRunId() {
		return eqLocDowRunId;
	}
	public void setEqLocDowRunId(String eqLocDowRunId) {
		this.eqLocDowRunId = eqLocDowRunId;
	}
	public String getDow() {
		return dow;
	}
	public void setDow(String dow) {
		this.dow = dow;
	}
	public String getDowDesc() {
		return dowDesc;
	}
	public void setDowDesc(String dowDesc) {
		this.dowDesc = dowDesc;
	}
	public String getDayRunTime() {
		return dayRunTime;
	}
	public void setDayRunTime(String dayRunTime) {
		this.dayRunTime = CommonUtil.convertMoney(dayRunTime);
	}
	public String getNightRunTime() {
		return nightRunTime;
	}
	public void setNightRunTime(String nightRunTime) {
		this.nightRunTime = CommonUtil.convertMoney(nightRunTime);
	}
	public String getExtraRunTime() {
		return extraRunTime;
	}
	public void setExtraRunTime(String extraRunTime) {
		this.extraRunTime = CommonUtil.convertMoney(extraRunTime);
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
}