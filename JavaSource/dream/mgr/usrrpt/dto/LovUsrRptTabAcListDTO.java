package dream.mgr.usrrpt.dto;

import common.bean.BaseDTO;
/**
 * LOV DTO
 * @author youngjoo38
 * @version $Id: LovUsrRptTabAcListDTO.java,v 1.0 2017/09/12  16:08:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class LovUsrRptTabAcListDTO extends BaseDTO
{
    /**   ����� REPORT ���̺� ID */
    private String usrRptTabId			= "";
    /**   ����� REPORT ����Ʈ ID */
    private String usrRptListId		= "";
    
	/**   ���̺� ID */
	private String tableId			= "";
	/**   ���̺�� */
	private String tableDesc		= "";
	
	
    public String getUsrRptTabId()
    {
        return usrRptTabId;
    }
    public void setUsrRptTabId(String usrRptTabId)
    {
        this.usrRptTabId = usrRptTabId;
    }
    public String getUsrRptListId()
    {
        return usrRptListId;
    }
    public void setUsrRptListId(String usrRptListId)
    {
        this.usrRptListId = usrRptListId;
    }
    public String getTableId()
    {
        return tableId;
    }
    public void setTableId(String tableId)
    {
        this.tableId = tableId;
    }
    public String getTableDesc()
    {
        return tableDesc;
    }
    public void setTableDesc(String tableDesc)
    {
        this.tableDesc = tableDesc;
    }
	
}
