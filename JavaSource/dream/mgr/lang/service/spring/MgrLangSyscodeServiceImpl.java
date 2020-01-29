package dream.mgr.lang.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.lang.dao.MgrLangSyscodeDAO;
import dream.mgr.lang.dto.MaResCommonDTO;
import dream.mgr.lang.service.MgrLangSyscodeService;

/**
 * 다국어사용된메뉴 serviceimpl
 * @author euna0207
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrLangSyscodeServiceTarget"
 * @spring.txbn id="mgrLangSyscodeService"
 * @spring.property name="mgrLangSyscodeDAO" ref="mgrLangSyscodeDAO"
 */
public class MgrLangSyscodeServiceImpl implements MgrLangSyscodeService
{
    private MgrLangSyscodeDAO mgrLangSyscodeDAO = null;

	public MgrLangSyscodeDAO getMgrLangSyscodeDAO() {
		return mgrLangSyscodeDAO;
	}

	public void setMgrLangSyscodeDAO(MgrLangSyscodeDAO mgrLangSyscodeDAO) {
		this.mgrLangSyscodeDAO = mgrLangSyscodeDAO;
	}

	@Override
	public List findList(MaResCommonDTO maResCommonDTO, User user) {
		return mgrLangSyscodeDAO.findList(maResCommonDTO, user);
	}

	@Override
	public String findTotalCount(MaResCommonDTO maResCommonDTO, User user) throws Exception {
		return mgrLangSyscodeDAO.findTotalCount(maResCommonDTO, user);
	}

}