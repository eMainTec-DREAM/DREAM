package dream.consult.comp.emp.service;

import java.io.IOException;
import java.util.List;

import common.bean.User;
import dream.consult.comp.emp.dto.ConsultCompEmpCommonDTO;

/**
 * 사원 - 목록 service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface ConsultCompEmpListService
{     
    /**
     *  grid find
     * @author  
     * @version $Id:  $
     * @param consultCompEmpCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws IOException 
     * @throws Exception
     */
    public List findEmpList(ConsultCompEmpCommonDTO consultCompEmpCommonDTO, User user) throws IOException;     
   
    /**
     * delete List
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteList(String compNo, String[] deleteRows) throws Exception;

    public String findTotalCount(ConsultCompEmpCommonDTO consultCompEmpCommonDTO, User user);   

}
