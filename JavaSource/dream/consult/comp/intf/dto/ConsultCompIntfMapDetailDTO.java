package dream.consult.comp.intf.dto;

import common.bean.BaseDTO;

/**
 * Interface MAP - Detail DTO
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 *
 */
public class ConsultCompIntfMapDetailDTO extends BaseDTO
{
    /**인터페이스 MAP ID*/
    private String intfMapId        = "";
	/**인터페이스 ID*/
	private String intfId 		    = "";
	/**회사코드*/
	private String compNo 		    = "";
	
	/**Parameter 순서 */
	private String paramSeq		    = "";
	/**Param Type ID */
	private String paramType	    = "";
	/**Param Type DESC */
	private String paramTypeDesc	= "";
	
	/**Source 테이블명 */
	private String stabName	    	= "";
	/**Source 컬럼명 */
	private String sfieldName	    = "";
	/**Source PK 여부 ID */
	private String sisPk		    = "";
	/**Source PK 여부 DESC */
	private String sisPkDesc	    = "";
	/**Source 컬럼타입 */
	private String sfieldTypeDesc	= "";
	/**Source 컬럼 Size */
	private String sfieldSizeDesc   = "";
	/**Source Not Null 여부 ID */
	private String sisNotNull	    = "";
	/**Source Not Null 여부 DESC */
	private String sisNotNullDesc   = "";
	/**Source Default Value */
	private String sdefaultVal	    = "";
	/**Source 컬럼 설명 */
	private String sremark		    = "";
	
	/**Target 테이블명 */
	private String ttabName	    	= "";
	/**Target 컬럼명 */
	private String tfieldName	    = "";
	/**Target PK 여부 ID */
	private String tisPk		    = "";
	/**Target PK 여부 DESC */
	private String tisPkDesc	    = "";
	/**Target 컬럼타입 */
	private String tfieldTypeDesc	= "";
	/**Target 컬럼 Size */
	private String tfieldSizeDesc   = "";
	/**Target Not Null 여부 ID */
	private String tisNotNull	    = "";
	/**Target Not Null 여부 DESC */
	private String tisNotNullDesc   = "";
	/**Target Default Value */
	private String tdefaultVal	    = "";
	/**Target 컬럼 설명 */
	private String tremark		    = "";
	
	public String getIntfMapId() {
		return intfMapId;
	}
	public String getSdefaultVal() {
		return sdefaultVal;
	}
	public void setSdefaultVal(String sdefaultVal) {
		this.sdefaultVal = sdefaultVal;
	}
	public String getTdefaultVal() {
		return tdefaultVal;
	}
	public void setTdefaultVal(String tdefaultVal) {
		this.tdefaultVal = tdefaultVal;
	}
	public void setIntfMapId(String intfMapId) {
		this.intfMapId = intfMapId;
	}
	public String getIntfId() {
		return intfId;
	}
	public void setIntfId(String intfId) {
		this.intfId = intfId;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getParamSeq() {
		return paramSeq;
	}
	public void setParamSeq(String paramSeq) {
		this.paramSeq = paramSeq;
	}
	public String getParamType() {
		return paramType;
	}
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	public String getParamTypeDesc() {
		return paramTypeDesc;
	}
	public void setParamTypeDesc(String paramTypeDesc) {
		this.paramTypeDesc = paramTypeDesc;
	}
	public String getStabName() {
		return stabName;
	}
	public void setStabName(String stabName) {
		this.stabName = stabName;
	}
	public String getSfieldName() {
		return sfieldName;
	}
	public void setSfieldName(String sfieldName) {
		this.sfieldName = sfieldName;
	}
	public String getSisPk() {
		return sisPk;
	}
	public void setSisPk(String sisPk) {
		this.sisPk = sisPk;
	}
	public String getSisPkDesc() {
		return sisPkDesc;
	}
	public void setSisPkDesc(String sisPkDesc) {
		this.sisPkDesc = sisPkDesc;
	}
	public String getSfieldTypeDesc() {
		return sfieldTypeDesc;
	}
	public void setSfieldTypeDesc(String sfieldTypeDesc) {
		this.sfieldTypeDesc = sfieldTypeDesc;
	}
	public String getSfieldSizeDesc() {
		return sfieldSizeDesc;
	}
	public void setSfieldSizeDesc(String sfieldSizeDesc) {
		this.sfieldSizeDesc = sfieldSizeDesc;
	}
	public String getSisNotNull() {
		return sisNotNull;
	}
	public void setSisNotNull(String sisNotNull) {
		this.sisNotNull = sisNotNull;
	}
	public String getSisNotNullDesc() {
		return sisNotNullDesc;
	}
	public void setSisNotNullDesc(String sisNotNullDesc) {
		this.sisNotNullDesc = sisNotNullDesc;
	}
	public String getSremark() {
		return sremark;
	}
	public void setSremark(String sremark) {
		this.sremark = sremark;
	}
	public String getTtabName() {
		return ttabName;
	}
	public void setTtabName(String ttabName) {
		this.ttabName = ttabName;
	}
	public String getTfieldName() {
		return tfieldName;
	}
	public void setTfieldName(String tfieldName) {
		this.tfieldName = tfieldName;
	}
	public String getTisPk() {
		return tisPk;
	}
	public void setTisPk(String tisPk) {
		this.tisPk = tisPk;
	}
	public String getTisPkDesc() {
		return tisPkDesc;
	}
	public void setTisPkDesc(String tisPkDesc) {
		this.tisPkDesc = tisPkDesc;
	}
	public String getTfieldTypeDesc() {
		return tfieldTypeDesc;
	}
	public void setTfieldTypeDesc(String tfieldTypeDesc) {
		this.tfieldTypeDesc = tfieldTypeDesc;
	}
	public String getTfieldSizeDesc() {
		return tfieldSizeDesc;
	}
	public void setTfieldSizeDesc(String tfieldSizeDesc) {
		this.tfieldSizeDesc = tfieldSizeDesc;
	}
	public String getTisNotNull() {
		return tisNotNull;
	}
	public void setTisNotNull(String tisNotNull) {
		this.tisNotNull = tisNotNull;
	}
	public String getTisNotNullDesc() {
		return tisNotNullDesc;
	}
	public void setTisNotNullDesc(String tisNotNullDesc) {
		this.tisNotNullDesc = tisNotNullDesc;
	}
	public String getTremark() {
		return tremark;
	}
	public void setTremark(String tremark) {
		this.tremark = tremark;
	}
}
