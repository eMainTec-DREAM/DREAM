package dream.work.let.categ.service;

import common.bean.User;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.dto.WorkLetCategEtcDetailDTO;
import dream.work.let.categ.dto.WorkLetCategEtcListDTO;

/**
 * 안전작업유형 - 보완사항 detail Page - Detail Service
 * @author euna0207
 * @version $Id: WorkLetCategEtcDetailService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 */
public interface WorkLetCategEtcDetailService
{    
	/**
	 * FIND PROGRAM GUIDE DETAIL
	 * @param workLetCategEtcListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public WorkLetCategEtcDetailDTO findDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategEtcListDTO workLetCategEtcListDTO, User user) throws Exception;
	/**
	 * INSERT PROGRAM GUIDE
	 * @param consultPgmGuideDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategEtcDetailDTO workLetCategEtcDetailDTO, User user) throws Exception;
    /**
     * UPDATE PROGRAM GUIDE
     * @param consultPgmGuideDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategEtcDetailDTO workLetCategEtcDetailDTO, User user) throws Exception;
}
