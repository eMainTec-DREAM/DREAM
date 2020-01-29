package dream.consult.program.msg.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.msg.dto.ConsultPgmMsgCommonDTO;

/**
 * 메시지 설정(메일, SMS) - 목록 service
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface ConsultPgmMsgListService
{
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @param consultPgmMsgCommonDTO
     * @param user
     * @since   1.0
     *
     * @return 조회 결과
     * @throws Exception
     */
    public List findList(ConsultPgmMsgCommonDTO consultPgmMsgCommonDTO, User user);
    public String findTotalCount(ConsultPgmMsgCommonDTO consultPgmMsgCommonDTO, User user);
    /**
     * delete
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     *
     * @param deleteRows
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User user) throws Exception;
}
