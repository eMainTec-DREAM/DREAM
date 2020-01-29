package dream.consult.program.page.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * 화면 - 목록 dao
 * @author  kim21017
 * @version $Id: MaPgMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaPgMngListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPgMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngCommonDTO
     * @return List
     */
    public List findPgList(MaPgMngCommonDTO maPgMngCommonDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaPgMngListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngDTOList
     * @return
     */
    public int deletePage(String id);
    
    /**
     * FIND TOTAL LIST
     * @param maPgMngCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(MaPgMngCommonDTO maPgMngCommonDTO, User user) throws Exception;

}