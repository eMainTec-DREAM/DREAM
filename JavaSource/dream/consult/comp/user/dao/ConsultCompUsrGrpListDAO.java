package dream.consult.comp.user.dao;

import java.util.List;

import common.bean.User;
import dream.consult.comp.user.dto.ConsultCompUsrGrpCommonDTO;

/**
 * ���ѱ׷� - ��� dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface ConsultCompUsrGrpListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUsrGrpCommonDTO
     * @return List
     */
    public List findUsrGrpList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, User user);
    
    /**
     * ����
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUsrGrpGridDTO
     * @return
     */
    public int deleteUsrGrp(String compNo, String usrGrpId);
    
    /**
     * �޴� ���� ���� 
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUsrGrpGridDTO
     * @return
     */
    public int deleteUsrGrpMenu(String compNo, String usrGrpId);

    public String findTotalCount(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, User user);

}