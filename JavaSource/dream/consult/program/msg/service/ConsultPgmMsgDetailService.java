package dream.consult.program.msg.service;

import common.bean.User;
import dream.consult.program.msg.dto.ConsultPgmMsgCommonDTO;
import dream.consult.program.msg.dto.ConsultPgmMsgDetailDTO;

/**
 * 메시지 설정(메일, SMS) - 상세 service
 *
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface ConsultPgmMsgDetailService
{
    /**
     * detail find
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     *
     * @param consultPgmMsgCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public ConsultPgmMsgDetailDTO findDetail(ConsultPgmMsgCommonDTO consultPgmMsgCommonDTO, User user)throws Exception;
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     *
     * @param consultPgmMsgDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(ConsultPgmMsgDetailDTO consultPgmMsgDetailDTO, User user) throws Exception;
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     *
     * @param consultPgmMsgDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(ConsultPgmMsgDetailDTO consultPgmMsgDetailDTO, User user) throws Exception;
    public String validMsgObjType(ConsultPgmMsgDetailDTO consultPgmMsgDetailDTO, User user) throws Exception;
}
