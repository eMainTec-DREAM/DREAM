/**
 * 
 */
package dream.work.service.service;

import java.util.List;

import common.bean.User;
import dream.work.service.dto.WorkServiceCommonDTO;
/**
 * 서비스 마스터 - List Service
 * @author cjscjs9
 * @version $Id: WorkServiceListService.java,v 1.0 2018/08/02 09:12:40 cjscjs9 Exp $
 * @since 1.0
 */
public interface WorkServiceListService { 
	/**
	 * FIND PROGRAM GUIDE LIST
	 * @param workServiceCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findWorkServiceList(WorkServiceCommonDTO workServiceCommonDTO, User user) throws Exception;
	/**
	 * DELETE PROGRAM GUIDE LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deleteWorkServiceList(String[] deleteRows, User user) throws Exception;
	/**
	 * FIND PROGRAM GUIDE LIST COUNT
	 * @param workServiceCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(WorkServiceCommonDTO workServiceCommonDTO, User user) throws Exception;
}
