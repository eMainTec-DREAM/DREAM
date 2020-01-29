package dream.consult.comp.user.service;

import common.bean.User;
import dream.consult.comp.user.dto.ConsultCompUserCommonDTO;
import dream.consult.comp.user.dto.ConsultCompUserDetailDTO;

/**
 * CompUser Page - Detail Service
 * @author youngjoo38
 * @version $Id: ConsultCompUserDetailService.java,v 1.0 2017/08/10 09:12:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface ConsultCompUserDetailService
{
    /**
     * FIND Comp User DETAIL
     * @param consultCompUserCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public ConsultCompUserDetailDTO findCompUserDetail(ConsultCompUserCommonDTO consultCompUserCommonDTO, User user) throws Exception;
    /**
     * INSERT Comp User
     * @param consultCompUserDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertCompUserDetail(ConsultCompUserDetailDTO consultCompUserDetailDTO, User user) throws Exception;
    /**
     * UPDATE Comp User
     * @param consultCompUserDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateCompUserDetail(ConsultCompUserDetailDTO consultCompUserDetailDTO, User user) throws Exception;
    
    public String valUserNo (ConsultCompUserCommonDTO consultCompUserCommonDTO, ConsultCompUserDetailDTO consultCompUserDetailDTO) throws Exception;
}
