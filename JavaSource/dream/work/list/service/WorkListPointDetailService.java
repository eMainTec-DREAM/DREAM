package dream.work.list.service;

import common.bean.User;
import dream.work.list.dto.WorkListPointDetailDTO;
import dream.work.list.dto.WorkListPointListDTO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;
/**
 * 점검내용 - Detail Service
 * @author youngjoo38
 * @version $Id: WorkListPointDetailService.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface WorkListPointDetailService
{    
	/**
	 * FIND DETAIL
	 * @param workListPtrlResultCommonDTO
	 * @param workListPointListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public WorkListPointDetailDTO findDetail(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO,WorkListPointListDTO workListPointListDTO, User user) throws Exception;
	/**
	 * INSERT DETAIL
     * @param workListPtrlResultCommonDTO
	 * @param workListPointDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertDetail(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO,WorkListPointDetailDTO workListPointDetailDTO, User user) throws Exception;
    /**
     * UPDATE DETAIL
     * @param workListPtrlResultCommonDTO
     * @param workListPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO,WorkListPointDetailDTO workListPointDetailDTO, User user) throws Exception;
    
}
