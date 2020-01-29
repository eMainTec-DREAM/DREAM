package dream.work.list.energy.service;

import common.bean.User;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyMstrDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;

/**
 * 에너지관리 - 에너지값 등록 상세 service
 * 
 * @author sy.yang
 * @version $Id: WorkListEnergyMstrDetailService.java,v 1.0 2015/12/02 09:12:40 sy.yang Exp $
 * @since 1.0
 */
public interface WorkListEnergyMstrDetailService 
{    
    /**
     * detail find
     * @author sy.yang
     * @version $Id: WorkListEnergyMstrDetailService.java,v 1.0 2015/12/02 09:12:40 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrListCommonDTO
     * @return
     * @throws Exception
     */
    public WorkListEnergyMstrDetailDTO findDetail(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, User user)throws Exception;
    
    /**
     * detail insert
     * @author sy.yang
     * @version $Id: WorkListEnergyMstrDetailService.java,v 1.0 2015/12/02 09:12:40 sy.yang Exp $
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
     * @version $Id: WorkListEnergyMstrDetailService.java,v 1.0 2015/12/02 09:12:40 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user) throws Exception;
    
    public int completeDetail(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user) throws Exception;
    public int completeCancelDetail(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user) throws Exception;
    
    /**
     * 점검항목 검사
     * @param workListEnergyMstrDetailDTO
     * @param user
     * @return
     */
    public String checkPoint(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO,User user) throws Exception;

    public String isLastPoint(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO,User user) throws Exception;
    
    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user) throws Exception;
    
    public int insertEnergyPmInsSched(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user) throws Exception;
    public int insertWoEnergyMstrDetail(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user) throws Exception;
    

    /**
     * 확정 전 체크
     * @author js.lee
     * @since   1.0
     *
     * @param workListEnergyMstrDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String checkConfirm(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO,User user) throws Exception;
}
