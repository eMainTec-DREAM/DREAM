package dream.work.let.categ.service.spring;

import common.bean.User;
import dream.work.let.categ.dao.WorkLetCategEtcDetailDAO;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.dto.WorkLetCategEtcDetailDTO;
import dream.work.let.categ.dto.WorkLetCategEtcListDTO;
import dream.work.let.categ.service.WorkLetCategEtcDetailService;

/**
 * 안전작업유형 - 보완사항 detail Page - Detail Service implements
 * 
 * @author euna0207
 * @version $Id: WorkLetCategEtcDetailServiceImpl.java,v 1.0 2015/12/02 09:12:40
 *          euna0207 Exp $
 * @since 1.0
 * @spring.bean id="workLetCategEtcDetailServiceTarget"
 * @spring.txbn id="workLetCategEtcDetailService"
 * @spring.property name="workLetCategEtcDetailDAO"
 *                  ref="workLetCategEtcDetailDAO"
 */
public class WorkLetCategEtcDetailServiceImpl implements WorkLetCategEtcDetailService {
	private WorkLetCategEtcDetailDAO workLetCategEtcDetailDAO = null;

	public WorkLetCategEtcDetailDTO findDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategEtcListDTO workLetCategEtcListDTO, User user)
			throws Exception {
		return workLetCategEtcDetailDAO.findDetail(workLetCategCommonDTO, workLetCategEtcListDTO, user);
	}

	public int insertDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategEtcDetailDTO workLetCategEtcDetailDTO, User user) throws Exception {
		return workLetCategEtcDetailDAO.insertDetail(workLetCategCommonDTO, workLetCategEtcDetailDTO, user);
	}

	public int updateDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategEtcDetailDTO workLetCategEtcDetailDTO, User user) throws Exception {
		return workLetCategEtcDetailDAO.updateDetail(workLetCategCommonDTO, workLetCategEtcDetailDTO, user);
	}

	public WorkLetCategEtcDetailDAO getWorkLetCategEtcDetailDAO() {
		return workLetCategEtcDetailDAO;
	}

	public void setWorkLetCategEtcDetailDAO(WorkLetCategEtcDetailDAO workLetCategEtcDetailDAO) {
		this.workLetCategEtcDetailDAO = workLetCategEtcDetailDAO;
	}

	
}
