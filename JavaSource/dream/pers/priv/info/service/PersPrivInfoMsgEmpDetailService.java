package dream.pers.priv.info.service;

import common.bean.User;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.dto.PersPrivInfoMsgEmpDetailDTO;

/**
 * »ó¼¼
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface PersPrivInfoMsgEmpDetailService
{    
    /**
     * detail find
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param pageId
     * @param grdColId
     * @param user 
     * @return
     * @throws Exception
     */
    public PersPrivInfoMsgEmpDetailDTO findDetail(MaMyInfoCommonDTO maMyInfoCommonDTO, User user)throws Exception;
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param persPrivInfoMsgEmpDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(PersPrivInfoMsgEmpDetailDTO persPrivInfoMsgEmpDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO, User user) throws Exception;
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param persPrivInfoMsgEmpDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(PersPrivInfoMsgEmpDetailDTO persPrivInfoMsgEmpDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO, User user) throws Exception;
    public String validMsgObjType(PersPrivInfoMsgEmpDetailDTO persPrivInfoMsgEmpDetailDTO, User user) throws Exception;
}
