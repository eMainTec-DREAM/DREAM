package dream.mgr.lang.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.lang.dao.MgrLangPgpageDAO;
import dream.mgr.lang.dto.MaResCommonDTO;
import dream.mgr.lang.service.MgrLangPgpageService;

/**
 * 다국어사용된메뉴 serviceimpl
 * @author euna0207
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrLangPgpageServiceTarget"
 * @spring.txbn id="mgrLangPgpageService"
 * @spring.property name="mgrLangPgpageDAO" ref="mgrLangPgpageDAO"
 */
public class MgrLangPgpageServiceImpl implements MgrLangPgpageService
{
    private MgrLangPgpageDAO mgrLangPgpageDAO = null;

	public MgrLangPgpageDAO getMgrLangPgpageDAO() {
		return mgrLangPgpageDAO;
	}

	public void setMgrLangPgpageDAO(MgrLangPgpageDAO mgrLangPgpageDAO) {
		this.mgrLangPgpageDAO = mgrLangPgpageDAO;
	}

	@Override
	public List findList(MaResCommonDTO maResCommonDTO, User user) {
		return mgrLangPgpageDAO.findList(maResCommonDTO, user);
	}

	@Override
	public String findTotalCount(MaResCommonDTO maResCommonDTO, User user) throws Exception {
		return mgrLangPgpageDAO.findTotalCount(maResCommonDTO, user);
	}

}