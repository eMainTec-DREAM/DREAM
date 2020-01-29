package dream.consult.program.msg.dao;

import common.bean.User;
import dream.consult.program.msg.dto.ConsultPgmMsgCommonDTO;
import dream.consult.program.msg.dto.ConsultPgmMsgDetailDTO;

/**
 * 메시지 설정(메일, SMS) - 상세 dao
 *
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface ConsultPgmMsgDetailDAO
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
     */
    public ConsultPgmMsgDetailDTO findDetail(ConsultPgmMsgCommonDTO consultPgmMsgCommonDTO, User user);

    /**
     * detail insert
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     *
     * @param consultPgmMsgDetailDTO
     * @param user
     * @return
     */
    public int insertDetail(ConsultPgmMsgDetailDTO consultPgmMsgDetailDTO, User user);

    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     *
     * @param consultPgmMsgDetailDTO
     * @param user
     * @return
     */
    public int updateDetail(ConsultPgmMsgDetailDTO consultPgmMsgDetailDTO, User user);
    public String validMsgObjType(ConsultPgmMsgDetailDTO consultPgmMsgDetailDTO, User user);
    public int updateLang(ConsultPgmMsgDetailDTO consultPgmMsgDetailDTO, User user)throws Exception ;
}