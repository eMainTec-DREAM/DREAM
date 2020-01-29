package dream.work.list.energy.dao;

import java.util.List;

import common.bean.User;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;
import dream.work.list.energy.dto.WorkListEnergyPointListDTO;

/**
 * 에너지 값 측정항목 목록 dao
 * @author  sy.yang
 * @version $Id: WorkListEnergyPointListDAO.java,v 1.0 2015/12/02 09:14:12 sy.yang Exp $
 * @since   1.0
 */
public interface WorkListEnergyPointListDAO
{
    /**
     * grid find
     * @author  sy.yang
     * @version $Id: WorkListEnergyPointListDAO.java,v 1.0 2015/12/02 09:14:12 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrListCommonDTO
     * @param workListEnergyPointListDTO
     * @param loginUser
     * @return List
     */
    public List findPointList(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyPointListDTO workListEnergyPointListDTO, User loginUser) throws Exception;
    
    public String findTotalCount(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyPointListDTO workListEnergyPointListDTO, User loginUser, boolean isComplete) throws Exception;
}