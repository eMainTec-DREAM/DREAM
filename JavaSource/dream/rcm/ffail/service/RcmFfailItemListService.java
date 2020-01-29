package dream.rcm.ffail.service;

import java.util.List;

import common.bean.User;
import dream.rcm.ffail.dto.RcmFfailItemListDTO;
import dream.rcm.ffail.dto.RcmFfailCommonDTO;

/**
 * 답변 목록
 * @author  kim21017
 * @version $Id: RcmFfailItemListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface RcmFfailItemListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: RcmFfailItemListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailCommonDTO
     * @param rcmFfailItemListDTO
     * @throws Exception
     */
    public List findItemList(RcmFfailCommonDTO rcmFfailCommonDTO, RcmFfailItemListDTO rcmFfailItemListDTO);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: RcmFfailItemListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param strings
     * @param d_id 
     * @throws Exception
     */
    public int deleteItemList(String[] m_id, String[] d_id) throws Exception;
	public String findTotalCount(RcmFfailCommonDTO rcmFfailCommonDTO, RcmFfailItemListDTO rcmFfailItemListDTO,
			User user);

}
