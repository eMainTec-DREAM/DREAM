package dream.work.let.permit.service;

import java.util.List;

import common.bean.User;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointListDTO;

/**
 * 안전작업허가서 작업유형  - 점검항목 목록 Service
 * @author syyang
 * @version $Id: WorkLetPermitPointListService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
 * @since 1.0
 */
public interface WorkLetPermitPointListService {
	/**
	 * FIND PROGRAM GUIDE LIST
	 * @param workLetCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointListDTO workLetPermitPointListDTO, User user) throws Exception;
	/**
	 * DELETE PROGRAM GUIDE LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deleteList(String[] deleteRows, User user) throws Exception;
	/**
	 * FIND PROGRAM GUIDE LIST COUNT
	 * @param workLetCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointListDTO workLetPermitPointListDTO, User user) throws Exception;
    
    public int insertStdPointList(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointDetailDTO workLetPermitPointDetailDTO, User user) throws Exception;
}
