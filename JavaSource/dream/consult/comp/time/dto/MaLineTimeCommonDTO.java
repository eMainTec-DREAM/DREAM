package dream.consult.comp.time.dto;

import common.bean.BaseDTO;

/**
 * �����ð����� ����
 * @author  kim21017
 * @version $Id: MaLineTimeCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaLineTimeCommonDTO extends BaseDTO
{
	/** �����޷�ID */
	private String lnWrkListId 					= "";
	/** ȸ�� */
	private String compNo 		     	= "";
	/** ���� ȸ��� */
	private String filterCompDesc 			= "";
	/** ���� �����޷¸� */
	private String filterLnWrkListDesc         = "";
	
    public String getLnWrkListId()
    {
        return lnWrkListId;
    }
    public void setLnWrkListId(String lnWrkListId)
    {
        this.lnWrkListId = lnWrkListId;
    }
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
    public String getFilterCompDesc()
    {
        return filterCompDesc;
    }
    public void setFilterCompDesc(String filterCompDesc)
    {
        this.filterCompDesc = filterCompDesc;
    }
    public String getFilterLnWrkListDesc()
    {
        return filterLnWrkListDesc;
    }
    public void setFilterLnWrkListDesc(String filterLnWrkListDesc)
    {
        this.filterLnWrkListDesc = filterLnWrkListDesc;
    }
	
}
