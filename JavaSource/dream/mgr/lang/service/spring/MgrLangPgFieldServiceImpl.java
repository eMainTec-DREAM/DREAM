package dream.mgr.lang.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.lang.dao.MgrLangPgFieldDAO;
import dream.mgr.lang.dto.MaResCommonDTO;
import dream.mgr.lang.service.MgrLangPgFieldService;

/**
 * 다국어사용된메뉴 serviceimpl
 * @author euna0207
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrLangPgFieldServiceTarget"
 * @spring.txbn id="mgrLangPgFieldService"
 * @spring.property name="mgrLangPgFieldDAO" ref="mgrLangPgFieldDAO"
 */
public class MgrLangPgFieldServiceImpl implements MgrLangPgFieldService
{
    private MgrLangPgFieldDAO mgrLangPgFieldDAO = null;

	public MgrLangPgFieldDAO getMgrLangPgFieldDAO() {
		return mgrLangPgFieldDAO;
	}

	public void setMgrLangPgFieldDAO(MgrLangPgFieldDAO mgrLangPgFieldDAO) {
		this.mgrLangPgFieldDAO = mgrLangPgFieldDAO;
	}

	@Override
	public List findList(MaResCommonDTO maResCommonDTO, User user) {
		return mgrLangPgFieldDAO.findList(maResCommonDTO, user);
	}

	@Override
	public String findTotalCount(MaResCommonDTO maResCommonDTO, User user) throws Exception {
		return mgrLangPgFieldDAO.findTotalCount(maResCommonDTO, user);
	}

}