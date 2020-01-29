package dream.part.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.list.dao.MaPtMstrIssStatListDAO;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.service.MaPtMstrIssStatListService;

/**
 * 부품마스터 입고이력 - 목록 serviceimpl
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtMstrIssStatListServiceTarget"
 * @spring.txbn id="maPtMstrIssStatListService"
 * @spring.property name="maPtMstrIssStatListDAO" ref="maPtMstrIssStatListDAO"
 */
public class MaPtMstrIssStatListServiceImpl implements MaPtMstrIssStatListService
{
    private MaPtMstrIssStatListDAO maPtMstrIssStatListDAO = null;
    
    public MaPtMstrIssStatListDAO getMaPtMstrIssStatListDAO() 
    {
		return maPtMstrIssStatListDAO;
	}

	public void setMaPtMstrIssStatListDAO(MaPtMstrIssStatListDAO maPtMstrIssStatListDAO) 
	{
		this.maPtMstrIssStatListDAO = maPtMstrIssStatListDAO;
	}
	
    public List findPtMstrIssStatList(MaPtMstrCommonDTO maPtMstrCommonDTO, User user)
    {      
        return maPtMstrIssStatListDAO.findPtMstrIssStatList(maPtMstrCommonDTO,user);
    }
	
    @Override
    public String findTotalCount(MaPtMstrCommonDTO maPtMstrCommonDTO, User user) throws Exception
    {
        return maPtMstrIssStatListDAO.findTotalCount(maPtMstrCommonDTO, user);
    }
}

