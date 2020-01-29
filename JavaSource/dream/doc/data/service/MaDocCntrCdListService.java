package dream.doc.data.service;

import java.util.List;

import common.bean.User;
import dream.doc.data.dto.MaDocCntrCdCommonDTO;

/**
 * �Ϲ��ڷ�� - ��� service
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaDocCntrCdListService
{     
    /**
     * grid find
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDocCntrCdCommonDTO
     * @param loginUser
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(MaDocCntrCdCommonDTO maDocCntrCdCommonDTO, User loginUser);    
   
    /**
     * delete List
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param deleteRows
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User loginUser) throws Exception;

    public String findTotalCount(MaDocCntrCdCommonDTO maDocCntrCdCommonDTO, User user);   

}
