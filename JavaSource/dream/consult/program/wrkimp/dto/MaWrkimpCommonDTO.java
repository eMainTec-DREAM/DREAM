package dream.consult.program.wrkimp.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 공통 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaWrkimpCommonDTO extends BaseDTO
{
	
	private String gowrkimpId					= "";
	private String gowrkimpNo					= "";
	
	/** 진행상태[작업중,작업중,완료] */
	private String gowrkimpStatus               = "";
	/** 진행상태[작업중,작업중,완료] */
	private String gowrkimpStatusDesc           = "";

	private String description					= "";
	private String perform					    = "";
	
	/** 발생구분[유지보수, 내부개선, HelpDesk] */
	private String wrkImpCreType                = "";
	private String wrkImpCreTypeDesc            = "";
	
	private String wrImpSite                    = "";
	
	private String writeDate					= "";
	private String writeByName					= "";
	
	private String viewDate						= "";
	private String viewByName					= "";
	
	private String workSDate					= "";
	private String workEDate					= "";
	private String workTime   					= "";
	private String workName   					= "";
	
	/** 회사코드 */
	private String compNo                  		= "";
	/** Key Id */
	private String helpdeskId                   = "";
	
	private String wrkimpSite                   = "";
	private String workByName                   = "";
	
	
	
	

	
	
	public String getWorkByName() {
		return workByName;
	}
	public void setWorkByName(String workByName) {
		this.workByName = workByName;
	}
	public String getWrkimpSite() {
		return wrkimpSite;
	}
	public void setWrkimpSite(String wrkimpSite) {
		this.wrkimpSite = wrkimpSite;
	}
	public String getGowrkimpId() {
		return gowrkimpId;
	}
	public void setGowrkimpId(String gowrkimpId) {
		this.gowrkimpId = gowrkimpId;
	}
	public String getGowrkimpNo() {
		return gowrkimpNo;
	}
	public void setGowrkimpNo(String gowrkimpNo) {
		this.gowrkimpNo = gowrkimpNo;
	}
	public String getGowrkimpStatus() {
		return gowrkimpStatus;
	}
	public void setGowrkimpStatus(String gowrkimpStatus) {
		this.gowrkimpStatus = gowrkimpStatus;
	}
	public String getGowrkimpStatusDesc() {
		return gowrkimpStatusDesc;
	}
	public void setGowrkimpStatusDesc(String gowrkimpStatusDesc) {
		this.gowrkimpStatusDesc = gowrkimpStatusDesc;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPerform() {
		return perform;
	}
	public void setPerform(String perform) {
		this.perform = perform;
	}
	public String getWrkImpCreType() {
		return wrkImpCreType;
	}
	public void setWrkImpCreType(String wrkImpCreType) {
		this.wrkImpCreType = wrkImpCreType;
	}
	public String getWrkImpCreTypeDesc() {
		return wrkImpCreTypeDesc;
	}
	public void setWrkImpCreTypeDesc(String wrkImpCreTypeDesc) {
		this.wrkImpCreTypeDesc = wrkImpCreTypeDesc;
	}
	public String getWrImpSite() {
		return wrImpSite;
	}
	public void setWrImpSite(String wrImpSite) {
		this.wrImpSite = wrImpSite;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = CommonUtil.convertDate(writeDate);
	}
	public String getWriteByName() {
		return writeByName;
	}
	public void setWriteByName(String writeByName) {
		this.writeByName = writeByName;
	}
	public String getViewDate() {
		return viewDate;
	}
	public void setViewDate(String viewDate) {
		this.viewDate = CommonUtil.convertDate(viewDate);
	}
	public String getViewByName() {
		return viewByName;
	}
	public void setViewByName(String viewByName) {
		this.viewByName = viewByName;
	}
	public String getWorkSDate() {
		return workSDate;
	}
	public void setWorkSDate(String workSDate) {
		this.workSDate = CommonUtil.convertDate(workSDate);
	}
	public String getWorkEDate() {
		return workEDate;
	}
	public void setWorkEDate(String workEDate) {
		this.workEDate = CommonUtil.convertDate(workEDate);
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getHelpdeskId() {
		return helpdeskId;
	}
	public void setHelpdeskId(String helpdeskId) {
		this.helpdeskId = helpdeskId;
	}
	
	
	
}
