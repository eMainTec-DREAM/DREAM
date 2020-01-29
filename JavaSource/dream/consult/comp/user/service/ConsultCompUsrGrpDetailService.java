package dream.consult.comp.user.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.consult.comp.user.dto.ConsultCompUsrGrpCommonDTO;
import dream.consult.comp.user.dto.ConsultCompUsrGrpDetailDTO;


/**
 * 권한그룹 - 상세 service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface ConsultCompUsrGrpDetailService
{    
    /**
     * detail find
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param usrGrpId
     * @return
     * @throws Exception
     */
    public ConsultCompUsrGrpDetailDTO findDetail(String compNo, String usrGrpId)throws Exception;
    
    /**
     * detail insert
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultCompUsrGrpDetailDTO
     * @param menuIds
     * @return
     * @throws Exception
     */
    public int insertDetail(ConsultCompUsrGrpDetailDTO consultCompUsrGrpDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultCompUsrGrpDetailDTO
     * @param menuIds
     * @return
     * @throws Exception
     */
    public int updateDetail(ConsultCompUsrGrpDetailDTO consultCompUsrGrpDetailDTO) throws Exception;
    
    /**
     * valid userGroup
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultCompUsrGrpDetailDTO
     * @return
     * @throws Exception
     */
    public String validUserGroup(ConsultCompUsrGrpDetailDTO consultCompUsrGrpDetailDTO) throws Exception;

    /**
     *  menu find
     * @author  
     * @version $Id:  $
     * @param consultCompUsrGrpCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findMenuList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

    public Map findMenuListForTree(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, User user);  
}
