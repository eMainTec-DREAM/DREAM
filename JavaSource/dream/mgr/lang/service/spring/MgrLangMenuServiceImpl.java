package dream.mgr.lang.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.lang.dao.MgrLangMenuDAO;
import dream.mgr.lang.dto.MaResCommonDTO;
import dream.mgr.lang.service.MgrLangMenuService;

/**
 * 다국어사용된메뉴 serviceimpl
 * @author euna0207
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrLangMenuServiceTarget"
 * @spring.txbn id="mgrLangMenuService"
 * @spring.property name="mgrLangMenuDAO" ref="mgrLangMenuDAO"
 */
public class MgrLangMenuServiceImpl implements MgrLangMenuService
{
    private MgrLangMenuDAO mgrLangMenuDAO = null;

	public MgrLangMenuDAO getMgrLangMenuDAO() {
		return mgrLangMenuDAO;
	}

	public void setMgrLangMenuDAO(MgrLangMenuDAO mgrLangMenuDAO) {
		this.mgrLangMenuDAO = mgrLangMenuDAO;
	}

	@Override
	public List findList(MaResCommonDTO maResCommonDTO, User user) {
		return mgrLangMenuDAO.findList(maResCommonDTO, user);
	}

	@Override
	public String findTotalCount(MaResCommonDTO maResCommonDTO, User user) throws Exception {
		return mgrLangMenuDAO.findTotalCount(maResCommonDTO, user);
	}

}