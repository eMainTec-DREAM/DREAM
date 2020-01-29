package dream.doc.data.dto;

import common.bean.BaseDTO;

/**
 * 일반자료실 List DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaDocCntrCdListDTO extends BaseDTO
{
    /** 자료 Id */
    private String docCntrId                = "";

    public String getDocCntrId()
    {
        return docCntrId;
    }

    public void setDocCntrId(String docCntrId)
    {
        this.docCntrId = docCntrId;
    }
   
    
}
