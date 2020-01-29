package dream.doc.data.dao;

import java.util.List;

import common.bean.User;
import dream.doc.data.dto.MaDocCntrCdCommonDTO;
import dream.doc.data.dto.MaDocCntrCdListDTO;

/**
 * �Ϲ��ڷ�� - ��� dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaDocCntrCdListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDocCntrCdCommonDTO
     * @return List
     */
    public List findList(MaDocCntrCdCommonDTO maDocCntrCdCommonDTO, User loginUser);
    

    /**
     * ����
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDocCntrCdListDTO
     * @param loginUser
     * @return
     */
    public int deleteDocCntr(MaDocCntrCdListDTO maDocCntrCdListDTO, User loginUser);
    
    /**
     * ���� ÷������ ����
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDocCntrCdListDTO
     * @param loginUser
     * @return
     */
    public int deleteObjDoc(MaDocCntrCdListDTO maDocCntrCdListDTO, User loginUser);

    public String findTotalCount(MaDocCntrCdCommonDTO maDocCntrCdCommonDTO, User user);
    
}