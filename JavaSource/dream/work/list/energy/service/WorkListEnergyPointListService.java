package dream.work.list.energy.service;

import java.util.List;

import common.bean.User;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;
import dream.work.list.energy.dto.WorkListEnergyPointListDTO;

/**
 * ������ �� �����׸� ��� Service
 * @author  sy.yang
 * @version $Id: WorkListEnergyPointListService.java,v 1.0 2015/12/02 09:12:40 sy.yang Exp $
 * @since   1.0
 */
public interface WorkListEnergyPointListService
{     
    /**
     *  grid find
     * @author  sy.yang
     * @version $Id: WorkListEnergyPointListService.java,v 1.0 2015/12/02 09:12:40 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrListCommonDTO
     * @param workListEnergyPointListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findPointList(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyPointListDTO workListEnergyPointListDTO, User loginUser) throws Exception;

	public String findTotalCount(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyPointListDTO workListEnergyPointListDTO, User loginUser)throws Exception;
}
