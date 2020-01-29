package dream.work.service.service.spring;

import common.bean.User;
import dream.work.service.dao.WorkServiceDetailDAO;
import dream.work.service.dto.WorkServiceCommonDTO;
import dream.work.service.dto.WorkServiceDetailDTO;
import dream.work.service.service.WorkServiceDetailService;

/**
 * 서비스 마스터 - Detail Service implements
 * @author cjscjs9
 * @version $Id: WorkServiceDetailServiceImpl.java,v 1.0 2018/07/27 09:12:40 cjscjs9 Exp $
 * @since 1.0
 * @spring.bean id="workServiceDetailServiceTarget"
 * @spring.txbn id="workServiceDetailService"
 * @spring.property name="workServiceDetailDAO" ref="workServiceDetailDAO"
 */
public class WorkServiceDetailServiceImpl implements WorkServiceDetailService
{
    private WorkServiceDetailDAO workServiceDetailDAO = null;
    
    public WorkServiceDetailDTO findWorkServiceDetail(WorkServiceCommonDTO workServiceCommonDTO, User user) throws Exception
    {
    	return workServiceDetailDAO.findWorkServiceDetail(workServiceCommonDTO, user);
    }
    
    public int insertWorkServiceDetail(WorkServiceDetailDTO workServiceDetailDTO, User user) throws Exception
    {
    	return workServiceDetailDAO.insertWorkServiceDetail(workServiceDetailDTO, user);
    }
    
    public int updateWorkServiceDetail(WorkServiceDetailDTO workServiceDetailDTO, User user) throws Exception
    {
    	 return workServiceDetailDAO.updateWorkServiceDetail(workServiceDetailDTO, user);
    }

	public WorkServiceDetailDAO getWorkServiceDetailDAO() {
		return workServiceDetailDAO;
	}

	public void setWorkServiceDetailDAO(WorkServiceDetailDAO workServiceDetailDAO) {
		this.workServiceDetailDAO = workServiceDetailDAO;
	}
}
