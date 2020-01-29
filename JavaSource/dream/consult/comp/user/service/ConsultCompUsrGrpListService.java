package dream.consult.comp.user.service;

import java.util.List;

import common.bean.User;
import dream.consult.comp.user.dto.ConsultCompUsrGrpCommonDTO;

/**
 * ���ѱ׷� - ��� service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface ConsultCompUsrGrpListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id:  $
     * @param maUsrGrpCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findUseGrpList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, User user);    
    
    /**
     * delete List
     * @author ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteList(String compNo, String[] deleteRows) throws Exception;
    
    public String findTotalCount(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, User user);

}
