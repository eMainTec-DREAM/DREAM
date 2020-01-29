package dream.consult.program.msg.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.msg.dto.ConsultPgmMsgCommonDTO;

/**
 * 메시지 설정(메일, SMS) - 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface ConsultPgmMsgListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     *
     * @param consultPgmMsgCommonDTO
     * @param user
     * @return List
     */
    public List findList(ConsultPgmMsgCommonDTO consultPgmMsgCommonDTO, User user);
    public String findTotalCount(ConsultPgmMsgCommonDTO consultPgmMsgCommonDTO, User user);
    /**
     * delete
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     *
     * @param id
     * @param user
     * @return
     */
    public int deleteList(String id, User user);
}