package dream.work.list.energy.service;

import common.bean.User;
import dream.work.list.energy.dto.WorkListEnergyMstrDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;
import dream.work.list.energy.dto.WorkListEnergyPointDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyPointListDTO;

/**
 * 에너지 값 측정항목 상세
 * @author  sy.yang
 * @version $Id: WorkListEnergyPointDetailService.java,v 1.0 2015/12/04 09:08:29 sy.yang Exp $
 * @since   1.0
 */
public interface WorkListEnergyPointDetailService
{    
    /**
     * detail find
     * @author sy.yang
     * @version $Id: WorkListEnergyPointDetailService.java,v 1.0 2015/12/02 09:12:40 sy.yang Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param pmPointId
     * @param compNo
     * @return
     * @throws Exception
     */
    public WorkListEnergyPointDetailDTO findDetail(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO,WorkListEnergyPointListDTO workListEnergyPointListDTO, User user)throws Exception;
    /**
     * detail update
     * @author sy.yang
     * @version $Id: WorkListEnergyPointDetailService.java,v 1.0 2015/12/02 09:12:40 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyPointDetailDTO
     * @param workListEnergyMstrListCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkListEnergyPointDetailDTO workListEnergyPointDetailDTO, WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, User user) throws Exception;
    
    public int insertEnergyPmPoint(WorkListEnergyPointDetailDTO workListEnergyPointDetailDTO, WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user) throws Exception;
    
}
