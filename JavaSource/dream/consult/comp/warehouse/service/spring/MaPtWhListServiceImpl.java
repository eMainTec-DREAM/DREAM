package dream.consult.comp.warehouse.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.comp.warehouse.dao.MaPtWhListDAO;
import dream.consult.comp.warehouse.dto.MaPtWhCommonDTO;
import dream.consult.comp.warehouse.service.MaPtWhListService;

/**
 * 부품창고 - 목록 serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtWhListServiceTarget"
 * @spring.txbn id="maPtWhListService"
 * @spring.property name="maPtWhListDAO" ref="maPtWhListDAO"
 */
public class MaPtWhListServiceImpl implements MaPtWhListService
{
    private MaPtWhListDAO maPtWhListDAO = null;

    public MaPtWhListDAO getMaPtWhListDAO() 
    {
		return maPtWhListDAO;
	}

	public void setMaPtWhListDAO(MaPtWhListDAO maPtWhListDAO) 
	{
		this.maPtWhListDAO = maPtWhListDAO;
	}

    public List findPtWhList(MaPtWhCommonDTO maPtWhCommonDTO, User user)
    {      
        return maPtWhListDAO.findPtWhList(maPtWhCommonDTO, user);
    }
	
	public int deletePtWh(String[] deleteRows, String[] deleteRowsExt, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null)){
        	for(int i = 0; i< deleteRows.length; i++){
        		
        		result = result + maPtWhListDAO.deletePtWh(deleteRows[i], deleteRowsExt[i], user);
        	}
        }
        
        
        return result;
    }
	
	public String findTotalCount(MaPtWhCommonDTO maPtWhCommonDTO, User user)
	{
		return maPtWhListDAO.findTotalCount(maPtWhCommonDTO, user);
	}
}

