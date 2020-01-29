package dream.rcm.ffail.dao;

import java.util.List;

import common.bean.User;
import dream.rcm.ffail.dto.RcmFfailItemListDTO;
import dream.rcm.ffail.dto.RcmFfailCommonDTO;

/**
 * 답변 목록 dao
 * @author  kim21017
 * @version $Id: RcmFfailItemListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface RcmFfailItemListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmFfailItemListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailCommonDTO
     * @param rcmFfailItemListDTO
     * @return List
     */
    public List findItemList(RcmFfailCommonDTO rcmFfailCommonDTO, RcmFfailItemListDTO rcmFfailItemListDTO);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmFfailItemListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteItemList(String deleteRow);

	public String findTotalCount(RcmFfailCommonDTO rcmFfailCommonDTO, RcmFfailItemListDTO rcmFfailItemListDTO,
			User user);
}