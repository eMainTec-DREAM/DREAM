package dream.part.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.list.dao.MaPtMstrUsedDeptListDAO;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrUsedDeptListDTO;
import dream.part.list.service.MaPtMstrUsedDeptListService;

/**
 * 何前荤侩何辑 格废
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtMstrUsedDeptListServiceTarget"
 * @spring.txbn id="maPtMstrUsedDeptListService"
 * @spring.property name="maPtMstrUsedDeptListDAO" ref="maPtMstrUsedDeptListDAO"
 */
public class MaPtMstrUsedDeptListServiceImpl implements MaPtMstrUsedDeptListService
{
    private MaPtMstrUsedDeptListDAO maPtMstrUsedDeptListDAO = null;

	public MaPtMstrUsedDeptListDAO getMaPtMstrUsedDeptListDAO() 
	{
		return maPtMstrUsedDeptListDAO;
	}

	public void setMaPtMstrUsedDeptListDAO(MaPtMstrUsedDeptListDAO maPtMstrUsedDeptListDAO) 
	{
		this.maPtMstrUsedDeptListDAO = maPtMstrUsedDeptListDAO;
	}
	
	public List findList(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser)
	{      
	    return maPtMstrUsedDeptListDAO.findList(maPtMstrCommonDTO, loginUser);
	}
	
	public int deleteList(String[] deleteRows, User loginUser) throws Exception
	{
        int result = 0;
        MaPtMstrUsedDeptListDTO maPtMstrUsedDeptListDTO = null;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                maPtMstrUsedDeptListDTO = new MaPtMstrUsedDeptListDTO();
                maPtMstrUsedDeptListDTO.setPtUsedDeptId(id);
                result = result + maPtMstrUsedDeptListDAO.deleteList(maPtMstrUsedDeptListDTO, loginUser);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(MaPtMstrCommonDTO maPtMstrCommonDTO, User user) {
		  return maPtMstrUsedDeptListDAO.findTotalCount(maPtMstrCommonDTO, user);
	}
}

