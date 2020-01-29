package dream.work.service.service;

import common.bean.User;
import dream.work.service.dto.WorkServiceCommonDTO;
import dream.work.service.dto.WorkServiceDetailDTO;
/**
 * 서비스 마스터 - Detail Service
 * @author cjscjs9
 * @version $Id: WorkServiceDetailService.java,v 1.0 2018/08/02 09:12:40 cjscjs9 Exp $
 * @since 1.0
 */
public interface WorkServiceDetailService
{    
	/**
	 * FIND PROGRAM GUIDE DETAIL
	 * @param workServiceCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public WorkServiceDetailDTO findWorkServiceDetail(WorkServiceCommonDTO workServiceCommonDTO, User user) throws Exception;
	/**
	 * INSERT PROGRAM GUIDE
	 * @param workServiceDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertWorkServiceDetail(WorkServiceDetailDTO workServiceDetailDTO, User user) throws Exception;
    /**
     * UPDATE PROGRAM GUIDE
     * @param workServiceDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateWorkServiceDetail(WorkServiceDetailDTO workServiceDetailDTO, User user) throws Exception;
}
