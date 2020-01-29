package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업상세  - 검교정 - 표준기 목록 dao
 * @author  kim21017
 * @version $Id: WorkListGnlCaEqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface WorkListGnlCaEqListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: WorkListGnlCaEqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param workListGnlCaEqListDTO
     * @param loginUser
     * @return List
     */
    public List findCavalList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: WorkListGnlCaEqListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteCavalList(String id, String compNo);
    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser)throws Exception;
}