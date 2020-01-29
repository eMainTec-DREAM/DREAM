package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.WorkListCavalListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업상세  - 검교정 - 측정값 목록 dao
 * @author  kim21017
 * @version $Id: WorkListCavalListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface WorkListCavalListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: WorkListCavalListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param workListCavalListDTO
     * @param loginUser
     * @return List
     */
    public List findCavalList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListCavalListDTO workListCavalListDTO, User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: WorkListCavalListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteCavalList(String id, String compNo);
    
    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListCavalListDTO workListCavalListDTO, User loginUser) throws Exception;
}