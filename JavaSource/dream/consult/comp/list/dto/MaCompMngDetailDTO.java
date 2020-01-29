package dream.consult.comp.list.dto;

import java.util.List;

import common.bean.BaseDTO;

/**
 * ȸ�缳�� - �� DTO
 * @author  kim21017
 * @version $Id: MaCompMngDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaCompMngDetailDTO extends BaseDTO
{
	
	/** ȸ�缳��ID */
	private String compNo 				= "";
	private String compId 				= "";
	/** ������ */
	private String compDesc 			= "";
	/** ��Ų(���̾ƿ�)������ */
	private String ctPath 				= "";
	private String ctPathDesc			= "";
	/** default ȸ�翩�� */
	private String initCtPathYn			= "";
	/**��뿩��*/
	private String isUse 				= "";
	/** Login Title Logo */ 
	private List loginTitleLogo			= null;
	/** Login Sub Title Logo */ 
	private List loginSubTitleLog		= null;
	/** Main Title Logo */ 
	private List mainTitleLogo			= null;
	/** Logo �������� ������ */
	private String subImgType			= "";
	
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public String getSubImgType() {
		return subImgType;
	}
	public void setSubImgType(String subImgType) {
		this.subImgType = subImgType;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getCompDesc() {
		return compDesc;
	}
	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
	}
	public String getCtPath() {
		return ctPath;
	}
	public void setCtPath(String ctPath) {
		this.ctPath = ctPath;
	}
	public String getCtPathDesc() {
		return ctPathDesc;
	}
	public void setCtPathDesc(String ctPathDesc) {
		this.ctPathDesc = ctPathDesc;
	}
	public String getInitCtPathYn() {
		return initCtPathYn;
	}
	public void setInitCtPathYn(String initCtPathYn) {
		this.initCtPathYn = initCtPathYn;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public List getLoginTitleLogo() {
		return loginTitleLogo;
	}
	public void setLoginTitleLogo(List loginTitleLogo) {
		this.loginTitleLogo = loginTitleLogo;
	}
	public List getLoginSubTitleLog() {
		return loginSubTitleLog;
	}
	public void setLoginSubTitleLog(List loginSubTitleLog) {
		this.loginSubTitleLog = loginSubTitleLog;
	}
	public List getMainTitleLogo() {
		return mainTitleLogo;
	}
	public void setMainTitleLogo(List mainTitleImageList) {
		this.mainTitleLogo = mainTitleImageList;
	}
}
