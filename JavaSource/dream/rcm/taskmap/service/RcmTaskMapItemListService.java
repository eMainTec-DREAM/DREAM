package dream.rcm.taskmap.service;

import java.util.List;

import common.bean.User;
import dream.rcm.taskmap.dto.RcmTaskMapItemListDTO;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;

/**
 * 답변 목록
 * @author  kim21017
 * @version $Id: RcmTaskMapItemListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface RcmTaskMapItemListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: RcmTaskMapItemListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapCommonDTO
     * @param rcmTaskMapItemListDTO
     * @throws Exception
     */
    public List findItemList(RcmTaskMapCommonDTO rcmTaskMapCommonDTO, RcmTaskMapItemListDTO rcmTaskMapItemListDTO, User user);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: RcmTaskMapItemListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param strings
     * @param d_id 
     * @throws Exception
     */
    public int deleteItemList(String[] m_id, String[] d_id) throws Exception;
	public String findTotalCount(RcmTaskMapCommonDTO rcmTaskMapCommonDTO, RcmTaskMapItemListDTO rcmTaskMapItemListDTO, User user);

}
