package dream.part.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.list.dao.MaPtMstrVendorListDAO;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.service.MaPtMstrVendorListService;

/**
 * 何前芭贰贸 格废
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtMstrVendorListServiceTarget"
 * @spring.txbn id="maPtMstrVendorListService"
 * @spring.property name="maPtMstrVendorListDAO" ref="maPtMstrVendorListDAO"
 */
public class MaPtMstrVendorListServiceImpl implements MaPtMstrVendorListService
{
    private MaPtMstrVendorListDAO maPtMstrVendorListDAO = null;

	public MaPtMstrVendorListDAO getMaPtMstrVendorListDAO() 
	{
		return maPtMstrVendorListDAO;
	}

	public void setMaPtMstrVendorListDAO(MaPtMstrVendorListDAO maPtMstrVendorListDAO) 
	{
		this.maPtMstrVendorListDAO = maPtMstrVendorListDAO;
	}
	
	public List findList(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser)
	{      
	    return maPtMstrVendorListDAO.findList(maPtMstrCommonDTO, loginUser);
	}
	
	public int deleteList(String[] deleteRows, User loginUser) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maPtMstrVendorListDAO.deleteList(id, loginUser);
            }
        
        return result;
    }
	public String findTotalCount(MaPtMstrCommonDTO maPtMstrCommonDTO, User user) throws Exception
    {
        return maPtMstrVendorListDAO.findTotalCount(maPtMstrCommonDTO, user);
    }

}

