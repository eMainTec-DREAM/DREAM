package dream.asset.categ.list.dto;

import common.bean.BaseDTO;

/**
 * ���������˻� �˾� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public class LovEqCtgListDTO extends BaseDTO
{
    /** ���� */
    private String lvl				= "";
    /** ������ */
    private String fullDesc			= "";
    /** extCode1 */
    private String extCode1 		= "";
    /** extCode2 */
    private String extCode2 		= "";
    /** code type */
    private String codeType 		= "";
    
	public String getLvl() {
		return lvl;
	}
	public void setLvl(String lvl) {
		this.lvl = lvl;
	}
	public String getFullDesc() {
		return fullDesc;
	}
	public void setFullDesc(String fullDesc) {
		this.fullDesc = fullDesc;
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
