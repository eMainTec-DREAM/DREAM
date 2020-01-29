package dream.consult.comp.user.dao;

import java.util.List;

import common.bean.User;
import dream.consult.comp.user.dto.ConsultCompUsrGrpCommonDTO;
import dream.consult.comp.user.dto.ConsultCompUsrGrpDetailDTO;


/**
 * 권한그룹 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 */
public interface ConsultCompUsrGrpDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0 
     * 
     * @param userGroup
     * @return
     */
    public ConsultCompUsrGrpDetailDTO findDetail(String compNo, String usrGrpId);
    
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompUsrGrpDetailDTO
     * @return
     */
    public int insertDetail(ConsultCompUsrGrpDetailDTO consultCompUsrGrpDetailDTO);

    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompUsrGrpDetailDTO
     * @return
     */
    public int updateDetail(ConsultCompUsrGrpDetailDTO consultCompUsrGrpDetailDTO);

    /**
     * valid userGroup
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param consultCompUsrGrpDetailDTO
     * @return
     */
    public String validUsrGrpNo(ConsultCompUsrGrpDetailDTO consultCompUsrGrpDetailDTO);
    
    public List findMenuList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

    public void deleteUserGrpMenuLsit(String compNo, String usrGrpId);

    public void insertUserGrpMenu(
            ConsultCompUsrGrpDetailDTO consultCompUsrGrpDetailDTO);

    public List findMenuListForTree(String string, String usrGrpId,
            String compNo, User user);
}