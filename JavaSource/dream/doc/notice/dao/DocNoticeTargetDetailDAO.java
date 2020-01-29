package dream.doc.notice.dao;

import common.bean.User;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeTargetDetailDTO;
import dream.doc.notice.dto.DocNoticeTargetListDTO;

/**
 * DocNoticeTarget - Detail DAO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public interface DocNoticeTargetDetailDAO
{
    /**
     * FIND DETAIL
     * @param docNoticeCommonDTO
     * @param docNoticeTargetListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public DocNoticeTargetDetailDTO findDetail(DocNoticeCommonDTO docNoticeCommonDTO,DocNoticeTargetListDTO docNoticeTargetListDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param docNoticeCommonDTO
     * @param docNoticeTargetDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(DocNoticeCommonDTO docNoticeCommonDTO,DocNoticeTargetDetailDTO docNoticeTargetDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param docNoticeCommonDTO
     * @param docNoticeTargetDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(DocNoticeCommonDTO docNoticeCommonDTO,DocNoticeTargetDetailDTO docNoticeTargetDetailDTO, User user) throws Exception;
    
}