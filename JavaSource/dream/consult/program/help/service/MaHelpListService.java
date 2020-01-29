package dream.consult.program.help.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.help.dto.MaHelpCommonDTO;

/**
 * ��� service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface MaHelpListService
{     
    /**
     *  grid find
     * @author  
     * @version $Id:  $
     * @param maHelpCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findHelpList(MaHelpCommonDTO maHelpCommonDTO, User user);     
   
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

    public String findTotalCount(MaHelpCommonDTO maHelpCommonDTO, User user) throws Exception;
}
