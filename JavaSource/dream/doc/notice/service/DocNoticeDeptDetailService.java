package dream.doc.notice.service;

import common.bean.User;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeDeptDetailDTO;
import dream.doc.notice.dto.DocNoticeDeptListDTO;
/**
 * DocNoticeDept - Detail Service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface DocNoticeDeptDetailService
{    
	/**
	 * FIND DETAIL
	 * @param docNoticeCommonDTO
	 * @param docNoticeDeptListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public DocNoticeDeptDetailDTO findDetail(DocNoticeCommonDTO docNoticeCommonDTO,DocNoticeDeptListDTO docNoticeDeptListDTO, User user) throws Exception;
	/**
	 * INSERT DETAIL
     * @param docNoticeCommonDTO
	 * @param docNoticeDeptDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertDetail(DocNoticeCommonDTO docNoticeCommonDTO,DocNoticeDeptDetailDTO docNoticeDeptDetailDTO, User user) throws Exception;
    /**
     * UPDATE DETAIL
     * @param docNoticeCommonDTO
     * @param docNoticeDeptDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(DocNoticeCommonDTO docNoticeCommonDTO,DocNoticeDeptDetailDTO docNoticeDeptDetailDTO, User user) throws Exception;
    
    public String checkDetail(DocNoticeDeptDetailDTO docNoticeDeptDetailDTO, User user) throws Exception;

}
