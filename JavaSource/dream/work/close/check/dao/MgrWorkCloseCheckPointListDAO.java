package dream.work.close.check.dao;

import java.util.List;

import common.bean.User;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckPointListDTO;

/**
 * ǥ���׸� ����Ʈ - ��� dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MgrWorkCloseCheckPointListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrWorkCloseCheckCommonDTO
     * @param mgrWorkCloseCheckPointListDTO
     * @param loginUser
     * @return List
     */
    public List findStdPointList(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, MgrWorkCloseCheckPointListDTO mgrWorkCloseCheckPointListDTO, User loginUser);
    
    /**
     * ǥ���׸� ����
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param id
     * @param loginUser
     * @return
     */
    public int deleteList(String id, User loginUser);
    
    public String findTotalCount(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, MgrWorkCloseCheckPointListDTO mgrWorkCloseCheckPointListDTO, User loginUser);
}