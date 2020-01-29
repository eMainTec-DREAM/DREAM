package dream.fail.code.service.spring;

import java.util.List;

import common.bean.User;
import dream.fail.code.dao.MaFailureListDAO;
import dream.fail.code.dto.MaFailureCommonDTO;
import dream.fail.code.service.MaFailureListService;

/**
 * 고장코드 - 목록 serviceimpl
 * @author ssong
 * @version
 * @since 1.0
 * 
 * @spring.bean id="maFailureListServiceTarget"
 * @spring.txbn id="maFailureListService"
 * @spring.property name="maFailureListDAO" ref="maFailureListDAO"
 */
public class MaFailureListServiceImpl implements MaFailureListService
{
    private MaFailureListDAO maFailureListDAO = null;

    public MaFailureListDAO getMaFailureListDAO() 
    {
		return maFailureListDAO;
	}

	public void setMaFailureListDAO(MaFailureListDAO maFailureListDAO) 
	{
		this.maFailureListDAO = maFailureListDAO;
	}

	public List findList(MaFailureCommonDTO maFailureCommonDTO, User user)
    {      
        return maFailureListDAO.findList(maFailureCommonDTO,user);
    }

	public int deleteList(String compNo, String[] deleteRows) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                maFailureListDAO.deleteLangData(compNo, id);
                result = result + maFailureListDAO.deleteParts(compNo, id);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(MaFailureCommonDTO maFailureCommonDTO, User user)
	{
		return maFailureListDAO.findTotalCount(maFailureCommonDTO, user);
	}
}

