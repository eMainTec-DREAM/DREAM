package dream.work.pmi.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiPointDetailDTO;
import dream.work.pmi.list.dto.WorkPmiPointListDTO;

/**
 * 점검작업 점검 목록 dao
 * @author  kim21017
 * @version $Id: WorkPmiPointListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface WorkPmiPointListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: WorkPmiPointListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmiCommonDTO
     * @param workPmiPointListDTO
     * @param loginUser
     * @return List
     */
    public List findPointList(WorkPmiCommonDTO workPmiCommonDTO, WorkPmiPointListDTO workPmiPointListDTO, User loginUser, boolean isComplete) throws Exception;
    
    /**
     * delete
     * @author kim21017
     * @version $Id: WorkPmiPointListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int[] deletePointList(List<WorkPmiPointDetailDTO> list, User user);
    
    public String findTotalCount(WorkPmiCommonDTO workPmiCommonDTO, WorkPmiPointListDTO workPmiPointListDTO, User loginUser, boolean isComplete) throws Exception;
}