package common.mafinder.mamstr.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 설비기안품의서 팝업 DTO
 * @author  kim21017
 * @version $Id: LovEqAppListDTO.java,v 1.1 2016/01/18 09:12:12 kim21017 Exp $
 * @since   1.0
 */
public class LovEqAppListDTO extends BaseDTO
{
    /** 품의번호 */
    private String eqAppId 		= "";
    /** 제목 */
    private String title 		= "";
    /** 기안일자 */
    private String reqDate 		= "";
    /** 결재일자 */
    private String appDate 		= "";
    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    /** code type */
    private String codeType 	= "";
    
	public String getEqAppId() {
		return eqAppId;
	}
	public void setEqAppId(String eqAppId) {
		this.eqAppId = eqAppId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = CommonUtil.convertDate(reqDate);
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = CommonUtil.convertDate(appDate);
	}
	public String getExtCode1() {
		return extCode1;
	}
	public void setExtCode1(String extCode1) {
		this.extCode1 = extCode1;
	}
	public String getExtCode2() {
		return extCode2;
	}
	public void setExtCode2(String extCode2) {
		this.extCode2 = extCode2;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
    
}
