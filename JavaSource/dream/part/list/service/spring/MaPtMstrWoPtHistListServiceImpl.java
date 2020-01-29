package dream.part.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.list.dao.MaPtMstrWoPtHistListDAO;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.service.MaPtMstrWoPtHistListService;

/**
 * 부품마스터 사용이력 - 목록 serviceimpl
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtMstrWoPtHistListServiceTarget"
 * @spring.txbn id="maPtMstrWoPtHistListService"
 * @spring.property name="maPtMstrWoPtHistListDAO" ref="maPtMstrWoPtHistListDAO"
 */
public class MaPtMstrWoPtHistListServiceImpl implements MaPtMstrWoPtHistListService
{
    private MaPtMstrWoPtHistListDAO maPtMstrWoPtHistListDAO = null;
    
    public MaPtMstrWoPtHistListDAO getMaPtMstrWoPtHistListDAO() 
    {
		return maPtMstrWoPtHistListDAO;
	}

	public void setMaPtMstrWoPtHistListDAO(MaPtMstrWoPtHistListDAO maPtMstrWoPtHistListDAO) 
	{
		this.maPtMstrWoPtHistListDAO = maPtMstrWoPtHistListDAO;
	}
	
    public List findPtMstrWoPtHistList(MaPtMstrCommonDTO maPtMstrCommonDTO, User user)
    {      
        return maPtMstrWoPtHistListDAO.findPtMstrWoPtHistList(maPtMstrCommonDTO,user);
    }
	
    @Override
    public String findTotalCount(MaPtMstrCommonDTO maPtMstrCommonDTO, User user) throws Exception
    {
        return maPtMstrWoPtHistListDAO.findTotalCount(maPtMstrCommonDTO, user);
    }
}

