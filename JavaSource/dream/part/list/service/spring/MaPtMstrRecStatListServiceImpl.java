package dream.part.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.list.dao.MaPtMstrRecStatListDAO;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.service.MaPtMstrRecStatListService;

/**
 * 부품마스터 입고이력 - 목록 serviceimpl
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtMstrRecStatListServiceTarget"
 * @spring.txbn id="maPtMstrRecStatListService"
 * @spring.property name="maPtMstrRecStatListDAO" ref="maPtMstrRecStatListDAO"
 */
public class MaPtMstrRecStatListServiceImpl implements MaPtMstrRecStatListService
{
    private MaPtMstrRecStatListDAO maPtMstrRecStatListDAO = null;
    
    public MaPtMstrRecStatListDAO getMaPtMstrRecStatListDAO() 
    {
		return maPtMstrRecStatListDAO;
	}

	public void setMaPtMstrRecStatListDAO(MaPtMstrRecStatListDAO maPtMstrRecStatListDAO) 
	{
		this.maPtMstrRecStatListDAO = maPtMstrRecStatListDAO;
	}
	
    public List findPtMstrRecStatList(MaPtMstrCommonDTO maPtMstrCommonDTO, User user)
    {      
        return maPtMstrRecStatListDAO.findPtMstrRecStatList(maPtMstrCommonDTO,user);
    }
	
    @Override
    public String findTotalCount(MaPtMstrCommonDTO maPtMstrCommonDTO, User user) throws Exception
    {
        return maPtMstrRecStatListDAO.findTotalCount(maPtMstrCommonDTO, user);
    }
}

