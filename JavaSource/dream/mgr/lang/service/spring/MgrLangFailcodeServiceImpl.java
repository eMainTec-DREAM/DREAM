package dream.mgr.lang.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.lang.dao.MgrLangFailcodeDAO;
import dream.mgr.lang.dto.MaResCommonDTO;
import dream.mgr.lang.service.MgrLangFailcodeService;

/**
 * 다국어사용된메뉴 serviceimpl
 * @author euna0207
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrLangFailcodeServiceTarget"
 * @spring.txbn id="mgrLangFailcodeService"
 * @spring.property name="mgrLangFailcodeDAO" ref="mgrLangFailcodeDAO"
 */
public class MgrLangFailcodeServiceImpl implements MgrLangFailcodeService
{
    private MgrLangFailcodeDAO mgrLangFailcodeDAO = null;

	public MgrLangFailcodeDAO getMgrLangFailcodeDAO() {
		return mgrLangFailcodeDAO;
	}

	public void setMgrLangFailcodeDAO(MgrLangFailcodeDAO mgrLangFailcodeDAO) {
		this.mgrLangFailcodeDAO = mgrLangFailcodeDAO;
	}

	@Override
	public List findList(MaResCommonDTO maResCommonDTO, User user) {
		return mgrLangFailcodeDAO.findList(maResCommonDTO, user);
	}

	@Override
	public String findTotalCount(MaResCommonDTO maResCommonDTO, User user) throws Exception {
		return mgrLangFailcodeDAO.findTotalCount(maResCommonDTO, user);
	}

}