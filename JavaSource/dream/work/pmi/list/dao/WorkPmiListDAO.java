package dream.work.pmi.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiDetailDTO;

/**
 * 점검작업 - 목록 dao
 * @author  kim21017
 * @version $Id: WorkPmiListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface WorkPmiListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: WorkPmiListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmiCommonDTO
     * @return List
     */
    public List findList(WorkPmiCommonDTO workPmiCommonDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: WorkPmiListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    //public int deletePoint(String id, String compNo);
    
    public String findTotalCount(WorkPmiCommonDTO workPmiCommonDTO,User user);

    public int[] updateDeleteTag(List<WorkPmiDetailDTO> list, User user);

}