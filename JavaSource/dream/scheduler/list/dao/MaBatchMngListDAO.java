package dream.scheduler.list.dao;

import java.util.List;

import common.bean.User;
import dream.scheduler.list.dto.MaBatchMngCommonDTO;

/**
 * 버튼 - 목록 dao
 * @author  kim21017
 * @version $Id: MaBatchMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaBatchMngListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaBatchMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBatchMngCommonDTO
     * @return List
     */
    public List findBatchList(MaBatchMngCommonDTO maBatchMngCommonDTO);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaBatchMngListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteBatch(String id, User user);
    
    /**
     * exec
     * @author kim21017
     * @version $Id: MaBatchMngListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public String execBatch(String id, User user);
    
    public String[] getBatchName(String id, User user);
    
    public String findTotalCount(MaBatchMngCommonDTO maBatchMngCommonDTO, User user);
}