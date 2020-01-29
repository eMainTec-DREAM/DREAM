package dream.work.list.energy.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.list.energy.dto.WorkListEnergyMstrDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;
import dream.work.list.energy.dto.WorkListEnergyPointDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyPointListDTO;

/**
 * 에너지 값 측정항목 상세 dao
 * @author  sy.yang
 * @version $Id: WorkListEnergyPointDetailDAO.java,v 1.0 2015/12/04 08:10:27 sy.yang Exp $
 * @since   1.0
 */
public interface WorkListEnergyPointDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author sy.yang
     * @version $Id: WorkListEnergyPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 sy.yang Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woPointId
     * @param compNo
     * @return
     */
    public WorkListEnergyPointDetailDTO findDetail(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyPointListDTO workListEnergyPointListDTO, User user);

    /**
     * detail update
     * @author sy.yang
     * @version $Id: WorkListEnergyPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyPointDetailDTO
     * @param workListEnergyMstrListCommonDTO
     * @return
     */
    public int updateDetail(WorkListEnergyPointDetailDTO workListEnergyPointDetailDTO, WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, User user);
    
    public int insertEnergyPmPoint(WorkListEnergyPointDetailDTO workListEnergyPointDetailDTO, WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user);
    
}