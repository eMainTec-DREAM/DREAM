package dream.consult.comp.time.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���Ϻ� ���� �� dto
 * @author  kim21017
 * @version $Id: MaLineTimeDowDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaLineTimeDowDetailDTO extends BaseDTO
{
	/** ���Ϻ� ���� id */
	private String eqLocDowRunId	= "";
	/** ȸ�� No */
    private String compNo    = "";
	/** ���� */
	private String dow				= "";
	/** ���� ��*/
	private String dowDesc			= "";
	/** 1����(�ְ���) */
	private String dayRunTime		= "";
	/** 2����(�߰���) */
	private String nightRunTime		= "";
	/** 3����(������) */
	private String extraRunTime		= "";
	/** ��ȸ���� */
	private String ordNo			= "";
	/** ��뿩�� */
	private String isUse			= "";
	/** �����޷� id */
	private String lnWrkListId			= "";
	/** ���Ƚ�� */
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