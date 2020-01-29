package dream.mgr.lang.service;

import java.util.List;

import common.bean.User;
import dream.mgr.lang.dto.MaResCommonDTO;

/**
 * ��� - ��� service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface MaResListService
{     
    /**
     *  grid find
     * @author  
     * @version $Id:  $
     * @param maResCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findResList(MaResCommonDTO maResCommonDTO, User user);

    public String findTotalCount(MaResCommonDTO maResCommonDTO, User user);     

}
