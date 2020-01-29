package dream.mgr.lang.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.lang.dao.MgrLangPageDAO;
import dream.mgr.lang.dto.MaResCommonDTO;
import dream.mgr.lang.service.MgrLangPageService;

/**
 * 다국어사용된메뉴 serviceimpl
 * @author euna0207
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrLangPageServiceTarget"
 * @spring.txbn id="mgrLangPageService"
 * @spring.property name="mgrLangPageDAO" ref="mgrLangPageDAO"
 */
public class MgrLangPageServiceImpl implements MgrLangPageService
{
    private MgrLangPageDAO mgrLangPageDAO = null;

	public MgrLangPageDAO getMgrLangPageDAO() {
		return mgrLangPageDAO;
	}

	public void setMgrLangPageDAO(MgrLangPageDAO mgrLangPageDAO) {
		this.mgrLangPageDAO = mgrLangPageDAO;
	}

	@Override
	public List findList(MaResCommonDTO maResCommonDTO, User user) {
		return mgrLangPageDAO.findList(maResCommonDTO, user);
	}

	@Override
	public String findTotalCount(MaResCommonDTO maResCommonDTO, User user) throws Exception {
		return mgrLangPageDAO.findTotalCount(maResCommonDTO, user);
	}

}