package dream.work.rpt.pmiequipcmptrate.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmiequipcmptrate.dto.WorkRptPmiEquipCmptDetailListDTO;

/**
 * 예방점검 실행 상세 목록  Service
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 */
public interface WorkRptPmiEquipCmptDetailListService
{
    /**
     * FIND LIST
     * @param workRptPmiEquipCmptDetailListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(WorkRptPmiEquipCmptDetailListDTO workRptPmiEquipCmptDetailListDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmiEquipCmptDetailListDTO
     * @return
     */
    public String findTotalCount(WorkRptPmiEquipCmptDetailListDTO workRptPmiEquipCmptDetailListDTO, User user) throws Exception;
}
