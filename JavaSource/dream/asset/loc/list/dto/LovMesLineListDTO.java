package dream.asset.loc.list.dto;

import common.bean.BaseDTO;

/**
 * MESLINE°Ë»ö ÆË¾÷ DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public class LovMesLineListDTO extends BaseDTO
{
    /** MESLINE Code */
    private String mesLineNo    = "";
    /** MESLINE ¸í */
    private String mesLineDesc   = "";
    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    /** code type */
    private String codeType 	= "";
    
    
	public String getMesLineNo() {
		return mesLineNo;
	}
	public void setMesLineNo(String mesLineNo) {
		this.mesLineNo = mesLineNo;
	}
	public String getMesLineDesc() {
		return mesLineDesc;
	}
	public void setMesLineDesc(String mesLineDesc) {
		this.mesLineDesc = mesLineDesc;
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
