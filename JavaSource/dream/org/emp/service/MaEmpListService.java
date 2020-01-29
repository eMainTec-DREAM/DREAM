package dream.org.emp.service;

import java.io.IOException;
import java.util.List;

import common.bean.User;
import dream.org.emp.dto.MaEmpCommonDTO;

/**
 * 사원 - 목록 service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface MaEmpListService
{     
    /**
     *  grid find
     * @author  
     * @version $Id:  $
     * @param maEmpCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws IOException 
     * @throws Exception
     */
    public List findEmpList(MaEmpCommonDTO maEmpCommonDTO, User user) throws IOException;     
   
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

    public String findTotalCount(MaEmpCommonDTO maEmpCommonDTO, User user);   

}
