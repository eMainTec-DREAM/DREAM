package dream.part.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.list.dao.MaPtMstrEqPartListDAO;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrEqPartDetailDTO;
import dream.part.list.service.MaPtMstrEqPartDetailService;
import dream.part.list.service.MaPtMstrEqPartListService;

/**
 * 사용설비 목록
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtMstrEqPartListServiceTarget"
 * @spring.txbn id="maPtMstrEqPartListService"
 * @spring.property name="maPtMstrEqPartListDAO" ref="maPtMstrEqPartListDAO"
 * @spring.property name="maPtMstrEqPartDetailService" ref="maPtMstrEqPartDetailService"
 */
public class MaPtMstrEqPartListServiceImpl implements MaPtMstrEqPartListService
{
    private MaPtMstrEqPartListDAO maPtMstrEqPartListDAO = null;
    private MaPtMstrEqPartDetailService maPtMstrEqPartDetailService = null;

	public MaPtMstrEqPartDetailService getMaPtMstrEqPartDetailService()
    {
        return maPtMstrEqPartDetailService;
    }

    public void setMaPtMstrEqPartDetailService(
            MaPtMstrEqPartDetailService maPtMstrEqPartDetailService)
    {
        this.maPtMstrEqPartDetailService = maPtMstrEqPartDetailService;
    }

    public MaPtMstrEqPartListDAO getMaPtMstrEqPartListDAO() 
	{
		return maPtMstrEqPartListDAO;
	}

	public void setMaPtMstrEqPartListDAO(MaPtMstrEqPartListDAO maPtMstrEqPartListDAO) 
	{
		this.maPtMstrEqPartListDAO = maPtMstrEqPartListDAO;
	}
	
	public List findList(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser)
	{      
	    return maPtMstrEqPartListDAO.findList(maPtMstrCommonDTO, loginUser);
	}
	
	public int deleteList(String[] deleteRows, User loginUser) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maPtMstrEqPartListDAO.deleteList(id, loginUser);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(MaPtMstrCommonDTO maPtMstrCommonDTO, User user) throws Exception {
		return maPtMstrEqPartListDAO.findTotalCount(maPtMstrCommonDTO, user);
	}

    @Override
    public int insertList(MaPtMstrEqPartDetailDTO maPtMstrEqPartDetailDTO, User user) throws Exception
    {
        int result = 0;
        
        String[] multiKey = maPtMstrEqPartDetailDTO.getMultiKey().split("\\+");
        
        for(int i=0; multiKey.length > i; i++)
        {
            maPtMstrEqPartDetailDTO.setEqPartId(maPtMstrEqPartListDAO.getNextSequence("SQAEQPART_ID"));
            maPtMstrEqPartDetailDTO.setEquipId(multiKey[i]);
            result = result + maPtMstrEqPartDetailService.insertDetail(maPtMstrEqPartDetailDTO, user);
        }
        
        return result;
    }
}

