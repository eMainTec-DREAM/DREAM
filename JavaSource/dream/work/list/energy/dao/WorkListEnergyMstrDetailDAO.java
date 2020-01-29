package dream.work.list.energy.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyMstrDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;

/**
 * 에너지관리 - 에너지값 등록 상세 dao 
 * @author sy.yang
 * @version $Id: WorkListEnergyMstrDetailDAO.java,v 1.0 2015/12/02 08:25:47 sy.yang Exp $
 * @since 1.0
 */
public interface WorkListEnergyMstrDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author sy.yang
     * @version $Id: WorkListEnergyMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrListCommonDTO
     * @return
     */
    public WorkListEnergyMstrDetailDTO findDetail(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, User user);

    /**
     * detail insert
     * @author sy.yang
     * @version $Id: WorkListEnergyMstrDetailDAO.java,v 1.0 2015/12/02 09:12:40 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrListCommonDTO
     * @param workListEnergyMstrDetailDTO 
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user) throws Exception;
    
    /**
     * detail update
     * @author sy.yang
     * @version $Id: WorkListEnergyMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrDetailDTO
     * @return
     */
    public int updateDetail(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user);
    
    /**
     * detail update
     * @author sy.yang
     * @version $Id: WorkListEnergyMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrDetailDTO
     * @return
     */
    public int completeDetail(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user);
    
    public String checkPoint(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO,User user);
    
    public String isLastPoint(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO,User user);
    
    /**
     * detail update
     * @author sy.yang
     * @version $Id: WorkListEnergyMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrDetailDTO
     * @return
     */
    public int completeSched(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user);
    
    public int completePoint(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user);
    
    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user);
    
    public int insertEnergyPmInsSched(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user) throws Exception;
    public int insertWoEnergyMstrDetail(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user) throws Exception;
    

    public String checkConfirm(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO,User user);
}