package dream.work.rpt.pmiequipcmptrate.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmiequipcmptrate.dto.WorkRptPmiEquipCmptDetailListDTO;

/**
 * 예방점검 실행 상세 목록 DAO
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 *
 */
public interface WorkRptPmiEquipCmptDetailListDAO
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
     * FIND TOTAL LIST
     * @param workRptPmiEquipCmptDetailListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(WorkRptPmiEquipCmptDetailListDTO workRptPmiEquipCmptDetailListDTO, User user) throws Exception;
    
}
