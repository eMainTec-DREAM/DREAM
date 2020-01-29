package dream.pers.priv.pgm.dto;

import common.bean.BaseDTO;

/**
 * ȭ�麰 �ʵ� �� dto
 * @author  kim21017
 * @version $Id: MaPgUsrFieldDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class PersPrivUsrFieldDetailDTO extends BaseDTO
{
	/** PGFIELD_ID */
	private String pgFieldId			= "";
	/** USER PGFIELD_ID */
    private String usrPgFieldId         = "";
	/** ������ ���̵� */
	private String pageId				= "";
	/** �ʵ� ���̵� */
	private String fieldId				= "";
	/** �� keyType */
	private String keyType				= "";
	/** �� keyNo */
	private String keyNo				= "";
	/** Label �� */
	private String keyName				= "";
	/** �ý��� ���迩�� */
	private String hiddenYn				= "";
	/** ��ȸ���� */
	private String ordNo				= "";
	/** ȭ�� Display ���� */
	private String displayYn			= "";
	/** �ʵ弳�� */
	private String fieldDesc			= "";
	/** Check YN */
	private String checkYn              = "";
	
	private String fieldOption 			= "";
	
	private String fieldOptionDesc		= "";
	
	private String groupOption			= "";
	
	private String groupOptionDesc		= "";
	
	private String groupKeyType			= "";
	/** �׷� Key No */
	private String groupKeyNo			= "";
	
	private String groupKeyName			= "";
	
	public String getGroupOption() {
		return groupOption;
	}
	public void setGroupOption(String groupOption) {
		this.groupOption = groupOption;
	}
	public String getGroupOptionDesc() {
		return groupOptionDesc;
	}
	public void setGroupOptionDesc(String groupOptionDesc) {
		this.groupOptionDesc = groupOptionDesc;
	}
	public String getGroupKeyType() {
		return groupKeyType;
	}
	public void setGroupKeyType(String groupKeyType) {
		this.groupKeyType = groupKeyType;
	}
	public String getGroupKeyNo() {
		return groupKeyNo;
	}
	public void setGroupKeyNo(String groupKeyNo) {
		this.groupKeyNo = groupKeyNo;
	}
	public String getGroupKeyName() {
		return groupKeyName;
	}
	public void setGroupKeyName(String groupKeyName) {
		this.groupKeyName = groupKeyName;
	}
	public String getFieldOption() {
		return fieldOption;
	}
	public void setFieldOption(String fieldOption) {
		this.fieldOption = fieldOption;
	}
	public String getFieldOptionDesc() {
		return fieldOptionDesc;
	}
	public void setFieldOptionDesc(String fieldOptionDesc) {
		this.fieldOptionDesc = fieldOptionDesc;
	}
	public String getCheckYn()
    {
        return checkYn;
    }
    public void setCheckYn(String checkYn)
    {
        this.checkYn = checkYn;
    }
    public String getUsrPgFieldId()
    {
        return usrPgFieldId;
    }
    public void setUsrPgFieldId(String usrPgFieldId)
    {
        this.usrPgFieldId = usrPgFieldId;
    }
    public String getPgFieldId() {
		return pgFieldId;
	}
	public void setPgFieldId(String pgFieldId) {
		this.pgFieldId = pgFieldId;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
	public String getKeyNo() {
		return keyNo;
	}
	public void setKeyNo(String keyNo) {
		this.keyNo = keyNo;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getHiddenYn() {
		return hiddenYn;
	}
	public void setHiddenYn(String hiddenYn) {
		this.hiddenYn = hiddenYn;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getDisplayYn() {
		return displayYn;
	}
	public void setDisplayYn(String displayYn) {
		this.displayYn = displayYn;
	}
	public String getFieldDesc() {
		return fieldDesc;
	}
	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}
	
}