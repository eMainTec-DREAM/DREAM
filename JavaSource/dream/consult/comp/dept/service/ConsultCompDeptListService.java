package dream.consult.comp.dept.service;

import java.util.List;

import common.bean.User;
import dream.consult.comp.dept.dto.ConsultCompDeptCommonDTO;

/**
 * 보전부서 - 목록 service
 * @author  hyosung
 * @version $Id: $
 * @since   1.0
 */
public interface ConsultCompDeptListService
{     
    /**
     *  grid find
     * @author  hyosung
     * @version $Id: $
     * @param consultCompDeptCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findDeptList(ConsultCompDeptCommonDTO consultCompDeptCommonDTO, User user);    
   
    /**
     * delete List
     * @author  hyosung
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows,String[] deleteRowsExt) throws Exception;
}
