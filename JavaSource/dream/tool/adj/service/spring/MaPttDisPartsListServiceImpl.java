package dream.tool.adj.service.spring;

import java.util.List;

import common.bean.User;
import dream.tool.adj.dao.MaPttDisPartsListDAO;
import dream.tool.adj.dto.MaPttDisCommonDTO;
import dream.tool.adj.dto.MaPttDisPartsListDTO;
import dream.tool.adj.service.MaPttDisPartsListService;


/**
 * ¸ñ·Ï serviceimpl
 * @author kim21017
 * @version $Id: MaPttDisPartsListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPttDisPartsListServiceTarget"
 * @spring.txbn id="maPttDisPartsListService"
 * @spring.property name="maPttDisPartsListDAO" ref="maPttDisPartsListDAO"
 */
public class MaPttDisPartsListServiceImpl implements MaPttDisPartsListService
{
    private MaPttDisPartsListDAO maPttDisPartsListDAO = null;

    public MaPttDisPartsListDAO getMaPttDisPartsListDAO() {
		return maPttDisPartsListDAO;
	}
	public void setMaPttDisPartsListDAO(MaPttDisPartsListDAO maPttDisPartsListDAO) {
		this.maPttDisPartsListDAO = maPttDisPartsListDAO;
	}
	
	public List findAnsList(MaPttDisCommonDTO maPttDisCommonDTO, MaPttDisPartsListDTO maPttDisPartsListDTO, User user)
    {      
        return maPttDisPartsListDAO.findAnsList(maPttDisCommonDTO, maPttDisPartsListDTO, user);
    }
	
	public int deleteItemList(String[] deleteRows , String[] deleteRowsExt) throws Exception{
        int result = 0;
        for(int i = 0; deleteRows.length > i ; i++)
        {
            result = result + maPttDisPartsListDAO.deleteItemList(deleteRows[i]);
        }
        
        return result;
    }
	@Override
	public String findTotalCount(MaPttDisCommonDTO maPttDisCommonDTO, MaPttDisPartsListDTO maPttDisPartsListDTO, User user) {
		return maPttDisPartsListDAO.findTotalCount(maPttDisCommonDTO, maPttDisPartsListDTO, user);
	}
}

