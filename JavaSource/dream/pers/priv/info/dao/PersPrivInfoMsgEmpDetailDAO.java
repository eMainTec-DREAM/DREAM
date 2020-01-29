package dream.pers.priv.info.dao;

import common.bean.User;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.dto.PersPrivInfoMsgEmpDetailDTO;


/**
 * »ó¼¼ dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface PersPrivInfoMsgEmpDetailDAO
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
     */
    public PersPrivInfoMsgEmpDetailDTO findDetail(MaMyInfoCommonDTO maMyInfoCommonDTO, User user);

    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param persPrivInfoMsgEmpDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     */
    public int updateDetail(PersPrivInfoMsgEmpDetailDTO persPrivInfoMsgEmpDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO, User user);
    
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param persPrivInfoMsgEmpDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     */
    public int insertDetail(PersPrivInfoMsgEmpDetailDTO persPrivInfoMsgEmpDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO, User user);
    public String validMsgObjType(PersPrivInfoMsgEmpDetailDTO persPrivInfoMsgEmpDetailDTO, User user) throws Exception;
}