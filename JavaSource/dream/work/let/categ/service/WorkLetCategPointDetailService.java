package dream.work.let.categ.service;

import common.bean.User;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.dto.WorkLetCategPointDetailDTO;
import dream.work.let.categ.dto.WorkLetCategPointListDTO;

/**
 * 안전작업유형 점검항목 detail Page - Detail Service
 * @author euna0207
 * @version $Id: WorkLetCategPointDetailService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 */
public interface WorkLetCategPointDetailService
{    
	/**
	 * FIND PROGRAM GUIDE DETAIL
	 * @param workLetCategCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public WorkLetCategPointDetailDTO findDetail(WorkLetCategCommonDTO workLetCategCommonDTO , WorkLetCategPointListDTO WorkLetCategPointListDTO, User user) throws Exception;
	/**
	 * INSERT PROGRAM GUIDE
	 * @param workLetCategPointDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategPointDetailDTO workLetCategPointDetailDTO, User user) throws Exception;
    /**
     * UPDATE PROGRAM GUIDE
     * @param workLetCategPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategPointDetailDTO workLetCategPointDetailDTO, User user) throws Exception;
}
