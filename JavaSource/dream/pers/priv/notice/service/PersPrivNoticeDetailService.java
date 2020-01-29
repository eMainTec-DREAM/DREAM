package dream.pers.priv.notice.service;

import common.bean.User;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.notice.dto.PersPrivNoticeDetailDTO;

/**
 * Notice 설정 - 상세 Service
 * @author  nhkim8548
 * @version $Id$
 * @since   1.0
 */
public interface PersPrivNoticeDetailService
{    
    /**
     * detail find
     * @author  nhkim8548
     * @version $Id$
     * @since   1.0
     * 
     * @param maMyInfoCommonDTO
     * @param user 
     * @return
     * @throws Exception
     */
    public PersPrivNoticeDetailDTO findDetail(MaMyInfoCommonDTO maMyInfoCommonDTO, User user)throws Exception;
    
    /**
     * detail insert
     * @author  nhkim8548
     * @version $Id$
     * @since   1.0
     * 
     * @param persPrivNoticeDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(PersPrivNoticeDetailDTO persPrivNoticeDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO, User user) throws Exception;
    
    /**
     * detail update
     * @author  nhkim8548
     * @version $Id$
     * @since   1.0
     * 
     * @param persPrivNoticeDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(PersPrivNoticeDetailDTO persPrivNoticeDetailDTO, User user) throws Exception;
}
