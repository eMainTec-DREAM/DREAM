package dream.ass.base.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �򰡱��� - Detail DTO
 * @author kim21017
 * @version $Id: AssBasePointValDetailDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class AssBasePointValDetailDTO extends BaseDTO
{
	/** �򰡱��� ID */
	private String assBaseListId			= "";
	/** ���׸� ID*/
	private String assBasePointId 			= "";
	/** �򰡱��� ID*/
	private String assBasePointValId 		= "";
	/** �򰡱��� */
	private String assEval					= "";
	/** ���� */
	private String evalValue				= "";
	/** ��ȸ����*/
	private String ordNo 					= "";
	/** ��뿩�� ID */
	private String isUseId					= "";
	/** ��뿩�� �� */
	private String isUseDesc				= "";
	/** ��� */
	private String remark					= "";
	/** ��� */
	private String script					= "";
	/** �򰡱������� from */
	private String assEvalFrom					= "";
	/** �򰡱������� to */
	private String assEvalTo					= "";
	
	
	public String getAssEvalFrom() {
		return assEvalFrom;
	}
	public void setAssEvalFrom(String assEvalFrom) {
		this.assEvalFrom = CommonUtil.convertMoney(assEvalFrom);
	}
	public String getAssEvalTo() {
		return assEvalTo;
	}
	public void setAssEvalTo(String assEvalTo) {
		this.assEvalTo = CommonUtil.convertMoney(assEvalTo);
	}
	public String getAssBaseListId() {
		return assBaseListId;
	}
	public String getScript()
    {
        return script;
    }
    public void setScript(String script)
    {
        this.script = script;
    }
    public void setAssBaseListId(String assBaseListId) {
		this.assBaseListId = assBaseListId;
	}
	public String getAssBasePointId() {
		return assBasePointId;
	}
	public void setAssBasePointId(String assBasePointId) {
		this.assBasePointId = assBasePointId;
	}
	public String getAssBasePointValId() {
		return assBasePointValId;
	}
	public void setAssBasePointValId(String assBasePointValId) {
		this.assBasePointValId = assBasePointValId;
		super.setAuditKey(assBasePointValId);
	}
	public String getAssEval() {
		return assEval;
	}
	public void setAssEval(String assEval) {
		this.assEval = assEval;
	}
	public String getEvalValue() {
		return evalValue;
	}
	public void setEvalValue(String evalValue) {
		this.evalValue = CommonUtil.convertMoney(evalValue);
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getIsUseId() {
		return isUseId;
	}
	public void setIsUseId(String isUseId) {
		this.isUseId = isUseId;
	}
	public String getIsUseDesc() {
		return isUseDesc;
	}
	public void setIsUseDesc(String isUseDesc) {
		this.isUseDesc = isUseDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
