package dream.work.list.dao;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.WorkListSclCavalListDTO;

/**
 * 작업상세  - 검교정(저울) - 측정값 목록 dao
 * @author  kim21017
 * @version $Id: WorkListSclCavalListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface WorkListSclCavalListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: WorkListSclCavalListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param workListSclCavalListDTO
     * @param loginUser
     * @return List
     */
    public WorkListSclCavalListDTO findCavalList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User loginUser);
    
    /**
     * merge
     * @author kim21017
     * @version $Id: WorkListSclCavalListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int mergeCavalList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListSclCavalListDTO workListSclCavalListDTO, User loginUser);
}