package mobile.dream.mapm.mains.service.spring;

import java.util.List;
import common.bean.User;
import mobile.dream.mapm.mains.dao.MaPmInsListDAO;
import mobile.dream.mapm.mains.dto.MaPmInsCommonDTO;
import mobile.dream.mapm.mains.form.MaPmInsListForm;
import mobile.dream.mapm.mains.service.MaPmInsListService;

/**
 * 예방점검 - 목록 serviceimpl
 * @author jung7126
 * @version $Id: MaPmInsListServiceImpl.java,v 1.0 2015/12/02 09:12:51 jung7126 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPmInsListServiceTarget"
 * @spring.txbn id="maPmInsListService"
 * @spring.property name="maPmInsListDAO" ref="maPmInsListDAO"
 */
public class MaPmInsListServiceImpl implements MaPmInsListService
{
    private MaPmInsListDAO maPmInsListDAO = null;

    public MaPmInsListDAO getMaPmInsListDAO() {
		return maPmInsListDAO;
	}

	public void setMaPmInsListDAO(MaPmInsListDAO maPmInsListDAO) {
		this.maPmInsListDAO = maPmInsListDAO;
	}

	public List findList(MaPmInsCommonDTO maPmInsCommonDTO, User user)
    {      
        return maPmInsListDAO.findList(maPmInsCommonDTO,user);
    }

	public String findTotalCount(MaPmInsListForm maPmInsListForm, User user) {
		// TODO Auto-generated method stub
		return maPmInsListDAO.findTotalCount(maPmInsListForm,user);
	}
}

