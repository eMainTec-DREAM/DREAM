package dream.mgr.lang.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.lang.dao.MgrLangPgLinkedDAO;
import dream.mgr.lang.dto.MaResCommonDTO;
import dream.mgr.lang.service.MgrLangPgLinkedService;

/**
 * 다국어사용된메뉴 serviceimpl
 * @author euna0207
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrLangPgLinkedServiceTarget"
 * @spring.txbn id="mgrLangPgLinkedService"
 * @spring.property name="mgrLangPgLinkedDAO" ref="mgrLangPgLinkedDAO"
 */
public class MgrLangPgLinkedServiceImpl implements MgrLangPgLinkedService
{
    private MgrLangPgLinkedDAO mgrLangPgLinkedDAO = null;

	public MgrLangPgLinkedDAO getMgrLangPgLinkedDAO() {
		return mgrLangPgLinkedDAO;
	}

	public void setMgrLangPgLinkedDAO(MgrLangPgLinkedDAO mgrLangPgLinkedDAO) {
		this.mgrLangPgLinkedDAO = mgrLangPgLinkedDAO;
	}

	@Override
	public List findList(MaResCommonDTO maResCommonDTO, User user) {
		return mgrLangPgLinkedDAO.findList(maResCommonDTO, user);
	}

	@Override
	public String findTotalCount(MaResCommonDTO maResCommonDTO, User user) throws Exception {
		return mgrLangPgLinkedDAO.findTotalCount(maResCommonDTO, user);
	}

}