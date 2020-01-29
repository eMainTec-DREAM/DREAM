package dream.mgr.msgrec.dao;

import common.bean.User;
import dream.mgr.msgrec.dto.MgrMsgRecCommonDTO;
import dream.mgr.msgrec.dto.MgrMsgRecDetailDTO;

/**
 * MgrMsgRec Page - Detail DAO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public interface MgrMsgRecDetailDAO
{
    /**
     * FIND DETAIL
     * @param mgrMsgRecCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public MgrMsgRecDetailDTO findDetail(MgrMsgRecCommonDTO mgrMsgRecCommonDTO, User user) throws Exception;
    
    public int insertDetail(MgrMsgRecDetailDTO mgrMsgRecDetailDTO, User user) throws Exception;
    public int updateDetail(MgrMsgRecDetailDTO mgrMsgRecDetailDTO, User user) throws Exception;
    public int updateLang(MgrMsgRecDetailDTO mgrMsgRecDetailDTO, User user) throws Exception;
    public String validMsgObjType(MgrMsgRecDetailDTO mgrMsgRecDetailDTO, User user) throws Exception;
}
