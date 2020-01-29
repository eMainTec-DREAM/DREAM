package dream.rcm.taskmap.dao;

import java.util.List;

import common.bean.User;
import dream.rcm.taskmap.dto.RcmTaskMapItemListDTO;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;

/**
 * 답변 목록 dao
 * @author  kim21017
 * @version $Id: RcmTaskMapItemListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface RcmTaskMapItemListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmTaskMapItemListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapCommonDTO
     * @param rcmTaskMapItemListDTO
     * @return List
     */
    public List findItemList(RcmTaskMapCommonDTO rcmTaskMapCommonDTO, RcmTaskMapItemListDTO rcmTaskMapItemListDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmTaskMapItemListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteItemList(String deleteRow);

	public String findTotalCount(RcmTaskMapCommonDTO rcmTaskMapCommonDTO, RcmTaskMapItemListDTO rcmTaskMapItemListDTO, User user);
}