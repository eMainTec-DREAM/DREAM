package dream.consult.program.help.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.help.dto.MaHelpCommonDTO;

/**
 * 사원 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaHelpListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maHelpCommonDTO
     * @return List
     */
    public List findHelpList(MaHelpCommonDTO maHelpCommonDTO, User user);
    
    /**
     * 삭제
     * @author ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param empId
     * @return
     */
    public int deleteHelp(String compNo, String helpdesk_id);
    
    public String findTotalCount(MaHelpCommonDTO maHelpCommonDTO, User user) throws Exception;
}