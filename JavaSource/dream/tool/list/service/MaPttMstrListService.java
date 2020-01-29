package dream.tool.list.service;

import java.util.List;

import common.bean.User;
import dream.tool.list.dto.MaPttMstrCommonDTO;

/**
 * ��������з�(������) - ��� service
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaPttMstrListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPttMstrCommonDTO
     * @param loginUser
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(MaPttMstrCommonDTO maPttMstrCommonDTO, User loginUser);    
   
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
    
    public String findTotalCount(MaPttMstrCommonDTO maPttMstrCommonDTO, User user);
}
