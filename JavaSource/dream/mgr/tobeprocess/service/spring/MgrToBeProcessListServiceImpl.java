package dream.mgr.tobeprocess.service.spring;

import java.io.File;
import java.util.List;

import common.bean.User;
import dream.mgr.tobeprocess.dao.MgrToBeProcessListDAO;
import dream.mgr.tobeprocess.dto.MgrToBeProcessCommonDTO;
import dream.mgr.tobeprocess.service.MgrToBeProcessListService;

/**
 * ToBeProcess Page - List Service implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="mgrToBeProcessListServiceTarget"
 * @spring.txbn id="mgrToBeProcessListService"
 * @spring.property name="mgrToBeProcessListDAO" ref="mgrToBeProcessListDAO"
 */
public class MgrToBeProcessListServiceImpl implements MgrToBeProcessListService
{
	private MgrToBeProcessListDAO mgrToBeProcessListDAO = null;

	public List findToBeProcessList(MgrToBeProcessCommonDTO mgrToBeProcessCommonDTO, User user) throws Exception
    {      
		return mgrToBeProcessListDAO.findToBeProcessList(mgrToBeProcessCommonDTO,user);
    }

	public MgrToBeProcessListDAO getMgrToBeProcessListDAO() {
		return mgrToBeProcessListDAO;
	}

	public void setMgrToBeProcessListDAO(MgrToBeProcessListDAO mgrToBeProcessListDAO) {
		this.mgrToBeProcessListDAO = mgrToBeProcessListDAO;
	}
	public String findTotalCount(MgrToBeProcessCommonDTO mgrToBeProcessCommonDTO,User user) throws Exception
    {
        return mgrToBeProcessListDAO.findTotalCount(mgrToBeProcessCommonDTO, user);
    }
}

