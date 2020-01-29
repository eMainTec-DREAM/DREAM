package dream.work.list.energy.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;

/**
 * 에너지관리 - 에너지값 등록 목록 dao
 * @author  sy.yang
 * @version $Id: WorkListEnergyMstrListDAO.java,v 1.0 2015/12/02 09:14:12 sy.yang Exp $
 * @since   1.0
 */
public interface WorkListEnergyMstrListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  sy.yang
     * @version $Id: WorkListEnergyMstrListDAO.java,v 1.0 2015/12/02 09:14:12 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrListCommonDTO
     * @return List
     */
    public List findList(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, User user);
    
    /**
     * delete
     * @author sy.yang
     * @version $Id: WorkListEnergyMstrListDAO.java,v 1.0 20155/12/02 08:25:47 sy.yang Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteSchedList(String id, String compNo);
    public int deleteList(String id, String compNo);
    public int deletePoint(String id, String compNo);

    public String findTotalCount(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO,User user);
    
}