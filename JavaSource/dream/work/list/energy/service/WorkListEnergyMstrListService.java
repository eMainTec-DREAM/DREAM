package dream.work.list.energy.service;

import java.util.List;

import common.bean.User;
import dream.work.list.energy.dto.WorkListEnergyMstrDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;

/**
 * 에너지관리 - 에너지값 등록 목록 service
 * @author  sy.yang
 * @version $Id: WorkListEnergyMstrListService.java,v 1.0 2015/12/02 09:12:40 sy.yang Exp $
 * @since   1.0
 */
public interface WorkListEnergyMstrListService
{     
    /**
     *  grid find
     * @author  sy.yang
     * @version $Id: WorkListEnergyMstrListService.java,v 1.0 2015/12/02 09:12:40 sy.yang Exp $
     * @param workListEnergyMstrListCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO,User user);
    
    /**
     * delete
     * @author sy.yang
     * @version $Id: WorkListEnergyMstrListService.java,v 1.0 2015/12/02 09:12:40 sy.yang Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, String compNo) throws Exception;

    public String findTotalCount(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO,User user);

    public int insertList(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User loginUser) throws Exception;
    
}
