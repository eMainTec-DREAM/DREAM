package dream.work.pm.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dao.MaPmMstrSelectDAO;
import dream.work.pm.list.dto.MaPmMstrSelectDTO;
import dream.work.pm.list.service.MaPmMstrSelectService;

/**
 * 작업형태 팝업 ServiceImpl
 * @author  kim2107
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="maPmMstrSelectServiceTarget"
 * @spring.txbn id="maPmMstrSelectService"
 * @spring.property name="maPmMstrSelectDAO" ref="maPmMstrSelectDAO"
 */
public class MaPmMstrSelectServiceImpl implements MaPmMstrSelectService
{
    private MaPmMstrSelectDAO maPmMstrSelectDAO = null;
    public MaPmMstrSelectDAO getMaPmMstrSelectDAO() 
    {
		return maPmMstrSelectDAO;
	}

	public void setMaPmMstrSelectDAO(MaPmMstrSelectDAO maPmMstrSelectDAO) 
	{
		this.maPmMstrSelectDAO = maPmMstrSelectDAO;
	}

	public List findWoTypeList(User loginUser,MaPmMstrSelectDTO maPmMstrSelectDTO)
    {
        List resultList = null;
        resultList = maPmMstrSelectDAO.findWoTypeList(loginUser, maPmMstrSelectDTO);
        
        return resultList;
    }
	
	public List findPmTypeList(User loginUser, MaPmMstrSelectDTO maPmMstrSelectDTO)
	{
		List resultList = null;
		resultList = maPmMstrSelectDAO.findPmTypeList(loginUser, maPmMstrSelectDTO);
		
		return resultList;
	}
}