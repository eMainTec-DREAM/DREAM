package dream.mgr.lang.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.lang.dao.MgrLangPgBtnDAO;
import dream.mgr.lang.dto.MaResCommonDTO;
import dream.mgr.lang.service.MgrLangPgBtnService;

/**
 * 다국어사용된메뉴 serviceimpl
 * @author euna0207
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrLangPgBtnServiceTarget"
 * @spring.txbn id="mgrLangPgBtnService"
 * @spring.property name="mgrLangPgBtnDAO" ref="mgrLangPgBtnDAO"
 */
public class MgrLangPgBtnServiceImpl implements MgrLangPgBtnService
{
    private MgrLangPgBtnDAO mgrLangPgBtnDAO = null;

	public MgrLangPgBtnDAO getMgrLangPgBtnDAO() {
		return mgrLangPgBtnDAO;
	}

	public void setMgrLangPgBtnDAO(MgrLangPgBtnDAO mgrLangPgBtnDAO) {
		this.mgrLangPgBtnDAO = mgrLangPgBtnDAO;
	}

	@Override
	public List findList(MaResCommonDTO maResCommonDTO, User user) {
		return mgrLangPgBtnDAO.findList(maResCommonDTO, user);
	}

	@Override
	public String findTotalCount(MaResCommonDTO maResCommonDTO, User user) throws Exception {
		return mgrLangPgBtnDAO.findTotalCount(maResCommonDTO, user);
	}

}