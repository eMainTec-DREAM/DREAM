package dream.mgr.lang.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.lang.dao.MgrLangPgGridColDAO;
import dream.mgr.lang.dto.MaResCommonDTO;
import dream.mgr.lang.service.MgrLangPgGridColService;

/**
 * 다국어사용된메뉴 serviceimpl
 * @author euna0207
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrLangPgGridColServiceTarget"
 * @spring.txbn id="mgrLangPgGridColService"
 * @spring.property name="mgrLangPgGridColDAO" ref="mgrLangPgGridColDAO"
 */
public class MgrLangPgGridColServiceImpl implements MgrLangPgGridColService
{
    private MgrLangPgGridColDAO mgrLangPgGridColDAO = null;

	public MgrLangPgGridColDAO getMgrLangPgGridColDAO() {
		return mgrLangPgGridColDAO;
	}

	public void setMgrLangPgGridColDAO(MgrLangPgGridColDAO mgrLangPgGridColDAO) {
		this.mgrLangPgGridColDAO = mgrLangPgGridColDAO;
	}

	@Override
	public List findList(MaResCommonDTO maResCommonDTO, User user) {
		return mgrLangPgGridColDAO.findList(maResCommonDTO, user);
	}

	@Override
	public String findTotalCount(MaResCommonDTO maResCommonDTO, User user) throws Exception {
		return mgrLangPgGridColDAO.findTotalCount(maResCommonDTO, user);
	}

}